package bo.com.emprendeya.preparation.utils;

import com.google.firebase.auth.FirebaseUser;

import bo.com.emprendeya.preparation.models.users.User;

public class FirebaseMapper {
    public static User firebaseUserToUser(FirebaseUser currentUser) {
        User user = new User(currentUser.getEmail(), null);
        user.setDisplayName(currentUser.getDisplayName());
        user.setPhoto(currentUser.getPhotoUrl() != null ? currentUser.getPhotoUrl().getPath() : "");
        return user;
    }
}
