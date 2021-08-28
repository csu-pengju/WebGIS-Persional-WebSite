package cn.pj.web.persistence.impl;

import cn.pj.web.domain.ArticleInfo;
import cn.pj.web.persistence.ArticleDao;
import cn.pj.web.persistence.UtilDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ArticleDaoImpl implements ArticleDao {
    String setOfCharacters = "abcdefghijklmnopqrstuvwxyz";

    @Override
    public boolean insertIntoArticleInfo(ArticleInfo articleInfo) {
        Connection connection = null;
        try{
            connection = UtilDao.getConnection_BasicGeoDataDB();
            String insertSql = "insert into blog_articles" +
                    "(blog_title,blog_author,blog_day,blog_month,blog_abstract,blog_filename,blog_type,blog_html,blog_md)" +
                    "values(?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, articleInfo.getBlog_title());
            preparedStatement.setString(2, articleInfo.getAuthor());
            preparedStatement.setString(3, articleInfo.getDay());
            preparedStatement.setString(4, articleInfo.getMonth());
            preparedStatement.setString(5, articleInfo.getBlog_abstract());
            preparedStatement.setString(6, articleInfo.getFileName());
            preparedStatement.setString(7, articleInfo.getBlog_type());
            preparedStatement.setString(8, articleInfo.getBlog_html());
            preparedStatement.setString(9, articleInfo.getBlog_md());
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
    public String generateHtmlFile(String blog_md,String blog_html, String blog_fileName,String folder) {
        BufferedWriter writer;
        String randomStr = getRandomStr();

        String mdPath = folder + blog_fileName + ".md";
        String htmlPath = folder + blog_fileName + ".html";
        File file1= new File(mdPath);
        File file2 = new File(htmlPath);

        if (file1.exists()){
            mdPath = mdPath.replace(".", "_" + randomStr+".");
            blog_fileName = blog_fileName + "_" + randomStr;
        }
        if (file2.exists()){
            htmlPath = htmlPath.replace(".", "_" + randomStr+".");
            blog_fileName = blog_fileName + "_" + randomStr;
        }
        try{
            System.out.println("dsdsdsd");
            System.out.println(htmlPath);
            System.out.println(mdPath);
            writer = new BufferedWriter (new OutputStreamWriter
                    (new FileOutputStream(htmlPath,true),"UTF-8"));
            writer.write(blog_html);
            writer.flush();
            writer = new BufferedWriter (new OutputStreamWriter
                    (new FileOutputStream(mdPath,true),"UTF-8"));
            writer.write(blog_md);
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return blog_fileName;
    }

    @Override
    public JSONObject getBlogList() {
        Connection connection = null;
        JSONObject res = new JSONObject();
        JSONArray res_Array = new JSONArray();
        SimpleDateFormat sdf =  new SimpleDateFormat( "yyyy-MM-dd" );
        try {
            connection = UtilDao.getConnection_BasicGeoDataDB();
            String selectSql = "select * from blog_articles";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet.toString());
            while (resultSet.next()){
                JSONObject oneObject = new JSONObject();
                oneObject.put("blog_title", resultSet.getString("blog_title"));
                oneObject.put("blog_author",resultSet.getString("blog_author"));
                String blog_time = sdf.format(resultSet.getDate("blog_time"));
                oneObject.put("blog_time", blog_time);
                oneObject.put("blog_abstract", resultSet.getString("blog_abstract"));
                oneObject.put("blog_month", resultSet.getString("blog_month"));
                oneObject.put("blog_day", resultSet.getString("blog_day"));
                oneObject.put("blog_html", resultSet.getString("blog_html"));
                oneObject.put("blog_type", resultSet.getString("blog_type"));
                oneObject.put("blog_filename", resultSet.getString("blog_filename"));

                res_Array.add(oneObject);
            }
            connection.close();
            if (res_Array.isEmpty()){
               res.put("success","false") ;
               res.put("blog_list", "");
            }else {
                res.put("success","true") ;
                res.put("blog_list", res_Array);
            }
        }catch (Exception e){
            e.printStackTrace();
            res.put("success","false") ;
            res.put("blog_list", "");
            return res;
        }
        return res;
    }

    @Override
    public JSONObject getBlogByFileName(String blog_filename) {
        Connection connection = null;
        JSONObject res = new JSONObject();
        JSONArray array = new JSONArray();
        SimpleDateFormat sdf =  new SimpleDateFormat( "yyyy-MM-dd" );
        try {
            connection = UtilDao.getConnection_BasicGeoDataDB();
            String selectOneSql = "select * from blog_articles where blog_filename like '"+blog_filename +"'";
            System.out.println(selectOneSql);
            PreparedStatement preparedStatement = connection.prepareStatement(selectOneSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet.toString());
            while (resultSet.next()){
                JSONObject oneObject = new JSONObject();
                oneObject.put("blog_title", resultSet.getString("blog_title"));
                oneObject.put("blog_author",resultSet.getString("blog_author"));
                String blog_time = sdf.format(resultSet.getDate("blog_time"));
                oneObject.put("blog_time", blog_time);
                oneObject.put("blog_abstract", resultSet.getString("blog_abstract"));
                oneObject.put("blog_month", resultSet.getString("blog_month"));
                oneObject.put("blog_day", resultSet.getString("blog_day"));
                oneObject.put("blog_html", resultSet.getString("blog_html"));
                oneObject.put("blog_type", resultSet.getString("blog_type"));
                oneObject.put("blog_filename", resultSet.getString("blog_filename"));
                array.add(oneObject);
            }
            connection.close();
            if (array.isEmpty()){
                res.put("success","false") ;
                res.put("blog_list", "");
            }else {
                res.put("success","true") ;
                res.put("blog_list", array);
            }
        }catch (Exception e){
            e.printStackTrace();
            res.put("success","false") ;
            res.put("blog_list", "");
            return res;
        }
        return res;
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
