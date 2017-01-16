package com.eyelinecom.whoisd.sads2.ccc.utils;

import com.eyelinecom.whoisd.sads2.esdpapi.api.XmlService;
import com.eyelinecom.whoisd.sads2.esdpapi.impl.Configuration;
import com.eyelinecom.whoisd.sads2.esdpapi.impl.Configuration_Service;
import com.eyelinecom.whoisd.sads2.esdpapi.impl.EsdpServiceException;
import com.sun.xml.internal.ws.developer.WSBindingProvider;

import javax.xml.bind.DatatypeConverter;
import javax.xml.ws.handler.MessageContext;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gev
 * Date: 06.12.16
 * Time: 13:36
 * To change this template use File | Settings | File Templates.
 */
public class ConfApi {

  private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ConfApi.class);

  private Configuration api;

  private ConfApi(Configuration api) {
    this.api = api;
  }

  public static ConfApi get(String provider, String apiKey) {
    // TODO: cache apis?
    Configuration_Service configurationService = new Configuration_Service();
    Configuration port = configurationService.getWebPort();
    WSBindingProvider bindingProvider = (WSBindingProvider) port;
    bindingProvider.getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS,
      Collections.singletonMap("Authorization", Collections.singletonList("Basic " +
        DatatypeConverter.printBase64Binary((provider + ":" + apiKey).getBytes()))));
    return new ConfApi(port);
  }

  public Boolean isEnabled(String serviceId) {
    try {
      // TODO: cache services?
      XmlService service = api.getService(serviceId);
      XmlService.Properties properties = service.getProperties();
      for (XmlService.Properties.Entry entry : properties.getEntry()) {
        if ("ccc-enabled".equals(entry.getKey())) return "true".equals(entry.getValue());
      }
      return false;
    } catch (Exception e) {
      log.error("conf api error", e);
      return null;
    }
  }

  public boolean setEnabled(String serviceId, boolean enabled) {
    try {
      // TODO: cache services?
      XmlService service = api.getService(serviceId);
      XmlService.Properties properties = service.getProperties();
      boolean found = false;
      for (XmlService.Properties.Entry entry : properties.getEntry()) {
        if ("ccc-enabled".equals(entry.getKey())) {
          found = true;
          entry.setValue(enabled ? "true" : "false");
        }
      }
      if (!found) {
        XmlService.Properties.Entry entry = new XmlService.Properties.Entry();
        entry.setKey("ccc-enabled");
        entry.setValue(enabled ? "true" : "false");
        properties.getEntry().add(entry);
      }
      api.updateService(service);
      return true;
    } catch (Exception e) {
      log.error("conf api error", e);
      return false;
    }
  }
}
