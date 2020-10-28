package bo.com.emprendeya.repository.firebase.storage;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.utils.Constants;

public class FirebaseStorageManager {
    private StorageReference storage;

    public FirebaseStorageManager() {
        storage = FirebaseStorage.getInstance().getReference();
    }

    public LiveData<Base<String>> uploadPostImage(String uuidPost, Uri image) {
        MutableLiveData<Base<String>> results = new MutableLiveData<>();
        String path = "images/" + uuidPost + ".jpg";
        StorageReference ref = storage.child(path);
        UploadTask uploadTask = ref.putFile(image);
        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                // Continue with the task to get the download URL
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    results.postValue(new Base<>(downloadUri.toString()));
                } else {
                    results.postValue(new Base<>(Constants.ERROR_UPLOAD_IMAGE, task.getException()));
                }
            }
        });
        return results;
    }
}
