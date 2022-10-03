package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    private CircleImageView foto;
    private TextView fullname;
    private TextView email;
    private TextView about;
    private TextView homepage;
    private Button tombol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Bundle extra = getIntent().getExtras();
        byte[] byteArray = extra.getByteArray("img");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        foto = findViewById(R.id.image_profile);
        foto.setImageBitmap(bmp);
        String nama = getIntent().getExtras().getString("nama");
        String imel = getIntent().getExtras().getString("email");
        String tentang = getIntent().getExtras().getString("about");
        final String rumahpage = getIntent().getExtras().getString("homepage");
        fullname = findViewById(R.id.label_fullname);
        email = findViewById(R.id.label_email);
        about = findViewById(R.id.label_about);
        homepage = findViewById(R.id.label_homepage);
        fullname.setText(nama);
        email.setText(imel);
        about.setText(tentang);
        homepage.setText(rumahpage);
        tombol = findViewById(R.id.button_homepage);
        tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent website = new Intent();
                website.setAction(Intent.ACTION_VIEW);
                website.addCategory(Intent.CATEGORY_BROWSABLE);
                website.setData(Uri.parse(Uri.decode(rumahpage)));
                startActivity(website);
            }
        });
    }
}
