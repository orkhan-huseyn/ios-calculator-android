package com.example.orkhan.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
//import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView link = (TextView)findViewById(R.id.textView2);
        final EditText edittext = (EditText)findViewById(R.id.editText);
        final Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String searchQuery = edittext.getText().toString();
                        final String urlToGo = getUrl(searchQuery);
                        link.post(new Runnable() {
                            @Override
                            public void run() {
                                link.setText(urlToGo);
                            }
                        });
                    }
                });
            }
        });
    }

    public String getUrl(String searchQuery){
        List<String> url_list = new ArrayList<String>();
        String wikiHow = "http://www.wikihow.com/wikiHowTo?search=";
        try {
            Document html = Jsoup.connect(wikiHow+searchQuery).get();
            Elements elements = html.getElementsByClass("result_link");
            for(Element e : elements) {
                url_list.add(e.attr("href"));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return url_list.get(0);
    }

    public List<String> crawl(String URL) {
        List<String> dataCollected = new ArrayList<String>();
        try {
            Document html = Jsoup.connect(URL).get();
            Elements elements = html.getElementsByClass("whb");
            for(Element element : elements) {
                dataCollected.add(element.text());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return dataCollected;
    }

    public boolean isConnected(){
        boolean connected = false;
        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(manager.getActiveNetworkInfo()!=null || manager.getActiveNetworkInfo().isConnected()) {
            connected = true;
        }
        else {
            connected = false;
        }
        return connected;
    }
}