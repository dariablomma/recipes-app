import { createFileRoute } from '@tanstack/react-router';
import { Link } from '@tanstack/react-router';

export const Route = createFileRoute('/')({
    component: () => (
        <div>
            <h1>Доброе пожаловать в твою записную книжку рецептов!</h1>
            <p></p>
            <Link to="/recipes">К списку рецептов</Link>
        </div>
    ),
});