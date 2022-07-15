import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
public class Video implements Comparable<Video> {

    private String content;
    private String uniqueID;
    private String description;
    private ArrayList<String> categories;
    private String name;
    private String uploader;
    private String date_upload;
    private ArrayList<String> history; // datetime + description
    private Ratings ratings;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Video(String uploader, String name, String description, ArrayList<String> categories, String content, String uniqueID,
                 ArrayList<String> ratings,String date_upload){
        this.name = name;
        this.uploader = uploader;
        this.content = content;
        this.date_upload = date_upload;
//        this.history.add("We will add features later");
        //this.history = new ArrayList<String>();
        //this.history.add(LocalDateTime.now().toString() + "/" + "upload video" + "/"); Remove and add to upload video
        this.description = description;
        this.categories = categories;
        this.uniqueID = uniqueID;
        this.ratings = new Ratings(new ArrayList<>());

        //need to decide how we initialize the video construct
        //it will heavily depend on upload video in videomanager
    }
    // getters - retrieve video information


    public String getContent() {
        return content;
    }

    public Integer getRatings() {
        return ratings.getTotalLikes();
    }

    public String getUniqueID() {
        return uniqueID;
        //Todo check if it is wise to allow this to be public, maybe protected?
        //Nicholas: yea I agree that it can be protected, but you know the link for now is just a hyprelink,
        // which it doesn't have any meaning of being protected. We can just limit some other methods to be
        // authorize by specific people, and that I think will be enough.
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getUploader() {
        return uploader;
    }

    public String getDate_upload() {
        return date_upload;
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    // Setters - We will allow users/programs change these data fields
    public void setContent(String content) {
        this.content = content;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    //todo include append and remove categories as well since setCategories only adds a predetermined arraylist to replace the current one
    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public void addRatings(String userName){
        ratings.addTotalLikes(userName);
    }

    @Override
    public int compareTo(Video v) {
        int i = this.getName().compareTo(v.getName());
        if (i == 0) {
            return 0;
        }
        else if (i > 0) {
            return 1;
        }
        else {
            return -1;
        }
    }

    @Override
    public String toString() {
        Iterator<String> it1 = categories.iterator();
        StringBuilder s1 = new StringBuilder();
        while (it1.hasNext()) {
            s1.append(it1.next()).append("/");
        }

        Iterator<String> it2 = ratings.getLikeUserName().iterator();
        StringBuilder s2 = new StringBuilder();
        while (it2.hasNext()) {
            s2.append(it2.next()).append("/");
        }


        return this.getUploader() + "," + this.getName() + "," + this.getDescription() + "," + s1 + "," + this.getContent() + "," + this.getUniqueID() + "," + s2 + "," + this.getDate_upload();
    }
}

