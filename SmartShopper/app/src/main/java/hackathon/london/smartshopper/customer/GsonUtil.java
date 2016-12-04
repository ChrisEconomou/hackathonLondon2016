package hackathon.london.smartshopper.customer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Creates {@link Gson} object, manages (de)serialization.
 */
public class GsonUtil {


    public static Gson createGson() {
        return new GsonBuilder()
                .create();
    }

    public static <T> T deserialize(String json, Class<T> clazz) {

        return createGson().fromJson(json, clazz);
    }

    public static String serialize(Object obj) {

        return createGson().toJson(obj);
    }

    public static <T> ArrayList<T> deserialize(String json, Type type) {

        return createGson().fromJson(json, type);
    }

}