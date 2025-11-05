package com.turktelekom.e_conversion.invoice_user_registry.infra.services.parsing.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@Getter
public class DocumentElement {
    @XmlAttribute(name = "type")
    String type;

    @XmlElement(name = "Alias")
    List<AliasElement> aliases;

    static final String INVOICE = "Invoice";

    public boolean isInvoiceAndValid() {
        boolean flag = true;
        flag &= INVOICE.equals(type);
        flag &= aliases != null && !aliases.isEmpty();
        return flag;
    }
}
