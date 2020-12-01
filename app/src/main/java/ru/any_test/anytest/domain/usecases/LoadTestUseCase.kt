package ru.any_test.anytest.domain.usecases

import android.util.Log
import io.reactivex.Single
import ru.any_test.anytest.repo.TestRepositoryImpl
import ru.any_test.anytest.contracts.CategoriesRepository
import ru.any_test.anytest.domain.executor.PostExecutionThread
import ru.any_test.anytest.domain.executor.ThreadExecutor
import ru.any_test.anytest.model.Category
import ru.any_test.anytest.repo.responses.TestResponse
import javax.inject.Inject

class LoadTestUseCase
@Inject
constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val testRepository: CategoriesRepository<Single<TestResponse>>
) : SingleUseCase<List<Category>, Unit>(threadExecutor, postExecutionThread) {

    private var param = 0

    override fun buildUseCaseSingle(params: Unit?): Single<List<Category>> {
        if(param == TestRepositoryImpl.QUESTIONS_LOADED) {
            return testRepository.getCategories(param).map { convertQuestionResponse(it) }
        }
        else {
            return testRepository.getCategories(param).map { convertCommentResponse(it) }
        }
    }

    fun setParam(param: Int) {
        this.param = param
    }

    /**
     * Конвертируем десериализованный объект-ответ в List<Category>.
     */
    private fun convertCommentResponse(responseList: TestResponse): List<Category> {
        val comments = mutableListOf<Category>()
        try {
            responseList.comments.forEach{
                comments.add(Category.Comment(
                    it.userName,
                    Category.TYPE_FOREIGN_MESSAGE,
                    arrayListOf(it.text)
                )
                )
            }
            Log.d("convertResponse", "$comments")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return comments
    }

    private fun convertQuestionResponse(responseList: TestResponse): List<Category> {
        val testQuestions = mutableListOf<Category>()
        try {
            var id = 0
            var answer = 0
            responseList.questions.forEach { questionJson ->
                if (questionJson.type.equals("RADIO_GROUP")) {
                    answer = 0
                    testQuestions.add(
                        Category.RadioGroupQuestion(
                            id++,
                            questionJson.text,
                            arrayListOf<String>().apply {
                                questionJson.answers.forEach { answerJson ->
                                    add(answerJson.answer)
                                }
                            },
                            answer.apply {
                                for (i in 0 until questionJson.answers.size) {
                                    if (questionJson.answers.get(i).correct) {
                                        answer = i
                                    }
                                }
                            },
                            null
                        )
                    )
                }
                else if(questionJson.type.equals("CHECK_BOX")) {
                    testQuestions.add(
                        Category.CheckBoxQuestion(
                            id++,
                            questionJson.text,
                            arrayListOf<String>().apply {
                                questionJson.answers.forEach { answerJson ->
                                    add(answerJson.answer)
                                }
                            },
                            arrayListOf<Int>().apply {
                                for (i in 0 until questionJson.answers.size) {
                                    if (questionJson.answers.get(i).correct) {
                                        add(i)
                                    }
                                }
                            },
                            null
                        )
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return testQuestions
    }

}