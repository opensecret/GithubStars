package com.opensecret.githubstars.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.opensecret.githubstars.data.BaseData

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(data: BaseData, clickListener: UserListener, adapter: UserAdapter)
}