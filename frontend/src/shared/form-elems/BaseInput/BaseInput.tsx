import type {JSX} from "react";
import styles from "./BaseInput.module.scss";
import type {BaseInputProps} from "@/shared/form-elems/types";

export function BaseInput({ label, id, className, ...inputProps }: BaseInputProps): JSX.Element {
    const mergedClassName = className
        ? `${styles.input} ${className}`
        : styles.input;
    return (
        <div className={styles.field}>
            {label && <label htmlFor={id}  className={styles.label}>{label}</label>}
            <input
                id={id}
                className={mergedClassName}
                {...inputProps}
            />
        </div>
    )
}