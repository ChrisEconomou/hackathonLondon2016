package hackathon.london.smartshopper.customer;

import android.util.Log;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Retry the network call forcing it from the cache, if the appropriate header
 * {@link StaleResponseInterceptor#STALE_IF_ERROR_HEADER_FULL} is present
 */
public class StaleResponseInterceptor implements Interceptor {

    /**
     * The name of the stale header, used to query if it is present
     */
    public static final String STALE_IF_ERROR_HEADER_NAME = "X-asos-stale-if-error";
    /**
     * The full header declaration. Used in rest api methods
     */
    public static final String STALE_IF_ERROR_HEADER_FULL = STALE_IF_ERROR_HEADER_NAME + ": ";

    @Override
    public Response intercept(Chain chain) throws IOException {
        boolean fallbackToCache = false;
        if (chain.request().header(STALE_IF_ERROR_HEADER_NAME) != null) {
            fallbackToCache = true;
        }
        Request.Builder newRequest = chain.request().newBuilder().removeHeader(STALE_IF_ERROR_HEADER_NAME);
        try {
            return chain.proceed(newRequest.build());
        } catch (IOException e) {
            if (fallbackToCache) {
                Log.w(StaleResponseInterceptor.class.getName(), "Failed during network call. Falling back to cached version.", e);
                return chain.proceed(newRequest
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build());
            }
            throw e;
        }
    }
}
