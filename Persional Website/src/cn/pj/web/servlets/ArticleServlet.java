package cn.pj.web.servlets;

import cn.pj.web.domain.ArticleInfo;
import cn.pj.web.services.ArticleService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
        articleInfo.setFileName(blog_fileName);
//       保存文件
        articleService.generateHtmlFile(blog_html, localPath + blog_fileName+".md");
        articleService.generateHtmlFile(blog_html, localPath + blog_fileName+".html");
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
