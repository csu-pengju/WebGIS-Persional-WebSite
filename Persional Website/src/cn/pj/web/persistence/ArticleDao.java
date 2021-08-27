package cn.pj.web.persistence;

import cn.pj.web.domain.ArticleInfo;

import java.io.IOException;

public interface ArticleDao {
    public boolean insertIntoArticleInfo(ArticleInfo articleInfo);

    public void generateHtmlFile(String blog_html, String blog_fileName) throws IOException;


}
