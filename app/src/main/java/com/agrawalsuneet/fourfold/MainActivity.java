package com.agrawalsuneet.fourfold;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.agrawalsuneet.fourfoldloader.FourFoldLoader;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private FourFoldLoader mainLoader, loader;

    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initControls();
        addViewProgrammatically();
    }

    private void initControls() {
        mainLoader = (FourFoldLoader)findViewById(R.id.main_fourfoldloader);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainLoader.isLoading()){
                    mainLoader.stopLoading();
                } else {
                    mainLoader.startLoading();
                }

                if (loader.isLoading()) {
                    loader.stopLoading();
                } else {
                    loader.startLoading();
                }
            }
        });


    }

    private void addViewProgrammatically() {
        container = (LinearLayout) findViewById(R.id.container);

        /*loader = new FourFoldLoader(this, 200,
                getResources().getColor(R.color.green),
                getResources().getColor(R.color.red),
                getResources().getColor(R.color.blue),
                getResources().getColor(R.color.colorAccent), true);*/

        loader = new FourFoldLoader(this, true);
        loader.setSquareLenght(200);
        loader.setFirstSquareColor(getResources().getColor(R.color.green));
        loader.setSecondSquareColor(getResources().getColor(R.color.green));
        loader.setThirdSquareColor(getResources().getColor(R.color.green));
        loader.setForthSquareColor(getResources().getColor(R.color.green));
        loader.setAnimationDuration(800);
        loader.setDisappearAnimationDurationr(200);

        container.addView(loader);
    }
}
