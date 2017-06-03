package com.agrawalsuneet.fourfold;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.agrawalsuneet.fourfoldloader.FourFoldLoader;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private FourFoldLoader loader;

    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initControls();
        addViewProgrammatically();
    }

    private void initControls() {
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loader.isLoading()){
                    loader.stopLoading();
                } else {
                    loader.startLoading();
                }
            }
        });

        //loader = (FourFoldLoader)findViewById(R.id.fourfoldloader);
    }

    private void addViewProgrammatically(){
        container = (LinearLayout) findViewById(R.id.container);

        loader = new FourFoldLoader(this);
        container.addView(loader);
        loader.startLoading();
    }
}
