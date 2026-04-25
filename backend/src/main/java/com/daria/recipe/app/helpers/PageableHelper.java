package com.daria.recipe.app.helpers;

import com.daria.recipe.app.exception.InvalidRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class PageableHelper {
    public void validateSortFields(Sort sort, Set<String> ALLOWED_SORT_FIELDS) {
        for (Sort.Order order : sort) {
            String prop = order.getProperty();
            if (!ALLOWED_SORT_FIELDS.contains(prop)) {
                throw new InvalidRequestException("Such property is not supported for sorting " + prop);
            }
        }
    }

    public Pageable addFallbackSort(Pageable pageable) {
        Sort sort = pageable.getSort();
        if (sort.getOrderFor("id") == null) {
            sort = sort.and(Sort.by("id").ascending());
        }
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    }
}
