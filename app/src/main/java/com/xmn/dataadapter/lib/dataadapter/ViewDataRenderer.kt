package com.xmn.dataadapter.lib.dataadapter

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
abstract class ViewDataRenderer<T : Any> {

    abstract fun clazz(): KClass<T>
    abstract fun ViewGroup.view(): View
    abstract fun ViewHolder.bind(
        item: T,
        payloads: MutableList<Any>
    )

    abstract fun T.identify(): Any

    fun areItemsTheSame(
        oldItem: Any,
        newItem: Any
    ): Boolean =
        if (!clazz().isInstance(newItem)) false else
            (oldItem as? T)?.let { it.identify() } ==
                    (newItem as? T)?.let { it.identify() }

    class ViewHolder(val containerView: View) :
        RecyclerView.ViewHolder(containerView) {
        private val viewsCache: MutableMap<Int, View> = mutableMapOf()
        fun <T : View> view(@IdRes id: Int): T {
            return this.viewsCache[id] as T? ?: containerView.findViewById<T>(id)
                .also { this.viewsCache[id] = it }
        }
    }
}