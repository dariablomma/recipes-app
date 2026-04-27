import { useForm, type FieldValues, type Path, type UseFormProps } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';
import type { InputHTMLAttributes } from 'react';

interface ValidationProps {
    error?: string;
    'aria-invalid'?: boolean;
    'aria-describedby'?: string;
}

interface FieldData {
    props: ValidationProps;
}

export function useFormWithValidation<T extends FieldValues>(
    schema: z.ZodType<T>,
    options?: UseFormProps<T>
) {
    const form = useForm<T>({
        resolver: zodResolver(schema),
        ...options,
    });

    const { errors, isValid } = form.formState;

    const getField = <K extends Path<T>>(name: K) => {
        const { onChange, onBlur, ref, ...rest } = form.register(name);
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
            model: form.watch(name),
        };
    };

    const getFields = () => {
        const keys = Object.keys(form.getValues()) as Path<T>[];
        return Object.fromEntries(
            keys.map((key) => [key, getField(key)])
        ) as Record<Path<T>, ReturnType<typeof getField<Path<T>>>>;
    };

    const validateForm = async () => {
        return await form.trigger();
    };

    const isFormValidAndTouched = isTouched && isValid && Object.keys(errors).length === 0;
    const isFormInvalid = !isValid && Object.keys(errors).length > 0;

    return {
        form,
        getField,
        getFields,
        validateForm,
        isFormValidAndTouched,
        isFormInvalid,
        isTouched,
        handleSubmit: form.handleSubmit,
        errors,
    };
}