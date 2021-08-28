package cn.pj.web.services;

import cn.pj.web.domain.ArticleInfo;
import cn.pj.web.persistence.ArticleDao;
import cn.pj.web.persistence.impl.ArticleDaoImpl;
import net.sf.json.JSONObject;

import java.io.IOException;

public class ArticleService {
    private ArticleDao arctileDao;


    public boolean insertArticleInfo(ArticleInfo articleInfo){
        arctileDao = new ArticleDaoImpl();
        return arctileDao.insertIntoArticleInfo(articleInfo);
    }

    public String generateHtmlFile(String blog_md, String blog_html, String file_name, String folder) throws IOException {
        arctileDao = new ArticleDaoImpl();
        return arctileDao.generateHtmlFile(blog_md, blog_html,file_name, folder);
    }

    public JSONObject getBlogList(){
        arctileDao = new ArticleDaoImpl();
        return arctileDao.getBlogList();
    }

    public JSONObject getBlogByFileName(String blog_fileName){
        arctileDao = new ArticleDaoImpl();
        return arctileDao.getBlogByFileName(blog_fileName);
    }
}
