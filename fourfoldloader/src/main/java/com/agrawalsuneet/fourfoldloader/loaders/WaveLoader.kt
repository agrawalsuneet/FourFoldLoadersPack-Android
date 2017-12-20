package com.agrawalsuneet.fourfoldloader.loaders

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.agrawalsuneet.fourfoldloader.basicviews.LoaderContract

/**
 * Created by suneet on 12/20/17.
 */
class WaveLoader : LinearLayout, LoaderContract {


    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttributes(attrs)
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttributes(attrs)
        initView()
    }

    override fun initAttributes(attrs: AttributeSet) {

    }

    private fun initView() {

    }
}