package com.example.user.kittenrecyclerview;

public class WallPaperino {

    private String ImageUrl;
    private String mCreatorname;
    private int mLikeCount;

    public WallPaperino(String imageUrl, String mCreatorname, int mLikeCount) {
        ImageUrl = imageUrl;
        this.mCreatorname = mCreatorname;
        this.mLikeCount = mLikeCount;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getmCreatorname() {
        return mCreatorname;
    }

    public int getmLikeCount() {
        return mLikeCount;
    }
}
