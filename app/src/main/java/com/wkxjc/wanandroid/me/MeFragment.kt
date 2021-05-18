package com.wkxjc.wanandroid.me

import com.base.library.project.BaseFragment
import com.base.library.project.myStartActivity
import com.bumptech.glide.Glide
import com.wkxjc.wanandroid.MyApplication
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.databinding.FragmentMeBinding
import com.wkxjc.wanandroid.me.language.LanguageActivity

class MeFragment : BaseFragment<FragmentMeBinding>() {

    override fun initView() {

        binding.btnChangeLanguage.setOnClickListener {
            myStartActivity<LanguageActivity>()
        }
    }

    override fun initData() {
        val user = MyApplication.user
        Glide.with(this).load(user.avatar).circleCrop().fallback(R.drawable.ic_avatar_tourist).into(binding.ivAvatar)
        binding.tvUserName.text = user.name
        binding.tvUserDescription.text = user.description
        binding.btnLogin.setText(user.logonButtonDisplayedResId)
        binding.btnLogin.setOnClickListener {
            user.onClickLogon(context!!)
        }
        binding.btnMyCollection.setOnClickListener {
            user.onClickMyCollection(context!!)
        }
        binding.btnMyTodo.setOnClickListener {
            user.onClickMyTODO(context!!)
        }
    }

}