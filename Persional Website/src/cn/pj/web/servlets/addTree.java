package cn.pj.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;


@WebServlet(name = "addTree")
public class addTree extends HttpServlet {
    Connection conn;
    boolean isConnect = false;
    private void connetDB(){
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/gisdb";
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "pj19980806");
            conn = DriverManager.getConnection(url, props);
            String sql2 = " select * from gis_user";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        String data = "{'success':'true'," +
                "'message':'add tree success '}";

        String layer = "Point_";
        String requestData = request.getParameter("wkt").toString();
        try {
            if (!isConnect){
                connetDB();
                isConnect = true;
            }
            connetDB();

            String sql = "insert into  "+layer+" (geo) values(st_geomfromtext('"+requestData+"',3857))";
            PreparedStatement st = conn.prepareStatement(sql);
            st.executeUpdate();
        }catch (Exception e){
             data = "{'success':'true'," +
                    "'message':'fail to add tree '}";
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        out.write(data);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

}
