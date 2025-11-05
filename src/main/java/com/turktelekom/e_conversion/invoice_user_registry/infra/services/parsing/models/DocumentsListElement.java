package com.turktelekom.e_conversion.invoice_user_registry.infra.services.parsing.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@Getter
public class DocumentsListElement {
    @XmlElement(name = "Document")
    private List<DocumentElement> documents;
}
