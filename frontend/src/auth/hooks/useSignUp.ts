import { useMutation } from '@tanstack/react-query';
import { AuthService } from '@/auth/services/AuthService';
import type { SignUpFormData } from '@/auth/types';
import {extractErrorMessage} from "@/core/api/utils/errors.ts";
import type {AxiosError} from "axios";
import {useNavigate} from "@tanstack/react-router";

export function useSignUp() {
    const navigate = useNavigate();
    const mutation = useMutation({
        mutationFn: (data: SignUpFormData) => {
            return  AuthService.signUp(data);
        },
        onSuccess: () => {
            navigate({ to: '/' });
        },
    });

    const serverError = mutation.error ? extractErrorMessage(mutation.error as AxiosError) : null;

    return {
        signUp: mutation.mutate,
        isPending: mutation.isPending,
        serverError,
        isSuccess: mutation.isSuccess,
    };
}