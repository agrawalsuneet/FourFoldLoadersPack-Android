package com.agrawalsuneet.fourfold;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.agrawalsuneet.fourfoldloader.loaders.FourFoldLoader;
import com.agrawalsuneet.fourfoldloader.loaders.ZipZapLoader;

/**
 * Created by suneet on 10/28/17.
 */

public class MainActivityJava extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FourFoldLoader loader = new FourFoldLoader(this, 40,
                ContextCompat.getColor(this, R.color.red),
                ContextCompat.getColor(this, R.color.green),
                ContextCompat.getColor(this, R.color.blue),
                ContextCompat.getColor(this, R.color.colorAccent),
                true);

        loader.setDisappearAnimationDuration(100);
        loader.setAnimationDuration(500);

        ZipZapLoader zipZapLoader = new ZipZapLoader(this, 40,
                ContextCompat.getColor(this, R.color.red),
                ContextCompat.getColor(this, R.color.green),
                ContextCompat.getColor(this, R.color.blue),
                ContextCompat.getColor(this, R.color.colorAccent),
                true);

        zipZapLoader.setAnimationDuration(500);
        zipZapLoader.setFromScale(1.0f);
        zipZapLoader.setToScale(0.8f);
    }
}
