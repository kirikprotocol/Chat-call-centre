
package com.eyelinecom.whoisd.sads2.esdpapi.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="service" type="{http://api.esdpapi.sads2.whoisd.eyelinecom.com/}xmlService" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createService", propOrder = {
    "service"
})
public class CreateService {

    protected XmlService service;

    /**
     * Gets the value of the service property.
     * 
     * @return
     *     possible object is
     *     {@link XmlService }
     *     
     */
    public XmlService getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlService }
     *     
     */
    public void setService(XmlService value) {
        this.service = value;
    }

}
