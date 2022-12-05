package com.example.listviewanimation

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listviewanimation.databinding.FragmentABinding
import kotlin.random.Random


class FragmentA : Fragment() {
    lateinit var binding: FragmentABinding
    var songg = mutableListOf<Song>()
    private val adapter = CustomAdapter(this::OnItemClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentABinding.inflate(inflater, container, false)
        setupData()
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.my_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemAdd -> {
                addSongs()
                Log.d("song", songg.toString())
                adapter.setData(songg)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupData() {
        binding.listItem.layoutManager = LinearLayoutManager(context)
        binding.listItem.adapter = adapter
        adapter.setData(getSongs())
    }

    private fun addSongs(): List<Song> {
        val list = listOf(
            "Baby Shark", "Lucky", "Baby I Love You", "Why not't me",
            "My Love", "You're A Woman", "All that she wants", "Seft Control", "Beautiful Sunday"
        )
        val randomIndex = Random.nextInt(list.size);
        val randomElement = list[randomIndex]
        songg.add(Song(randomElement))
        return songg
    }

    private fun getSongs(): List<Song> {
        songg.add(Song("A Litte Love"))
        songg.add(Song("Hotel California"))
        songg.add(Song("Forever And One"))
        songg.add(Song("Apologize"))
        return songg
    }

    private fun OnItemClick(song: Song, position: Int) {
        songg.remove(song)
        adapter.setData(songg)
        adapter.notifyDataSetChanged()
    }
}