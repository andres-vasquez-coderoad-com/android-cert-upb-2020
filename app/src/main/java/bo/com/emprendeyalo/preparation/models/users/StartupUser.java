package bo.com.emprendeyalo.preparation.models.users;

import java.util.Map;

import bo.com.emprendeyalo.preparation.models.Startup;

public class StartupUser extends User {
    private Startup startup;
    private String phone;
    private String resume;
    private Map<String, String> socialNetworks;

    public StartupUser(String email, String password) {
        super(email, password);
    }

    public Startup getStartup() {
        return startup;
    }

    public void setStartup(Startup startup) {
        this.startup = startup;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Map<String, String> getSocialNetworks() {
        return socialNetworks;
    }

    public void setSocialNetworks(Map<String, String> socialNetworks) {
        this.socialNetworks = socialNetworks;
    }
}
