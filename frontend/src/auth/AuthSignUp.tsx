import { useState, type FormEvent } from 'react';
import styles from './AuthSignUp.module.scss';

export function AuthSignUp() {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e: FormEvent) => {
        e.preventDefault();
        // логика отправки будет позже
    };

    return (
        <form className={styles.form} onSubmit={handleSubmit}>
            <h2 className={styles.title}>Регистрация</h2>

            <div className={styles.field}>
                <label htmlFor="username" className={styles.label}>
                    Имя пользователя
                </label>
                <input
                    id="username"
                    type="text"
                    className={styles.input}
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    placeholder="Введите имя пользователя"
                    autoComplete="username"
                />
            </div>

            <div className={styles.field}>
                <label htmlFor="email" className={styles.label}>
                    Email
                </label>
                <input
                    id="email"
                    type="email"
                    className={styles.input}
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder="Введите email"
                    autoComplete="email"
                />
            </div>

            <div className={styles.field}>
                <label htmlFor="password" className={styles.label}>
                    Пароль
                </label>
                <input
                    id="password"
                    type="password"
                    className={styles.input}
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    placeholder="Введите пароль"
                    autoComplete="new-password"
                />
            </div>

            <button type="submit" className={styles.button}>
                Зарегистрироваться
            </button>
        </form>
    );
}