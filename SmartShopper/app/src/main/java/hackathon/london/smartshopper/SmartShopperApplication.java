package hackathon.london.smartshopper;

import android.app.Application;
import android.content.Context;

/**
 * Created by christosoikonomou on 03/12/2016.
 */

public class SmartShopperApplication extends Application {
    private static Context context;

    public static Context getAppContext() {
        return SmartShopperApplication.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Static application context
        context = getApplicationContext();
    }
}
