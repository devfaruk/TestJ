package com.example.jagroto.testj;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    Button loadJ;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadJ =(Button)findViewById(R.id.btn_json);
        progressBar =(ProgressBar)findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);
        loadJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isline()){
                    BackTask task =new BackTask(MainActivity.this);
                    task.execute("http://jakir.me/files/breakfast_menu.json");
                }

            }
        });
    }
    private boolean isline(){
        ConnectivityManager connectivityManager =(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
        if (networkInfo !=null && networkInfo.isConnectedOrConnecting()){
            return true;
        }else return false;
    }
}
