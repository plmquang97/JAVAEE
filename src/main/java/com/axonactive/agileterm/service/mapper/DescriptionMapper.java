package com.axonactive.agileterm.service.mapper;

import com.axonactive.agileterm.entity.DescriptionEntity;
import com.axonactive.agileterm.rest.model.DescriptionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface DescriptionMapper {

    @Mapping(target = "authorName", source = "userEntity.username")
//    @Mapping(target = "votePoint", source = "vote.point")

    DescriptionDto toDto(DescriptionEntity description);

    List<DescriptionDto> toDtos(List<DescriptionEntity> descriptionList);

}
