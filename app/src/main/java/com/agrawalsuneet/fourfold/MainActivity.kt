package com.agrawalsuneet.fourfold

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.LinearLayout
import com.agrawalsuneet.fourfoldloader.loaders.FourFoldLoader

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var fourFoldLoaderXML: FourFoldLoader
    private lateinit var fourfoldLoader: FourFoldLoader

    private lateinit var container: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.container) as LinearLayout

        supportActionBar!!.title = "FourFoldLoader"

        //initControls();
        //initFourfoldLoader();
    }

    private fun initControls() {

        /*button = findViewById(R.id.button) as Button

        button.setOnClickListener {
            if (fourFoldLoaderXML.isLoading) {
                fourFoldLoaderXML.stopLoading()
            } else {
                fourFoldLoaderXML.startLoading()
            }

            if (fourfoldLoader.isLoading) {
                fourfoldLoader.stopLoading()
            } else {
                fourfoldLoader.startLoading()
            }

        }*/
    }

    private fun initFourfoldLoader() {
        fourFoldLoaderXML = findViewById(R.id.main_fourfoldloader) as FourFoldLoader

        fourfoldLoader = FourFoldLoader(this, 200,
                ContextCompat.getColor(baseContext, R.color.green),
                ContextCompat.getColor(baseContext, R.color.red),
                ContextCompat.getColor(baseContext, R.color.blue),
                ContextCompat.getColor(baseContext, R.color.colorAccent), true)
                .apply {
                    animationDuration = 200
                    disappearAnimationDuration = 100
                }

        container.addView(fourfoldLoader)
    }
}
