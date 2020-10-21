package bo.com.emprendeyalo.preparation.repository.firebase.database;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import bo.com.emprendeyalo.preparation.models.Base;

public class FirebaseDb<T> {
    private static final String LOG = FirebaseDb.class.getSimpleName();

    private FirebaseDatabase database;

    public FirebaseDb() {
        database = FirebaseDatabase.getInstance();
    }

    public void demoData() {
        // Write a message to the database
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.e(LOG + ".onComplete", "Yes");
                    }
                })
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.e(LOG + ".onSuccess", "Yes");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(LOG + ".onFailure", "No", e);
                    }
                });
    }

    public LiveData<Base<T>> setValue(String path, Object value) {
        final MutableLiveData<Base<T>> result = new MutableLiveData<>();
        database.getReference(path).setValue(value);
        return result;
    }

    public LiveData<Base<T>> subscribeToValues(String path) {
        final MutableLiveData<Base<T>> result = new MutableLiveData<>();
        database.getReference(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String values = new Gson().toJson(dataSnapshot.getValue());
                Log.e("Database", values);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return result;
    }
}
