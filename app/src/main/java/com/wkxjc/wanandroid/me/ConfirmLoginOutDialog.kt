package com.wkxjc.wanandroid.me

import android.view.Gravity
import androidx.fragment.app.activityViewModels
import com.base.library.project.BaseDialogFragment
import com.wkxjc.wanandroid.databinding.DialogConfimLoginOutBinding

class ConfirmLoginOutDialog : BaseDialogFragment<DialogConfimLoginOutBinding>() {
    private val meViewModel by activityViewModels<MeViewModel>()

    override fun widthPercentage() = 70
    override fun heightPercentage() = 25
    override fun gravity() = Gravity.CENTER

    override fun initView() {
        binding.ivClose.setOnClickListener {
            dismiss()
        }
        binding.btnYes.setOnClickListener {
            meViewModel.user.value?.loginOut()
            meViewModel.user.value = Tourist()
            dismiss()
        }
        binding.btnNo.setOnClickListener {
            dismiss()
        }
    }

    override fun initData() {
    }
}