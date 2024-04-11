package com.example.galerie;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;

    public class ImageViewFragment extends Fragment {

        private static final String ARG_IMAGE_PATH = "imagePath";
        private String imagePath;

        public static ImageViewFragment newInstance(String imagePath) {
            ImageViewFragment fragment = new ImageViewFragment();
            Bundle args = new Bundle();
            args.putString(ARG_IMAGE_PATH, imagePath);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                imagePath = getArguments().getString(ARG_IMAGE_PATH);
            }
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_image_view, container, false);

            ImageView imageView = view.findViewById(R.id.image_view);
            Glide.with(requireContext())
                    .load(imagePath)
                    .into(imageView);

            return view;
        }
    }

