package cn.pj.web.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Logger;

public class UtilDao {

    private static  String driver = "org.postgresql.Driver";
    private static String user = "postgres";
    private static String password = "pj19980806";
    private static String basicGeoDataUrl = "jdbc:postgresql://localhost/gisdb";

    //db connection.
    //you can get connection by function :getConnection
    //@ params:
    //@ returns:db Connection
    public  static Connection getConnection_BasicGeoDataDB() throws Exception{
        try{
            Class.forName(driver);
            Properties props = new Properties();
            props.setProperty("user",user);
            props.setProperty("password",password);
            return DriverManager.getConnection(basicGeoDataUrl,props);
        }catch (Exception e){
            Logger.getLogger(e.getMessage());
            throw  e;
        }
    }


    public static  void closeConnection(Connection connection)throws  Exception{
        if(connection!=null){
            connection.close();
        }
    }
}
