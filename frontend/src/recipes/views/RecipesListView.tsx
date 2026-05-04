import { RecipesList } from "../widgets/list/RecipesList.tsx";
import {RecipesFilters} from "@/recipes/widgets/RecipesFilters.tsx";
import {BaseButton} from "@/shared/form-elems/BaseButton/BaseButton.tsx";
import styles from "./RecipesListView.module.scss"

export function RecipesListView() {
    return (
        <div className={styles.view}>
            <BaseButton>Создать рецепт</BaseButton>
            <RecipesFilters/>
            <RecipesList/>
        </div>
    );
}