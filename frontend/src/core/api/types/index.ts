import type { AxiosError } from 'axios';

export interface ApiRequestConfig {
    silent?: boolean;
    onError?: (error: AxiosError) => void;
}

export interface ErrorResponse {
    status: number;
    message: string;
    timestamp: string;
    path: string;
    errors?: Record<string, string>;
}