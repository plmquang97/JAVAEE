package com.axonactive.agileterm.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TermTopicDto {
    private Integer termId;
    private String termName;
    private Integer topicId;
    private String topicName;
    private String topicColor;
}
