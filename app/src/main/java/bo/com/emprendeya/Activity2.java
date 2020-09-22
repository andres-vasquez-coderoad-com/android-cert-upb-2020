package bo.com.emprendeya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class Activity2 extends AppCompatActivity {

    private static final String LOG = Activity2.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(LOG, "onCreate");

        setContentView(R.layout.activity_2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w(LOG, "onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.w(LOG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(LOG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(LOG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.w(LOG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(LOG, "onDestroy");
    }
}