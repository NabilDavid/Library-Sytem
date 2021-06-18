/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package librarysytem;

/**
 *
 * @author Nabil
 */
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class DB_Class 
{
    private final static String unicode="?useUnicode=yes&characterEncoding=UTF-8";
    
   private final static String s="oracle.jdbc.OracleDriver";
    public static Connection getConnection(String userName,String passWord )
  {
      Connection conn=null;
      String t1="jdbc:oracle:thin:@localhost:1521:xe";
      try {
          Class.forName(s);
      } 
      catch (ClassNotFoundException ex) {
          Logger.getLogger(ex.getMessage());
      }
  
        try 
        {
        
           conn=DriverManager.getConnection(t1,userName,passWord); 
            conn.setAutoCommit(true);
        }
        catch (SQLException e) 
        {
            System.err.println(e.getMessage());
        }
      
      
      
      return conn;
  }
  
     public static int executeSql(String sql)
     {
         int f=0;
         Connection conn=getConnection("lib","lib");
         PreparedStatement s;
        try {
            s= conn.prepareStatement(sql);
            s.executeUpdate();
             f=1;
            } 
        catch (SQLException e)
        {
         f=0;
        }
      return f;
     }
    
    public static ResultSet executeSelect(String sql) 
    {
        
      Connection conn=getConnection("lib", "lib");
      PreparedStatement s;
      ResultSet rs=null;

        try 
        {
            s = conn.prepareStatement(sql);
            rs=s.executeQuery();
        }
        catch (SQLException e) 
        {
           System.out.print(e.getMessage());
        }
        return rs;
    }

    public static int check(String sql) 
    {
        
      Connection conn=getConnection("lib", "lib");
      PreparedStatement s;
      ResultSet rs=null;
      int f=0;
        try 
        {
            s = conn.prepareStatement(sql);
            rs=s.executeQuery();
            if(rs.next())
                f=1;
            else                
                f=0;

        }
        catch (SQLException e) 
        {
            System.err.println(e.getMessage());
        }
     return f;
    }
    
    
    public static void print(String Message,JTable table)
    {
        MessageFormat format=new MessageFormat(Message);
        MessageFormat format1=new MessageFormat("0,number,integer");
        try {
            table.print(JTable.PrintMode.FIT_WIDTH, format, format1);
            JOptionPane.showMessageDialog(null," تم الطبع");
        } catch (PrinterException ex) {
               JOptionPane.showMessageDialog(null,"لم تتم الطباعه" );
                       }
        
         
    }
  
    
}

