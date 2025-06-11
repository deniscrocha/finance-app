package com.denis.api.modules.category.controller.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GetAllCategoriesResponse {
    private Long id;
    private String name;
}
