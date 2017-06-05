package com.agrawalsuneet.fourfold;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

import com.agrawalsuneet.fourfold.dialog.FourFoldDialog;
import com.agrawalsuneet.fourfoldloader.FourFoldLoader;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private FourFoldLoader mainLoader, loader;

    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initControls();
        //addViewProgrammatically();
    }

    private void initControls() {
        /*mainLoader = (FourFoldLoader)findViewById(R.id.main_fourfoldloader);
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
        });*/


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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_dialog:
                showAlertDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAlertDialog() {
        FourFoldDialog dotsDialog = new FourFoldDialog.Builder(this)
                .setTextColor(R.color.white)
                .setMessage("Loading...")
                .setTextSize(24)
                .setFirstSquareColor(ContextCompat.getColor(this, R.color.white))
                .setSecondSquareColor(ContextCompat.getColor(this, R.color.white))
                .setThirdSquareColor(ContextCompat.getColor(this, R.color.white))
                .setForthSquareColor(ContextCompat.getColor(this, R.color.white))
                .setAnimDuration(800)
                .setFadeAnimDuration(200)
                .create();

        //dotsDialog.setCancelable(false);
        dotsDialog.show(getSupportFragmentManager(), "dotsDialog");
    }
}
