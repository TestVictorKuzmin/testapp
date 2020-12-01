package ru.any_test.anytest.categories

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.item_category.view.*
import ru.any_test.anytest.R
import ru.any_test.anytest.extensions.inflate
import ru.any_test.anytest.model.Category
import ru.any_test.anytest.utils.Images

class CategoriesAdapter(private val images: Images)
    : RecyclerView.Adapter<CategoriesAdapter.CategoryHolder>() {

    var listCategories: List<Category> = listOf()
    private var onItemCLickListener: OnItemClickListener? = null

    fun setCategories(categories: List<Category>) {
        listCategories = categories
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
            return CategoryHolder(parent.inflate(R.layout.item_category))
    }

    override fun getItemCount(): Int {
        return listCategories.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val category: Category.TestCategory = listCategories[position] as Category.TestCategory
        holder.bind(category, onItemCLickListener)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemCLickListener = onItemClickListener
    }

    inner class CategoryHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageViewCategory: ImageView = view.imageViewCategory
        private val textViewCategoryName: TextView = view.textViewCategoryName
        private val textViewNumTests: TextView = view.textViewNumTests
        private val textViewDescription: TextView = view.textViewDescription

        fun bind(category: Category.TestCategory, onItemCLickListener: OnItemClickListener?) {
            if(category.image == null) {
                imageViewCategory.setImageResource((R.drawable.ic_default))
            }
            else {
                imageViewCategory.setImageDrawable(images.getImage(category.image))
            }
            textViewCategoryName.text = category.name
            textViewNumTests.text = "${category?.numTests} тест"
            textViewDescription.text = category.descripion

            itemView.setOnClickListener {
                onItemCLickListener!!.onItemClick(adapterPosition)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}