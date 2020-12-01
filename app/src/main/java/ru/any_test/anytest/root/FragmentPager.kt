package ru.any_test.anytest.root

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_pager.*
import kotlinx.android.synthetic.main.fragment_pager.view.*
import kotlinx.android.synthetic.main.toolbar_basic.*
import ru.any_test.anytest.App
import ru.any_test.anytest.R
import javax.inject.Inject

class FragmentPager : Fragment(){

    @Inject
    lateinit var pagerAdapter: MainPagerAdapter
    lateinit var viewPager: ViewPager2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //TabLayoutMediator согласно докам Гугла в onViewCreated нужно делать!
        //Но не очень правильно отображать названия вкладок вызывая метода адаптера createFragment.
        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            tab.text = "${pagerAdapter.createFragment(position)}"
            viewPager.setCurrentItem(tab.position, true)
        }.attach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pager, container, false)

        App.instance?.initPagerComponent(context as MainActivity)?.inject(this)
        viewPager = view.viewPager
        viewPager.setAdapter (pagerAdapter)

        return view
    }

    companion object {
        fun newInstance(): FragmentPager {
            val mainFragment = FragmentPager()
            return mainFragment
        }
    }
}