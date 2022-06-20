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
import com.opensecret.githubstars.data.BaseData
import com.opensecret.githubstars.data.UserData
import com.opensecret.githubstars.databinding.FragmentLocalBinding
import com.opensecret.githubstars.factory.ViewModelFactory
import com.opensecret.githubstars.util.hideKeyboard
import com.opensecret.githubstars.viewmodel.UserViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LocalFragment : DaggerFragment() {

    lateinit var binding: FragmentLocalBinding
    @Inject
    lateinit var userViewModel: UserViewModel
    lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_local, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListener()
        userAdapter = UserAdapter(UserListener {
            userViewModel.removeFavoriteUser(it)
        })
        binding.rvLocal.apply { adapter = userAdapter }
        userViewModel.localList.observe(viewLifecycleOwner) { t->
            t?.apply { userAdapter.setAdapterList(t) }
        }
        userViewModel.searchLocalUser("")
    }

    private fun setListener() {
        binding.ivDelete.setOnClickListener{ binding.etSearch.text?.clear() }
        binding.ivSearch.setOnClickListener { userViewModel.searchLocalUser(binding.etSearch.text.toString()) }
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
                userViewModel.searchLocalUser(binding.etSearch.text.toString())
                return@setOnEditorActionListener true
            } else {
                return@setOnEditorActionListener false
            }
        }
        binding.etSearch.setOnFocusChangeListener { _, hasFocus ->
            binding.ivDelete.visibility = if (hasFocus && binding.etSearch.text.toString().isNullOrEmpty().not()) View.VISIBLE else View.GONE
        }
        binding.rvLocal.setOnTouchListener { _, _ ->
            hideKeyboard()
            false
        }
    }
}