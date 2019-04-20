package com.yushka.parss;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListNewsActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private String MY_LOG = "my_Log";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);

        mRecyclerView  = (RecyclerView) findViewById(R.id.rv);

        ArrayList<News> mListNews = getIntent().getParcelableArrayListExtra("news");

       Log.d(MY_LOG, String.valueOf(mListNews.size()));

        GridLayoutManager manager;
        if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            manager= new GridLayoutManager(this,1);
        }else {
            manager = new GridLayoutManager(this,1);

        }


        mRecyclerView.setLayoutManager(manager);

        RVAdapter adapter = new RVAdapter(mListNews);
        mRecyclerView.setAdapter(adapter);

    }

    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.NewsViewHolder>{

        List<News> mNewss;
        public RVAdapter(List<News> newss){
            mNewss = newss;
        }

        @Override
        public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
            NewsViewHolder newsViewHolder = new NewsViewHolder(v);
            return newsViewHolder;
        }

        @Override
        public void onBindViewHolder(NewsViewHolder holder, int position) {

            holder.newsName.setText(mNewss.get(position).getmNameNews());
            holder.newsPageLink.setText(mNewss.get(position).getmLinkPageNews());

            Picasso.get().load(mNewss.get(position).getmLinkImage()).fit().centerCrop().into(holder.newsPhoto);

        }

        @Override
        public int getItemCount() {
            return mNewss.size();
        }

        public class NewsViewHolder extends RecyclerView.ViewHolder {

            CardView cv;
            ImageView newsPhoto;
            TextView newsName, newsPageLink;

            public NewsViewHolder(View itemView) {
                super(itemView);

                cv = itemView.findViewById(R.id.cv);
                newsPhoto= itemView.findViewById(R.id.imageNews);
                newsName = itemView.findViewById(R.id.nameNews);
                newsPageLink= itemView.findViewById(R.id.LinkPageNews);



            }
        }
    }

}
