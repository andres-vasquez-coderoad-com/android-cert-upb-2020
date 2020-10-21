package bo.com.emprendeya.preparation.models;

import java.util.List;

import bo.com.emprendeya.preparation.models.users.User;

public class Post {
    private String uuid;
    private String coverPhoto;
    private String content;
    private long timestamp;
    private List<User> likes;

    public Post(String uuid, String coverPhoto) {
        this.uuid = uuid;
        this.coverPhoto = coverPhoto;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }
}
