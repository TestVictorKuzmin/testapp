package ru.any_test.anytest.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment

fun <T: Fragment> T.withParams(action: Bundle.() -> Unit) : T{
    return apply {
        val args = Bundle().apply(action)
        arguments = args
    }
}