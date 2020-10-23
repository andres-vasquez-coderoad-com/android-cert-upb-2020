package bo.com.emprendeya.model.users;

public class User {
    protected String uuid;
    protected String email;
    protected String password;
    protected String displayName;
    protected String photo;
    protected UserProfile profile = UserProfile.REGULAR;
    public long lastLogin;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(long lastLogin) {
        this.lastLogin = lastLogin;
    }
}
