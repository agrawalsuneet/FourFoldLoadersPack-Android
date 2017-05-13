package com.agrawalsuneet.fourfold;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.agrawalsuneet.fourfoldloader.FourFoldLoader;
import com.agrawalsuneet.fourfoldloader.FourFoldLoader2;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private FourFoldLoader loader;
    private FourFoldLoader2 loader2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();
    }

    private void initControls() {
        loader = (FourFoldLoader) findViewById(R.id.fourfoldloader);
        loader2 = (FourFoldLoader2)findViewById(R.id.loader2);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loader2.startAnimation();

                if (loader.isLoading()){
                    loader.stopAnimation();
                } else {
                    loader.startAnimation();
                }
            }
        });
    }
}
