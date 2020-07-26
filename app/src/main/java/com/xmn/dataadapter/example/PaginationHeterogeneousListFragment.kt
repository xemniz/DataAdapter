package com.xmn.dataadapter.example

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.postDelayed
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.xmn.dataadapter.DataAdapter
import com.xmn.dataadapter.dataAdapterItems
import com.xmn.dataadapter.example.delegates.*
import kotlin.math.max


class PaginationHeterogeneousListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pagination_heterogenous_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        val dataAdapter = DataAdapter.from(
            listOf(
                TextRendererBlue(),
                TextRendererYellow(),
                TextRendererGreen(),
                TextRendererRed(),
                LoadingRenderer()
            )
        )
        recyclerView.adapter = dataAdapter
        dataAdapter.registerAdapterDataObserver(object :
            RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                val smoothScroller = object : LinearSmoothScroller(context) {
                    override fun getVerticalSnapPreference(): Int {
                        return SNAP_TO_START
                    }
                }
                smoothScroller.targetPosition = positionStart
                (recyclerView.layoutManager as LinearLayoutManager).startSmoothScroll(smoothScroller)
            }
        })
        addShuffledList(recyclerView)
        view.findViewById<Button>(R.id.btn_next_page).setOnClickListener {
            addShuffledList(recyclerView)
        }
    }

    private fun addShuffledList(recyclerView: RecyclerView) {
        val listSize = 40
        val oldItems = recyclerView.dataAdapterItems
        val from = oldItems.size / 4 + 1
        val to = (oldItems.size + listSize) / 4
        val list = (from..to).map { GreenTextViewData(it.toString(), it.toString()) } +
                (from..to).map { RedTextViewData(it.toString(), it.toString()) } +
                (from..to).map { YellowTextViewData(it.toString(), it.toString()) } +
                (from..to).map { BlueTextViewData(it.toString(), it.toString()) }
        recyclerView.dataAdapterItems = oldItems + Loading()

        view?.findViewById<Button>(R.id.btn_next_page)?.isEnabled = false
        Handler().postDelayed(1000) {
            recyclerView.dataAdapterItems = oldItems + list

            view?.findViewById<Button>(R.id.btn_next_page)?.isEnabled = true
        }
    }
}