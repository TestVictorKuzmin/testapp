package ru.any_test.anytest.question

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_test.*
import ru.any_test.anytest.App
import ru.any_test.anytest.R
import ru.any_test.anytest.contracts.ImmutableView
import ru.any_test.anytest.contracts.TestPresenter
import ru.any_test.anytest.model.Category
import java.util.ArrayList
import javax.inject.Inject
import javax.inject.Named

class FragmentQuestion : Fragment(R.layout.fragment_test), ImmutableView {

    private var testId = 0
    var currentQuestion = 0
    var questions: List<Category> = ArrayList()

    @field:[Inject Named("questionsPresenter")]
    lateinit var presenter: TestPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("fragmentQuestion", "onActivityCreated onStart, ${hashCode()}")

        if(savedInstanceState != null) {
            currentQuestion = savedInstanceState.getInt(CURRENT_QUESTION)
            testId = savedInstanceState.getInt(CURRENT_TEST_ID)
            init()
        }
        else {
            currentQuestion = 0
            init()
        }
    }

    private fun init() {
        App.instance?.initQuestionComponent(this)?.inject(this)
        presenter.onPresenterCreated(currentQuestion)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_QUESTION, currentQuestion)
        outState.putInt(CURRENT_TEST_ID, testId)
    }

    /**
     * id теста передается в параметр.
     */
    override fun onCategoryViewCreated(position: Int) {
        this.testId = position
    }

    override fun showCategories(questions: List<Category>) {
        this.questions = questions
        openVariantsFragment(questions)
    }

    override fun updateCategories(categories: List<Category>) {
        TODO("Not yet implemented")
    }

    fun nextQuestion(position: Int) {
        currentQuestion = position
        openVariantsFragment(questions)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_test, container, false)

        return view
    }

    private fun openVariantsFragment(categories: List<Category>) {
        textViewTestName.text = categories.get(currentQuestion).name
        val fragmentVariants: FragmentVariants =
            FragmentVariants.newInstance(categories, currentQuestion)
        childFragmentManager.beginTransaction()
            .replace(R.id.questionContainer, fragmentVariants)
            .commit()
    }

    fun onClick(answers: List<String>) {
        presenter.onConfirm(currentQuestion, answers)
    }

    override fun showProgress() {
        TODO("Not yet implemented")
    }

    override fun hideProgress() {
        TODO("Not yet implemented")
    }

    companion object {
        fun newInstance(): FragmentQuestion {
            val testFragment = FragmentQuestion()

            return testFragment
        }
        const val CURRENT_QUESTION = "currentQuestion"
        const val CURRENT_TEST_ID = "currentTestId"
    }
}