package hackathon.london.smartshopper.customer;

import static hackathon.london.smartshopper.customer.ServiceFactory.createRetrofitService;


/**
 * Rest service factory for keeping all rest api service instances.
 */
public class RestApiServiceFactory {
    private static final String URL = "https://avs-alexa-na.amazon.com/";
    private static AlexaRestApi alexaRestApi;

    /**
     * Creates a customer rest api instance
     *
     * @return an instance of the bag rest api Service
     */
    public static AlexaRestApi getCustomerRestAPI() {

        if (alexaRestApi == null) {


            alexaRestApi = new AlexaRestApi(
                    createRetrofitService(AlexaRestApiService.class,
                            URL, false, "")
            );
        }
        return alexaRestApi;
    }

}
