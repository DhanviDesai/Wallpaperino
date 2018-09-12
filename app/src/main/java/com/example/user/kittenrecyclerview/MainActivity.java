package com.example.user.kittenrecyclerview;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements WallpaperinoAdapter.ItemClickListener {

    RequestQueue mRequestQueue;
    RecyclerView mRecyclerView;
    WallpaperinoAdapter mWallpaperinoAdapter;
    ArrayList<WallPaperino> mWallpaperList;
    ConstraintLayout layout;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.layout);

        TransitionManager.beginDelayedTransition(layout);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.hasFixedSize();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mWallpaperList = new ArrayList<>();

        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container),true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fade.excludeTarget(android.R.id.statusBarBackground,true);
            fade.excludeTarget(android.R.id.navigationBarBackground,true);
            getWindow().setEnterTransition(fade);
            getWindow().setExitTransition(fade);
        }


        mRequestQueue = Volley.newRequestQueue(this);
        getJsonData();

    }

    private void getJsonData(){
        String url = "https://pixabay.com/api/?key=9630474-2d9e8f7238286792060d3e49b&q=nature+landscape&per_page=50";
       JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
           @Override
           public void onResponse(JSONObject response) {
               try {
                   JSONArray array = response.getJSONArray("hits");
                   for(int i=0;i<array.length();i++){
                       JSONObject obj = array.getJSONObject(i);
                       String ImageUrl = obj.getString("webformatURL");
                       String CreatorName = obj.getString("user");
                       int LikeCount = obj.getInt("likes");
                       mWallpaperList.add(new WallPaperino(ImageUrl,CreatorName,LikeCount));
                   }
               } catch (JSONException e) {
                   e.printStackTrace();
               }

               mWallpaperinoAdapter = new WallpaperinoAdapter(MainActivity.this,mWallpaperList);
               mRecyclerView.setAdapter(mWallpaperinoAdapter);
               mWallpaperinoAdapter.setmItemClickListener(MainActivity.this);

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               error.printStackTrace();

           }
       });

       mRequestQueue.add(request);
    }


    @Override
    public void onItemClick(int Position, ImageView imageView) {
        Intent in = new Intent(MainActivity.this,Main2Activity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,imageView, ViewCompat.getTransitionName(imageView));
        in.putExtra("ImageUrl",mWallpaperList.get(Position).getImageUrl());
        startActivity(in,options.toBundle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_contents,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.addIcon){
            Intent in = new Intent(this,AddIcon.class);
            startActivity(in);

        }
        else{
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
