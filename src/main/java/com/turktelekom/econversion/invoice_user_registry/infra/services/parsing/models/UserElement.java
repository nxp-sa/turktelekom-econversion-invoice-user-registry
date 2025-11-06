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
