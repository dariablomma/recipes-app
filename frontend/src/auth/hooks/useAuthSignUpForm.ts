import { useFormWithValidation } from "@/shared/hooks/useFormWithValidation.ts";

interface SignUpFormData {
    username: string;
    email: string;
    password: string;
}

export function useSignUpForm() {
    const { getFields, isFormInvalid, isFormValidAndTouched, validateForm } =
        useFormWithValidation<SignUpFormData>({
            initialValues: {
                username: '',
                email: '',
                password: '',
            },
            validationSchema: {
                username: {
                    required: 'Обязательное поле',
                    minLength: { value: 3, message: 'Минимум 3 символа' },
                    maxLength: { value: 20, message: 'Максимум 20 символов' },
                },
                email: {
                    required: 'Обязательное поле',
                    pattern: {
                        value: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
                        message: 'Некорректный email',
                    },
                },
                password: {
                    required: 'Обязательное поле',
                    minLength: { value: 6, message: 'Минимум 6 символов' },
                },
            },
        });

    const fields = getFields();

    return {
        fields,
        isFormInvalid,
        isFormValidAndTouched,
        validateForm,
    };
}