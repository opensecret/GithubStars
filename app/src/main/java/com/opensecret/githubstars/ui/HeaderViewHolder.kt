package com.opensecret.githubstars.ui

import com.opensecret.githubstars.data.HeaderData
import com.opensecret.githubstars.data.BaseData
import com.opensecret.githubstars.databinding.ItemHeaderBinding

class HeaderViewHolder(val binding: ItemHeaderBinding) : BaseViewHolder(binding.root) {

    override fun bind(data: BaseData, clickListener: UserListener, adapter: UserAdapter) {
        val headerData = data as HeaderData
        binding.tvIndex.text = headerData.index
    }
}