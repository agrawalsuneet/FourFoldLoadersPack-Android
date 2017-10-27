package com.agrawalsuneet.fourfold

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout

import com.agrawalsuneet.fourfold.dialog.FourFoldDialog
import com.agrawalsuneet.fourfoldloader.loaders.FourFoldLoader
import com.agrawalsuneet.fourfoldloader.loaders.ZipZapLoader

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var fourFoldLoaderXML: FourFoldLoader
    private lateinit var fourfoldLoader: FourFoldLoader

    private lateinit var zipzapXML: ZipZapLoader
    private lateinit var zipzap: ZipZapLoader

    private var container: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.container) as LinearLayout

        //initZipZapLoader()
        initControls();
        initFourfoldLoader();

    }


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
                    animationDuration = 2000
                    disappearAnimationDuration = 1000
                }

        container!!.addView(fourfoldLoader)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.show_dialog -> {
                showAlertDialog()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showAlertDialog() {
        val dotsDialog = FourFoldDialog.Builder(this)
                .setTextColor(R.color.white)
                .setMessage("Loading...")
                .setTextSize(24f)
                .setFirstSquareColor(ContextCompat.getColor(this, R.color.white))
                .setSecondSquareColor(ContextCompat.getColor(this, R.color.white))
                .setThirdSquareColor(ContextCompat.getColor(this, R.color.white))
                .setForthSquareColor(ContextCompat.getColor(this, R.color.white))
                .setAnimDuration(800)
                .setFadeAnimDuration(200)
                .create()

        //dotsDialog.setCancelable(false);
        dotsDialog.show(supportFragmentManager, "dotsDialog")
    }
}
