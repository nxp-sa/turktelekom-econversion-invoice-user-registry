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
public class UserElement {
    @XmlElement(name = "Identifier")
    String identifier;

    @XmlElement(name = "Title")
    String title;

    @XmlElement(name = "Type")
    String type;

    @XmlElement(name = "FirstCreationTime")
    Date firstCreationTime;

    @XmlElement(name = "AccountType")
    String accountType;

    @XmlElement(name = "Documents")
    DocumentsListElement documents;
}
