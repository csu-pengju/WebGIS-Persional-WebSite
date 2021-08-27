

ArticleControl = function (options){
    var me = this;
    me._init()
    me.CreateArticleDom()

}

ArticleControl.prototype._init = function (){
    /*
    初始化一些参数
     */
    var me = this;
    me.article = []
    me.abstract = ''
    me.title = ''
    me.day = ''
    me.month = ''
    me.author = ''
    me.date = ''
}

ArticleControl.prototype.getArticle_list = function (){
    /*
    获取文章列表
     */
    var me = this;
    $.ajax({
        type: 'POST',
        url: "/Persional_Website/getArticleList",
        data: {},
        success: function (res) {
            res = res.replace(/'/g, '\"')
            console.log( res)
            const jsons = JSON.parse(res);
            if (jsons['success'] == 'true'){
                me.article = jsons['articles']
                me.CreateArticleDom()
            }
        },
    });
}

ArticleControl.prototype.CreateArticleDom = function (){
    /*
    根据获取的文章列表渲染html页面的最新文章
     */
    var me = this;
    me.ul = document.getElementsByClassName("latest-ul")[0]


    for(var i=0;i<1; i++){
        me.li = $("<li>").appendTo(me.ul)
        me.li_div = $("<div>").appendTo(me.li).addClass("blog")
        me.li_div_divTitle = $("<div>").appendTo(me.li_div).addClass("blog-title-box")
        me.li_div_divAbstract = $("<div>").appendTo(me.li_div).addClass("blog-abstract-box")
        me.li_div_divTitle_id = $("<div>").appendTo(me.li_div_divTitle).addClass("blog-id")
        me.span_date_day = $("<span>").appendTo(me.li_div_divTitle_id).addClass("date-day")
        me.span_date_month = $("<span>").appendTo(me.li_div_divTitle_id).addClass("date-month")
        me.li_div_divTitle_title = $("<div>").appendTo(me.li_div_divTitle).addClass("blog-title")
        me.p_title = $("<p>").appendTo(me.li_div_divTitle_title)
        me.span_blog_author_time = $("<span>").appendTo(me.li_div_divTitle_title).addClass("blog_author_time")
        me.span_blog_author = $("<span>").appendTo(me.span_blog_author_time).addClass("blog-author")
        me.span_blog_time = $("<span>").appendTo(me.span_blog_author_time).addClass("blog-time")
        me.p_abstract = $("<p>").addClass(me.li_div_divAbstract).addClass("blog-abstract")

    }
    console.log(me.ul)

}