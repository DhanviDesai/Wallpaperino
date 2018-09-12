package com.example.user.kittenrecyclerview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class AddIcon extends AppCompatActivity {
    private static final int PRIMARY_KEY_IMAGE = 1;
    ImageView view;
    Uri ImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_icon);

        view = findViewById(R.id.iconimage);
    }

    public void OpenIcon(View view){
        Intent in = new Intent();
        in.setType("image/*");
        in.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(in,PRIMARY_KEY_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PRIMARY_KEY_IMAGE && resultCode == RESULT_OK && data != null
                  && data.getData() != null){
            ImageUri = data.getData();
            Bitmap bm = BitmapFactory.decodeFile(ImageUri.getPath());
            view.setImageBitmap(bm);
        }
    }
}
