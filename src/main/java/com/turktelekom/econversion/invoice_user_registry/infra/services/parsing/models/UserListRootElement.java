package com.turktelekom.econversion.invoice_user_registry.infra.services.parsing.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@XmlRootElement(name = "UserList")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class UserListRootElement {
    @XmlElement(name = "User")
    List<UserElement> users;
}
