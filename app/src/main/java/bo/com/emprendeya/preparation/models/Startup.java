package bo.com.emprendeya.preparation.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.Map;

@Entity(tableName = "startup_table")
public class Startup {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uuid")
    private String uuid;

    @NonNull
    @ColumnInfo(name = "coverPhoto")
    private String coverPhoto;

    @ColumnInfo(name = "displayName")
    private String displayName;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "address")
    private String address;

    @Ignore
    private List<Post> posts;

    @ColumnInfo(name = "codivInfo")
    private Map<String, String> codivInfo;

    @ColumnInfo(name = "schedule")
    private Map<String, List<String>> schedule;

    public Startup(String uuid, String coverPhoto, String displayName, String category, String address) {
        this.uuid = uuid;
        this.coverPhoto = coverPhoto;
        this.displayName = displayName;
        this.category = category;
        this.address = address;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
