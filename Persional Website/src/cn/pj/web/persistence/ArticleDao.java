package cn.pj.web.persistence;

import cn.pj.web.domain.ArticleInfo;
import net.sf.json.JSONObject;

import java.io.IOException;

public interface ArticleDao {
    public boolean insertIntoArticleInfo(ArticleInfo articleInfo);

    public String generateHtmlFile(String blog_md,String blog_html, String blog_fileName, String folder) throws IOException;

    public JSONObject getBlogList();

    public JSONObject getBlogByFileName(String blog_filename);
}
