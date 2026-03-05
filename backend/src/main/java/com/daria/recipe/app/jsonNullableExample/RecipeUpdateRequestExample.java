package com.daria.recipe.app.jsonNullableExample;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.openapitools.jackson.nullable.JsonNullable;

@Data
@NoArgsConstructor
public class RecipeUpdateRequestExample {
    @Size(max = 200, message = "Recipe name must be between 2 and 200 characters")
    private JsonNullable<String> name = JsonNullable.undefined();

    private JsonNullable<Long> categoryId;

    @Valid
    @URL(message = "Invalid URL format")
    @Size(max = 500)
    private JsonNullable<String> externalLink = JsonNullable.undefined();

    @Valid
    @Size(max = 2000, message = "Recipe description name must be between 2 and 2000 characters")
    private JsonNullable<String> description = JsonNullable.undefined();
}
