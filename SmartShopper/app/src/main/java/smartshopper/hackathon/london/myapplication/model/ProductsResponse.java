package smartshopper.hackathon.london.myapplication.model;

import java.util.List;

/**
 * Created by christosoikonomou on 04/12/2016.
 */

public class ProductsResponse {
   public String   messageType;
   public List<Product> products;

    public String getMessageType() {
        return messageType;
    }

    public List<Product> getProducts() {
        return products;
    }
}
