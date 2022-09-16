package com.axonactive.agileterm.service.mapper;

import com.axonactive.agileterm.entity.TermTopicEntity;
import com.axonactive.agileterm.rest.model.TermTopicDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface TermTopicMapper {
    @Mapping(target ="termId", source = "term.id")
    @Mapping(source = "term.name", target = "termName")
    @Mapping(source = "topic.id", target = "topicId")
    @Mapping(source = "topic.name", target = "topicName")
    @Mapping(source = "topic.color", target = "topicColor")
    TermTopicDto toDto(TermTopicEntity termTopicEntity);

    List<TermTopicDto> toDtos(List<TermTopicEntity> termTopicEntityList);
}
