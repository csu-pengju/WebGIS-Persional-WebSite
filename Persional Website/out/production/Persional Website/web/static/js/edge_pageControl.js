

EdgePageControl = function () {
    var me = this;
    me._init()
    me.monthJson = {1:"Jan", 2:"Feb", 3:"Mar", 4:"Apr", 5:"May", 6:"Jun",
        7:"Jul", 8:"Aug", 9:"Sep", 10:"Oct", 11:"Nov", 12:"Dec"}
    me.month = '';
    me.day = "";
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
    var blog_type =$('input:radio:checked').val();
    me.getMonthDayStr()
    console.log(blog_type)
    me.getMonthDayStr();
    console.log(author, fileName, title, blog_type)
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
            'blog_month': me.month,
            'blog_day': me.day
        },
        dataType:'text',
        success: function(data){
                console.log(data)
            },
    });
}

EdgePageControl.prototype.getMonthDayStr = function (){
    var me = this;
    var date = new Date();
    var nowMonth = date.getMonth() + 1;
    var strDate = date.getDate();
    me.month = me.monthJson[nowMonth]
    me.day = strDate.toString();

}