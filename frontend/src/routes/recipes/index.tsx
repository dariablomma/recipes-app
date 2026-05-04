import {createFileRoute} from '@tanstack/react-router'
import { RecipesListView } from "@/recipes/views/RecipesListView.tsx";

export const Route = createFileRoute('/recipes/')({
    component: RouteComponent,
})

function RouteComponent() {
    return (
        <div>
            <RecipesListView />
        </div>
    )
}
