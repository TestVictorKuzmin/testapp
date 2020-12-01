package ru.any_test.anytest

import android.app.Application
import android.util.Log
import ru.any_test.anytest.answers.FragmentAnswerList
import ru.any_test.anytest.categories.FragmentCategoriesList
import ru.any_test.anytest.comments.FragmentComments
import ru.any_test.anytest.di.components.*
import ru.any_test.anytest.di.modules.*
import ru.any_test.anytest.question.FragmentQuestion
import ru.any_test.anytest.testlist.FragmentTestsList
import ru.any_test.anytest.root.*

class App : Application() {
    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        Log.d("lifecycle", "App onCreate, ${hashCode()}")
        instance = this
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getAppComponent(): AppComponent? {
        return appComponent
    }

    fun initPagerComponent(mainActivity: MainActivity): PagerComponent? {
        val pagerComponent = appComponent?.pagerComponent(PagerModule(mainActivity))
        return pagerComponent
    }

    fun initCategoriesComponent(fragmentCategoriesList: FragmentCategoriesList): CategoriesComponent? {
        val categoriesComponent = appComponent?.categoriesComponent(CategoriesModule(fragmentCategoriesList))
        return categoriesComponent
    }

    fun initTestsComponent(mainActivity: MainActivity, fragmentTestsList: FragmentTestsList): TestsComponent? {
        val testsListComponent = appComponent?.testsComponent(TestsModule(mainActivity, fragmentTestsList))
        return testsListComponent
    }

    fun initCommentsComponent(mainActivity: MainActivity, fragmentComments: FragmentComments): CommentsComponent? {
        val commentsComponent = appComponent?.commentsComponent(CommentsModule(mainActivity, fragmentComments))
        return commentsComponent
    }

    fun initQuestionComponent(fragmentQuestion: FragmentQuestion): QuestionComponent? {
        val questionComponent = appComponent?.questionComponent(QuestionModule(fragmentQuestion))
        return questionComponent
    }

    fun initAnswersComponent(fragmentAnswerList: FragmentAnswerList): AnswersComponent? {
        val answersComponent = appComponent?.answersComponent(AnswersModule(fragmentAnswerList))
        return answersComponent
    }

    companion object {
        var instance: App? = null
            private set
    }
}