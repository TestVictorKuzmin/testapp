package ru.any_test.anytest.root

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.toolbar_basic.*
import kotlinx.android.synthetic.main.toolbar_profile.*
import ru.any_test.anytest.App
import ru.any_test.anytest.R
import ru.any_test.anytest.answers.FragmentAnswerList
import ru.any_test.anytest.contracts.RootPresenter
import ru.any_test.anytest.contracts.RootView
import ru.any_test.anytest.profile.FragmentProfile
import ru.any_test.anytest.question.FragmentQuestion
import javax.inject.Inject
import javax.inject.Named


class MainActivity : AppCompatActivity(), RootView {

    @field:[Inject Named("rootPresenter")]
    internal lateinit var presenter: RootPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            App.instance?.getAppComponent()?.inject(this)
            presenter.onViewCreated(this)
        }

        //TODO логику с тулбар нужно п презентер перенести!
        toolbarBasic.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.item_profile -> {
                    toolbarBasic.visibility = View.GONE
                    toolbarProfile.visibility = View.VISIBLE
                    showProfile()
                    true
                }
                else -> false
            }
        }

        toolbarBasic.setNavigationOnClickListener {
            onBackPressed()
        }
        toolbarProfile.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun showProfile() {
        replaceFragment(
            FragmentProfile.newInstance(),
            ROOT,
            true)
    }

    override fun replaceFragment(fragment: Fragment, tag: String, inBackStack: Boolean) {
        if(supportFragmentManager.findFragmentByTag(tag) == null) {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            if(tag == ROOT) { //переход с профиля
                fragmentTransaction
                    .replace(R.id.placeHolderMain, fragment, tag)
                    .addToBackStack(tag)
                    .commit()
            }
            else {
                if(inBackStack) {
                    fragmentTransaction
                        .replace(R.id.placeHolderMain, fragment, tag)
                        .addToBackStack(tag)
                        .commit()
                }
                else {
                    fragmentTransaction
                        .replace(R.id.placeHolderMain, fragment)
                        .commit()
                }
            }
        }
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.placeHolderMain)
        if (supportFragmentManager.getBackStackEntryCount() > 0) {
            if(fragment is FragmentProfile) {
                toolbarProfile.visibility = View.GONE
                toolbarBasic.visibility = View.VISIBLE
            }
            if(fragment is FragmentAnswerList) {
                supportFragmentManager.popBackStack(VIEW_TEST, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
            else if(fragment is FragmentQuestion) {
                supportFragmentManager.popBackStack(VIEW_COMMENTS, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
            else {
                super.onBackPressed()
            }
        }
        else {
            super.onBackPressed()
        }
    }

    companion object {
        const val ROOT = "root"
        const val VIEW_PAGER = "fragmentPager"
        const val VIEW_TESTS_LIST = "fragmentTestsList"
        const val VIEW_COMMENTS = "fragmentComments"
        const val VIEW_TEST = "fragmentTest"
    }
}
