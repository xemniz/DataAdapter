package com.xmn.dataadapter.example.delegates

import android.graphics.Color
import android.view.ViewGroup
import android.widget.TextView
import com.xmn.dataadapter.StaticDelegate
import com.xmn.dataadapter.ViewDataRenderer
import com.xmn.dataadapter.example.R
import com.xmn.dataadapter.inflate
import kotlin.reflect.KClass

data class GreenTextViewData(val id: String, val string: String)
data class RedTextViewData(val id: String, val string: String)
data class BlueTextViewData(val id: String, val string: String)
data class YellowTextViewData(val id: String, val string: String)
class Loading

class TextRendererGreen : ViewDataRenderer<GreenTextViewData>() {
    override fun clazz(): KClass<GreenTextViewData> = GreenTextViewData::class

    override fun ViewGroup.view() = inflate(R.layout.text)

    override fun GreenTextViewData.identify() = id

    override fun ViewHolder.bind(
        item: GreenTextViewData,
        oldItem: GreenTextViewData?
    ) {
        view<TextView>(R.id.textView).text = item.string
        view<TextView>(R.id.textView).setBackgroundColor(Color.GREEN)
    }
}

class TextRendererRed : ViewDataRenderer<RedTextViewData>() {
    override fun clazz(): KClass<RedTextViewData> = RedTextViewData::class

    override fun ViewGroup.view() = inflate(R.layout.text)

    override fun RedTextViewData.identify() = id

    override fun ViewHolder.bind(
        item: RedTextViewData,
        oldItem: RedTextViewData?
    ) {
        containerView.findViewById<TextView>(R.id.textView).text = item.string
        containerView.findViewById<TextView>(R.id.textView).setBackgroundColor(Color.RED)
    }
}

class TextRendererBlue : ViewDataRenderer<BlueTextViewData>() {
    override fun clazz(): KClass<BlueTextViewData> = BlueTextViewData::class

    override fun ViewGroup.view() = inflate(R.layout.text)

    override fun BlueTextViewData.identify() = id

    override fun ViewHolder.bind(
        item: BlueTextViewData,
        oldItem: BlueTextViewData?
    ) {
        containerView.findViewById<TextView>(R.id.textView).text = item.string
        containerView.findViewById<TextView>(R.id.textView).setBackgroundColor(Color.BLUE)
    }
}

class TextRendererYellow : ViewDataRenderer<YellowTextViewData>() {
    override fun clazz(): KClass<YellowTextViewData> = YellowTextViewData::class

    override fun ViewGroup.view() = inflate(R.layout.text)

    override fun YellowTextViewData.identify() = id

    override fun ViewHolder.bind(
        item: YellowTextViewData,
        oldItem: YellowTextViewData?
    ) {
        containerView.findViewById<TextView>(R.id.textView).text = item.string
        containerView.findViewById<TextView>(R.id.textView).setBackgroundColor(Color.YELLOW)
    }
}

class LoadingRenderer : StaticDelegate<Loading>() {
    override fun clazz(): KClass<Loading> = Loading::class

    override fun ViewGroup.view() = inflate(R.layout.item_loading)
}