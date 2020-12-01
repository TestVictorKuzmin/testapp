package ru.any_test.anytest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

sealed class Category : Parcelable {

    abstract val name: String
    abstract var userData: List<String>?

    @Parcelize
    data class TestCategory(
        val id: Int,
        override val name: String,
        val image: String?,
        val descripion: String,
        val numTests: Int,
        override var userData: List<String>?
    ) : Category(), Parcelable

    @Parcelize
    data class Test(
        val id: Int,
        override val name: String,
        val image: String?,
        override var userData: List<String>?,
        val testsLaunched: Float,
        val testsSuccess: Float
    ) : Category(), Parcelable

    @Parcelize
    data class CheckBoxQuestion(
        val id: Int,
        override val name: String,
        val variants: ArrayList<String>,
        val answers: ArrayList<Int>,
        override var userData: List<String>?
    ): Category(), Parcelable

    @Parcelize
    data class RadioGroupQuestion(
        val id: Int,
        override val name: String,
        val variants: ArrayList<String>,
        val answer: Int,
        override var userData: List<String>?
    ): Category(), Parcelable

    @Parcelize
    data class Comment(
        override val name: String,
        val messageType: Int,
        override var userData: List<String>?
    ): Category(), Parcelable

    companion object {
        const val TYPE_FOREIGN_MESSAGE = 0
        const val TYPE_MY_MESSAGE = 1
    }
}