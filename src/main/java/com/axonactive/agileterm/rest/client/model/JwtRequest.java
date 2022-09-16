package com.axonactive.agileterm.rest.client.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtRequest {

    private String username;

    private String password;
}
