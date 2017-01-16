
package com.eyelinecom.whoisd.sads2.esdpapi.api;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for permission.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="permission">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ewallet"/>
 *     &lt;enumeration value="personalization"/>
 *     &lt;enumeration value="push-service"/>
 *     &lt;enumeration value="push-sms"/>
 *     &lt;enumeration value="configuration"/>
 *     &lt;enumeration value="statistics"/>
 *     &lt;enumeration value="push-ussd"/>
 *     &lt;enumeration value="als"/>
 *     &lt;enumeration value="bill"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "permission")
@XmlEnum
public enum Permission {

    @XmlEnumValue("ewallet")
    EWALLET("ewallet"),
    @XmlEnumValue("personalization")
    PERSONALIZATION("personalization"),
    @XmlEnumValue("push-service")
    PUSH_SERVICE("push-service"),
    @XmlEnumValue("push-sms")
    PUSH_SMS("push-sms"),
    @XmlEnumValue("configuration")
    CONFIGURATION("configuration"),
    @XmlEnumValue("statistics")
    STATISTICS("statistics"),
    @XmlEnumValue("push-ussd")
    PUSH_USSD("push-ussd"),
    @XmlEnumValue("als")
    ALS("als"),
    @XmlEnumValue("bill")
    BILL("bill");
    private final String value;

    Permission(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Permission fromValue(String v) {
        for (Permission c: Permission.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
