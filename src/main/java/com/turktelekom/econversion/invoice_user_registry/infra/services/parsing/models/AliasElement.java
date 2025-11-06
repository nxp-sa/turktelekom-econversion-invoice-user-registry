package com.turktelekom.econversion.invoice_user_registry.infra.services.parsing.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class AliasElement {
    @XmlElement(name = "Name")
    String name;

    @XmlElement(name = "CreationTime")
    Date creationTime;

    @XmlElement(name = "DeletionTime")
    Date deletionTime;
}
