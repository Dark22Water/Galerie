package com.example.galerie;

// GalleryActivity.java

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private GridView gridView;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        gridView = findViewById(R.id.grid_view);
        imageAdapter = new ImageAdapter(this, getImagePathsFromStorage());
        gridView.setAdapter(imageAdapter);

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String imagePath = imageAdapter.getItem(position);
                File imageFile = new File(imagePath);
                if (imageFile.exists()) {
                    boolean deleted = imageFile.delete();
                    if (deleted) {
                        // Remove the item from the adapter and refresh the grid view
                        imageAdapter.removeItem(position);
                        imageAdapter.notifyDataSetChanged(); // Notify adapter of data change
                        Toast.makeText(GalleryActivity.this, "Image deleted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(GalleryActivity.this, "Failed to delete image", Toast.LENGTH_SHORT).show();
                    }
                }
                return true; // Consume long click event
            }
        });


        // Handle regular item click to view image in ImageViewActivity
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String imagePath = imageAdapter.getItem(position);
                Intent intent = new Intent(GalleryActivity.this, ImageViewActivity.class);
                intent.putExtra("imagePath", imagePath);
                startActivity(intent);
            }
        });
    }

    private List<String> getImagePathsFromStorage() {
        List<String> imagePaths = new ArrayList<>();
        // Retrieve image paths from internal storage directory
        File directory = new File(getFilesDir(), "images");
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    imagePaths.add(file.getAbsolutePath());
                }
            }
        }
        return imagePaths;
    }
}
