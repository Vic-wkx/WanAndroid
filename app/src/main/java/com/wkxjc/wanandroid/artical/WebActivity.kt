package com.wkxjc.wanandroid.artical

import android.view.ViewGroup.LayoutParams
import android.widget.LinearLayout
import com.base.library.project.BaseActivity
import com.just.agentweb.AgentWeb
import com.wkxjc.wanandroid.R
import kotlinx.android.synthetic.main.activity_web.*

const val LINK = "link"

class WebActivity : BaseActivity() {

    private lateinit var agentWeb: AgentWeb

    override fun layoutId() = R.layout.activity_web

    override fun initView() {
        agentWeb = AgentWeb.with(this)
            .setAgentWebParent(webContainer, LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
            .go(intent.extras?.getString(LINK)?.replace("http://", "https://"))
    }

    override fun initData() {
    }

    override fun onResume() {
        agentWeb.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onPause() {
        agentWeb.webLifeCycle.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        agentWeb.webLifeCycle.onDestroy()
        super.onDestroy()
    }
}
