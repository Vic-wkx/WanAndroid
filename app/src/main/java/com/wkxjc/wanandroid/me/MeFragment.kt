package com.wkxjc.wanandroid.me

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.base.library.project.BaseFragment
import com.base.library.project.myStartActivity
import com.base.library.project.showToast
import com.bumptech.glide.Glide
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.databinding.FragmentMeBinding
import com.wkxjc.wanandroid.me.language.LanguageActivity

class MeFragment : BaseFragment<FragmentMeBinding>() {

    private val meViewModel by activityViewModels<MeViewModel>()
    override fun initView() {
        binding.btnChangeLanguage.setOnClickListener {
            myStartActivity<LanguageActivity>()
        }
    }

    override fun initData() {
        meViewModel.user.observe(this) { user ->
            Glide.with(this).load(user.avatar).circleCrop().placeholder(ColorDrawable(Color.BLUE)).fallback(user.avatarFallbackResId).into(binding.ivAvatar)
            binding.tvUserName.text = user.name
            binding.tvUserDescription.text = user.description
            binding.btnLogin.setText(user.logonButtonDisplayedResId)
            binding.btnLogin.setOnClickListener {
                user.onClickLogon(this)
            }
            binding.btnMyCollection.setOnClickListener {
                user.onClickMyCollection(requireContext())
            }
            binding.btnMyTodo.setOnClickListener {
                user.onClickMyTODO(requireContext())
            }
            binding.btnWaiting.setOnClickListener {
                showToast(R.string.stay_tuned_hint)
            }
        }
    }
}