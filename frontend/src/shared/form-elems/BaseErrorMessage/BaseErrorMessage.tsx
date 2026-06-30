import styles from "./BaseErrorMessage.module.scss";

interface Props {
    error: string;
}
export const BaseErrorMessage: React.FC<Props> = ({ error }) => {
    return (
        <div className={styles.error} role="alert">
            {error}
        </div>
    )
}