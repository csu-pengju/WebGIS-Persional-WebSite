
BlogTemplate = function (options){
    let me = this;
    me._init(options)
    me.options = options
}

BlogTemplate.prototype._init =function (blog_filename){
    let me = this;
    $.ajax({
        type: 'POST',
        url: '/Persional_Website/getBlogByFileNameServlet',
        data:{
            'blog_filename':blog_filename
        },
        dataType:'text',
        success: function (res) {
            console.log(res)
            me.blog_list = JSON.parse(res)['blog_list']
            me.setBlogTemplate_Html(me.blog_list[0]);
        }
    });
}

BlogTemplate.prototype.setBlogTemplate_Html = function (blog) {
    console.log(blog)
    let me = this;
    let blog_html = blog["blog_html"];
    let blog_author = blog['blog_author']
    let blog_title = blog['blog_title']
    let blog_month = blog['blog_month']
    let blog_day = blog['blog_day']
    let blog_time = blog['blog_time'];
    $(".blog_author").text("by "+blog_author+ "   |  ")
    $(".blog_time").text(blog_time)
    $(".blog-title").text(blog_title)
    $(".date-month").text(blog_month)
    $(".date-day").text(blog_day)
    $(".blog_content").html(blog_html)

}