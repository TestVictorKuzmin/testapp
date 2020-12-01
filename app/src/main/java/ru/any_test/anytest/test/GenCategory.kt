package ru.any_test.anytest.test

import ru.any_test.anytest.model.Category
import java.util.ArrayList

class GenCategory {

    companion object {
        @JvmStatic
        fun generateCategories(): List<Category> {
            val categories = ArrayList<Category>()

            val categoryOhrana : Category = Category.TestCategory(0,"Тесты охранников", null,
            "Тесты для охранников разных разрядов", getTests(0).size, null)
            categories.add(categoryOhrana)

            val categoryBiology : Category = Category.TestCategory(1,"Тесты по биологии", "ic_biologiya",
            "Тесты по биологии", getTests(1).size, null)
            categories.add(categoryBiology)

            return categories
        }

        @JvmStatic
        fun getTests(position: Int): List<Category> {
            val tests = ArrayList<Category>()
            when(position) {
                0 -> with(tests) {
                    tests.add(Category.Test(0,"разряд 1", null, null, 10f, 8f))
                    tests.add(Category.Test(1,"разряд 2", null, null, 20f, 5f))
                    tests.add(Category.Test(2,"разряд 3", null, null, 30f, 25f))
                    tests.add(Category.Test(3,"разряд 4", null, null, 23f, 14f))
                    tests.add(Category.Test(3,"разряд 5", null, null, 3f, 3f))
                }
                1 -> tests.add(Category.Test(4,"Биология", null, null, 31f, 27f))
            }
            return tests
        }

        @JvmStatic
        fun getQuestions(position: Int): List<Category> {
            val questions = ArrayList<Category>()
            when(position) {
                0 -> with(questions) {
                    questions.add(Category.RadioGroupQuestion(0,"Вопрос 10", arrayListOf("вариант 1", "вариант 2", "вариант 3"), 2, null))
                    questions.add(Category.RadioGroupQuestion(1,"Вопрос 20", arrayListOf("вариант 1", "вариант 2"), 1, null))
                    questions.add(Category.CheckBoxQuestion(2,"Вопрос 30", arrayListOf("вариант 1", "вариант 2", "вариант 3"), arrayListOf(1, 2), null))
                    questions.add(Category.RadioGroupQuestion(3,"Вопрос 40", arrayListOf("вариант 1", "вариант 2", "вариант 3"),1, null))
                    questions.add(Category.CheckBoxQuestion(4,"Вопрос 50", arrayListOf("вариант 1", "вариант 2", "вариант 3"), arrayListOf(0, 2), null))
                    questions.add(Category.RadioGroupQuestion(5,"Вопрос 60", arrayListOf("вариант 1", "вариант 2", "вариант 3", "вариант 4"), 3, null))
                }
                1 -> with(questions) {
                    questions.add(Category.RadioGroupQuestion(6,"Вопрос 11", arrayListOf("вариант 1", "вариант 2", "вариант 3"), 2, null))
                    questions.add(Category.RadioGroupQuestion(7,"Вопрос 21", arrayListOf("вариант 1", "вариант 2"), 1, null))
                }
                2 -> with(questions) {
                    questions.add(Category.CheckBoxQuestion(8,"Вопрос 12", arrayListOf("вариант 1", "вариант 2", "вариант 3"), arrayListOf(0, 2), null))
                    questions.add(Category.RadioGroupQuestion(9,"Вопрос 22", arrayListOf("вариант 1", "вариант 2", "вариант 3", "вариант 4"), 3, null))
                }
                3 -> with(questions) {
                    questions.add(Category.RadioGroupQuestion(10,"Вопрос 13", arrayListOf("вариант 1", "вариант 2"), 1, null))
                    questions.add(Category.CheckBoxQuestion(11,"Вопрос 23", arrayListOf("вариант 1", "вариант 2", "вариант 3"), arrayListOf(1, 2), null))
                    questions.add(Category.RadioGroupQuestion(12,"Вопрос 33", arrayListOf("вариант 1", "вариант 2", "вариант 3"),1, null))
                    questions.add(Category.CheckBoxQuestion(13,"Вопрос 43", arrayListOf("вариант 1", "вариант 2", "вариант 3"), arrayListOf(0, 2), null))
                }
                4 -> questions.add(Category.RadioGroupQuestion(14,"Биология", arrayListOf("вариант 1", "вариант 2", "вариант 3"), 2, null))
            }
            return questions
        }

        @JvmStatic
        fun getComments(position: Int): List<Category> {
            val comments = ArrayList<Category>()

            when(position) {
                0 -> with(comments) {
                    comments.add(Category.Comment("user 0", Category.TYPE_FOREIGN_MESSAGE, arrayListOf("mmessage 1")))
                    comments.add(Category.Comment("user 3", Category.TYPE_FOREIGN_MESSAGE, arrayListOf("mmessage 2")))
                    comments.add(Category.Comment("user 1", Category.TYPE_FOREIGN_MESSAGE, arrayListOf("mmessage 3")))
                    comments.add(Category.Comment("user 0", Category.TYPE_FOREIGN_MESSAGE, arrayListOf("mmessage 4")))
                    comments.add(Category.Comment("user 111111111111111111111111111111111111111111", Category.TYPE_FOREIGN_MESSAGE, arrayListOf("message 4 1")))
                    comments.add(Category.Comment("u", Category.TYPE_FOREIGN_MESSAGE, arrayListOf("message 455555555555555555555555555555555555555555555555555555555555555")))
                    comments.add(Category.Comment("md", Category.TYPE_MY_MESSAGE, arrayListOf("message 1")))
                }
            }
            return comments
        }
    }
}