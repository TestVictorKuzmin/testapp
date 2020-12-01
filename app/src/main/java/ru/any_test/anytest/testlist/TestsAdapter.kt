package ru.any_test.anytest.testlist

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.item_test.view.*
import ru.any_test.anytest.R
import ru.any_test.anytest.extensions.inflate
import ru.any_test.anytest.model.Category
import ru.any_test.anytest.utils.Images

class TestsAdapter(private val images: Images)
    : RecyclerView.Adapter<TestsAdapter.TestHolder>() {

    var listTests: List<Category> = listOf()
    private var onItemCLickListener: OnItemClickListener? = null

    fun setCategories(categories: List<Category>) {
        listTests = categories
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemCLickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TestHolder {
        return TestHolder(parent.inflate(R.layout.item_test))
    }

    override fun getItemCount(): Int {
        return listTests!!.size
    }

    override fun onBindViewHolder(holder: TestHolder, position: Int) {
        val test: Category.Test = listTests[position] as Category.Test
        holder.bind(test, onItemCLickListener)
    }

    inner class TestHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewCategory: ImageView = view.imageViewTest
        val textViewTestName: TextView = view.textViewCurrentTest
        val textViewStatistics: TextView = view.textViewStatistics

        fun bind(category: Category.Test, onItemCLickListener: OnItemClickListener?) {
            if(category.image == null) {
                imageViewCategory.setImageResource(R.drawable.ic_default)
            }
            else {
                imageViewCategory.setImageDrawable(images.getImage(category.image))
            }
            textViewTestName.text = category.name
            val statistics = "Тест запущен: ${category.testsLaunched.toInt()} раз(а)." +
                    " Тест пройден: ${category.testsSuccess.toInt()} раз(а)." +
                    " Процент успешных сдач: ${(category.testsSuccess / category.testsLaunched * 100).toInt()}%."
            textViewStatistics.text = statistics

            itemView.setOnClickListener {
                onItemCLickListener!!.onItemClick(adapterPosition)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}