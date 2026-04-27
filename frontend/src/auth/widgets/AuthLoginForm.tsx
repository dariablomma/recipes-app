import type {JSX} from "react";
import styles from "./AuthForm.module.scss";
import {Link} from "@tanstack/react-router";
import {useInput} from "@/shared/hooks/useInput.ts";
import { BaseInput } from "@/shared/form-elems/BaseInput/BaseInput.tsx";
import {BaseInputPassword} from "@/shared/form-elems/BaseInputPassword/BaseInputPassword.tsx";
import {BaseButton} from "@/shared/form-elems/BaseButton/BaseButton.tsx";

export function AuthLoginForm(): JSX.Element {
    const username = useInput('');
    const password = useInput('');

    return (
        <div className={styles.form}>
            <h2 className={styles.title}>Войти</h2>

            <BaseInput
                label={"Имя пользователя"}
                placeholder={"Имя пользователя"}
                type={"text"}
                {...username}
            />

            <BaseInputPassword
                label={"Пароль"}
                placeholder="Введите пароль"
                {...password}
            />

            <BaseButton label={"Войти"} />
            <div className={styles.subtext}>Нет аккаунта?  <Link to="/auth/sign-up">Зарегистрироваться</Link></div>
        </div>
    )
}