import type {JSX} from "react";
import styles from "./AuthSignUpForm.module.scss";
import {Link} from "@tanstack/react-router";
import {useInput} from "@/shared/hooks/useInput.ts";
import { BaseInput } from "@/shared/form-elems/BaseInput/BaseInput.tsx";
import {BaseInputPassword} from "@/shared/form-elems/BaseInputPassword/BaseInputPassword.tsx";
import {BaseButton} from "@/shared/form-elems/BaseButton/BaseButton.tsx";

export function AuthSignUpForm(): JSX.Element {
    const username = useInput('');
    const email = useInput('');
    const password = useInput('');

    return (
        <div className={styles.form}>
            <h2 className={styles.title}>Зарегистрироваться</h2>

            <BaseInput
                label={"Имя пользователя"}
                placeholder={"Имя пользователя"}
                type={"text"}
                {...username}
            />
            <BaseInput
                label={"Email"}
                placeholder="Введите email"
                type={"email"}
                {...email}
            />

            <BaseInputPassword
                label={"Пароль"}
                placeholder="Введите пароль"
                {...password}
            />

            <BaseButton label={"Зарегистрироваться"} />
            <div className={styles.subtext}>Уже есть аккаунт?  <Link to="/auth/login">Войти</Link></div>
        </div>
    )
}