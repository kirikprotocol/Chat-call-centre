
package com.eyelinecom.whoisd.sads2.esdpapi.api;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for esdpErrorType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="esdpErrorType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="UNKNOWN"/>
 *     &lt;enumeration value="OBJECT_NOT_FOUND_EXCEPTION"/>
 *     &lt;enumeration value="PROVIDER_NOT_FOUND"/>
 *     &lt;enumeration value="PARENT_PROVIDER_NOT_FOUND"/>
 *     &lt;enumeration value="AUTHENTICATION_FAILED"/>
 *     &lt;enumeration value="ACCESS_ERROR"/>
 *     &lt;enumeration value="ACCESS_ERROR_SELF_UPDATE"/>
 *     &lt;enumeration value="ACCESS_ERROR_SELF_DELETE"/>
 *     &lt;enumeration value="SERVICE_NOT_FOUND"/>
 *     &lt;enumeration value="SERVICE_NOT_INITIALIZED"/>
 *     &lt;enumeration value="UNKNOWN_PERMISSION"/>
 *     &lt;enumeration value="SIP_NUMBER_NOT_AVALIABLE"/>
 *     &lt;enumeration value="SIP_NUMBER_IN_USE"/>
 *     &lt;enumeration value="API_NOT_PERMITTED"/>
 *     &lt;enumeration value="PROVIDER_EXISTS"/>
 *     &lt;enumeration value="NO_API_PARAMETER"/>
 *     &lt;enumeration value="API_NOT_FOUND"/>
 *     &lt;enumeration value="NO_METHOD_PARAMETER"/>
 *     &lt;enumeration value="METHOD_NOT_FOUND"/>
 *     &lt;enumeration value="AUTHENTICATION_FAILED_LOGIN"/>
 *     &lt;enumeration value="AUTHENTICATION_FAILED_PASSWORD"/>
 *     &lt;enumeration value="NOT_IMPLEMENTED"/>
 *     &lt;enumeration value="TAG_DOES_NOT_MATCH_PROVIDER_ID"/>
 *     &lt;enumeration value="PARENT_ID_DOES_NOT_MATCH_PROVIDER_ID"/>
 *     &lt;enumeration value="PROVIDER_DISABLED"/>
 *     &lt;enumeration value="PROVIDER_HAS_CHILDREN"/>
 *     &lt;enumeration value="PROVIDER_HAS_SERVICES"/>
 *     &lt;enumeration value="PERMISSIONS_NOT_ALLOWED"/>
 *     &lt;enumeration value="PVSS_ILLEGAL_PROFILE_ID"/>
 *     &lt;enumeration value="WRONG_PARAMETER_TYPE"/>
 *     &lt;enumeration value="WRONG_DATA_TYPE"/>
 *     &lt;enumeration value="PVSS_SCOPE_NOT_SET"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "esdpErrorType")
@XmlEnum
public enum EsdpErrorType {

    UNKNOWN,
    OBJECT_NOT_FOUND_EXCEPTION,
    PROVIDER_NOT_FOUND,
    PARENT_PROVIDER_NOT_FOUND,
    AUTHENTICATION_FAILED,
    ACCESS_ERROR,
    ACCESS_ERROR_SELF_UPDATE,
    ACCESS_ERROR_SELF_DELETE,
    SERVICE_NOT_FOUND,
    SERVICE_NOT_INITIALIZED,
    UNKNOWN_PERMISSION,
    SIP_NUMBER_NOT_AVALIABLE,
    SIP_NUMBER_IN_USE,
    API_NOT_PERMITTED,
    PROVIDER_EXISTS,
    NO_API_PARAMETER,
    API_NOT_FOUND,
    NO_METHOD_PARAMETER,
    METHOD_NOT_FOUND,
    AUTHENTICATION_FAILED_LOGIN,
    AUTHENTICATION_FAILED_PASSWORD,
    NOT_IMPLEMENTED,
    TAG_DOES_NOT_MATCH_PROVIDER_ID,
    PARENT_ID_DOES_NOT_MATCH_PROVIDER_ID,
    PROVIDER_DISABLED,
    PROVIDER_HAS_CHILDREN,
    PROVIDER_HAS_SERVICES,
    PERMISSIONS_NOT_ALLOWED,
    PVSS_ILLEGAL_PROFILE_ID,
    WRONG_PARAMETER_TYPE,
    WRONG_DATA_TYPE,
    PVSS_SCOPE_NOT_SET;

    public String value() {
        return name();
    }

    public static EsdpErrorType fromValue(String v) {
        return valueOf(v);
    }

}
