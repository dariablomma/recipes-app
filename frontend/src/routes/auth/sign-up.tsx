import { createFileRoute } from '@tanstack/react-router';
import { AuthSignUpView } from "../../auth/views/AuthSignUpView.tsx";

export const Route = createFileRoute('/auth/sign-up')({
    component: () => (
        <div>
            <AuthSignUpView />
        </div>
    ),
});