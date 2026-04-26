import { createFileRoute } from '@tanstack/react-router';

export const Route = createFileRoute('/')({
    component: () => (
        <div>
            <h1>Recipes list</h1>
            <p>Coming soon</p>
        </div>
    ),
});