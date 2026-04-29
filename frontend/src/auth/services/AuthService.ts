import { api } from '@/core/api/AxiosInstance';
import type {SignUpFormData} from "@/auth/types";

export interface JwtToken {
    access: string;
    refresh: string;
    expires: string;
}

interface AuthError {
    reason?: string;
    message?: string;
    force?: boolean;
}

const ACCESS_KEY = 'token:access';
const REFRESH_KEY = 'token:refresh';
const EXPIRES_KEY = 'token:expires';
const ERROR_KEY = 'auth:error';

export class AuthService {
    public static token: JwtToken | undefined;

    static init() {
        const access = localStorage.getItem(ACCESS_KEY);
        const refresh = localStorage.getItem(REFRESH_KEY);
        const expires = localStorage.getItem(EXPIRES_KEY);

        if (access && refresh && expires) {
            this.token = { access, refresh, expires };
        }
    }

    static setToken(token: JwtToken) {
        this.token = token;

        localStorage.setItem(ACCESS_KEY, token.access);
        localStorage.setItem(REFRESH_KEY, token.refresh);
        localStorage.setItem(EXPIRES_KEY, token.expires);

        return token;
    }

    static removeToken() {
        this.token = undefined;
        localStorage.removeItem(ACCESS_KEY);
        localStorage.removeItem(REFRESH_KEY);
        localStorage.removeItem(EXPIRES_KEY);
    }

    static setError(error: AuthError) {
        localStorage.setItem(ERROR_KEY, JSON.stringify(error));
    }

    static getError(): AuthError | undefined {
        const error = localStorage.getItem(ERROR_KEY);
        if (error) {
            return JSON.parse(error);
        }
    }

    static removeError() {
        localStorage.removeItem(ERROR_KEY);
    }

    static get isAuthenticated(): boolean {
        if (!this.token?.access || !this.token.expires) return false;
        return Date.parse(this.token.expires) > Date.now();
    }

    static async signUp(data: SignUpFormData) {
        const response = await api.post<JwtToken>('/auth/signup', data);

        this.setToken(response.data);
        return response.data;
    }

    static async login(login: string, password: string) {
        const response = await api.post<JwtToken>('/auth/sign-in', {
            login,
            password,
        });

        if (response.data) {
            this.setToken(response.data);
            return true;
        }

        return false;
    }

    static async signOut() {
        try {
            await api.delete('/auth/token');
        } catch {
            // ignore
        }
        this.removeToken();
    }

    static async refreshToken(): Promise<boolean> {
        if (!this.token?.refresh) return false;

        try {
            const response = await api.post<JwtToken>('/auth/refresh', {
                refresh: this.token.refresh,
            });

            if (response.data) {
                this.setToken(response.data);
                return true;
            }
        } catch {
            // ignore
        }

        return false;
    }

    static async tryRefreshToken(): Promise<JwtToken> {
        if (this.token && this.isAuthenticated) {
            return this.token;
        }

        if (this.token?.refresh) {
            const success = await this.refreshToken();
            if (success && this.token) {
                return this.token;
            }
        }

        throw new Error('Fail to refresh token');
    }
}