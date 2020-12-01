package ru.any_test.anytest.question

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import ru.any_test.anytest.R
import ru.any_test.anytest.extensions.fromDPToPixels
import ru.any_test.anytest.model.Category


class FragmentVariants : Fragment() {
    lateinit var question: Category
    private var answers = ArrayList<String>()
    private var questionType = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            question = arguments?.getParcelable(KEY_VARIANTS)!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(question)
    }

    private fun createView(question: Category): View {
        val rootView = LinearLayout(context)
        rootView.layoutParams = LinearLayout.LayoutParams(
            LinearLayoutCompat.LayoutParams.MATCH_PARENT,
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        )
        rootView.orientation = LinearLayout.VERTICAL

        val marginLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        marginLayoutParams.setMargins(32.fromDPToPixels(requireContext()), 16.fromDPToPixels(requireContext()),
            32.fromDPToPixels(requireContext()), 16.fromDPToPixels(requireContext()))

        var radioCheckBoxId = -1
        when (question) {
            is Category.CheckBoxQuestion ->
                let {
                    question.variants.forEach{
                        val checkBox = CheckBox(context)
                        radioCheckBoxId++
                        checkBox.id = radioCheckBoxId
                        checkBox.text = it
                        rootView.addView(checkBox, marginLayoutParams) }
                    questionType = TYPE_CHECK_BOX
                }
            is Category.RadioGroupQuestion ->
                let{
                    val radioGroup = RadioGroup(context)
                    //set id for radio group (name of radio group is in ids.xml)
                    radioGroup.id = R.id.radioGroupQuestion
                    radioGroup.orientation = RadioGroup.VERTICAL
                    question.variants.forEach{
                        val radioButton = RadioButton(context)
                        radioCheckBoxId++
                        radioButton.id = radioCheckBoxId
                        radioButton.text = it
                        radioGroup.addView(radioButton, marginLayoutParams)
                    }
                    rootView.addView(radioGroup, marginLayoutParams)
                    questionType = TYPE_RADIO
                }
            else -> error("Что-то пошло не так")
        }

        val answerButton = Button(context)
        answerButton.text = "Ответить"
        val buttonLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        )
        buttonLayoutParams.setMargins(0, 16.fromDPToPixels(requireContext()),0, 16.fromDPToPixels(requireContext()))
        buttonLayoutParams.gravity = Gravity.CENTER_HORIZONTAL
        rootView.addView(answerButton, buttonLayoutParams)

        when(questionType) {
            TYPE_RADIO ->
                setRadioListener(answerButton, rootView.findViewById(R.id.radioGroupQuestion))
            TYPE_CHECK_BOX ->
                setCheckBoxListener(answerButton, rootView, radioCheckBoxId)
            else -> error("тип вопросов: ни radio, ни checkbox")
        }
        return rootView
    }

    private fun setCheckBoxListener(answerButton: Button, rootView: LinearLayout, checkBoxIds: Int) {
        answerButton.setOnClickListener(View.OnClickListener {
            var id = -1
            for(i in 0..checkBoxIds) {
                val checkBox: CheckBox = rootView.findViewById(i)
                if(checkBox.isChecked) {
                    answers.add(i.toString())
                    id++
                }
            }
            if(id > -1) {
                (parentFragment as FragmentQuestion).onClick(answers)
            }
            else {
                answers.clear()
            }
        })
    }

    private fun setRadioListener(answerButton: Button, radioGroup: RadioGroup) {
        answerButton.setOnClickListener(View.OnClickListener {
            var id: Int = radioGroup.checkedRadioButtonId
            if (id != -1) { // If any radio button checked from radio group
                //set answer id and call onClick.
                answers.add(id.toString())
                (parentFragment as FragmentQuestion).onClick(answers)
            }
            else {
                answers.clear()
            }
        })
    }

    companion object {
        fun newInstance(categories: List<Category>, position: Int): FragmentVariants {
            val questionFragment = FragmentVariants().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_VARIANTS, categories.get(position))
                }
            }
            return questionFragment
        }
        const val KEY_VARIANTS = "variants"
        const val TYPE_RADIO = 0
        const val TYPE_CHECK_BOX = 1
    }
}