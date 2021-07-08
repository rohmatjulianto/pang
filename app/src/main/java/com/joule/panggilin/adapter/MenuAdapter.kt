package com.joule.panggilin.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joule.panggilin.R
import com.joule.panggilin.databinding.ItemMenuBinding
import com.joule.panggilin.detail.DetailActivity

class MenuAdapter(val data: ArrayList<String>) : RecyclerView.Adapter<MenuAdapter.viewHolder>() {
    class viewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemMenuBinding.bind(itemView)
        fun onBind(s: String) {
            binding.tvMenu.text = s
            binding.tvMenu.setOnClickListener {
                val intent = Intent(it.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_MENU, s)
                it.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount(): Int {
       return data.size
    }
}