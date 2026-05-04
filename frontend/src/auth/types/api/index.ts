import type {ISODateTimeString} from "@/shared/types";

export interface AuthResponse {
    accessToken: string;
    refreshToken: string;
    expiresAt: ISODateTimeString;
    userName: string;
    userId: number;
}

export interface JwtTokenMin {
    access: string,
    refresh: string,
    expires: ISODateTimeString,
}

export interface AuthError {
    reason?: string;
    message?: string;
    force?: boolean;
}