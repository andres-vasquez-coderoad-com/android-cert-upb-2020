package bo.com.emprendeya.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class LoginViewModel extends AndroidViewModel {
    private static final String LOG = LoginViewModel.class.getSimpleName();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }
}
