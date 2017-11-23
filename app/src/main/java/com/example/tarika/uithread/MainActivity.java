package com.example.tarika.uithread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //////------------------------แบบที่2 ---------------------------//////

    public void onClick(View view) {
        new  LoadImageTask().execute("https://food.mthai.com/app/uploads/2017/07/STEAMED-SQUID-with-LIME-SAUCE.jpg");
    }

    public class LoadImageTask extends AsyncTask<String,Integer, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... urls) {
            URL url = null;
            try {
                url = new URL(urls[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Bitmap bmp = null;
            try {
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }
        protected void onPostExecute(Bitmap result)  {

            ImageView imageView = (ImageView)findViewById(R.id.imageView);
            imageView.setImageBitmap(result);
        }
        protected void onProgressUpdate(Integer... values) {
            TextView update =(TextView)findViewById(R.id.Update);
            update.setText("5555");
        }
    }


    //////// ---------------แบบที่ 1---------------- ///////


    public void onClick2(View view) {
        new Thread(new Runnable() {
            public void run() {
                //Bitmap b = loadImageFromNetwork("http://example.com/image.png");
                //mImageView.setImageBitmap(b);
                // URL url = new URL("https://yt3.ggpht.com/-wG3D0ovspIU/AAAAAAAAAAI/AAAAAAAAAAA/5lQUWxCDZ5k/s100-c-k-no-mo-rj-c0xffffff/photo.jpg");
                //Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                //Bitmap b = loadImageFromNetwork("https://yt3.ggpht.com/-wG3D0ovspIU/AAAAAAAAAAI/AAAAAAAAAAA/5lQUWxCDZ5k/s100-c-k-no-mo-rj-c0xffffff/photo.jpg");
                //ImageView imageView = (ImageView)findViewById(R.id.imageView);
                //imageView.setImageBitmap(b);
                URL url = null;
                try {
                    //  url = new URL("https://yt3.ggpht.com/-wG3D0ovspIU/AAAAAAAAAAI/AAAAAAAAAAA/5lQUWxCDZ5k/s100-c-k-no-mo-rj-c0xffffff/photo.jpg");
                    //  url = new URL("http://www.araseoul.com/wp-content/uploads/20111103_top_calvinklein.jpg");
                    url =new URL("http://www.chillpainai.com/src/wewakeup/scoop/img_scoop/scoop/kang/travel/5cfooddelivery/sfff.jpg");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Bitmap bmp = null;
                try {
                    bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final ImageView imageView2 = (ImageView)findViewById(R.id.imageView2);
                final Bitmap finalBmp = bmp;
                imageView2.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView2.setImageBitmap(finalBmp);
                    }
                });

            }
        }).start();
    }



}






