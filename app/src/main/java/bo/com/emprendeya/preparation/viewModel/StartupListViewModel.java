package bo.com.emprendeya.preparation.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import bo.com.emprendeya.preparation.models.Base;
import bo.com.emprendeya.preparation.models.Post;
import bo.com.emprendeya.preparation.models.Startup;
import bo.com.emprendeya.preparation.repository.Repository;
import bo.com.emprendeya.preparation.repository.RepositoryImpl;

public class StartupListViewModel extends AndroidViewModel {
    private static final String LOG = StartupListViewModel.class.getSimpleName();
    RepositoryImpl repository;

    public StartupListViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<Base<List<Post>>> getMostLikelyPosts() {
        MutableLiveData<Base<List<Post>>> mutableLiveData = new MutableLiveData<>();
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("1", "https://images.squarespace-cdn.com/content/v1/58e306c359cc689fc7b613e9/1493150371561-9YQ7KQBLMU7DPKQP47UH/ke17ZwdGBToddI8pDm48kAlIP4PFSFSzdbKF_bP3y0wUqsxRUqqbr1mOJYKfIPR7LoDQ9mXPOjoJoqy81S2I8N_N4V1vUb5AoIIIbLZhVYxCRW4BPu10St3TBAUQYVKckb3AVJqW93B_Yrl1GKfkNTFC290qmMoARMHYgq2SctW8vHQ7-dyjZJ481VG-F6Q7/El-Rey-16_tacos.jpg?format=1500w"));
        posts.add(new Post("2", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSdaX9aazCdxacZnRsPUYWXCn-Nl_LLmzUK2Q&usqp=CAU"));
        posts.add(new Post("3", "https://images.squarespace-cdn.com/content/v1/58e306c359cc689fc7b613e9/1493150371561-9YQ7KQBLMU7DPKQP47UH/ke17ZwdGBToddI8pDm48kAlIP4PFSFSzdbKF_bP3y0wUqsxRUqqbr1mOJYKfIPR7LoDQ9mXPOjoJoqy81S2I8N_N4V1vUb5AoIIIbLZhVYxCRW4BPu10St3TBAUQYVKckb3AVJqW93B_Yrl1GKfkNTFC290qmMoARMHYgq2SctW8vHQ7-dyjZJ481VG-F6Q7/El-Rey-16_tacos.jpg?format=1500w\"));"));
        posts.add(new Post("4", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSdaX9aazCdxacZnRsPUYWXCn-Nl_LLmzUK2Q&usqp=CAU"));
        mutableLiveData.postValue(new Base<>(posts));
        return mutableLiveData;
    }

    public LiveData<Base<List<Startup>>> getStartups(String category) {
        return repository.getStartups(category);
    }
}
