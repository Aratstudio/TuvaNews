package com.yushka.parss;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private String MY_LOG = "myLog";
    private List<News> mListNews = new ArrayList<>();
    Toast toast = Toast.makeText(getApplicationContext(),"Пора покормить кота!", Toast.LENGTH_SHORT).toast.show();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ParseAllNews().execute();

    }

//---------------------------------------------------------
//    @Override
//    protected void onScrollChanged(int l, int t, int oldl, int oldt)
//    {
//        // Grab the last child placed in the ScrollView, we need it to determinate the bottom position.
//        View view = (View) getChildAt(getChildCount()-1);
//
//        // Calculate the scrolldiff
//        int diff = (view.getBottom()-(getHeight()+getScrollY()));
//
//        // if diff is zero, then the bottom has been reached
//        if( diff == 0 )
//        {
//            // notify that we have reached the bottom
//            Log.d(ScrollTest.LOG_TAG, "MyScrollView: Bottom has been reached" );
//        }
//
//        super.onScrollChanged(l, t, oldl, oldt);
//    }
//---------------------------------------------------------
//
//    class FFF{
//
//    }




//
//  //  ScrollView scroll = new ScrollView(){     //context - то что должно быть внутри скрола
//        @Override
//        protected void onScrollChanged(int l, int t, int oldl, int oldt)
//        {
//            ScrollView PageContScroll = (ScrollView) findViewById(R.id.PageContScroll);
//            PageContScroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//
//            @Override
//            public void onScrollChanged() {
//           //     if(HaveMorePost == true) {
//
//
//
//
//                    ScrollView PageContScroll = (ScrollView) findViewById(R.id.PageContScroll);
//                    LinearLayout HeadTitLay = (LinearLayout) findViewById(R.id.HeadTitLay);
//                    LinearLayout PageCont = (LinearLayout) findViewById(R.id.PageCont);
//                    int scrollY = PageContScroll.getScrollY();
//                    int diff = PageCont.getBottom() - (PageContScroll.getHeight() + scrollY + HeadTitLay.getHeight());
//                try {
//
//
//                    if (diff <= 0) {
//                        Toast toast = Toast.makeText(getApplicationContext(), "Работает!!!", Toast.LENGTH_SHORT);
//                        toast.show();
//                    }
//                }catch (java.lang.NullPointerException err){
//                    Log.d(MY_LOG, "fuk");
//                }
//
//                //}
//
//            }
//
//        });
//        }
//   // };













    class ParseAllNews extends AsyncTask <Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            String urlHome = "https://www.tuvaonline.ru/society";
            try {
                org.jsoup.nodes.Document doc = (org.jsoup.nodes.Document) Jsoup.connect(urlHome).get();
                Elements els = doc.select("div[id=dle-content]"); // тут у меня сомнения, мне кажется не то выбрал
               // String LastPage = els.select("div[class=navigation]>a").text();// тут у меня сомнения, мне кажется не то выбрал

              //int count = Integer.parseInt(LastPage.replace("https://www.tuvaonline.ru/society/page", ""));// Вот тут застрял, ошибка


                for (int i= 1; i <=2; i++){
                    String url = "https://www.tuvaonline.ru/society/page/" +i+"/";
                   dleContent(url);


                }


            }







            catch (IOException e) {
                e.printStackTrace();
            }

            return null;


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Intent intent = new Intent(MainActivity.this, ListNewsActivity.class);
            intent.putParcelableArrayListExtra("news", (ArrayList<? extends Parcelable>) mListNews);
            startActivity(intent);

           finish();

        }
// до сюда еще не добрался!!   т.е он такой же
private void dleContent(String url){
    try {
        String imgNews, nameNews, linkPageNewsl;
        Document doc = Jsoup.connect(url).get();
        Elements els = doc.select("div[class=news_item]>table");

        for (Element el: els){
            imgNews = el.select("img").text();





//            String co = imgNews.replace("https://www.tuvaonline.ru/uploads/posts/2018-12/1544345634_mevpycaayy1gut4akebax0cr585angdn.jpg", "");

            // String LastPage = els.select("div[class=navigation]>a").text();// тут у меня сомнения, мне кажется не то выбрал

            //int count = Integer.parseInt(LastPage.replace("https://www.tuvaonline.ru/society/page", ""));// Вот тут застрял, ошибка

//            imgNews=el.select("img")("src=https://www.tuvaonline.ru/uploads/posts/2018-12/1544345634_mevpycaayy1gut4akebax0cr585angdn.jpg");
//            Log.d(MY_LOG, imgNews+1);




            nameNews = el.select("h2>a").text();
            linkPageNewsl =el.select("h2>a").attr("href");

            Log.d(MY_LOG, imgNews+ " "+nameNews+ " "+ linkPageNewsl);

            mListNews.add(new News(imgNews, nameNews, linkPageNewsl));

        }

    } catch (IOException e) {
        e.printStackTrace();
    }

}

    }
}


