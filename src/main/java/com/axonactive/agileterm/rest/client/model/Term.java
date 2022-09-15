package com.axonactive.agileterm.rest.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Term {
    @NotBlank
    @NotNull
    private String name;

    private List<Description> descriptionList;

    private List<Integer> topicIdList;

    private List<Integer> relatedTermIdList;
}
