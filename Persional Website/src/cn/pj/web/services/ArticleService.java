package cn.pj.web.services;

import cn.pj.web.domain.ArticleInfo;
import cn.pj.web.persistence.ArticleDao;
import cn.pj.web.persistence.impl.ArticleDaoImpl;

import java.io.IOException;

public class ArticleService {
    private ArticleDao arctileDao;


    public boolean insertArticleInfo(ArticleInfo articleInfo){
        arctileDao = new ArticleDaoImpl();
        return arctileDao.insertIntoArticleInfo(articleInfo);
    }

    public void generateHtmlFile(String blog_html, String file_name) throws IOException {
        arctileDao = new ArticleDaoImpl();
        arctileDao.generateHtmlFile(blog_html,file_name);
    }
}
