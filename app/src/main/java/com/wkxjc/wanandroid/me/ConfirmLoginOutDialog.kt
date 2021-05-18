package com.wkxjc.wanandroid.me

import androidx.fragment.app.activityViewModels
import com.base.library.project.BaseDialogFragment
import com.wkxjc.wanandroid.databinding.DialogConfimLoginOutBinding

class ConfirmLoginOutDialog : BaseDialogFragment<DialogConfimLoginOutBinding>() {
    private val meViewModel by activityViewModels<MeViewModel>()

    override fun initView() {
        binding.btnYes.setOnClickListener {
            meViewModel.user.value?.loginOut()
            meViewModel.user.value = Tourist()
            dismiss()
        }
    }

    override fun initData() {
        binding.btnNo.setOnClickListener {
            dismiss()
        }
    }
}