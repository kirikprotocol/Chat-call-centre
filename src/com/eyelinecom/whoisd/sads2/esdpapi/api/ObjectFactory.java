
package com.eyelinecom.whoisd.sads2.esdpapi.api;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.eyelinecom.whoisd.sads2.esdpapi.api package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UpdateProviderResponse_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "updateProviderResponse");
    private final static QName _DeleteServiceResponse_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "deleteServiceResponse");
    private final static QName _DeleteProvider_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "deleteProvider");
    private final static QName _UpdateServiceResponse_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "updateServiceResponse");
    private final static QName _CreateService_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "createService");
    private final static QName _EsdpServiceException_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "EsdpServiceException");
    private final static QName _CreateServiceResponse_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "createServiceResponse");
    private final static QName _ListProviders_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "listProviders");
    private final static QName _GetServiceResponse_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "getServiceResponse");
    private final static QName _DeleteProviderResponse_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "deleteProviderResponse");
    private final static QName _XmlProvider_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "xmlProvider");
    private final static QName _UpdateProvider_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "updateProvider");
    private final static QName _ListProvidersResponse_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "listProvidersResponse");
    private final static QName _DeleteService_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "deleteService");
    private final static QName _GetService_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "getService");
    private final static QName _CreateProvider_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "createProvider");
    private final static QName _ListServices_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "listServices");
    private final static QName _CreateProviderResponse_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "createProviderResponse");
    private final static QName _GetProviderResponse_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "getProviderResponse");
    private final static QName _ListServicesResponse_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "listServicesResponse");
    private final static QName _UpdateService_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "updateService");
    private final static QName _GetProvider_QNAME = new QName("http://api.esdpapi.sads2.whoisd.eyelinecom.com/", "getProvider");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.eyelinecom.whoisd.sads2.esdpapi.api
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link XmlService }
     * 
     */
    public XmlService createXmlService() {
        return new XmlService();
    }

    /**
     * Create an instance of {@link XmlService.Properties }
     * 
     */
    public XmlService.Properties createXmlServiceProperties() {
        return new XmlService.Properties();
    }

    /**
     * Create an instance of {@link Provider }
     * 
     */
    public Provider createProvider() {
        return new Provider();
    }

    /**
     * Create an instance of {@link Provider.Properties }
     * 
     */
    public Provider.Properties createProviderProperties() {
        return new Provider.Properties();
    }

    /**
     * Create an instance of {@link DeleteProviderResponse }
     * 
     */
    public DeleteProviderResponse createDeleteProviderResponse() {
        return new DeleteProviderResponse();
    }

    /**
     * Create an instance of {@link ListProviders }
     * 
     */
    public ListProviders createListProviders() {
        return new ListProviders();
    }

    /**
     * Create an instance of {@link GetServiceResponse }
     * 
     */
    public GetServiceResponse createGetServiceResponse() {
        return new GetServiceResponse();
    }

    /**
     * Create an instance of {@link EsdpServiceException }
     * 
     */
    public EsdpServiceException createEsdpServiceException() {
        return new EsdpServiceException();
    }

    /**
     * Create an instance of {@link CreateServiceResponse }
     * 
     */
    public CreateServiceResponse createCreateServiceResponse() {
        return new CreateServiceResponse();
    }

    /**
     * Create an instance of {@link DeleteServiceResponse }
     * 
     */
    public DeleteServiceResponse createDeleteServiceResponse() {
        return new DeleteServiceResponse();
    }

    /**
     * Create an instance of {@link UpdateProviderResponse }
     * 
     */
    public UpdateProviderResponse createUpdateProviderResponse() {
        return new UpdateProviderResponse();
    }

    /**
     * Create an instance of {@link UpdateServiceResponse }
     * 
     */
    public UpdateServiceResponse createUpdateServiceResponse() {
        return new UpdateServiceResponse();
    }

    /**
     * Create an instance of {@link CreateService }
     * 
     */
    public CreateService createCreateService() {
        return new CreateService();
    }

    /**
     * Create an instance of {@link DeleteProvider }
     * 
     */
    public DeleteProvider createDeleteProvider() {
        return new DeleteProvider();
    }

    /**
     * Create an instance of {@link CreateProviderResponse }
     * 
     */
    public CreateProviderResponse createCreateProviderResponse() {
        return new CreateProviderResponse();
    }

    /**
     * Create an instance of {@link ListServices }
     * 
     */
    public ListServices createListServices() {
        return new ListServices();
    }

    /**
     * Create an instance of {@link CreateProvider }
     * 
     */
    public CreateProvider createCreateProvider() {
        return new CreateProvider();
    }

    /**
     * Create an instance of {@link GetProvider }
     * 
     */
    public GetProvider createGetProvider() {
        return new GetProvider();
    }

    /**
     * Create an instance of {@link UpdateService }
     * 
     */
    public UpdateService createUpdateService() {
        return new UpdateService();
    }

    /**
     * Create an instance of {@link ListServicesResponse }
     * 
     */
    public ListServicesResponse createListServicesResponse() {
        return new ListServicesResponse();
    }

    /**
     * Create an instance of {@link GetProviderResponse }
     * 
     */
    public GetProviderResponse createGetProviderResponse() {
        return new GetProviderResponse();
    }

    /**
     * Create an instance of {@link GetService }
     * 
     */
    public GetService createGetService() {
        return new GetService();
    }

    /**
     * Create an instance of {@link DeleteService }
     * 
     */
    public DeleteService createDeleteService() {
        return new DeleteService();
    }

    /**
     * Create an instance of {@link ListProvidersResponse }
     * 
     */
    public ListProvidersResponse createListProvidersResponse() {
        return new ListProvidersResponse();
    }

    /**
     * Create an instance of {@link UpdateProvider }
     * 
     */
    public UpdateProvider createUpdateProvider() {
        return new UpdateProvider();
    }

    /**
     * Create an instance of {@link XmlService.Properties.Entry }
     * 
     */
    public XmlService.Properties.Entry createXmlServicePropertiesEntry() {
        return new XmlService.Properties.Entry();
    }

    /**
     * Create an instance of {@link Provider.Properties.Entry }
     * 
     */
    public Provider.Properties.Entry createProviderPropertiesEntry() {
        return new Provider.Properties.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateProviderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "updateProviderResponse")
    public JAXBElement<UpdateProviderResponse> createUpdateProviderResponse(UpdateProviderResponse value) {
        return new JAXBElement<UpdateProviderResponse>(_UpdateProviderResponse_QNAME, UpdateProviderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "deleteServiceResponse")
    public JAXBElement<DeleteServiceResponse> createDeleteServiceResponse(DeleteServiceResponse value) {
        return new JAXBElement<DeleteServiceResponse>(_DeleteServiceResponse_QNAME, DeleteServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteProvider }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "deleteProvider")
    public JAXBElement<DeleteProvider> createDeleteProvider(DeleteProvider value) {
        return new JAXBElement<DeleteProvider>(_DeleteProvider_QNAME, DeleteProvider.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "updateServiceResponse")
    public JAXBElement<UpdateServiceResponse> createUpdateServiceResponse(UpdateServiceResponse value) {
        return new JAXBElement<UpdateServiceResponse>(_UpdateServiceResponse_QNAME, UpdateServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "createService")
    public JAXBElement<CreateService> createCreateService(CreateService value) {
        return new JAXBElement<CreateService>(_CreateService_QNAME, CreateService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EsdpServiceException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "EsdpServiceException")
    public JAXBElement<EsdpServiceException> createEsdpServiceException(EsdpServiceException value) {
        return new JAXBElement<EsdpServiceException>(_EsdpServiceException_QNAME, EsdpServiceException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "createServiceResponse")
    public JAXBElement<CreateServiceResponse> createCreateServiceResponse(CreateServiceResponse value) {
        return new JAXBElement<CreateServiceResponse>(_CreateServiceResponse_QNAME, CreateServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListProviders }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "listProviders")
    public JAXBElement<ListProviders> createListProviders(ListProviders value) {
        return new JAXBElement<ListProviders>(_ListProviders_QNAME, ListProviders.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "getServiceResponse")
    public JAXBElement<GetServiceResponse> createGetServiceResponse(GetServiceResponse value) {
        return new JAXBElement<GetServiceResponse>(_GetServiceResponse_QNAME, GetServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteProviderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "deleteProviderResponse")
    public JAXBElement<DeleteProviderResponse> createDeleteProviderResponse(DeleteProviderResponse value) {
        return new JAXBElement<DeleteProviderResponse>(_DeleteProviderResponse_QNAME, DeleteProviderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Provider }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "xmlProvider")
    public JAXBElement<Provider> createXmlProvider(Provider value) {
        return new JAXBElement<Provider>(_XmlProvider_QNAME, Provider.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateProvider }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "updateProvider")
    public JAXBElement<UpdateProvider> createUpdateProvider(UpdateProvider value) {
        return new JAXBElement<UpdateProvider>(_UpdateProvider_QNAME, UpdateProvider.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListProvidersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "listProvidersResponse")
    public JAXBElement<ListProvidersResponse> createListProvidersResponse(ListProvidersResponse value) {
        return new JAXBElement<ListProvidersResponse>(_ListProvidersResponse_QNAME, ListProvidersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "deleteService")
    public JAXBElement<DeleteService> createDeleteService(DeleteService value) {
        return new JAXBElement<DeleteService>(_DeleteService_QNAME, DeleteService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "getService")
    public JAXBElement<GetService> createGetService(GetService value) {
        return new JAXBElement<GetService>(_GetService_QNAME, GetService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateProvider }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "createProvider")
    public JAXBElement<CreateProvider> createCreateProvider(CreateProvider value) {
        return new JAXBElement<CreateProvider>(_CreateProvider_QNAME, CreateProvider.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListServices }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "listServices")
    public JAXBElement<ListServices> createListServices(ListServices value) {
        return new JAXBElement<ListServices>(_ListServices_QNAME, ListServices.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateProviderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "createProviderResponse")
    public JAXBElement<CreateProviderResponse> createCreateProviderResponse(CreateProviderResponse value) {
        return new JAXBElement<CreateProviderResponse>(_CreateProviderResponse_QNAME, CreateProviderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProviderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "getProviderResponse")
    public JAXBElement<GetProviderResponse> createGetProviderResponse(GetProviderResponse value) {
        return new JAXBElement<GetProviderResponse>(_GetProviderResponse_QNAME, GetProviderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListServicesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "listServicesResponse")
    public JAXBElement<ListServicesResponse> createListServicesResponse(ListServicesResponse value) {
        return new JAXBElement<ListServicesResponse>(_ListServicesResponse_QNAME, ListServicesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "updateService")
    public JAXBElement<UpdateService> createUpdateService(UpdateService value) {
        return new JAXBElement<UpdateService>(_UpdateService_QNAME, UpdateService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProvider }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.esdpapi.sads2.whoisd.eyelinecom.com/", name = "getProvider")
    public JAXBElement<GetProvider> createGetProvider(GetProvider value) {
        return new JAXBElement<GetProvider>(_GetProvider_QNAME, GetProvider.class, null, value);
    }

}
