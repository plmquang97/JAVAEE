package com.axonactive.agileterm.rest.model;


import com.axonactive.agileterm.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@AllArgsConstructor
@ToString
public class JwtResponse {
    private String tokenValue;
    //    private Integer timeToLive;
    private String type = "Bearer";
    private String username;
    private List<Role> roles;
    private Boolean isActive;

    public JwtResponse(String tokenValue, String username, List<Role> roles,
                       Boolean isActive) {
        this.roles = roles;
        this.tokenValue = tokenValue;
        this.username = username;
        this.isActive = isActive;
    }
}
