package smartshopper.hackathon.london.myapplication.util;

import android.content.Context;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import smartshopper.hackathon.london.myapplication.model.ProductsResponse;


/**
 * Utils for obtaining mock json data.
 */
public class MockDataUtils {


    public static final String MOCK_PRODUCTS_JSON = "products.json";

    public static ProductsResponse getHeaderResponse(Context context) {
        return (ProductsResponse) getMockResponse(context, MOCK_PRODUCTS_JSON, ProductsResponse.class);
    }

    public static MessageReceivedEvent getMessageReceivedEvent(Context context) {
        return new MessageReceivedEvent(getJsonString(context, MOCK_PRODUCTS_JSON));
    }

    public static ProductsResponse getProductsResponse(Context context) {
        return (ProductsResponse) getMockResponse(context, "products.json", ProductsResponse.class);
    }


    private static Object getMockResponse(Context context, String fileName, Class className) {
        String jsonString = getJsonString(context, fileName);
        return GsonUtil.deserialize(jsonString, className);
    }


    public static String getJsonString(Context context, String fileName) {
        String jsonString = null;

        StringBuilder builder = new StringBuilder();
        InputStream json;
        try {
            json = context.getAssets().open("mock/" + fileName);
            BufferedReader in = new BufferedReader(new InputStreamReader(json));
            String str;

            while ((str = in.readLine()) != null) {
                builder.append(str);
            }

            in.close();
            jsonString = builder.toString();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return jsonString;
    }

}