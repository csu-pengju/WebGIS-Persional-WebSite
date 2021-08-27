package cn.pj.web.persistence.impl;

import cn.pj.web.domain.ArticleInfo;
import cn.pj.web.persistence.ArticleDao;
import cn.pj.web.persistence.UtilDao;


import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class ArticleDaoImpl implements ArticleDao {
    String setOfCharacters = "abcdefghijklmnopqrstuvwxyz";

    @Override
    public boolean insertIntoArticleInfo(ArticleInfo articleInfo) {
        Connection connection = null;
        try{
            connection = UtilDao.getConnection_BasicGeoDataDB();
            String insertSql = "insert into blog_articles" +
                    "(blog_title,blog_author,blog_day,blog_month,blog_abstract,blog_filename)" +
                    "values(?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, articleInfo.getBlog_title());
            preparedStatement.setString(2, articleInfo.getAuthor());
            preparedStatement.setString(3, articleInfo.getDay());
            preparedStatement.setString(4, articleInfo.getMonth());
            preparedStatement.setString(5, articleInfo.getBlog_abstract());
            preparedStatement.setString(6, articleInfo.getFileName());
            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();
            UtilDao.closeConnection(connection);
            if (rows > 0){
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void generateHtmlFile(String blog_html, String blog_fileName) {
        BufferedWriter writer;
        File file= new File(blog_fileName);
        System.out.println(blog_fileName);
        System.out.println(file.exists());
        if (file.exists()){
            blog_fileName = blog_fileName.replace(".", "_" + getRandomStr()+".");
            System.out.println(blog_fileName);
        }
        try{
            writer = new BufferedWriter (new OutputStreamWriter
                    (new FileOutputStream(blog_fileName,true),"UTF-8"));
            writer.write(blog_html);
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getRandomStr(){
        Random random = new Random();
        String randomStr = "";
        for(int i = 0;i<5;i++){
            int randomInt = random.nextInt(setOfCharacters.length());
            char randomChar = setOfCharacters.charAt(randomInt);
            randomStr = randomStr + randomChar;
        }
        return randomStr;
    }



}
