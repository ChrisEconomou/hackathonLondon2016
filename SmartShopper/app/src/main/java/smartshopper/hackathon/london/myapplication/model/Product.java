package smartshopper.hackathon.london.myapplication.model;

/**
 * Created by christosoikonomou on 04/12/2016.
 */

public class Product {
    private String id;
    private String image;
    private String link;
    private String pricePrevious;
    private String pricecurrent;

    public String getTitle() {
        return title;
    }

    private String title;

    public String getPricePrevious() {
        return pricePrevious;
    }

    public void setPricePrevious(String pricePrevious) {
        this.pricePrevious = pricePrevious;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPriceCurrent() {
        return pricecurrent;
    }

    public void setPriceCurrent(String priceCurrent) {
        this.pricecurrent = priceCurrent;
    }
}
