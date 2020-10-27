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
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import bo.com.emprendeya.R;
import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.Post;
import bo.com.emprendeya.utils.Constants;
import bo.com.emprendeya.utils.ErrorMapper;
import bo.com.emprendeya.viewModel.CreatePostViewModel;
import bo.com.emprendeya.viewModel.LoginViewModel;

public class CreatePostActivity extends AppCompatActivity {
    private static final String LOG = CreatePostActivity.class.getSimpleName();

    private CreatePostViewModel viewModel;
    private Context context;
    private String uuidStartup;

    private TextInputLayout postTitleTextInputLayout;
    private TextInputEditText postTitleTextInputEditText;
    private Button pickImageButton;
    private EditText photoUrlEditTExt;
    private EditText contentEditText;
    private Button cancelButton;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        context = this;
        viewModel = new ViewModelProvider(this).get(CreatePostViewModel.class);

        initViews();
        initEvents();
        getIntentValues();
    }

    private void initViews() {
        postTitleTextInputLayout = findViewById(R.id.postTitleTextInputLayout);
        postTitleTextInputEditText = findViewById(R.id.postTitleTextInputEditText);
        pickImageButton = findViewById(R.id.pickImageButton);
        photoUrlEditTExt = findViewById(R.id.photoUrlEditTExt);
        contentEditText = findViewById(R.id.contentEditText);
        cancelButton = findViewById(R.id.cancelButton);
        saveButton = findViewById(R.id.saveButton);
    }

    private void initEvents() {
        pickImageButton.setOnClickListener(view -> {
            //TODO
        });

        cancelButton.setOnClickListener(view -> {
            finish();
        });

        saveButton.setOnClickListener(view -> {
            Post post = new Post();
            post.setTitle(postTitleTextInputEditText.getText().toString());
            post.setContent(contentEditText.getText().toString());

            //TODO temp
            post.setCoverPhoto(photoUrlEditTExt.getText().toString());
            post.setTimestamp(Calendar.getInstance().getTimeInMillis());

            if (!post.getTitle().isEmpty() && !post.getContent().isEmpty() &&
                    !post.getCoverPhoto().isEmpty()) {

                viewModel.createPost(uuidStartup, post, null).observe(this, new Observer<Base<String>>() {
                    @Override
                    public void onChanged(Base<String> stringBase) {
                        if (stringBase.isSuccess()) {
                            Log.e(LOG, "createPost.isSuccess");
                        } else {
                            Log.e(LOG, "createPost.error", stringBase.getException());
                        }
                    }
                });
            } else {
                Toast.makeText(context, context.getString(R.string.error_fill_values),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getIntentValues() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_STARTUP_UUID_SELECTED)) {
            uuidStartup = intent.getStringExtra(Constants.KEY_STARTUP_UUID_SELECTED);
        }
    }
}