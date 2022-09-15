package com.axonactive.agileterm.rest.model;

import com.axonactive.agileterm.rest.client.model.Description;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TermDto {
    private String encodedId;
    private String name;
    private List<DescriptionDto> descriptionList;
}
