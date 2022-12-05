package com.example.assignmentday5

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.findFragment
import com.example.assignmentday5.databinding.FragmentMessageBinding
import com.google.android.material.navigation.NavigationView

class MessageFragment : Fragment() {
    lateinit var binding: FragmentMessageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessageBinding.inflate(layoutInflater)
        return binding.root
    }
}