package bo.com.emprendeya.model.users;

public class AdminUser extends User {

    public AdminUser(String email, String password) {
        super(email, password);
        profile = UserProfile.ADMIN;
    }
}
