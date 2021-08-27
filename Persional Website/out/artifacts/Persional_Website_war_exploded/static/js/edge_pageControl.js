

EdgePageControl = function () {
    var me = this;
    me._init()
}

EdgePageControl.prototype._init = function () {
}

EdgePageControl.prototype.submit_blog = function (){
    var me = this;
    var blog_html = $("#editormdhtml").val();
        var blog_md = $("#editormd").val()
    var author = $('input[name="author"]').val();
    var fileName = $('input[name="fileName"]').val();
    var title = $('input[name="title"]').val()
    var blog_type = $('input[name="blog_type"]').val()
    console.log(blog_html)
    // console.log(author, fileName, title, blog_type)
    $.ajax({
        type: 'POST',
        url: '/Persional_Website/ArticleServlet',
        data:{
            'blog_md':blog_md,
            'blog_html':blog_html,
            'blog_author':author,
            'blog_fileName':fileName,
            'blog_title':title,
            'blog_type': blog_type,
        },
        dataType:'text',
        success: function(data){
                console.log(data)
            },
    });
}