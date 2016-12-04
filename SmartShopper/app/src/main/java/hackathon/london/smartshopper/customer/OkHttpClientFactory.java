package hackathon.london.smartshopper.customer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;
import java.util.concurrent.TimeUnit;

import hackathon.london.smartshopper.SmartShopperApplication;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

public class OkHttpClientFactory {

    private static final String HTTP_CACHE = "httpcache";
    private static final int HTTP_CACHE_SIZE = 10 * 1024 * 1024;
    private static final long DEFAULT_REQUEST_TIMEOUT = 20;
    private static OkHttpClient sCheckoutHttpClient;
    private static OkHttpClient sSiteOriginHttpClient;
    private static OkHttpClient mOkHttpClient;
    private static OkHttpClient mOkHttpClientWithCache;
    private static OkHttpClient sCheckoutHttpClientWithCache;

    /**
     * create a single http client for retrofit request
     **/
    public static synchronized OkHttpClient getHTTPClient(boolean isCheckoutApi, @Nullable final String siteOrigin, boolean cache) {
        if (isCheckoutApi) {
            return cache ? checkoutHttpClientWithCache(siteOrigin) : getCheckoutHTTPClient(siteOrigin);
        } else if (siteOrigin!= null) {
            return getSiteOriginHTTPClient(siteOrigin);
        }

        return cache ? createHTTPClientWithCache() : createHTTPClient();
    }

    public static synchronized OkHttpClient getCheckoutHTTPClient(@Nullable final String origin) {
        if (sCheckoutHttpClient == null) {
            sCheckoutHttpClient = checkoutClient(origin).build();
        }
        return sCheckoutHttpClient;
    }

    public static synchronized OkHttpClient checkoutHttpClientWithCache(@Nullable final String origin) {
        if (sCheckoutHttpClientWithCache == null) {
            sCheckoutHttpClientWithCache = checkoutClient(origin)
                    .cache(responseCache(SmartShopperApplication.getAppContext()))
                    .build();
        }
        return sCheckoutHttpClientWithCache;
    }

    @NonNull
    private static OkHttpClient.Builder checkoutClient(@Nullable String origin) {
        OkHttpClient.Builder builder = baseOkHttpClientBuilder();

        return builder;
    }

    public static synchronized OkHttpClient getSiteOriginHTTPClient(@NonNull final String origin) {
        if (sSiteOriginHttpClient == null) {
            sSiteOriginHttpClient = baseOkHttpClientBuilder().build();
        }
        return sSiteOriginHttpClient;
    }

    public static synchronized OkHttpClient createHTTPClient() {
        if (mOkHttpClient == null) {
            mOkHttpClient = baseOkHttpClientBuilder().build();
        }
        return mOkHttpClient;
    }

    public static synchronized OkHttpClient createHTTPClientWithCache() {
        if (mOkHttpClientWithCache == null) {
            mOkHttpClientWithCache = baseOkHttpClientBuilder()
                    .cache(responseCache(SmartShopperApplication.getAppContext()))
                    .build();
        }
        return mOkHttpClientWithCache;
    }

    private static Cache responseCache(Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), HTTP_CACHE);
        return new Cache(httpCacheDirectory, HTTP_CACHE_SIZE);
    }

    @NonNull
    private static OkHttpClient.Builder baseOkHttpClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_REQUEST_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_REQUEST_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_REQUEST_TIMEOUT, TimeUnit.SECONDS);


        addPreviewModeInterceptorIfNeeded(builder);
        return builder;
    }

    private static OkHttpClient.Builder addPreviewModeInterceptorIfNeeded(OkHttpClient.Builder builder) {


        return builder;
    }

    private static boolean addInterceptor(OkHttpClient.Builder builder, Interceptor interceptor) {
        return builder.interceptors().add(interceptor);
    }
}
