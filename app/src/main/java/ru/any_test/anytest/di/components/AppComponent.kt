package ru.any_test.anytest.di.components

import dagger.Component
import ru.any_test.anytest.di.modules.*
import ru.any_test.anytest.root.MainActivity
import javax.inject.Singleton

/**
 * Если нужны синглтоны в других компонентах, то модули с этими синглтонами не нужно в соответствующих
 * компонентах указывать! Они указываются в главном AppComponent.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, RepositoryModule::class))
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun pagerComponent(pagerModule: PagerModule?): PagerComponent

    fun categoriesComponent(categoriesModule: CategoriesModule?): CategoriesComponent

    fun answersComponent(answersModule: AnswersModule?): AnswersComponent

    fun testsComponent(testsModule: TestsModule?): TestsComponent

    fun questionComponent(testsModule: QuestionModule?): QuestionComponent

    fun commentsComponent(commentsModule: CommentsModule?): CommentsComponent

}
