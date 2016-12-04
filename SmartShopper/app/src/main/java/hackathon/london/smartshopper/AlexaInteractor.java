package hackathon.london.smartshopper;

import java.util.Observable;

import hackathon.london.smartshopper.customer.AlexaRestApi;
import hackathon.london.smartshopper.customer.RestApiServiceFactory;

/**
 * Created by christosoikonomou on 03/12/2016.
 */

public class AlexaInteractor {
    AlexaRestApi alexaRestApi;

    public AlexaInteractor() {
        alexaRestApi = RestApiServiceFactory.getCustomerRestAPI();
    }


//    Observable<Object> sendAudio(){
//        return alexaRestApi.sendAudio();
//    }
}
