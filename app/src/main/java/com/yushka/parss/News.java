package com.yushka.parss;


import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable {
        private String mLinkImage;// Ссылки на новости
        private String mNameNews;// Название новости
        private String mLinkPageNews;// Ссылка на страницу

        public News(String mLinkImage, String mNameNews, String mLinkPageNews) {
            this.mLinkImage = mLinkImage;
            this.mNameNews = mNameNews;
            this.mLinkPageNews = mLinkPageNews;
        }

    protected News(Parcel in) {
        String[] data = new String[3];
        in.readStringArray(data);
        mLinkImage = data[0];
        mNameNews = data[1];
        mLinkPageNews = data[2];
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getmLinkImage() {
            return mLinkImage;
        }

        public void setmLinkImage(String mLinkImage) {
            this.mLinkImage = mLinkImage;
        }

        public String getmNameNews() {
            return mNameNews;
        }

        public void setmNameNews(String mNameNews) {
            this.mNameNews = mNameNews;
        }

        public String getmLinkPageNews() {
            return mLinkPageNews;
        }

        public void setmLinkPageNews(String mLinkPageNews) {
            this.mLinkPageNews = mLinkPageNews;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[] {mLinkImage, mNameNews, mLinkPageNews});

    }

}


