package com.example.galerie;


import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.IOException;
import android.Manifest;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private static final int REQUEST_CAMERA_PERMISSION = 100;
    private boolean isCameraPermissionGranted = false;
    private Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // Check camera permission and request if not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            isCameraPermissionGranted = true;
            initializeCamera();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA_PERMISSION);
        }

        // Initialize surface view and holder for camera preview
        surfaceView = findViewById(R.id.surface_view);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
    }

    private void initializeCamera() {
        if (isCameraPermissionGranted) {
            try {
                camera = Camera.open(); // Open the rear-facing camera
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to open camera", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                isCameraPermissionGranted = true;
                initializeCamera();
            } else {
                Toast.makeText(this, "Camera permission is required to capture photos.", Toast.LENGTH_SHORT).show();
                // Handle camera permission denied (e.g., show error message or disable camera functionality)
            }
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        // Set camera preview display
        try {
            if (camera != null) {
                camera.setPreviewDisplay(holder);
                camera.startPreview(); // Start camera preview
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error setting up camera preview", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        // Update camera parameters when surface changes
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        // Release camera resources when surface is destroyed
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }
}
