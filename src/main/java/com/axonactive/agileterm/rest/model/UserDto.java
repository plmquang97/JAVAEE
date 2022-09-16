package com.axonactive.agileterm.rest.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;

    private String username;

    private String email;

    private Boolean activated;
}
