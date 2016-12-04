package smartshopper.hackathon.london.myapplication.util;

import smartshopper.hackathon.london.myapplication.model.ProductsResponse;

/**
 * Created by christosoikonomou on 04/12/2016.
 */

public class PlaceOrderEvent {
    public ProductsResponse getOrder() {
        return order;
    }

    ProductsResponse order;

    public PlaceOrderEvent(ProductsResponse order) {
        this.order = order;
    }
}
