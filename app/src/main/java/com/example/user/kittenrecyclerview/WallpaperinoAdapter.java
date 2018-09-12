package com.example.user.kittenrecyclerview;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WallpaperinoAdapter extends RecyclerView.Adapter<WallpaperinoAdapter.WallpaperViewHolder> {

    ItemClickListener mItemClickListener;

    public interface ItemClickListener{
        void onItemClick(int Position,ImageView imageView);
    }

    public void setmItemClickListener(ItemClickListener itm){
        mItemClickListener = itm;
    }

    private Context mContext;
    private ArrayList<WallPaperino> mWallpaperList;

    public WallpaperinoAdapter(Context mContext, ArrayList<WallPaperino> mWallpaperList) {
        this.mContext = mContext;
        this.mWallpaperList = mWallpaperList;
    }

    @NonNull
    @Override
    public WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_layout,parent,false);
        return new WallpaperViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperViewHolder holder, int position) {
        String ImageUrl;
        String Creatorname;
        int Likecount;

        ImageUrl = mWallpaperList.get(position).getImageUrl();
        Creatorname = mWallpaperList.get(position).getmCreatorname();
        Likecount = mWallpaperList.get(position).getmLikeCount();

        holder.tv1.setText(Creatorname);
        holder.tv2.setText("Likes: "+Likecount);
        Picasso.get().load(ImageUrl).centerCrop().fit().into(holder.im);

    }

    @Override
    public int getItemCount() {
        return mWallpaperList.size();
    }

    public class WallpaperViewHolder extends RecyclerView.ViewHolder{
        public ImageView im;
        public TextView tv1;
        public TextView tv2;

        public WallpaperViewHolder(final View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.ImageView);
            tv1 = itemView.findViewById(R.id.CreatorTextView);
            tv2 = itemView.findViewById(R.id.LikeTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mItemClickListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mItemClickListener.onItemClick(position,im);
                        }
                    }
                }
            });
        }
    }
}
