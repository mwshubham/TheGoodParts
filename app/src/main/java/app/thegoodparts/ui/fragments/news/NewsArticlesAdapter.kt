package app.thegoodparts.ui.fragments.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.thegoodparts.data.source.local.NewsArticle
import app.thegoodparts.databinding.ItemNewsArticleBinding

/**
 * The News adapter to show the news in a list.
 */
class NewsArticlesAdapter(
    private val listener: (NewsAdapterEvent) -> Unit
) : ListAdapter<NewsArticle, NewsArticlesAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listener)
    }

    class ViewHolder private constructor(val binding: ItemNewsArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(newsArticle: NewsArticle, listener: (NewsAdapterEvent) -> Unit) {
            binding.newsArticle = newsArticle
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                val extras = FragmentNavigatorExtras(
                    binding.root to newsArticle.publishedAt
                )
                listener(NewsAdapterEvent.ClickEvent(newsArticle, extras))
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemNewsArticleBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsArticle>() {
            override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean =
                oldItem.publishedAt == newItem.publishedAt

            override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean =
                oldItem == newItem
        }
    }
}