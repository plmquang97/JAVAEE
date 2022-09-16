package com.axonactive.agileterm.dao;


import com.axonactive.agileterm.entity.VerificationTokenEntity;

import javax.ejb.Stateless;
@Stateless
public interface VerificationTokenDAO {

    VerificationTokenEntity save(VerificationTokenEntity verificationTokenEntity);
}
