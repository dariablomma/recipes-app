import {
    useForm, type FieldValues, type Path, type RegisterOptions,
    type DefaultValues
} from 'react-hook-form';
import type { InputHTMLAttributes } from 'react';

interface ValidationProps {
    error?: string;
    'aria-invalid'?: boolean;
    'aria-describedby'?: string;
}

type ValidationSchema<T extends FieldValues> = {
    [K in keyof T]?: RegisterOptions<T, Path<T>>;
};

interface UseFormWithValidationOptions<T extends FieldValues> {
    initialValues: T;
    validationSchema?: ValidationSchema<T>;
}

export function useFormWithValidation<T extends FieldValues>(
    options: UseFormWithValidationOptions<T>
) {
    const { initialValues, validationSchema } = options;

    const form = useForm<T>({
        defaultValues: initialValues as DefaultValues<T>,
        mode: 'onTouched',
    });

    const { errors, touchedFields, isValid } = form.formState;

    const isTouched = Object.keys(touchedFields).length > 0;

    const getField = <K extends Path<T>>(name: K) => {
        const rules = validationSchema?.[name] as RegisterOptions<T, K> | undefined;
        const { onChange, onBlur, ref, ...rest } = form.register(name, rules);
        const error = errors[name]?.message as string | undefined;

        const props: InputHTMLAttributes<HTMLInputElement> & ValidationProps = {
            ...rest,
            'aria-invalid': !!error,
            'aria-describedby': error ? `${name}-error` : undefined,
            error,
        };

        return {
            name,
            props,
            value: form.watch(name),
        };
    };

    const getFields = () => {
        const keys = Object.keys(initialValues) as Path<T>[];
        return Object.fromEntries(
            keys.map((key) => [key, getField(key)])
        ) as Record<Path<T>, ReturnType<typeof getField<Path<T>>>>;
    };

    const validateForm = async () => {
        return await form.trigger();
    };

    const resetForm = () => {
        form.reset(initialValues);
    };

    const isFormValidAndTouched = isTouched && isValid && Object.keys(errors).length === 0;
    const isFormInvalid = !isValid && Object.keys(errors).length > 0;
    const isFormValid = isValid && !Object.keys(errors).length;

    return {
        form,
        getField,
        getFields,
        validateForm,
        resetForm,
        isFormValidAndTouched,
        isFormInvalid,
        isFormValid,
        isTouched,
        errors,
    };
}