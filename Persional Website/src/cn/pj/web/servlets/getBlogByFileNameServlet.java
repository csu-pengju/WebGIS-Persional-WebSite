package cn.pj.web.servlets;

import cn.pj.web.services.ArticleService;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "getBlogByFileNameServlet", value = "/getBlogByFileNameServlet")
public class getBlogByFileNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String blog_filename = request.getParameter("blog_filename");
        PrintWriter out = response.getWriter();
        ArticleService articleService = new ArticleService();
        JSONObject blog_list = articleService.getBlogByFileName(blog_filename);
        System.out.println(blog_list);
        out.write(blog_list.toString());
    }
}
