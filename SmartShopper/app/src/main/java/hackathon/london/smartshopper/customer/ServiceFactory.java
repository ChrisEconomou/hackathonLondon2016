package hackathon.london.smartshopper.customer;

import android.support.annotation.Nullable;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Factory used for creating the retrofit service.
 */
public class ServiceFactory {

    /**
     * Creates a retrofit service from an arbitrary class
     *
     * @param clazz         Java interface of the retrofit service
     * @param isCheckoutApi boolean that identifies rest Apis used throughout Checkout
     * @param siteOrigin    identifies the origin we should point to
     * @return retrofit service with defined endpoint
     */
    public static <T> T createRetrofitService(final Class<T> clazz, String endpoint, final boolean isCheckoutApi, @Nullable final String siteOrigin) {
        return createRetrofitService(clazz, endpoint, isCheckoutApi, siteOrigin, false);
    }

    /**
     * Creates a retrofit service from an arbitrary class
     *
     * @param clazz         Java interface of the retrofit service
     * @param isCheckoutApi boolean that identifies rest Apis used throughout Checkout
     * @param siteOrigin    identifies the origin we should point to
     * @param cache         indicates whether the client should have a response cache or not
     * @return retrofit service with defined endpoint
     */
    public static <T> T createRetrofitService(final Class<T> clazz, String endpoint,
                                              final boolean isCheckoutApi,
                                              @Nullable final String siteOrigin,
                                              boolean cache) {
        return createRetrofitService(clazz, endpoint, isCheckoutApi, siteOrigin, GsonConverterFactory.create(GsonUtil.createGson()), cache);
    }

    /**
     * Creates a retrofit service from an arbitrary class
     *
     * @param clazz            Java interface of the retrofit service
     * @param isCheckoutApi    boolean that identifies rest Apis used throughout Checkout
     * @param siteOrigin       identifies the origin we should point to
     * @param converterFactory the converterFactory to use (for json or xml)
     * @return retrofit service with defined endpoint
     */
    public static <T> T createRetrofitService(final Class<T> clazz, String endpoint,
                                              final boolean isCheckoutApi,
                                              @Nullable final String siteOrigin,
                                              Converter.Factory converterFactory) {
        return createRetrofitService(clazz, endpoint, isCheckoutApi, siteOrigin, converterFactory, false);
    }

    /**
     * Creates a retrofit service from an arbitrary class
     *
     * @param clazz            Java interface of the retrofit service
     * @param isCheckoutApi    boolean that identifies rest Apis used throughout Checkout
     * @param siteOrigin       identifies the origin we should point to
     * @param converterFactory the converterFactory to use (for json or xml)
     * @param cache            indicates whether the client should have a response cache or not
     * @return retrofit service with defined endpoint
     */
    private static <T> T createRetrofitService(final Class<T> clazz, String endpoint,
                                               final boolean isCheckoutApi,
                                               @Nullable final String siteOrigin,
                                               Converter.Factory converterFactory,
                                               boolean cache) {
        OkHttpClient client = OkHttpClientFactory.getHTTPClient(isCheckoutApi, siteOrigin, cache);
        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(endpoint)
                .client(client)
                .build();
        return retrofit.create(clazz);
    }
}
