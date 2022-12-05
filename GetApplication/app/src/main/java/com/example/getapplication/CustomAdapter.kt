package com.example.getapplication


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.getapplication.databinding.GridItemBinding

class CustomAdapter(
    private val onItemClick: (App, Int) -> Unit
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private val apps = mutableListOf<App>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        GridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), onItemClick
    )

    override fun getItemCount(): Int {
        return apps.size
    }

    fun setData(data: List<App>) {
        apps.clear()
        apps.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(apps[position])

    }

    class ViewHolder(
        private val binding: GridItemBinding,
        private val onItemClick: (App, Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(app: App) {
            binding.apply {
                appName.text = app.appName
                appIcon.setImageDrawable(app.icon)
                val activity = binding.root.getContext() as AppCompatActivity
                root.setOnClickListener {
                    val intent = activity.getPackageManager()
                        .getLaunchIntentForPackage(app.packages)
                    activity.startActivity(intent)
                }
            }
        }
    }
}