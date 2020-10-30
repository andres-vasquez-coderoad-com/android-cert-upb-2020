package bo.com.emprendeya.ui.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import bo.com.emprendeya.R;
import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.users.User;
import bo.com.emprendeya.utils.CompressImage;
import bo.com.emprendeya.viewModel.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {
    private static final String LOG = RegisterActivity.class.getName();

    private Context context;
    private RegisterViewModel registerViewModel;

    private TextInputEditText nameTextInputEditText;
    private TextInputEditText emailTextInputEditText;
    private TextInputEditText passwordTextInputEditText;
    private TextInputEditText passwordRepeatTextInputEditText;
    private Button selectPhotoButton;

    private ProgressDialog loading;

    private static final int RC_PERMISSIONS = 201;
    private static final int RC_GALLERY = 202;

    private Uri fileCoverPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.context = this;
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        initViews();
        initEvents();
    }

    private void initViews() {
        nameTextInputEditText = findViewById(R.id.nameTextInputEditText);
        emailTextInputEditText = findViewById(R.id.emailTextInputEditText);
        passwordTextInputEditText = findViewById(R.id.passwordTextInputEditText);
        passwordRepeatTextInputEditText = findViewById(R.id.passwordRepeatTextInputEditText);
        selectPhotoButton = findViewById(R.id.selectPhotoButton);
    }

    private void initEvents() {
        selectPhotoButton.setOnClickListener(view -> {
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
    }

    private void showLoading() {
        loading = new ProgressDialog(context);
        loading.setTitle("Cargando");
        loading.setCancelable(false);
        loading.show();
    }

    private void dismissLoading() {
        if (loading != null && loading.isShowing()) {
            loading.dismiss();
        }
    }

    public void registerUser(View view) {
        //TODO validaciones
        User user = new User(emailTextInputEditText.getText().toString(),
                passwordTextInputEditText.getText().toString());
        user.setDisplayName(nameTextInputEditText.getText().toString());

        showLoading();
        registerViewModel.register(user, fileCoverPhoto).observe(this, new Observer<Base<User>>() {
            @Override
            public void onChanged(Base<User> userBase) {
                dismissLoading();
                if (userBase.isSuccess()) {
                    RegisterActivity.this.finish();
                } else {
                    Toast.makeText(context, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
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