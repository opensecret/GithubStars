package com.opensecret.githubstars.ui

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.opensecret.githubstars.R
import com.opensecret.githubstars.data.BaseData
import com.opensecret.githubstars.databinding.ItemHeaderBinding
import com.opensecret.githubstars.databinding.ItemUserBinding
import com.opensecret.githubstars.model.User

class UserAdapter(val clickListener: UserListener) : RecyclerView.Adapter<BaseViewHolder>() {

    var selectedItems: SparseBooleanArray = SparseBooleanArray(0)
    private var list: List<BaseData> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            BaseData.HEADER -> {
                val binding: ItemHeaderBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_header,
                    parent,
                    false
                )
                HeaderViewHolder(binding)
            }
            BaseData.USER -> {
                val binding: ItemUserBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_user,
                    parent,
                    false
                )
                UserViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.bind(list[position], clickListener, this)

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = list[position].getType()

    fun setAdapterList(list: List<BaseData>) {
        this.list = list
        notifyDataSetChanged()
    }
}

open class UserListener(val clickListener: (user: User) -> Unit) {
    fun onClick(user: User) = clickListener(user)
}