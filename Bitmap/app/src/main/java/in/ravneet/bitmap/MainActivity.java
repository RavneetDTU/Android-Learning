package in.ravneet.bitmap;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import id.zelory.compressor.Compressor;

public class MainActivity extends AppCompatActivity {
    public static final int RESULT_LOAD_IMG = 101;
    public static final String TAG = "false";
    ImageView iv_original;
    ImageView iv_modified;
    File actualImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        iv_original = (ImageView) findViewById(R.id.iv_originalImage);
        iv_modified = (ImageView) findViewById(R.id.iv_Lowsizeimage);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RESULT_LOAD_IMG && data != null) {
            ImageView original = (ImageView) findViewById(R.id.iv_originalImage);
            ImageView compressedImage = (ImageView) findViewById(R.id.iv_Lowsizeimage);
            try {
                actualImage = FileUtil.from(this, data.getData());
                original.setImageBitmap(BitmapFactory.decodeFile(actualImage.getAbsolutePath()));
//                actualSizeTextView.setText(String.format("Size : %s", getReadableFileSize(actualImage.length())));
                File compressedImageFile = new Compressor(this).compressToFile(actualImage);
                compressedImage.setImageBitmap(BitmapFactory.decodeFile(compressedImageFile.getAbsolutePath()));
            } catch (IOException e) {
                Toast.makeText(this, "Problem Occured", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}
