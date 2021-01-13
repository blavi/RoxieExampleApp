package com.example.roxieexampleapp.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.roxieexampleapp.databinding.ItemLayoutBinding
import com.example.roxieexampleapp.model.Image
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ImagesAdapter: RecyclerView.Adapter<ImagesAdapter.DataViewHolder>() {
    private var images: List<Image> = ArrayList<Image>(0)

    class DataViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String) {
            Glide.with(binding.image.context)
                .load(Uri.parse(imageUrl))
                .fitCenter()
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .placeholder(android.R.drawable.gallery_thumb)
                .into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(images[position].download_url)
    }

    fun addData(list: Array<Image>) {
        images= list.toList()
    }
}