/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class myconnect {
    String Host = "";
    String Username = "";
    String Password = "";
    String Database = "";
    String server="";
    Connection connect = null;
    Statement statement = null ;
    ResultSet result = null;
    public myconnect(String Host, String UserName, String Password, String Database, String server){
        this.Host = Host;
        this.Username = UserName;
        this.Password = Password;
        this.Database = Database;
        this.server=server;
    }
    protected void driverTest() throws Exception{
        try{
            if(this.server.equals("MySQL"))
            Class.forName("com.mysql.jdbc.Driver");
            else
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        }catch(java.lang.ClassNotFoundException e){
            throw new Exception("JDBC Driver not found.");
        }
    }
    protected Connection getConnect() throws Exception{
        if(this.connect == null){
                driverTest();
                String url;
                if(server.equals("MySQL"))
        url = "jdbc:mysql://" + this.Host + ":3306/" + this.Database;
                else
        url = "jdbc:derby://"+ this.Host +":1527/"+this.Database;
            try{
                this.connect = DriverManager.getConnection(url, this.Username, this.Password);
            }catch(java.sql.SQLException e){
                throw new Exception("Không thể kết nối đến database");
            }
        }
        return this.connect;
    }
    protected Connection khoitao() throws Exception{
        if(this.connect == null){
                driverTest();
                String url;
                if(server.equals("MySQL"))
        url = "jdbc:mysql://" + this.Host + ":3306/";
                else
        url = "jdbc:derby://"+ this.Host +":1527/";
            try{
                this.connect = DriverManager.getConnection(url, this.Username, this.Password);
            }catch(java.sql.SQLException e){
                throw new Exception("Không thể kết nối đến database");
            }
        }
        return this.connect;
    }
    protected Statement getStatement() throws Exception{
        if(this.statement == null ? true :this.statement.isClosed()){

            this.statement = this.getConnect().createStatement();
        }
        return this.statement;
    }
    public ResultSet executeQuery(String Query) throws Exception{
        try{
            this.result = this.getStatement().executeQuery(Query);
        }catch(Exception e){
            throw new Exception("Error" + e.getMessage() + "-" + Query);

        }
        return this.result;
    }
    public int executeUpdate(String Query) throws Exception{
        int res = Integer.MIN_VALUE;
        try{
            res = this.getStatement().executeUpdate(Query);

        }catch(Exception e){
            throw new Exception("Error : " + e.getMessage()+ "-" + Query );
        }finally{
            this.Close();
        }
        return res;
    }
    public void Close() throws Exception{
        if(this.result != null && !this.result.isClosed()){
            this.result.close();
            this.result = null;
        }
        if(this.statement != null && ! this.statement.isClosed()){
            this.statement.close();
            this.statement = null;
        }
        if(this.connect != null && ! this.connect.isClosed()){
            this.connect.close();
            this.connect = null;
        }    } 
}
