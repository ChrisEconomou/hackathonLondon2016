package hackathon.london.smartshopper.customer;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Rest communication engine, responsible with creating and managing server calls for the Customer API.
 */
public class AlexaRestApi {
    private AlexaRestApiService mService;



    public AlexaRestApi(@NonNull AlexaRestApiService restApiService) {

        mService = restApiService;
    }

    /**
     * Creates request to retrieve customer information.
     * <p>
     * Return information on Schedulers.io(), use custom scheduler if you want this on your specific thread.
     *
     * @param customerId
     * @return Observable with customerinfomodel.
     */
    public Observable<Object> sendAudio(String token, @NonNull final String customerId) {
        return mService.sendAudio(token, customerId)
                    .subscribeOn(Schedulers.io());
    }


    public Observable<Object> updateCustomerInfo(String token,@NonNull final String customerId) {
        return mService.updateCustomerInfo("", customerId)
                        .subscribeOn(Schedulers.io());
    }



}
