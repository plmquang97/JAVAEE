package com.axonactive.agileterm.rest.model;
import lombok.*;

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
