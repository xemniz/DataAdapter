package com.xmn.dataadapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xmn.dataadapter.delegates.TextRenderer
import com.xmn.dataadapter.delegates.TextViewData
import com.xmn.dataadapter.lib.dataadapter.DataDelegate
import com.xmn.dataadapter.lib.dataadapter.DataAdapter
import com.xmn.dataadapter.lib.dataadapter.dataAdapterItems

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = DataAdapter(
                listOf(
                    DataDelegate(TextRenderer())
                )
            )
            dataAdapterItems = listOf(
                TextViewData("1", "1"),
                TextViewData("2", "2"),
                TextViewData("3", "3")
            )
        }
    }
}