package com.example.galerie;



import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.navigation_gallery:
                            selectedFragment = new GalleryFragment();
                            break;
                        case R.id.navigation_camera:
                            selectedFragment = new CameraFragment();
                            break;
                        case R.id.navigation_image_view:
                            selectedFragment = new ImageViewFragment();
                            break;
                    }

                    if (selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, selectedFragment)
                                .commit();
                        return true;
                    }

                    return false;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        // Set default fragment when activity is created
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new GalleryFragment())
                .commit();
    }
}
