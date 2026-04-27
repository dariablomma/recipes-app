import { createFileRoute } from '@tanstack/react-router';
import { AuthLogin } from "../../auth/AuthLogin.tsx";

export const Route = createFileRoute('/auth/login')({
    component: () => (
        <div>
            <AuthLogin />
        </div>
    ),
});