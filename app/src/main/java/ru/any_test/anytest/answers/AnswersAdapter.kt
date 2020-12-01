package ru.any_test.anytest.answers

import android.app.Activity
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.item_answer.view.*
import ru.any_test.anytest.R
import ru.any_test.anytest.extensions.fromDPToPixels
import ru.any_test.anytest.model.Category

class AnswersAdapter(
    val activity: Activity
)
    : RecyclerView.Adapter<AnswersAdapter.AnswerHolder>() {

    lateinit var listCategories: List<Category>

    private val RADIO_GROUP = 1
    private val CHECK_BOX_GROUP = 2

     val colorCorrectGreen = ContextCompat.getColor(activity, R.color.colorCorrectGreen)
     val colorWrongRed = ContextCompat.getColor(activity, R.color.colorWrongRed)

    fun setCategories(categories: List<Category>) {
        listCategories = categories
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerHolder {
        when (viewType) {
            RADIO_GROUP -> {
                return RadioViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_answer, parent, false),
                    activity,
                    colorCorrectGreen,
                    colorWrongRed
                )
            }
            CHECK_BOX_GROUP -> {
                return CheckBoxViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_answer, parent, false),
                    activity,
                    colorCorrectGreen,
                    colorWrongRed
                )
            }
            else ->
                error("Нет такой группы вопросов.")
        }
    }

    override fun getItemCount(): Int {
        return listCategories.size
    }

    override fun getItemViewType(position: Int): Int {
        if (listCategories[position] is Category.RadioGroupQuestion)
            return RADIO_GROUP
        else if (listCategories[position] is Category.CheckBoxQuestion)
            return CHECK_BOX_GROUP
        else
            error("Нет такой группы вопросов.")
    }

    override fun onBindViewHolder(holder: AnswerHolder, position: Int) {
        when (holder) {
            is RadioViewHolder -> {
                holder.bind(listCategories[position] as Category.RadioGroupQuestion, position, listCategories.size)
            }
            is CheckBoxViewHolder -> {
                holder.bind(listCategories[position] as Category.CheckBoxQuestion, position, listCategories.size)
            }
        }
    }

    abstract class AnswerHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val questionPos: TextView = view.textViewQuestionPos
        private val textViewQuestion: TextView = view.textViewQuestionInAnswers

        fun bindCommon(category: Category, position: Int, questionsCount: Int) {
            questionPos.text = "Вопрос ${position + 1} из $questionsCount:"
            textViewQuestion.text = category.name
        }
    }

    class RadioViewHolder(val view: View, val activity: Activity, val colorCorrectGreen: Int, val colorWrongRed: Int) : AnswerHolder(view) {
        private val mainContainer: LinearLayout = view.answerContainer
        private val rootViewForButtons: LinearLayout = LinearLayout(activity)
        private val buttonList = ArrayList<RadioButton>()

        init{
            rootViewForButtons.layoutParams = LinearLayout.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT
            )
            rootViewForButtons.orientation = LinearLayout.VERTICAL

            val marginLayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            marginLayoutParams.setMargins(32.fromDPToPixels(activity), 16.fromDPToPixels(activity),
                32.fromDPToPixels(activity), 16.fromDPToPixels(activity))

            mainContainer.addView(rootViewForButtons)
        }

        fun bind(category: Category.RadioGroupQuestion, position: Int, questionsCount: Int) {
            bindCommon(category, position, questionsCount)

            if(buttonList.isEmpty()) {
                var radioButtonId = -1
                for(i in 0 until category.variants.size) {
                    val radioButton = RadioButton(activity)
                    radioButtonId++
                    radioButton.id = radioButtonId
                    radioButton.text = category.variants.get(i)
                    radioButton.setClickable(false)
                    rootViewForButtons.addView(radioButton)
                    buttonList.add(radioButton)
                }

                //Если правильный ответ
                if(category.answer == category.userData?.get(0)?.toInt()) {
                    val trueButton: RadioButton = view.findViewById(category.userData?.get(0)?.toInt()!!)
                    trueButton.setBackgroundColor(colorCorrectGreen)
                    trueButton.setChecked(true)
                }
                //Если неправильный ответ
                else {
                    val falseButton: RadioButton = view.findViewById(category.userData?.get(0)?.toInt()!!)
                    falseButton.setBackgroundColor(colorWrongRed)
                    falseButton.setChecked(true)

                    val trueButton: RadioButton = view.findViewById(category.answer)
                    trueButton.setBackgroundColor(colorCorrectGreen)
                    trueButton.setChecked(true)
                }
            }
        }
    }

    class CheckBoxViewHolder(val view: View, val activity: Activity, val colorCorrectGreen: Int, val colorWrongRed: Int) : AnswerHolder(view) {
        private val mainContainer: LinearLayout = view.answerContainer
        private val rootViewForButtons: LinearLayout = LinearLayout(activity)
        private val buttonList = ArrayList<CheckBox>()

        init{
            rootViewForButtons.layoutParams = LinearLayout.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT
            )
            rootViewForButtons.orientation = LinearLayout.VERTICAL

            val marginLayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            marginLayoutParams.setMargins(32.fromDPToPixels(activity), 16.fromDPToPixels(activity),
                32.fromDPToPixels(activity), 16.fromDPToPixels(activity))

            mainContainer.addView(rootViewForButtons)
        }

        fun bind(category: Category.CheckBoxQuestion, position: Int, questionsCount: Int) {
            bindCommon(category, position, questionsCount)

            if(buttonList.isEmpty()) {
                var checkBoxId = -1
                for(i in 0 until category.variants.size) {
                    val checkBox = CheckBox(activity)
                    checkBoxId++
                    checkBox.id = checkBoxId
                    checkBox.text = category.variants.get(i)
                    checkBox.setClickable(false)
                    rootViewForButtons.addView(checkBox)
                    buttonList.add(checkBox)
                }

                for(i in 0 until category.variants.size) {
                    if(category.userData?.contains(i.toString())!!) {
                        //Правильная галочка
                        if(category.answers.contains(i)) {
                            val trueButton: CheckBox = view.findViewById(i)
                            trueButton.setBackgroundColor(colorCorrectGreen)
                            trueButton.setChecked(true)
                        }
                        else {  //Непраивльная галочка
                            val falseButton: CheckBox = view.findViewById(i)
                            falseButton.setBackgroundColor(colorWrongRed)
                            falseButton.setChecked(true)
                        }
                    }
                    else {//юзер ничего не поставил, но галочка ставится как правильная
                        if(category.answers.contains(i)) {
                            val trueButton: CheckBox = view.findViewById(i)
                            trueButton.setBackgroundColor(colorCorrectGreen)
                            trueButton.setChecked(true)
                        }
                    }
                }
            }
        }
    }

}