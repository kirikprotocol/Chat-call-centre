
package com.eyelinecom.whoisd.sads2.esdpapi.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EsdpServiceException complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EsdpServiceException">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="errorType" type="{http://api.esdpapi.sads2.whoisd.eyelinecom.com/}esdpErrorType" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EsdpServiceException", propOrder = {
    "errorType",
    "message"
})
public class EsdpServiceException {

    protected EsdpErrorType errorType;
    protected String message;

    /**
     * Gets the value of the errorType property.
     * 
     * @return
     *     possible object is
     *     {@link EsdpErrorType }
     *     
     */
    public EsdpErrorType getErrorType() {
        return errorType;
    }

    /**
     * Sets the value of the errorType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EsdpErrorType }
     *     
     */
    public void setErrorType(EsdpErrorType value) {
        this.errorType = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

}
