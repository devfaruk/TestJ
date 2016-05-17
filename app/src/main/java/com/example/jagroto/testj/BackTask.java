package com.example.jagroto.testj;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by F on 5/16/2016.
 */
public class BackTask extends AsyncTask<String,String,String> {
    Activity mainActivity;
    TextView textView;
    ProgressBar progressBar;
    List<Food>foodLivet;


    public BackTask(Activity activity){
        mainActivity =activity;
    }

    @Override
    protected void onPreExecute() {
        //progress bar show
         progressBar=(ProgressBar)mainActivity.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... strings) {
        String centen=getDam(strings[0]);
        return centen;
    }

    @Override
    protected void onPostExecute(String s) {
        progressBar.setVisibility(View.INVISIBLE);
        textView=(TextView)mainActivity.findViewById(R.id.text_v);
        foodLivet =JSonparse.foods(s);
        if (foodLivet !=null){
            for (Food foodItem:foodLivet){
                textView.append("Working !");
                textView.append("Item Name :" +foodItem.getName()+ "\n");
                textView.append("Item Price :" + foodItem.getPrice()+ "\n");
                textView.append("Item Description :" + foodItem.getDescription() +"\n\n\n");
            }
        }
    }

    private String getDam(String uri){
        BufferedReader reader;
        try {
            URL url=new URL(uri);
            HttpURLConnection connection =(HttpURLConnection)url.openConnection();
            StringBuilder stringBuilder =new StringBuilder();
            reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line=reader.readLine())!=null){
                stringBuilder.append(line+ "\n");
            }
            return stringBuilder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
