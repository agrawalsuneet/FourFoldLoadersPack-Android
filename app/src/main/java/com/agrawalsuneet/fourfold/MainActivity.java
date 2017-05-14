package com.agrawalsuneet.fourfold;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.agrawalsuneet.fourfoldloader.FourFoldLoader;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private FourFoldLoader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();
    }

    private void initControls() {
        loader = (FourFoldLoader)findViewById(R.id.loader2);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loader.isLoading()){
                    loader.stopLoading();
                } else {
                    loader.startLoading();
                }

                Log.d("Suneet", Boolean.toString(loader.isLoading()));
            }
        });
    }
}
