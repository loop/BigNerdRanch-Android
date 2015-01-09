package com.yogeshn.photogallery;

/**
 * Created by yogesh on 01/09/2014.
 */
public class GalleryItem {
    private String mCaption;
    private String mId;
    private String mUrl;

    public String getmOwner() {
        return mOwner;
    }

    public void setmOwner(String mOwner) {
        this.mOwner = mOwner;
    }

    private String mOwner;

    public String toString() {
        return mCaption;
    }

    public String getmCaption() {
        return mCaption;
    }

    public void setmCaption(String mCaption) {
        this.mCaption = mCaption;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getPhotoPageUrl() {
        return "http://www.flickr.com/photos/"+ mOwner + "/" + mId;
    }
}
