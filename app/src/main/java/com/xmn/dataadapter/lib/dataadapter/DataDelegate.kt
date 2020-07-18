package com.xmn.dataadapter.lib.dataadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
class DataDelegate<T : Any>(val renderer: ViewDataRenderer<T>) :
    AdapterDelegate<List<Any>>() {
    public override fun onCreateViewHolder(parent: ViewGroup): ViewDataRenderer.ViewHolder {
        return ViewDataRenderer.ViewHolder(renderer.run { parent.view() })
    }

    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        val item = items[position]
        return renderer.clazz().isInstance(item)
    }

    public override fun onBindViewHolder(
        items: List<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val viewHolder = holder as ViewDataRenderer.ViewHolder
        val item = items[position]
        renderer.apply { viewHolder.bind(item as T, payloads) }
    }

    fun areItemsTheSame(
        oldItem: Any,
        newItem: Any
    ): Boolean =
        if (!renderer.clazz().isInstance(newItem)) false else
            (oldItem as? T)?.identify() ==
                    (newItem as? T)?.identify()

    private fun T.identify(): Any = renderer.apply { identify() }

    fun clazz(): KClass<T> {
        return renderer.clazz()
    }
}

abstract class UniqueDelegate<T : Any> : ViewDataRenderer<T>() {
    override fun T.identify(): Any = this@UniqueDelegate
}

abstract class StaticDelegate<T : Any> : UniqueDelegate<T>() {
    override fun ViewHolder.bind(
        item: T,
        payloads: MutableList<Any>
    ) {
    }
}

interface DataAdapterOwner {
    var delegatesFactory: () -> List<DataDelegate<*>>
}

class DataAdapterOwnerImpl : DataAdapterOwner {
    override var delegatesFactory: () -> List<DataDelegate<*>> = { emptyList() }
}