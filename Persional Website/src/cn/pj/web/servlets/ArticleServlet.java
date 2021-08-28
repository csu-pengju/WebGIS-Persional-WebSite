package cn.pj.web.servlets;

import cn.pj.web.domain.ArticleInfo;
import cn.pj.web.services.ArticleService;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@WebServlet(name = "ArticleServlet", value = "/ArticleServlet")
public class ArticleServlet extends HttpServlet {
    String setOfCharacters = "abcdefghijklmnopqrstuvwxyz";
    private ArticleService articleService;
    private ArticleInfo articleInfo;
    private String localPath = "D:\\Projects\\WebGIS-Persional-WebSite-master\\Persional Website\\web\\static\\html\\blogs\\";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        String blog_md = request.getParameter("blog_md");
        String blog_html = request.getParameter("blog_html");
        String blog_author = request.getParameter("blog_author");
        String blog_fileName = request.getParameter("blog_fileName");
        String blog_title = request.getParameter("blog_title");
        String blog_type = request.getParameter("blog_type");
        System.out.println(blog_html);
        articleService = new ArticleService();
        articleInfo = new ArticleInfo();
        articleInfo.setAuthor(blog_author);
        articleInfo.setMonth("");
        articleInfo.setDay("");
        articleInfo.setTitle(blog_title);
        articleInfo.setBlog_abstract("");

        articleInfo.setBlog_type(blog_type);
        articleInfo.setDay(request.getParameter("blog_day"));
        articleInfo.setMonth(request.getParameter("blog_month"));
        articleInfo.setBlog_html(blog_html);
        articleInfo.setBlog_md(blog_md);
//       保存文件,并且判断文件名是否已经存在
        blog_fileName = articleService.generateHtmlFile(blog_md,blog_html, blog_fileName, localPath);
        articleInfo.setFileName(blog_fileName);

        String data = "";
        boolean res = articleService.insertArticleInfo(articleInfo);
        if (res){
            data = "{'success':'true'," +
                    "'message':'blog written successfully '}";
        }else {
            data = "{'success':'false'," +
                    "'message':'failed to insert blog in db '}";
        }


        out.write(data);
    }
}
