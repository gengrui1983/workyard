package com.ruigeng.workyard.ui.main

import android.content.Context
import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ruigeng.workyard.R
import com.ruigeng.workyard.data.Item
import com.ruigeng.workyard.databinding.AdapterItemBinding
import com.ruigeng.workyard.utils.common.DataBoundListAdapter
import com.ruigeng.workyard.utils.common.DataBoundViewHolder

open class ItemsAdapter(private var dataBindingComponent: DataBindingComponent,
                        private val context: Context) :
        DataBoundListAdapter<Item, AdapterItemBinding>() {

    override fun createBinding(parent: ViewGroup?, viewType: Int): AdapterItemBinding {
        val binding = DataBindingUtil.inflate<AdapterItemBinding>(LayoutInflater.from(parent?.context),
                R.layout.adapter_item, parent, false, dataBindingComponent)

        return binding
    }

    override fun bind(binding: AdapterItemBinding?, item: Item?) {
        binding?.item = item
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder<AdapterItemBinding>, position: Int, payloads: MutableList<Any>) {
        items?.run {
            val item = this[position]

            Glide.with(context).load(item.photoUrl)
                    .into(holder.binding.imgIcon)

            Glide.with(context).load(item.defaultImageUrlOptimised)
                    .apply(RequestOptions.centerCropTransform().placeholder(R.drawable.loading))
                    .into(holder.binding.imgPost)

            holder.binding.txtName.text = if (item.name.isEmpty()) "Sydney Citizen" else item.name

            holder.binding.txtLikes.text = item.likeCount.toString()

            holder.binding.txtLocation.text = context.getString(R.string.location,
                    item.suburb, item.state)

            holder.binding.txtViews.text = item.viewCount.toString()
        }
    }

    override fun areItemsTheSame(oldItem: Item?, newItem: Item?): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: Item?, newItem: Item?): Boolean {
        return oldItem?.id == newItem?.id
    }
}