package com.xmn.dataadapter.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xmn.dataadapter.DataAdapter
import com.xmn.dataadapter.dataAdapterItems
import com.xmn.dataadapter.example.delegates.*


class HeterogeneousListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_heterogenous_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = DataAdapter.from(
            listOf(
                TextRendererBlue(),
                TextRendererYellow(),
                TextRendererGreen(),
                TextRendererRed()
            )
        )
        setShuffledList(recyclerView)
        view.findViewById<Button>(R.id.btn_shuffle).setOnClickListener {
            setShuffledList(recyclerView)
        }
    }

    private fun setShuffledList(recyclerView: RecyclerView) {
        val listSize = 50
        val list = (1..listSize / 2).map { GreenTextViewData(it.toString(), it.toString()) } +
                (1..listSize / 2).map { RedTextViewData(it.toString(), it.toString()) } +
                (1..listSize / 2).map { YellowTextViewData(it.toString(), it.toString()) } +
                (1..listSize / 2).map { BlueTextViewData(it.toString(), it.toString()) }
        recyclerView.dataAdapterItems =
            list.shuffled().dropLast(listSize)
    }
}