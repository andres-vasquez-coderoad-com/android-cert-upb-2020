package bo.com.emprendeya.ui.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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

    private File fileImagen;
    public static final int REQUEST_CODE_ATTACH = 103;
    public static final int REQUEST_PERMISSIONS = 201;

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
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                if (hasPermissions()) {
                    getPictureFromGallery();
                } else {
                    askPermissions();
                }
            } else {
                getPictureFromGallery();
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

            Uri fileUri = Uri.fromFile(fileImagen);
            if (!post.getTitle().isEmpty() && !post.getContent().isEmpty()) {
                viewModel.createPost(uuidStartup, post, fileUri).observe(this, new Observer<Base<String>>() {
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

    private boolean hasPermissions() {
        return ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void askPermissions() {
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSIONS:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getPictureFromGallery();
                } else {
                    Toast.makeText(context, "Sin permisos", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    private void getPictureFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, context.getResources().getString(R.string.post_image)),
                REQUEST_CODE_ATTACH);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_ATTACH && data != null && data.getData() != null) {
                Uri uri = data.getData();
                FileOutputStream out;
                String strPhotoName = "post_" + Calendar.getInstance().getTimeInMillis() + ".jpg";

                try {
                    //Get bitmap from the camera
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                    //Save in files
                    File folder = new File(Environment.getExternalStorageDirectory(),
                            Constants.DIRECTORY_NAME);
                    if (!folder.exists()) {
                        folder.mkdirs();
                    }

                    File file = new File(Environment.getExternalStorageDirectory(),
                            Constants.DIRECTORY_NAME + File.separator + strPhotoName);
                    out = new FileOutputStream(file);

                    //Matrix to compress the image
                    Matrix m = new Matrix();
                    m.setRectToRect(new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight()), new RectF(0, 0, 640, 480), Matrix.ScaleToFit.CENTER);
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

                    out.flush();
                    out.close();

                    fileImagen = file;
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(LOG, "IOException: " + e.getMessage());
                }
            }
        } else {
            Toast.makeText(this, "Error al obtener imagen.", Toast.LENGTH_SHORT).show();
        }
    }
}