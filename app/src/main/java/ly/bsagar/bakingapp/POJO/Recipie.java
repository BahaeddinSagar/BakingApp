package ly.bsagar.bakingapp.POJO;

import org.parceler.Parcel;

@Parcel
public class Recipie {
    String title;
    String serving;
    String id;
    String imageURL;

    public Recipie() {

    }

    public Recipie(String id, String title, String serving, String imageURL) {
        this.title = title;
        this.serving = serving;
        this.id = id;
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getTitle() {
        return title;
    }

    public String getServing() {
        return serving;
    }

    public String getId() {
        return id;
    }
}
