package com.bookit.bookit.business.dto;

import com.bookit.bookit.category.Category;
import com.bookit.bookit.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class BusinessDTO {
    private Integer id;
    private User owner;
    private Category category;
    private String name;
    private String urlName;
    private String description;
    private String address;
    private String city;
}
