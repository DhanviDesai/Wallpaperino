package com.example.user.kittenrecyclerview;

import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity {

    private String ImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ImageView iv = findViewById(R.id.image_view_second);
        FloatingActionButton fab = findViewById(R.id.download_button);

        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container),true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fade.excludeTarget(android.R.id.statusBarBackground,true);
            fade.excludeTarget(android.R.id.navigationBarBackground,true);
            getWindow().setEnterTransition(fade);
            getWindow().setExitTransition(fade);
        }

        ImageUrl = getIntent().getStringExtra("ImageUrl");
        Picasso.get().load(ImageUrl).centerCrop().fit().into(iv);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main2Activity.this, "Requested for a download", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
