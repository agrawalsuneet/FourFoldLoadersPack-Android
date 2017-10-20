package com.agrawalsuneet.fourfoldloader.loaders

import android.content.Context
import android.util.AttributeSet
import com.agrawalsuneet.fourfoldloader.basicviews.FourSquaresBaseLayout

/**
 * Created by suneet on 10/20/17.
 */
open class ZipZapLoader : FourSquaresBaseLayout {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, squareColor: Int, length: Int) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttributes(attrs!!)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttributes(attrs!!)
    }

    override fun initAttributes(attrs: AttributeSet) {
    }

    override fun startLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}