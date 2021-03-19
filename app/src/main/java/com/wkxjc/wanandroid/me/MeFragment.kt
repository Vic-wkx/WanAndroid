package com.wkxjc.wanandroid.me

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import com.base.library.project.BaseFragment
import com.base.library.project.myStartActivity
import com.base.library.rxRetrofit.common.utils.SPUtils
import com.wkxjc.wanandroid.databinding.FragmentMeBinding
import com.wkxjc.wanandroid.me.collection.CollectionActivity
import com.wkxjc.wanandroid.R
import com.wkxjc.wanandroid.me.common.api.LOGIN_INFO
import com.wkxjc.wanandroid.me.common.api.PASSWORD
import com.wkxjc.wanandroid.me.common.api.USER_NAME
import com.wkxjc.wanandroid.me.language.LanguageActivity
import com.wkxjc.wanandroid.me.user.User
import com.wkxjc.wanandroid.me.login.LoginActivity
import com.wkxjc.wanandroid.me.todo.TodoActivity
import com.wkxjc.wanandroid.me.user.UserActivity

class MeFragment : BaseFragment() {

    private var _binding: FragmentMeBinding? = null
    private val binding get() = _binding!!
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentMeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun releaseBinding() {
        _binding = null
    }

    override fun initView() {
        binding.tvUser.setOnClickListener {
            startActivity<UserActivity>()
        }
        binding.btnLogin.setOnClickListener {
            startActivity<LoginActivity>()
        }
        binding.btnChangeLanguage.setOnClickListener {
            startActivity<LanguageActivity>()
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