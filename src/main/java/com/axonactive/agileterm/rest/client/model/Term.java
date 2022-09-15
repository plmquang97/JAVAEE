package com.axonactive.agileterm.rest.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Term {
    private String name;
    private List<Description> descriptionList;
    private List<Integer> topicIdList;
    private List<Integer> relatedTermIdList;
}
