package bo.com.emprendeya.models;

import java.util.List;
import java.util.Map;

public class Startup {
    private String uuid;
    private String coverPhoto;
    private String displayName;
    private String address;
    private List<Post> posts;
    private Map<String, String> codivInfo;
    private Map<String, List<String>> schedule;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Map<String, String> getCodivInfo() {
        return codivInfo;
    }

    public void setCodivInfo(Map<String, String> codivInfo) {
        this.codivInfo = codivInfo;
    }

    public Map<String, List<String>> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<String, List<String>> schedule) {
        this.schedule = schedule;
    }
}
