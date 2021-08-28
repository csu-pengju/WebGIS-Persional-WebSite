
BlogList = function () {

    let me = this;
    me.dd = ''
}

BlogList.prototype.init = function () {
    let me = this;
    let fileName = "test";
    $.ajax({
        type: 'POST',
        url: '/Persional_Website/getBlogsServlet',
        data:{
            'filename':fileName
        },
        dataType:'text',
        success: function (res) {
            me.blog_list = JSON.parse(res)['blog_list']
            console.log(me.blog_list)
            me.setBlogList();
        }
    });
}

BlogList.prototype.setBlogList = function () {
    let me = this;
    me.ul = $("#blog_container_ul")
    let blog_length = me.blog_list.length;
    for (let i = 0;i<blog_length;i++){
        let blog = me.blog_list[i]
        let blog_filename = blog['blog_filename']
        let li_class = me.createBlogElement(me.ul,i,blog_filename)
        let blog_author = blog['blog_author']
        let blog_title = blog['blog_title']
        let blog_month = blog['blog_month']
        let blog_day = blog['blog_day']
        let blog_abstract = blog['blog_abstract']
        let blog_time = blog['blog_time'];
        $("."+li_class+" .blog_author").text("by "+blog_author+ "   |  ")
        $("."+li_class+" .blog_time").text(blog_time)
        $("."+li_class+" .blog-title").text(blog_title)
        $("."+li_class+" .date-month").text(blog_month)
        $("."+li_class+" .date-day").text(blog_day)
        $("."+li_class + " .blog_abstract").text(blog_title)
        // $("."+li_class + " .read_all").on('click',function () {
        //     me.onclickA(blog)
        // })
    }
}

BlogList.prototype.createBlogElement = function (ul,i,fileName){
    let me = this;
    let li_class = "blog-"+i.toString()
    me.li = $("<li>").appendTo(ul).addClass(li_class)
    me.div_blog_title = $("<div>").appendTo(me.li).addClass("blog_title");
    me.div_date_month = $("<div>").appendTo(me.div_blog_title).addClass("date_month");
    me.span_date_day = $("<span>").appendTo(me.div_date_month).addClass("date-day");
    me.span_date_month_ = $("<span>").appendTo(me.div_date_month).addClass("date-month");

    me.div_blog_title_author = $("<div>").appendTo(me.div_blog_title).addClass("blog_title_author");
    me.span_blog_title = $("<span>").appendTo(me.div_blog_title_author).addClass("blog-title");
    me.span_author_time = $("<span>").appendTo(me.div_blog_title_author).addClass("author_time");
    me.span_blog_author = $("<span>").appendTo(me.span_author_time).addClass("blog_author");
    me.span_blog_time = $("<span>").appendTo(me.span_author_time).addClass("blog_time");

    me.div_blog_abstract = $("<div>").appendTo(me.li).addClass("blog_abstract").css({
        "margin":"20px 20px 0px 50px",
    })

    me.div_readAll = $("<div>").appendTo(me.li).addClass("read_all_box")
        .css({
            "margin":"10px 0px 0px 45px"
        })
    me.a_read_all = $("<a>").appendTo(me.div_readAll).addClass("read_all")
        .css({
        "text-decoration":"none",
        "font-size":"20px",
        "color":"darkblue",
            "font-weight":"bold",
    })
        .text("点击阅读全文………")
        .attr("href","../html/blog_template.html?blog_filename="+fileName)
    return li_class;
}

BlogList.prototype.onclickA = function (blog){
    let me = this;
    let blogTemplate = new BlogTemplate(blog);

    console.log(blog)
}

