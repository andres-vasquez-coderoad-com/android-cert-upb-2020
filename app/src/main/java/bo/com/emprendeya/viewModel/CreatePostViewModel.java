package bo.com.emprendeya.viewModel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.Post;
import bo.com.emprendeya.model.Startup;
import bo.com.emprendeya.repository.Repository;
import bo.com.emprendeya.repository.RepositoryImpl;

public class CreatePostViewModel extends AndroidViewModel {

    private RepositoryImpl repository;

    public CreatePostViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<Base<String>> createPost(String uuidStartup, Post post, Uri image) {
        return repository.addPostToStartup(uuidStartup, post, image);
    }
}
