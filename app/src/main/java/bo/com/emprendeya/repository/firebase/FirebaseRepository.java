package bo.com.emprendeya.repository.firebase;

public class FirebaseRepository {
    private static final String LOG = FirebaseRepository.class.getSimpleName();
    private static FirebaseRepository instance;

    public static FirebaseRepository getInstance() {
        if (instance == null) {
            instance = new FirebaseRepository();
        }
        return instance;
    }
}
