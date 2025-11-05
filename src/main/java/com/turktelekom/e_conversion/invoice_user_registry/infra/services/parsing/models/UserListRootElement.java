package com.turktelekom.e_conversion.invoice_user_registry.infra.services.parsing.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@XmlRootElement(name = "UserList")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@Getter
public class UserListRootElement {
    @XmlElement(name = "User")
    List<UserElement> users;
}
