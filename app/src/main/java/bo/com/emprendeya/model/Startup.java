package bo.com.emprendeya.model;

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

    @ColumnInfo(name = "coverPhoto")
    private String coverPhoto;

    @ColumnInfo(name = "displayName")
    private String displayName; //Bolivian Foods SRL --> Burger King

    @ColumnInfo(name = "address")
    private String address;

    @Ignore
    private List<String> categories;

    @Ignore
    private List<Post> posts;

    //temperatura, SI, NO
    //Pediluvio, SI, NO
    //Solo delivery, SI, NO
    //Aforo, 40% --> 100personas, 0
    @Ignore
    private Map<String, String> codivInfo;

    //Lun-Vie, [10:00-12:30, 14:00-20:00]
    //Sábado [10:00-13:00]
    //Domingo []
    @Ignore
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

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
