package com.opensecret.githubstars.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import com.opensecret.githubstars.R
import com.opensecret.githubstars.databinding.FragmentRemoteBinding
import com.opensecret.githubstars.util.hideKeyboard
import com.opensecret.githubstars.viewmodel.UserViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RemoteFragment : DaggerFragment() {

    lateinit var binding: FragmentRemoteBinding
    @Inject
    lateinit var userViewModel: UserViewModel
    lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_remote, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListener()
        userAdapter = UserAdapter(UserListener { it ->
            if (it.favorite) {
                userViewModel.removeFavoriteUser(it)
                it.favorite = false
                userViewModel.searchLocalUser(binding.etSearch.text.toString())
            } else {
                userViewModel.addFavoriteUser(it)
                it.favorite = true
                userViewModel.searchLocalUser(binding.etSearch.text.toString())
            }
        })
        binding.rvRemote.apply { adapter = userAdapter }
        userViewModel.remoteList.observe(viewLifecycleOwner) { t->
            t?.apply { userAdapter.setAdapterList(t) }
        }
    }

    private fun setListener() {
        binding.ivDelete.setOnClickListener{ binding.etSearch.text?.clear() }
        binding.ivSearch.setOnClickListener { userViewModel.searchRemoteUser(binding.etSearch.text.toString()) }
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                binding.ivDelete.visibility = if (editable.toString().isNullOrEmpty()) View.GONE else View.VISIBLE
            }
        })
        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                userViewModel.searchRemoteUser(binding.etSearch.text.toString())
                return@setOnEditorActionListener true
            } else {
                return@setOnEditorActionListener false
            }
        }
        binding.etSearch.setOnFocusChangeListener { _, hasFocus ->
            binding.ivDelete.visibility = if (hasFocus && binding.etSearch.text.toString().isNullOrEmpty().not()) View.VISIBLE else View.GONE
        }
        binding.rvRemote.setOnTouchListener { _, _ ->
            hideKeyboard()
            false
        }
    }
}