import { useEffect } from 'react';
import { useNavigate, useRouterState } from '@tanstack/react-router';
import { AuthService } from '@/auth/services/AuthService';

export function useAuthCheck() {
    const navigate = useNavigate();
    const routerState = useRouterState();
    const currentPath = routerState.location.pathname;

    useEffect(() => {
        const isAuthPage = currentPath === '/auth/login' || currentPath === '/auth/sign-up';
        if (AuthService.isAuthenticated && isAuthPage) {
            navigate({ to: '/' });
        } else if (!AuthService.isAuthenticated && !isAuthPage) {
            navigate({ to: '/auth/login' });
        }
    }, [currentPath]);
}