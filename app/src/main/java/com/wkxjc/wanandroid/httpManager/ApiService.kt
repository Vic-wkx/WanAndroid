package com.wkxjc.wanandroid.httpManager

import android.icu.text.CaseMap
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {
    // 收藏站外文章
    @POST("lg/collect/add/json")
    @FormUrlEncoded
    fun collectArticleOutside(
        @Field("title") title: String, @Field("author") author: String, @Field(
            "link"
        ) link: String
    )

    // 收藏站内文章
    @POST("lg/collect/{articleId}/json")
    fun collectArticleInside(@Path("articleId") articleId: Int)

    // 收藏文章列表
    @GET("lg/collect/list/{page}/json")
    fun getCollectArticles(@Path("page") page: Int)

    // 退出
    @GET("user/logout/json")
    fun logOut()

    // 注册
    @POST("user/register")
    @FormUrlEncoded
    fun register(@Field("username") userName: String, @Field("password") password: String, @Field("repassword") rePassword: String)

    // 登录
    @POST("user/login")
    @FormUrlEncoded
    fun login(@Field("username") userName: String, @Field("password") password: String)

    // 项目列表数据
    @GET("project/list/{page}/json")
    fun getProjectList(@Path("page") page: Int, @Query("cid") cid: Int)

    // 项目分类
    @GET("project/tree/json")
    fun getProjectTree()

    // 导航数据
    @GET("navi/json")
    fun getNavigation()

    // 根据作者昵称搜索文章
    @GET("article/list/{page}/json")
    fun getArticlesByAuthor(@Path("page") page: Int, @Query("author") author: String)

    // 知识体系下的文章
    @GET("article/list/{page}/json")
    fun getArticlesByCategoryId(@Path("page") page: Int, @Query("cid") cid: Int)

    // 体系数据
    @GET("tree/json")
    fun getTree()

    // 置顶文章
    @GET("article/top/json")
    fun getTopArticles()

    // 搜索热词
    @GET("hotkey/json")
    fun getHotKey()

    // 常用网站
    @GET("friend/json")
    fun getFriendWebsites()

    // 首页 banner
    @GET("banner/json")
    fun getBanner()

    // 首页文章
    @GET("article/list/{page}/json")
    fun getHomeArticles(@Path("page") page: Int)

    // 微信公众号列表接口
    @GET("wxarticle/chapters/json")
    fun getWeChatPublicAccount(): Observable<String>
}