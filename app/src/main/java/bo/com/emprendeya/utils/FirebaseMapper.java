package bo.com.emprendeya.utils;

import com.google.firebase.auth.FirebaseUser;

import bo.com.emprendeya.model.users.User;

public class FirebaseMapper {
    static public User firebaseUserToUser(FirebaseUser firebaseUser) {
        User user = new User(firebaseUser.getEmail(), null);
        user.setUuid(firebaseUser.getUid());
        user.setDisplayName(firebaseUser.getDisplayName());
        user.setPhoto(firebaseUser.getPhotoUrl() != null ? firebaseUser.getPhotoUrl().toString() : "");
        return user;
    }
}
