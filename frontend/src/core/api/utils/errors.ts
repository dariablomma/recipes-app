import type {ErrorResponse} from "@/core/api/types";
import type {AxiosError} from "axios";

export const extractErrorMessage = (error: AxiosError): string => {
    const data = error.response?.data;

    if (!data) {
        return error.message || 'Ошибка сети. Проверьте подключение.';
    }

    if (typeof data === 'string') {
        return data;
    }

    const errorResponse = data as ErrorResponse;

    if (errorResponse.message) {
        return errorResponse.message;
    }

    return 'Произошла ошибка при выполнении запроса';
};