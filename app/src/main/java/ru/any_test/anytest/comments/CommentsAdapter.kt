package ru.any_test.anytest.comments

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_foreign_comment.view.*
import ru.any_test.anytest.R
import ru.any_test.anytest.model.Category
import ru.any_test.anytest.utils.Images

class CommentsAdapter(private val images: Images)
    : RecyclerView.Adapter<CommentsAdapter.CommentHolder>() {

    var listComments: List<Category> = mutableListOf<Category>()

    fun setCategories(categories: List<Category>) {
        listComments = categories
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        when (viewType) {
            Category.TYPE_FOREIGN_MESSAGE -> {
                return ForeignCommentHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_foreign_comment, parent, false)
                )
            }
            Category.TYPE_MY_MESSAGE -> {
                return MyCommentHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_my_comment, parent, false)
                )
            }
            else ->
                error("Это не комментарий")
        }
    }

    override fun getItemCount(): Int {
        return listComments.size
    }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        val comment: Category.Comment = listComments[position] as Category.Comment
        when (holder) {
            is ForeignCommentHolder -> {
                holder.bind(comment)
            }
            is MyCommentHolder -> {
                holder.bind(comment)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (listComments[position] is Category.Comment) {
            return (listComments[position] as Category.Comment).messageType
        }
        else {
            error("Это не комментарий")
        }
    }

    abstract class CommentHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewMessage: TextView = view.textViewMessage

        fun bindCommon(category: Category.Comment) {
            textViewMessage.text = category.userData?.get(0)
        }
    }

    class ForeignCommentHolder(view: View) : CommentHolder(view) {
        val textViewUser: TextView = view.textViewUser

        fun bind(comment: Category.Comment) {
            bindCommon(comment)
            textViewUser.text = comment.name
        }
    }

    class MyCommentHolder(view: View) : CommentHolder(view) {

        fun bind(comment: Category.Comment) {
            bindCommon(comment)
        }
    }

}