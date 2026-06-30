import { createFileRoute } from '@tanstack/react-router';
import { AuthLoginView } from "@/auth/views/AuthLoginView.tsx";

export const Route = createFileRoute('/auth/login')({
    component: () => (
            <AuthLoginView />
    ),
});