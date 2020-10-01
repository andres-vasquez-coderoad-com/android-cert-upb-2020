package bo.com.emprendeya.ui.activities;

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

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import bo.com.emprendeya.R;
import bo.com.emprendeya.models.Base;
import bo.com.emprendeya.models.users.User;
import bo.com.emprendeya.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG = LoginActivity.class.getName();
    private Context context;
    private LoginViewModel viewModel;

    private Button loginButton;
    private RelativeLayout parentRelativeLayout;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(LOG, "onCreate");
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        this.context = this;
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        initViews();
        initEvents();
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
}