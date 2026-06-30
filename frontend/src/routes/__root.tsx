import { Outlet, createRootRoute } from '@tanstack/react-router';
import { useAuthCheck } from '@/auth/hooks/useAuthCheck';

export const Route = createRootRoute({
    component: RootLayout,
});

function RootLayout() {
    useAuthCheck();

    return (
        <main>
            <Outlet />
        </main>
    );
}