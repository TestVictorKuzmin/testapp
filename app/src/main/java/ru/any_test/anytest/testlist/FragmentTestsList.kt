package ru.any_test.anytest.testlist

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.fragment_categories.view.*
import kotlinx.android.synthetic.main.fragment_tests_list.*
import kotlinx.android.synthetic.main.fragment_tests_list.view.*
import ru.any_test.anytest.App
import ru.any_test.anytest.R
import ru.any_test.anytest.categories.FragmentCategoriesList
import ru.any_test.anytest.contracts.CategoriesPresenter
import ru.any_test.anytest.contracts.ImmutableView
import ru.any_test.anytest.model.Category
import ru.any_test.anytest.question.FragmentQuestion
import ru.any_test.anytest.root.MainActivity
import javax.inject.Inject
import javax.inject.Named
import kotlin.random.Random

class FragmentTestsList : Fragment(), ImmutableView {

    private var position = 0
    var progressBar: View? = null

    @field:[Inject Named("testsAdapter")]
    lateinit var testsAdapter: TestsAdapter

    @field:[Inject Named("testsPresenter")]
    lateinit var presenter: CategoriesPresenter

    private var listState: Parcelable? = null

    override fun onCategoryViewCreated(position: Int) {
        this.position = position
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance?.initTestsComponent(context as MainActivity, this)?.inject(this)
    }

    override fun showCategories(categories: List<Category>) {
        testsAdapter.setCategories(categories)
        testsAdapter.setOnItemClickListener(object: TestsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                presenter.onItemWasClicked(position)
            }
        })
        testsAdapter.notifyDataSetChanged()
        recyclerViewTests.layoutManager?.onRestoreInstanceState(listState)
    }

    override fun updateCategories(categories: List<Category>) {
        TODO("Not yet implemented")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tests_list, container, false)

        progressBar = inflater.inflate(R.layout.progress, view.testListContainer, false)
        view.testListContainer?.addView(progressBar)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable<Parcelable>(LAYOUT_STATE)
//            position = savedInstanceState.getInt(CURRENT_POSITION)
            position = -1
        }

        presenter.onPresenterCreated(position)

        recyclerViewTests.setHasFixedSize(true)
        recyclerViewTests.setLayoutManager(LinearLayoutManager(context))
        recyclerViewTests.setAdapter(testsAdapter)
    }

    override fun showProgress() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar?.visibility = View.GONE
    }

    override fun toString(): String {
        return "Тесты"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(recyclerViewTests != null) {
            listState = recyclerViewTests.layoutManager?.onSaveInstanceState()
            outState.putParcelable(LAYOUT_STATE, listState)
            outState.putInt(CURRENT_POSITION, position)
            Log.d("anr", "onSaveInstanceState = $position")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): FragmentTestsList {
            val mainFragment = FragmentTestsList()

            return mainFragment
        }
        const val LAYOUT_STATE = "layoutState"
        const val CURRENT_POSITION = "currentPosition"
    }
}