package bo.com.emprendeya.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import bo.com.emprendeya.R;
import bo.com.emprendeya.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG = LoginActivity.class.getName();
    private Context context;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(LOG, "onCreate");
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        this.context = this;
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
    }

    private void initViews() {

    }

    private void initEvents() {

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