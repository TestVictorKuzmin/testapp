package ru.any_test.anytest.root

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class MainPagerAdapter constructor(activity: MainActivity, fragments: MutableList<Fragment>)
    : FragmentStateAdapter(activity) {

    val fragments = fragments

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments.get(position)
    }
}