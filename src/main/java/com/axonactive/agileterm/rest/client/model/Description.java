package com.axonactive.agileterm.rest.client.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Description {
    @NotBlank
    private String content;

    @NotBlank
    private String userName;
}
