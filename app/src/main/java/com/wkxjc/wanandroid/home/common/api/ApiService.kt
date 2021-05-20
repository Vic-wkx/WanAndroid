package com.wkxjc.wanandroid.home.common.api

import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {

    // 问答
    @GET("wenda/list/{page}/json")
    fun getQA(@Path("page") page: Int)

    // 分享文章
    @POST("lg/user_article/add/json")
    @FormUrlEncoded
    fun shareArticle(@Field("title") title: String, @Field("link") link: String)

    // 删除自己分享的文章
    @POST("lg/user_article/delete/{sharedArticleId}/json")
    fun deleteMySquareArticle(@Path("sharedArticleId") sharedArticleId: Int)

    // 自己分享的文章列表
    @GET("user/lg/private_articles/{page}/json")
    fun getMySquare(@Path("page") page: Int)

    // 分享人对应列表数据
    @GET("user/{id}/share_articles/{page}/json")
    fun getSquareByUser(@Path("id") id: Int, @Path("page") page: Int)

    // 广场列表数据
    @GET("user_article/list/{page}/json")
    fun getSquare(@Path("page") page: Int)

    // 获取个人积分获取列表
    @GET("lg/coin/list/{page}/json")
    fun getMyRankSource(@Path("page") page: Int)

    // 获取个人积分
    @GET("lg/coin/userinfo/json")
    fun getMyRank()

    // 积分排行榜
    @GET("coin/rank/{page}/json")
    fun getRank(@Path("page") page: Int)

    // TODO列表
    // 页码从1开始，拼接在url 上
    // status 状态， 1-完成；0未完成; 默认全部展示；
    // type 创建时传入的类型, 默认全部展示
    // priority 创建时传入的优先级；默认全部展示
    // orderby 1:完成日期顺序；2.完成日期逆序；3.创建日期顺序；4.创建日期逆序(默认)；

    @GET("lg/todo/v2/list/{page}/json")
    fun getTodoList(
        @Path("page") page: Int,
        @Query("status") status: Int?, @Query("type") type: Int?,
        @Query("priority") priority: Int?, @Query("orderby") orderBy: Int?
    ): Observable<String>

    // 仅更新完成状态TODO
    @POST("lg/todo/done/{id}/json")
    @FormUrlEncoded
    fun updateTodoStatus(@Path("id") id: Int, @Field("status") status: Int): Observable<String>

    // 删除一个TODO
    @POST("lg/todo/delete/{id}/json")
    fun deleteTodo(@Path("id") id: Int): Observable<String>

    // 更新一个TODO
    @POST("lg/todo/update/{id}/json")
    @FormUrlEncoded
    fun updateTodo(
        @Path("id") id: Int, @Field("title") title: String,
        @Field("content") content: String? = null, @Field("date") date: String,
        @Field("status") status: Int? = null, @Field("type") type: Int? = null,
        @Field("priority") priority: Int? = null
    ): Observable<String>

    // 新增一个TODO
    @POST("lg/todo/add/json")
    @FormUrlEncoded
    fun addTodo(
        @Field("title") title: String, @Field("content") content: String? = null,
        @Field("date") date: String? = null, @Field("type") type: Int? = null,
        @Field("priority") priority: Int? = null
    ): Observable<String>

    // 搜索
    @POST("article/query/{page}/json")
    fun search()

    // 删除收藏网站
    @POST("lg/collect/deletetool/json")
    fun deleteCollectWebsite(@Field("id") id: Int)

    // 编辑收藏网站
    @POST("lg/collect/updatetool/json")
    @FormUrlEncoded
    fun editCollectWebsite(@Field("id") id: Int, @Field("name") name: String, @Field("link") link: String)

    // 收藏网址
    @POST("lg/collect/addtool/json")
    fun collectWebsite(@Query("id") id: Int, @Query("name") name: String, @Query("link") link: String)

    // 收藏网站列表
    @GET("lg/collect/usertools/json")
    fun getMyCollectWebsites()

    // 我的收藏页面取消收藏
    @POST("lg/uncollect/{articleId}/json")
    @FormUrlEncoded
    fun unCollectArticle(@Path("articleId") articleId: Int, @Field("originId") originId: Int): Observable<String>

    // 首页文章列表页面取消收藏
    @POST("lg/uncollect_originId/{articleId}/json")
    fun unCollectArticle(@Path("articleId") articleId: Int): Observable<String>

    // 收藏站外文章
    @POST("lg/collect/add/json")
    @FormUrlEncoded
    fun collectArticleOutside(
        @Field("title") title: String, @Field("author") author: String,
        @Field("link") link: String
    )

    // 收藏站内文章
    @POST("lg/collect/{articleId}/json")
    fun collectArticleInside(@Path("articleId") articleId: Int): Observable<String>

    // 收藏文章列表
    @GET("lg/collect/list/{page}/json")
    fun getCollectArticles(@Path("page") page: Int): Observable<String>

    // 退出
    @GET("user/logout/json")
    fun logOut()

    // 注册
    @POST("user/register")
    @FormUrlEncoded
    fun register(@Field("username") userName: String, @Field("password") password: String, @Field("repassword") rePassword: String): Observable<String>

    // 登录 Fixme 登陆接口的 POST 参数是拼接在 url 后面的，而不是用的 json，导致无法使用 @Body
    @POST("user/login")
    @FormUrlEncoded
    fun login(@Field("username") userName: String, @Field("password") password: String): Observable<String>

    // 项目列表数据
    @GET("project/list/{page}/json")
    fun getProjectList(@Path("page") page: Int, @Query("cid") cid: Int)

    // 项目分类
    @GET("project/tree/json")
    fun getProjectTree()

    // 导航数据
    @GET("navi/json")
    fun getNavigation(): Observable<String>

    // 根据作者昵称搜索文章
    @GET("article/list/{page}/json")
    fun getArticlesByAuthor(@Path("page") page: Int, @Query("author") author: String)

    // 知识体系下的文章
    @GET("article/list/{page}/json")
    fun getArticlesByCategoryId(@Path("page") page: Int, @Query("cid") cid: Int): Observable<String>

    // 体系数据
    @GET("tree/json")
    fun getKnowledgeTree(): Observable<String>

    // 置顶文章
    @GET("article/top/json")
    fun getTopArticles()

    // 搜索热词
    @GET("hotkey/json")
    fun getHotKey()

    // 常用网站
    @GET("friend/json")
    fun getCommonWebsites(): Observable<String>

    // 首页 banner
    @GET("banner/json")
    fun getBanner(): Observable<String>

    // 首页文章
    @GET("article/list/{page}/json")
    fun getHomeArticles(@Path("page") page: Int): Observable<String>

    // 在某个公众号中搜索历史文章
    @GET("wxarticle/list/{id}/{page}/json")
    fun searchPublicAccountArticle(@Query("k") key: String, @Path("id") id: Int, @Path("page") page: Int)

    // 查看某个公众号历史数据
    @GET("wxarticle/list/{id}/{page}/json")
    fun getPublicAccountArticles(@Path("id") id: Int, @Path("page") page: Int): Observable<String>

    // 获取公众号列表
    @GET("wxarticle/chapters/json")
    fun getPublicAccount(): Observable<String>
}