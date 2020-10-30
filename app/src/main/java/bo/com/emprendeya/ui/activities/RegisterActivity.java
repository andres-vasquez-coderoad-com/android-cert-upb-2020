package bo.com.emprendeya.ui.activities;

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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import bo.com.emprendeya.R;
import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.users.User;
import bo.com.emprendeya.utils.Constants;
import bo.com.emprendeya.viewModel.LoginViewModel;
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

    private Uri image;
    private File fileImagen;
    public static final int REQUEST_CODE_ATTACH = 103;
    public static final int REQUEST_PERMISSIONS = 201;

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
        selectPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                    if (hasPermissions()) {
                        getPictureFromGallery();
                    } else {
                        askPermissions();
                    }
                } else {
                    getPictureFromGallery();
                }
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
        //TODO add validaciones
        User user = new User(emailTextInputEditText.getText().toString(),
                passwordTextInputEditText.getText().toString());
        user.setDisplayName(nameTextInputEditText.getText().toString());

        showLoading();
        registerViewModel.registerUser(user, image).observeForever(new Observer<Base<User>>() {
            @Override
            public void onChanged(Base<User> userBase) {
                dismissLoading();
                if (userBase.isSuccess()) {
                    RegisterActivity.this.finish();
                } else {
                    Toast.makeText(context, "Error registrando usuario", Toast.LENGTH_LONG).show();
                }
            }
        });
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
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);

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
                    image = Uri.fromFile(file);
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