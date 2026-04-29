import type {ButtonHTMLAttributes, JSX} from "react";
import styles from "./BaseButton.module.scss";

interface BaseButtonProps extends ButtonHTMLAttributes<HTMLButtonElement>{
    label?: string;
}
export function BaseButton({ label, disabled, className, children, ...props }: BaseButtonProps): JSX.Element {
    const mergedClassName = [
        styles.button,
        disabled ? styles.disabled : '',
        className || '',
    ].filter(Boolean).join(' ');

    return (
        <button className={mergedClassName} disabled={disabled} {...props}>
            { children }
        </button>
    )
}