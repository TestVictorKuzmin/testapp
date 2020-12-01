package ru.any_test.anytest.answers

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_answers.view.*
import kotlinx.android.synthetic.main.fragment_categories.*
import ru.any_test.anytest.App
import ru.any_test.anytest.R
import ru.any_test.anytest.contracts.ResultPresenter
import ru.any_test.anytest.contracts.ResultView
import ru.any_test.anytest.model.Category
import javax.inject.Inject
import javax.inject.Named

class FragmentAnswerList : Fragment(), ResultView {

    lateinit var recyclerViewAnswers: RecyclerView

    @field:[Inject Named("answersAdapter")]
    lateinit var answersAdapter: AnswersAdapter

    @field:[Inject Named("answersPresenter")]
    lateinit var presenter: ResultPresenter

    var answersList: List<Category>? = null

    override fun onCategoryViewCreated(list: List<Category>) {
        answersList = list
    }

    override fun showCategories(categories: List<Category>) {
        answersAdapter.setCategories(categories)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance?.initAnswersComponent(this)?.inject(this)

        presenter.onPresenterCreated(answersList)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_answers, container, false)

        recyclerViewAnswers = view.recyclerViewAnswers
        recyclerViewAnswers.setHasFixedSize(true)
        recyclerViewAnswers.setLayoutManager(LinearLayoutManager(context))

        recyclerViewAnswers.setAdapter(answersAdapter)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun toString(): String {
        return "Категории"
    }

    companion object {
        fun newInstance(): FragmentAnswerList {
            val mainFragment = FragmentAnswerList()

            return mainFragment
        }
    }
}