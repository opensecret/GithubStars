package com.opensecret.githubstars.ui

import com.opensecret.githubstars.R
import com.opensecret.githubstars.data.BaseData
import com.opensecret.githubstars.data.UserData
import com.opensecret.githubstars.databinding.ItemUserBinding

class UserViewHolder(val binding: ItemUserBinding) : BaseViewHolder(binding.root) {

    private var mAdapter: UserAdapter? = null

    init {
        binding.root.setOnClickListener {
            if (binding.user == null) return@setOnClickListener
            binding.user?.let { user ->
                when (user.favorite) {
                    true -> {   // select -> unselect
                        if (mAdapter?.selectedItems?.get(adapterPosition, false) == true) {
                            mAdapter?.selectedItems?.delete(adapterPosition)
                            user.favorite = false
                            mAdapter?.notifyItemChanged(adapterPosition)
                        }
                    }
                    false -> {  // unselect -> select
                        mAdapter?.selectedItems?.put(adapterPosition, true)
                        user.favorite = true
                        mAdapter?.notifyItemChanged(adapterPosition)
                    }
                }
            }
        }
    }

    override fun bind(data: BaseData, clickListener: UserListener, adapter: UserAdapter) {
        mAdapter = adapter
        if (mAdapter?.selectedItems?.get(adapterPosition, false) == true ) {
            binding.ivFavorite.setImageResource(R.drawable.ic_baseline_star_48)
        } else if (mAdapter?.selectedItems?.get(adapterPosition, false) == false) {
            binding.ivFavorite.setImageResource(R.drawable.ic_baseline_star_border_48)
        }

        val userData = data as UserData
        binding.apply {
            user = userData.user
            this.clickListener = clickListener
            executePendingBindings()
        }
    }
}