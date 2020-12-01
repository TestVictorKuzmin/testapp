package ru.any_test.anytest.root

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import ru.any_test.anytest.R
import ru.any_test.anytest.contracts.ImmutableView
import ru.any_test.anytest.model.Category

class FragmentBlog : Fragment(), ImmutableView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_blog, null)

        return view
    }

    override fun onCategoryViewCreated(position: Int) {
        TODO("Not yet implemented")
    }

    override fun showCategories(categories: List<Category>) {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "Блог"
    }

    override fun showProgress() {
        TODO("Not yet implemented")
    }

    override fun updateCategories(categories: List<Category>) {
        TODO("Not yet implemented")
    }

    override fun hideProgress() {
        TODO("Not yet implemented")
    }

    companion object {
        @JvmStatic
        fun newInstance(): FragmentBlog {
            val blogFragment = FragmentBlog()

            return blogFragment
        }
    }
}