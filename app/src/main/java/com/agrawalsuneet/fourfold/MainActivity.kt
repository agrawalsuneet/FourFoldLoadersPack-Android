package com.agrawalsuneet.fourfold

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.LinearLayout

import com.agrawalsuneet.fourfoldloader.loaders.FourFoldLoader
import com.agrawalsuneet.fourfoldloader.loaders.WaveLoader
import com.agrawalsuneet.fourfoldloader.loaders.ZipZapLoader

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var fourFoldLoaderXML: FourFoldLoader
    private lateinit var fourfoldLoader: FourFoldLoader

    private lateinit var zipzapXML: ZipZapLoader
    private lateinit var zipzap: ZipZapLoader

    private lateinit var waveLoader: WaveLoader

    private var container: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2)

        container = findViewById(R.id.container) as LinearLayout

        initWaveLoader()

        //initZipZapLoader()
        //initControls();
        //initFourfoldLoader();
    }

    private fun initWaveLoader() {
        waveLoader = findViewById(R.id.waveLoader) as WaveLoader
    }

    /*override fun onResume() {
        super.onResume()
        //waveLoader.resetLoader()
    }*/

    private fun initControls() {

        button = findViewById(R.id.button) as Button

        button!!.setOnClickListener {
            /*if (zipzap!!.isLoading) {
                zipzap!!.stopLoading()
            } else {
                zipzap!!.startLoading()
            }

            if (zipzapXML!!.isLoading) {
                zipzapXML!!.stopLoading()
            } else {
                zipzapXML!!.startLoading()
            }*/

            if (fourFoldLoaderXML!!.isLoading) {
                fourFoldLoaderXML!!.stopLoading()
            } else {
                fourFoldLoaderXML!!.startLoading()
            }

            if (fourfoldLoader!!.isLoading) {
                fourfoldLoader!!.stopLoading()
            } else {
                fourfoldLoader!!.startLoading()
            }

        }
    }

    private fun initZipZapLoader() {
        //zipzapXML = findViewById(R.id.zipzap) as ZipZapLoader

        zipzap = ZipZapLoader(this, 120,
                ContextCompat.getColor(this, R.color.red),
                ContextCompat.getColor(this, R.color.red),
                ContextCompat.getColor(this, R.color.red),
                ContextCompat.getColor(this, R.color.red),
                false).apply {
            fromScale = 1.0f
            toScale = 0.8f
            animationDuration = 1000
        }

        container!!.addView(zipzap)
    }

    private fun initFourfoldLoader() {
        fourFoldLoaderXML = findViewById(R.id.main_fourfoldloader) as FourFoldLoader

        fourfoldLoader = FourFoldLoader(this, 200,
                resources.getColor(R.color.green),
                resources.getColor(R.color.red),
                resources.getColor(R.color.blue),
                resources.getColor(R.color.colorAccent), true)
                .apply {
                    animationDuration = 200
                    disappearAnimationDuration = 100
                }

        container!!.addView(fourfoldLoader)
    }
}
