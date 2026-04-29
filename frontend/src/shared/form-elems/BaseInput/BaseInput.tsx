import type {JSX} from "react";
import styles from "./BaseInput.module.scss";
import type {BaseInputProps} from "@/shared/form-elems/types";

export function BaseInput({ label, id, className, error, required, ...inputProps }: BaseInputProps): JSX.Element {
    const mergedClassName = [
        styles.input,
        error ? styles.inputError : '',
        className || '',
    ].filter(Boolean).join(' ');

    return (
        <div className={styles.field}>
            {label && (
                <label htmlFor={id}  className={styles.label}>
                    {label}
                    {required && <span className={styles.requiredStar}> *</span>}
                </label>
            )}
            <input
                id={id}
                className={mergedClassName}
                aria-invalid={!!error}
                aria-describedby={error ? `${id}-error` : undefined}
                {...inputProps}
            />
            {error && (
                <span className={styles.errorText} id={`${id}-error`} role="alert">
                    {error}
                </span>
            )}
        </div>
    )
}