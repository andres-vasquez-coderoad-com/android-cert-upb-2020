package bo.com.emprendeya.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import bo.com.emprendeya.R;
import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.users.User;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG = LoginActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(LOG, "onCreate");
        setContentView(R.layout.activity_login);

        //Ocultar el ActionBar
        getSupportActionBar().hide();

        User user = new User("andres.vasquez@email.com", "test123");

        /*Base baseUser = new Base(user);
        Log.e(LOG + ".baseUser.success", "" + baseUser.isSuccess());
        Log.e(LOG + ".baseUser.email", "" + ((User) baseUser.getData()).getEmail());*/

        Base<User> baseUser = new Base<>(user);
        Log.e(LOG + ".baseUser.success", "" + baseUser.isSuccess());
        Log.e(LOG + ".baseUser.email", "" + baseUser.getData().getEmail());

        List<User> users = new ArrayList<>();
        User user2 = new User("benjamin.soto@email.com", "test123");
        users.add(user);
        users.add(user2);

        Base<List<User>> baseUsers = new Base<List<User>>(users);
        Log.e(LOG + ".baseUsers.success", "" + baseUsers.isSuccess());
        for (User userInTheList : baseUsers.getData()) {
            Log.e(LOG + ".baseUsers.success", "" + userInTheList.getEmail());
        }
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