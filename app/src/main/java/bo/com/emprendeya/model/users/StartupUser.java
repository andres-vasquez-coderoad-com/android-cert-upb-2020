package bo.com.emprendeya.model.users;

public class StartupUser extends User {
    public StartupUser(String email, String password) {
        super(email, password);
        profile = UserProfile.STARTUP;
    }
}
