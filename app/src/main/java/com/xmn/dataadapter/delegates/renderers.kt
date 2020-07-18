package com.xmn.dataadapter.delegates

import android.view.ViewGroup
import android.widget.TextView
import com.xmn.dataadapter.R
import com.xmn.dataadapter.lib.dataadapter.ViewDataRenderer
import com.xmn.dataadapter.lib.dataadapter.inflate
import kotlin.reflect.KClass

data class TextViewData(val id: String, val string: String)

class TextRenderer : ViewDataRenderer<TextViewData>() {
    override fun clazz(): KClass<TextViewData> = TextViewData::class

    override fun ViewGroup.view() = inflate(R.layout.text)

    override fun ViewHolder.bind(
        item: TextViewData,
        payloads: MutableList<Any>
    ) {
        view<TextView>(R.id.textView).text = item.string
    }

    override fun TextViewData.identify() = id
}