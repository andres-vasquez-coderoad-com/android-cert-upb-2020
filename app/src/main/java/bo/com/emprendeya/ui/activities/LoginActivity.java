package bo.com.emprendeya.ui.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import bo.com.emprendeya.R;
import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.users.User;
import bo.com.emprendeya.utils.Constants;
import bo.com.emprendeya.utils.ErrorMapper;
import bo.com.emprendeya.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG = LoginActivity.class.getName();
    private Context context;

    private RelativeLayout parentRelativeLayout;
    private Button googleButton;
    private Button emailButton;
    private FrameLayout registerFrameLayout;

    private FrameLayout popupLoginFrameLayout;
    private LinearLayout backgroundLoginLinearLayout;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private LoginViewModel viewModel;

    //Google Auth
    private GoogleSignInClient googleSignInClient;
    private int GOOGLE_AUTH_RC = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(LOG, "onCreate");
        setContentView(R.layout.activity_login);

        context = this; //Todo el entorno, variables del Activity
        //Aquí y ahora. --> Activity, Fragment, Application
        //Raisa: En su cuarto, escritorio, laptop, notas, con su perrito
        //Deportivo, tennis
        //Avena

        //Injectando el viewModel
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        //Ocultar el ActionBar
        getSupportActionBar().hide();

        initViews();
        initEvents();
        initGoogleAuthConfig();
    }

    private void initViews() {
        parentRelativeLayout = findViewById(R.id.parentRelativeLayout);
        googleButton = findViewById(R.id.googleButton);
        emailButton = findViewById(R.id.emailButton);
        registerFrameLayout = findViewById(R.id.registerFrameLayout);

        popupLoginFrameLayout = findViewById(R.id.popupLoginFrameLayout);
        backgroundLoginLinearLayout = findViewById(R.id.backgroundLoginLinearLayout);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        emailEditText.setText("paola.rivas@email.com");
        passwordEditText.setText("test123");
    }

    private void initEvents() {
        emailButton.setOnClickListener(view -> popupLoginFrameLayout.setVisibility(View.VISIBLE));
        popupLoginFrameLayout.setOnClickListener(view -> popupLoginFrameLayout.setVisibility(View.GONE));
        backgroundLoginLinearLayout.setOnClickListener(view -> {
            return;
        });

        googleButton.setOnClickListener(view -> {
            //Paso 2
            Intent intent = googleSignInClient.getSignInIntent();
            startActivityForResult(intent, GOOGLE_AUTH_RC);
        });
    }

    //Paso 1
    private void initGoogleAuthConfig() {
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, options);
    }

    public void login(View view) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        viewModel.loginWithEmailPassword(email, password).observe(this, new Observer<Base<User>>() {
            @Override
            public void onChanged(Base<User> userBase) {
                if (userBase.isSuccess()) {
                    openNextActivity(userBase.getData());
                } else {
                    Snackbar.make(parentRelativeLayout,
                            ErrorMapper.getError(context, userBase.getErrorCode()),
                            BaseTransientBottomBar.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(LOG, "onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e(LOG, "onResume");

        //Consumir los recursos
        //La foto fue subida con exito.
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(LOG, "onPause");

        //Liberar los recursos
        //Continuar subiendo la foto
    }

    private void subirFotoAInstagram() {

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(LOG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(LOG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(LOG, "onDestroy");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Paso 3

        if (requestCode == GOOGLE_AUTH_RC) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                //Paso 4
                viewModel.loginWithGoogle(account.getIdToken()).observe(LoginActivity.this, new Observer<Base<User>>() {
                    @Override
                    public void onChanged(Base<User> userBase) {
                        if (userBase.isSuccess()) {
                            openNextActivity(userBase.getData());
                        } else {
                            Snackbar.make(parentRelativeLayout,
                                    ErrorMapper.getError(context, Constants.ERROR_LOGIN_GOOGLE),
                                    BaseTransientBottomBar.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (ApiException ex) {
                Snackbar.make(parentRelativeLayout,
                        ErrorMapper.getError(context, Constants.ERROR_LOGIN_GOOGLE),
                        BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        }
    }

    private void openNextActivity(User user) {
        Intent intent = new Intent(context, StartupListActivity.class);
        intent.putExtra(Constants.KEY_UUID, user.getUuid());
        intent.putExtra(Constants.KEY_DISPLAY_NAME, user.getDisplayName());
        startActivity(intent);
    }
}