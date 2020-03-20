package com.base.library.banner

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.base.library.banner.ImageAdapter.BannerViewHolder
import com.youth.banner.adapter.BannerAdapter

class ImageAdapter(data: List<String>) : BannerAdapter<String, BannerViewHolder>(data) {
    private lateinit var context: Context
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        context = parent.context
        val imageView = ImageView(parent.context)
        imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }

    override fun onBindView(holder: BannerViewHolder, data: String, position: Int, size: Int) {
        Glide.with(context).load(data).into(holder.imageView)
    }

    class BannerViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)
}