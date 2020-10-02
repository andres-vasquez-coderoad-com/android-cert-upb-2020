package bo.com.emprendeya.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import bo.com.emprendeya.R;
import bo.com.emprendeya.utils.Constants;

public class StartupListActivity extends AppCompatActivity {

    private static final String LOG = StartupListActivity.class.getName();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(LOG, "onCreate");

        setContentView(R.layout.activity_startup_list);
        context = this;

        initViews();
        initEvents();
        getIntentValues();
    }

    private void initViews() {

    }

    private void initEvents() {

    }

    private void getIntentValues() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_DISPLAY_NAME)) {
            String displayName = intent.getStringExtra(Constants.KEY_DISPLAY_NAME);
            Toast.makeText(context, displayName, Toast.LENGTH_SHORT).show();
        }
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