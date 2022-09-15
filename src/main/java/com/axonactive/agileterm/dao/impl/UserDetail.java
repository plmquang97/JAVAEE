package com.axonactive.agileterm.dao.impl;

import javax.ejb.Stateless;
import java.io.Serializable;


@Stateless
public interface UserDetail extends Serializable {


    String getPassword();

    String getUserName();

    boolean isAccountNonExpired();

    boolean isAccountNonLocked();

    boolean isCredentialsNonExpired();

    boolean isEnable();

}
