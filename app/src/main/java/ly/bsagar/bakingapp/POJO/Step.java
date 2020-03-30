package ly.bsagar.bakingapp.POJO;

public class Step {
    String title;
    String id;
    String shorDescription;
    String imageURL;
    String videoURL;

    public Step(String title, String id, String shorDescription, String imageURL, String videoURL) {
        this.title = title;
        this.id = id;
        this.shorDescription = shorDescription;
        this.imageURL = imageURL;
        this.videoURL = videoURL;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getShorDescription() {
        return shorDescription;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getVideoURL() {
        return videoURL;
    }
}
