package com.axonactive.agileterm.dao;

import com.axonactive.agileterm.entity.DescriptionEntity;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public interface DescriptionDAO {
    DescriptionEntity save(DescriptionEntity descriptionEntity);

    List<DescriptionEntity> findDescriptionByTermIdAndDescriptionString(String termName, String descriptionString);
}
