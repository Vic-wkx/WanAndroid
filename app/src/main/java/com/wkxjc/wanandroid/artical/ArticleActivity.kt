package com.wkxjc.wanandroid.artical

import android.view.ViewGroup.LayoutParams
import android.widget.LinearLayout
import com.base.library.project.BaseActivity
import com.just.agentweb.AgentWeb
import com.wkxjc.wanandroid.R
import kotlinx.android.synthetic.main.activity_article.*


class ArticleActivity : BaseActivity() {

    private lateinit var agentWeb: AgentWeb

    override fun layoutId() = R.layout.activity_article

    override fun initView() {
        agentWeb = AgentWeb.with(this)
            .setAgentWebParent(webContainer, LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
            .go(intent.extras?.getString("link"))
    }

    override fun initData() {
    }

    override fun onPause() {
        agentWeb.webLifeCycle.onPause()
        super.onPause()
    }

    override fun onResume() {
        agentWeb.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        agentWeb.webLifeCycle.onDestroy()
        super.onDestroy()
    }
}
