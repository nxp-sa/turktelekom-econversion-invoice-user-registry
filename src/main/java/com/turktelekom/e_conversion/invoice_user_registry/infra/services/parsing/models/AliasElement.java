package com.turktelekom.e_conversion.invoice_user_registry.infra.services.parsing.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@Getter
public class AliasElement {
    @XmlElement(name = "Name")
    String name;

    @XmlElement(name = "CreationTime")
    Date creationTime;

    @XmlElement(name = "DeletionTime")
    Date deletionTime;
}
