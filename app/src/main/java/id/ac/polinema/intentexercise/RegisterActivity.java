package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {
    public static final int camera_code = 69;
    private Button btn;
    private ImageView edit;
    private CircleImageView gambar;
    private EditText fullname;
    private EditText email;
    private EditText homepage;
    private EditText about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullname = findViewById(R.id.text_fullname);
        email = findViewById(R.id.text_email);
        homepage = findViewById(R.id.text_homepage);
        about = findViewById(R.id.text_about);
        gambar = findViewById(R.id.image_profile);
        edit = findViewById(R.id.imageView);
        btn = findViewById(R.id.button_ok);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gambar.buildDrawingCache();
                Bitmap bmp = ((BitmapDrawable)gambar.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 50, baos);
                byte[] byteArray = baos.toByteArray();
                Intent ganti = new Intent(RegisterActivity.this, ProfileActivity.class);
                ganti.putExtra("img", byteArray);
                ganti.putExtra("nama", fullname.getText().toString());
                ganti.putExtra("email", email.getText().toString());
                ganti.putExtra("about", about.getText().toString());
                ganti.putExtra("homepage", homepage.getText().toString());
                startActivity(ganti);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent foto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(foto, camera_code);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == camera_code){
                Bitmap cameraimage = (Bitmap) data.getExtras().get("data");
                gambar.setImageBitmap(cameraimage);
            }
        }
    }
}
