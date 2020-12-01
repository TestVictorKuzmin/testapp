package ru.any_test.anytest.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import ru.any_test.anytest.R

class Images {

    private val images = HashMap<String, Drawable?>()

    val context: Context

    constructor(context: Context) {
        this.context = context
        setImages()
    }

    private fun setImages() {
        images.put("ic_default", ContextCompat.getDrawable(context, R.drawable.ic_default))
        images.put("ic_biologiya", ContextCompat.getDrawable(context, R.drawable.ic_biologiya))
        images.put("ic_bjd", ContextCompat.getDrawable(context, R.drawable.ic_bjd))
        images.put("ic_chop_chastnaya_ohrana", ContextCompat.getDrawable(context, R.drawable.ic_chop_chastnaya_ohrana))
        images.put("ic_ekonomika", ContextCompat.getDrawable(context, R.drawable.ic_ekonomika))
        images.put("ic_estestvoznanie", ContextCompat.getDrawable(context, R.drawable.ic_estestvoznanie))
        images.put("ic_etika", ContextCompat.getDrawable(context, R.drawable.ic_etika))
        images.put("ic_filosofiya", ContextCompat.getDrawable(context, R.drawable.ic_filosofiya))
        images.put("ic_informatika", ContextCompat.getDrawable(context, R.drawable.ic_informatika))
        images.put("ic_logika", ContextCompat.getDrawable(context, R.drawable.ic_logika))
        images.put("ic_marketing", ContextCompat.getDrawable(context, R.drawable.ic_marketing))
        images.put("ic_mejdunarodnie_otnosheniya", ContextCompat.getDrawable(context, R.drawable.ic_mejdunarodnie_otnosheniya))
        images.put("ic_menedjment", ContextCompat.getDrawable(context, R.drawable.ic_menedjment))
        images.put("ic_politika", ContextCompat.getDrawable(context, R.drawable.ic_politika))
        images.put("ic_urisprudenciya", ContextCompat.getDrawable(context, R.drawable.ic_urisprudenciya))
    }

    fun getImage(imageName : String) : Drawable? {
        return images.get(imageName)
    }
}