package ru.any_test.anytest.comments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.fragment_comments.*
import kotlinx.android.synthetic.main.fragment_comments.view.*
import ru.any_test.anytest.App
import ru.any_test.anytest.R
import ru.any_test.anytest.contracts.CategoriesPresenter
import ru.any_test.anytest.contracts.ImmutableView
import ru.any_test.anytest.contracts.MutableView
import ru.any_test.anytest.contracts.TestPresenter
import ru.any_test.anytest.model.Category
import ru.any_test.anytest.root.MainActivity
import ru.any_test.anytest.testlist.FragmentTestsList
import javax.inject.Inject
import javax.inject.Named

class FragmentComments : Fragment(), MutableView {

    private var testId = 1

    lateinit var recyclerViewComments: RecyclerView
    lateinit var comments: List<Category>
    private var testTitle = ""

    @field:[Inject Named("commentsAdapter")]
    lateinit var commentsAdapter: CommentsAdapter

    @field:[Inject Named("commentsPresenter")]
    lateinit var presenter: TestPresenter

    override fun onCategoryViewCreated(position: Int, title: String ) {
        testTitle = title
        this.testId = position
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState != null) {
            testId = savedInstanceState.getInt(TEST_ID)
            testTitle = savedInstanceState.getString(TEST_TITLE).toString()
        }
        App.instance?.initCommentsComponent(context as MainActivity, this)?.inject(this)
    }

    override fun showCategories(categories: List<Category>) {
        comments = categories
        commentsAdapter.setCategories(comments)
        commentsAdapter.notifyDataSetChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_comments, container, false)

        presenter.onPresenterCreated(testId)

        recyclerViewComments = view.recyclerViewComments
        recyclerViewComments.setHasFixedSize(true)
        recyclerViewComments.setLayoutManager(LinearLayoutManager(context))
        recyclerViewComments.setAdapter(commentsAdapter)
        view.textViewCurrentTest.text = testTitle

        setOnButtonsClickListeners(view)

        return view
    }

    private fun setOnButtonsClickListeners(view: View) {
        view.buttonMessageBoxSend.setOnClickListener {
            val message = view.editTextMessageBox.text.toString()
            if(!message.equals("") && message != null) {
                presenter.onConfirm(CommentsPresenterImpl.BUTTON_COMMENT, arrayListOf(message))
                view.editTextMessageBox.setText("")
            }
        }

        view.buttonStartTest.setOnClickListener {
            presenter.onConfirm(CommentsPresenterImpl.BUTTON_BEGIN_TEST, null)
        }
    }

    override fun addCategory(category: Category) {
        (comments as ArrayList).add(category)
        commentsAdapter.notifyItemInserted(comments.size - 1)
        recyclerViewComments.smoothScrollToPosition(commentsAdapter.getItemCount());
    }

    override fun toString(): String {
        return "Комментарии"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TEST_ID, testId)
        outState.putString(TEST_TITLE, testTitle)
    }

    companion object {
        fun newInstance(): FragmentComments {
            val mainFragment = FragmentComments()

            return mainFragment
        }
        const val TEST_ID = "testId"
        const val TEST_TITLE = "testTitle"
    }
}