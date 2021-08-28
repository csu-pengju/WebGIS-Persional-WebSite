package cn.pj.web.domain;

import java.util.Date;

public class ArticleInfo {
    private String blog_author;
    private String blog_title;
    private String blog_abstract;
    private String blog_day;
    private String blog_month;
    private Date blog_time;
    private String blog_link;
    private String blog_md;
    private String blog_html;
    private String fileName;
    private String blog_type;

    public String getAuthor(){
        return blog_author;
    }

    public void setAuthor(String blog_author){
        this.blog_author = blog_author;
    }

    public String getTitle() {
        return blog_title;
    }

    public void setTitle(String blog_title) {
        this.blog_title = blog_title;
    }

    public Date getTime() {
        return blog_time;
    }

    public void setTime(Date blog_time) {
        this.blog_time = blog_time;
    }

    public String getDay() {
        return blog_day;
    }

    public void setDay(String blog_day) {
        this.blog_day = blog_day;
    }

    public String getBlog_abstract() {
        return blog_abstract;
    }

    public void setBlog_abstract(String blog_abstract) {
        this.blog_abstract = blog_abstract;
    }

    public String getMonth() {
        return blog_month;
    }

    public void setMonth(String blog_month) {
        this.blog_month = blog_month;
    }

    public String getBlog_link() {
        return blog_link;
    }
    public void setBlog_link(String blog_link) {
        this.blog_link = blog_link;
    }

    public String getBlog_html() {
        return blog_html;
    }

    public void setBlog_html(String blog_html) {
        this.blog_html = blog_html;
    }

    public String getBlog_md() {
        return blog_md;
    }

    public void setBlog_md(String blog_md) {
        this.blog_md = blog_md;
    }

    public String getBlog_title() {
        return blog_title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBlog_type() {
        return blog_type;
    }

    public void setBlog_type(String blog_type) {
        this.blog_type = blog_type;
    }
}
