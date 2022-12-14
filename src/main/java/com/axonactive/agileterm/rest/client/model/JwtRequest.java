package com.axonactive.agileterm.rest.client.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
