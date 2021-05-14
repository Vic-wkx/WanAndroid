package com.wkxjc.wanandroid.me

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.library.project.BaseFragment
import com.base.library.project.myStartActivity
import com.wkxjc.wanandroid.databinding.FragmentMeBinding
import com.wkxjc.wanandroid.me.language.LanguageActivity
import com.wkxjc.wanandroid.me.login.LoginActivity
import com.wkxjc.wanandroid.me.user.User
import com.wkxjc.wanandroid.me.user.UserActivity

class MeFragment : BaseFragment<FragmentMeBinding>() {

    override fun initView() {
        binding.tvUser.setOnClickListener {
            myStartActivity<UserActivity>()
        }
        binding.btnLogin.setOnClickListener {
            myStartActivity<LoginActivity>()
        }
        binding.btnChangeLanguage.setOnClickListener {
            myStartActivity<LanguageActivity>()
        }
    }

    override fun initData() {
    }

    override fun onResume() {
        super.onResume()
        if (User.isLogon) {
            binding.tvUser.text = User.name
            binding.tvUser.visibility = View.VISIBLE
            binding.btnLogin.visibility = View.GONE
        } else {
            binding.tvUser.visibility = View.GONE
            binding.btnLogin.visibility = View.VISIBLE
        }
    }
}