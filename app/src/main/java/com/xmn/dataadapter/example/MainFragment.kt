package com.xmn.dataadapter.example

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_heterogenous_list).setOnClickListener {
            findNavController().navigate(R.id.HeterogenousListFragment)
        }

        view.findViewById<Button>(R.id.button_pagination_heterogenous_list).setOnClickListener {
            findNavController().navigate(R.id.paginationHeterogeneousListFragment)
        }
    }
}