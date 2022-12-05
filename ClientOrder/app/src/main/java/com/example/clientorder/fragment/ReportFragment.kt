package com.example.clientorder.fragment

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clientorder.adapter.Constant.PARSE_URI
import com.example.clientorder.adapter.ReportAdapter
import com.example.clientorder.databinding.FragmentReportBinding
import com.example.clientorder.model.Report

class ReportFragment : Fragment() {
    private lateinit var binding: FragmentReportBinding
    private val report = mutableListOf<Report>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportBinding.inflate(layoutInflater)
        val adapter = ReportAdapter()
        binding.rvReport.adapter = adapter
        binding.rvReport.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val cursorTemp: Cursor =
            activity?.contentResolver?.query(PARSE_URI, null, null, null, null)!!
        if (cursorTemp.moveToFirst()) {
            do {
                report.add(
                    Report(
                        date = cursorTemp.getString(1),
                        totalValue = cursorTemp.getString(2),
                        name = cursorTemp.getString(0)
                    )
                )
            } while (cursorTemp.moveToNext())
        }
        adapter.submitData(report)
        return binding.root
    }
}