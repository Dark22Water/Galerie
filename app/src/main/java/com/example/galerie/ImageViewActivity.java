package com.example.galerie;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.File;

public class ImageViewActivity extends AppCompatActivity {

    private ImageView imageView;
    private EditText commentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        imageView = findViewById(R.id.image_view);
        commentEditText = findViewById(R.id.et_comment);

        String imagePath = getIntent().getStringExtra("imagePath");
        if (imagePath != null) {
            Glide.with(this)
                    .load(new File(imagePath))
                    .into(imageView);
        }
    }

    public void saveComment(View view) {
        String comment = commentEditText.getText().toString();
        // Implement saving the comment associated with the image
        // You can use SharedPreferences, SQLite database, or any other storage mechanism
        Toast.makeText(this, "Comment saved: " + comment, Toast.LENGTH_SHORT).show();
    }
}
