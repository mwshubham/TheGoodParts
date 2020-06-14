package app.thegoodparts.ui.fragments.news

import androidx.navigation.fragment.FragmentNavigator
import app.thegoodparts.data.source.local.NewsArticle

/**
 * Describes all the events originated from
 * [NewsArticlesAdapter].
 */
sealed class NewsAdapterEvent {
    data class ClickEvent(
        val newsArticle: NewsArticle,
        val extras: FragmentNavigator.Extras
    ) : NewsAdapterEvent()
}