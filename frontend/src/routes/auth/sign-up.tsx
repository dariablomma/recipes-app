import { createFileRoute } from '@tanstack/react-router';
import { AuthSignUp } from "../../auth/AuthSignUp.tsx";

export const Route = createFileRoute('/auth/sign-up')({
    component: () => (
        <div>
            <AuthSignUp />
        </div>
    ),
});