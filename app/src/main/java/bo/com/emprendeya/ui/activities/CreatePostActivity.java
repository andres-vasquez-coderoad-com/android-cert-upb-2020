package bo.com.emprendeya.ui.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
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
import bo.com.emprendeya.utils.CompressImage;
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
    private EditText contentEditText;
    private Button cancelButton;
    private Button saveButton;

    private static final int RC_PERMISSIONS = 201;
    private static final int RC_GALLERY = 202;

    private Uri fileCoverPhoto;

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
        contentEditText = findViewById(R.id.contentEditText);
        cancelButton = findViewById(R.id.cancelButton);
        saveButton = findViewById(R.id.saveButton);
    }

    private void initEvents() {
        pickImageButton.setOnClickListener(view -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (hasPermissions()) {
                    openGallery();
                } else {
                    askPermissions();
                }
            } else {
                openGallery();
            }
        });

        cancelButton.setOnClickListener(view -> {
            finish();
        });

        saveButton.setOnClickListener(view -> {
            Post post = new Post();
            post.setTitle(postTitleTextInputEditText.getText().toString());
            post.setContent(contentEditText.getText().toString());
            post.setTimestamp(Calendar.getInstance().getTimeInMillis());

            if (!post.getTitle().isEmpty() && !post.getContent().isEmpty()) {

                viewModel.createPost(uuidStartup, post, fileCoverPhoto).observe(this, new Observer<Base<String>>() {
                    @Override
                    public void onChanged(Base<String> stringBase) {
                        if (stringBase.isSuccess()) {
                            Log.e(LOG, "createPost.isSuccess:" + stringBase.getData());
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

    private boolean hasPermissions() {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void askPermissions() {
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, RC_PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case RC_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                } else {
                    Toast.makeText(context, "Sin permisos", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.post_image)),
                RC_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == RC_GALLERY && data != null && data.getData() != null) {
                Uri image = data.getData();
                fileCoverPhoto = CompressImage.compressImage(image, context);
            }
        }
    }
}