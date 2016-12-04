package hackathon.london.smartshopper.customer;

import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Rest interface for Customer related actions.
 */
public interface AlexaRestApiService {

    String EVENTS = "v20160207/events";

    String POST_ADDRESS = "customers/{customerId}/addresses";

    String UPDATE_ADDRESS = "customers/{customerId}/addresses/{addressId}";

    @Headers("Content-Type: multipart/form-data")
    @POST(EVENTS)
    Observable<Object> sendAudio(@Header("Authorization") String token, @Path("customerId") String customerId);

    @PATCH(EVENTS)
    Observable<Object> updateCustomerInfo(@Header("Authorization") String token, @Path("customerId") String customerId);
}
