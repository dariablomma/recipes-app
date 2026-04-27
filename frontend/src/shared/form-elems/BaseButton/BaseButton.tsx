import type {JSX} from "react";
import styles from "./BaseButton.module.scss";

interface BaseButtonProps {
    label: string;
}
export function BaseButton({ label }: BaseButtonProps): JSX.Element {
    return (
        <button className={styles.button}>
            { label }
        </button>
    )
}