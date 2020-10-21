package bo.com.emprendeyalo.preparation.ui.activities;

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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import bo.com.emprendeyalo.preparation.R;
import bo.com.emprendeyalo.preparation.models.Base;
import bo.com.emprendeyalo.preparation.models.users.User;
import bo.com.emprendeyalo.preparation.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG = LoginActivity.class.getName();
    private Context context;
    private LoginViewModel viewModel;

    private Button loginButton;
    private RelativeLayout parentRelativeLayout;
    private EditText emailEditText;
    private EditText passwordEditText;

    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(LOG, "onCreate");
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        this.context = this;

        viewModel.getCurrentUser().observe(this, userBase -> {
            if (userBase.isSuccess()) {
                Toast.makeText(context, "One time login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, StartupListActivity.class);
                startActivity(intent);
            }
        });
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        initViews();
        initEvents();
        initGoogleSignIn();
    }

    private void initViews() {
        parentRelativeLayout = findViewById(R.id.parentRelativeLayout);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        emailEditText.setText("test@email.com");
        passwordEditText.setText("test1@email.com");
    }

    private void initEvents() {
        loginButton.setOnClickListener(view -> {
            viewModel.loginWithEmailPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                    .observe(this, userBase -> {
                        if (userBase.isSuccess()) {
                            Intent intent = new Intent(context, StartupListActivity.class);
                            startActivity(intent);
                        } else {
                            Snackbar.make(parentRelativeLayout, userBase.getMessage(),
                                    BaseTransientBottomBar.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    private void initGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
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

    public void openSecondActivity(View view) {
        startActivity(new Intent(LoginActivity.this, StartupListActivity.class));
    }

    public void googleLogin(View view) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                viewModel.loginWithGoogle(account.getIdToken()).observe(LoginActivity.this, new Observer<Base<User>>() {
                    @Override
                    public void onChanged(Base<User> userBase) {
                        if (userBase.isSuccess()) {
                            Intent intent = new Intent(context, StartupListActivity.class);
                            startActivity(intent);
                        } else {
                            Snackbar.make(parentRelativeLayout, userBase.getMessage(),
                                    BaseTransientBottomBar.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Snackbar.make(parentRelativeLayout, "" + e.getMessage(),
                        BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        }
    }
}