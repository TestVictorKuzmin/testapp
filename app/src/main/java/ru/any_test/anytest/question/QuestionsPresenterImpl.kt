package ru.any_test.anytest.question

import io.reactivex.observers.DisposableSingleObserver
import ru.any_test.anytest.answers.FragmentAnswerList
import ru.any_test.anytest.repo.TestRepositoryImpl
import ru.any_test.anytest.contracts.*
import ru.any_test.anytest.contracts.OnCategoriesFetchedListener
import ru.any_test.anytest.domain.usecases.LoadTestUseCase
import ru.any_test.anytest.model.Category
import ru.any_test.anytest.repo.CurrentTestRepository
import ru.any_test.anytest.root.MainActivity

class QuestionsPresenterImpl(
    private val fragmentTest: ImmutableView,
    private val fragmentAnswerList: ResultView,
    private val loadTestUseCase: LoadTestUseCase,
    private val currentTestRepository: CurrentTestRepository
) :
    TestPresenter {

    override fun onPresenterCreated(position: Int) {
        if(position <= 0){
            loadTestUseCase.setParam(TestRepositoryImpl.QUESTIONS_LOADED)
            loadTestUseCase.execute(QuestionObserver(TestRepositoryImpl.QUESTIONS_LOADED, null))
        }
        else {
            fragmentTest.showCategories(currentTestRepository.testQuestion)
        }
    }

    override fun onConfirm(position: Int, list: List<String>?) {
        var currentPosition = position
        var currentCategories = currentTestRepository.testQuestion
        if(position < currentCategories.size - 1) {
            currentCategories.get(position).userData = list
            currentPosition++
            (fragmentTest as FragmentQuestion).nextQuestion(currentPosition)
        }
        else {
            currentCategories.get(position).userData = list
            fragmentAnswerList.onCategoryViewCreated(currentCategories)
            ((fragmentTest as FragmentQuestion)
                .activity as RootView)
                .replaceFragment(fragmentAnswerList as FragmentAnswerList, MainActivity.VIEW_TEST, true)
        }
    }

    private inner class QuestionObserver(var position: Int, val answers: List<String>?) : DisposableSingleObserver<List<Category>>() {

        override fun onSuccess(categories: List<Category>) {
            currentTestRepository.testQuestion = categories
            fragmentTest.showCategories(categories)
        }

        override fun onError(e: Throwable) {
            //TODO Пока никак не обрабатываем. Еще предстоит с этим поработать!
        }
    }

}