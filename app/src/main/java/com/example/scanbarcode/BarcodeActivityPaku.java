package com.example.scanbarcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BarcodeActivityPaku extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_barcode_paku);
    }

    public void saveBarcodeToGallery(View view) {
        // Get the bitmap from ImageView
        ImageView barcodeImageView = findViewById(R.id.barcodeImageView);
        BitmapDrawable drawable = (BitmapDrawable) barcodeImageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        // Save bitmap to gallery
        String filename = "barcode_paku.jpg";
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), filename);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            Toast.makeText(this, "Barcode saved to gallery", Toast.LENGTH_SHORT).show();

            // Refresh gallery
            MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), null);
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save barcode: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
