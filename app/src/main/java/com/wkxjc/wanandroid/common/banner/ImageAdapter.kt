package com.wkxjc.wanandroid.common.banner

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.common.banner.ImageAdapter.BannerViewHolder
import com.wkxjc.wanandroid.common.bean.BannerBean
import com.youth.banner.adapter.BannerAdapter

class ImageAdapter(data: List<BannerBean>) : BannerAdapter<BannerBean, BannerViewHolder>(data) {
    private lateinit var context: Context
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        context = parent.context
        val imageView = ImageView(parent.context)
        imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }

    override fun onBindView(holder: BannerViewHolder, data: BannerBean, position: Int, size: Int) {
        Glide.with(context).load(data.imagePath).placeholder(R.drawable.ic_img_placeholder).into(holder.imageView)
    }

    class BannerViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)
}