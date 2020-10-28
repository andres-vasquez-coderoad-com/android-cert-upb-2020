package bo.com.emprendeya.repository.firebase.storage;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import bo.com.emprendeya.model.Base;

public class FirebaseStorageManager {
    private StorageReference storage;

    public FirebaseStorageManager() {
        storage = FirebaseStorage.getInstance().getReference();
    }

    public LiveData<Base<String>> uploadPostImage(String uuidPost, Uri image) {
        MutableLiveData<Base<String>> results = new MutableLiveData<>();
        new Thread(() -> {
            try {
                Thread.sleep(10000);
                results.postValue(new Base<>("https://saboryestilo.com.mx/wp-content/uploads/2020/05/parrilla-argentina-1-1200x720.jpg"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        return results;
    }
}
