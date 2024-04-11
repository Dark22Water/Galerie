package com.example.galerie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GridView gridView;
    private ImageAdapter imageAdapter;
    private ArrayList<String> imagePaths;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        gridView = view.findViewById(R.id.grid_view);
        imagePaths = loadImagesFromStorage(); // Implement this method to load image paths

        imageAdapter = new ImageAdapter(getContext(), imagePaths);
        gridView.setAdapter(imageAdapter);

        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            // Handle image click (open ImageViewFragment to display full-screen image)
            String selectedImagePath = imagePaths.get(position);
            ImageViewFragment imageViewFragment = ImageViewFragment.newInstance(selectedImagePath);
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container, imageViewFragment)
                    .addToBackStack(null)
                    .commit();
        });

        gridView.setOnItemLongClickListener((parent, view1, position, id) -> {
            // Handle long press (delete image)
            String imagePathToDelete = imagePaths.get(position);
            deleteImage(imagePathToDelete); // Implement this method to delete image
            imagePaths.remove(position);
            imageAdapter.notifyDataSetChanged();
            return true;
        });

        return view;
    }

    private ArrayList<String> loadImagesFromStorage() {
        // Implement this method to load image paths from internal storage or database
        // Return a list of image paths
        ArrayList<String> paths = new ArrayList<>();
        // Add logic to fetch image paths
        return paths;
    }

    private void deleteImage(String imagePath) {
        // Implement this method to delete image from internal storage or database
        // Use File or ContentResolver to delete the image
    }
}
