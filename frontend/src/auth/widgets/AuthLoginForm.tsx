import type {JSX} from "react";
import styles from "./AuthForm.module.scss";
import {Link} from "@tanstack/react-router";
import { BaseInput } from "@/shared/form-elems/BaseInput/BaseInput.tsx";
import {BaseInputPassword} from "@/shared/form-elems/BaseInputPassword/BaseInputPassword.tsx";
import {BaseButton} from "@/shared/form-elems/BaseButton/BaseButton.tsx";
import { useLoginForm } from "@/auth/hooks/useAuthLoginForm.ts";
import type {LoginFormData} from "@/auth/types";

export function AuthLoginForm(): JSX.Element {
    const {  isFormInvalid, fields, handleSubmit} = useLoginForm();

    const onSubmit = (data: LoginFormData) => {
        console.log('Валидные данные:', data);
    };

    return (
        <form className={styles.form} noValidate={true} onSubmit={handleSubmit(onSubmit)}>
            <h2 className={styles.title}>Войти</h2>

            <BaseInput
                label={"Имя пользователя"}
                placeholder={"Имя пользователя"}
                type={"text"}
                required={true}
                {...fields.userName.props}
            />

            <BaseInputPassword
                label={"Пароль"}
                placeholder="Введите пароль"
                required={true}
                {...fields.password.props}
            />

            <BaseButton type={"submit"} disabled={isFormInvalid}>
                Зарегистрироваться
            </BaseButton>
            <div className={styles.subtext}>Нет аккаунта?  <Link to="/auth/sign-up">Зарегистрироваться</Link></div>
        </form>
    )
}