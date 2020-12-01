package ru.any_test.anytest.comments

import io.reactivex.observers.DisposableSingleObserver
import ru.any_test.anytest.contracts.*
import ru.any_test.anytest.domain.usecases.LoadTestUseCase
import ru.any_test.anytest.model.Category
import ru.any_test.anytest.question.FragmentQuestion
import ru.any_test.anytest.root.MainActivity

class CommentsPresenterImpl(
    private val fragmentComments: MutableView,
    private val fragmentTest: ImmutableView,
    private val loadTestUseCase: LoadTestUseCase
) :
    TestPresenter{

    lateinit var comments: List<Category>

    override fun onPresenterCreated(position: Int) {
        loadTestUseCase.setParam(position)
        loadTestUseCase.execute(CommentsObserver())
    }

    override fun onConfirm(position: Int, list: List<String>?) {
        when(position) {
            BUTTON_COMMENT -> {
                val newMessage = Category.Comment("Я", Category.TYPE_MY_MESSAGE, list)
                fragmentComments.addCategory(newMessage)
            }
            BUTTON_BEGIN_TEST -> {
                fragmentTest.onCategoryViewCreated(0)
                ((fragmentComments as FragmentComments)
                    .activity as RootView)
                    .replaceFragment(fragmentTest as FragmentQuestion, MainActivity.VIEW_COMMENTS, true)
            }
        }
    }

    private inner class CommentsObserver : DisposableSingleObserver<List<Category>>() {
        override fun onSuccess(categories: List<Category>) {
            comments = categories
            fragmentComments.showCategories(categories)
        }

        override fun onError(e: Throwable) {
            //TODO Пока никак не обрабатываем. Еще предстоит с этим поработать!
        }
    }

    companion object {
        const val BUTTON_BEGIN_TEST = 0
        const val BUTTON_COMMENT = 1
    }
}