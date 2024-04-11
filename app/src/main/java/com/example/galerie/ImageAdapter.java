package com.example.galerie;

// ImageAdapter.java

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.List;

import com.bumptech.glide.Glide;

// ImageAdapter.java

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private List<String> imagePaths;

    public ImageAdapter(Context context, List<String> imagePaths) {
        this.context = context;
        this.imagePaths = imagePaths;
    }

    @Override
    public int getCount() {
        return imagePaths.size();
    }

    @Override
    public String getItem(int position) {
        return imagePaths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300)); // Adjust image size as needed
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        // Load image into ImageView using Glide
        String imagePath = getItem(position);
        Glide.with(context)
                .load(new File(imagePath))
                .placeholder(R.drawable.placeholder_image) // Placeholder image while loading
                .error(R.drawable.error_image) // Error image if loading fails
                .into(imageView);

        return imageView;
    }

    // Method to remove an item from the imagePaths list
    public void removeItem(int position) {
        if (position >= 0 && position < imagePaths.size()) {
            imagePaths.remove(position);
        }
    }
}

