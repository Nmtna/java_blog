
package jc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Sql {
    
    public static boolean loginCheck(String username , String password) throws ClassNotFoundException, SQLException {
        
        Class.forName("com.mysql.jdbc.Driver") ;
       
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DATA2" , "mmm" , "1111" ) ;
        
        Statement myS = myConn.createStatement() ; 
                
        String qry = "select * from cms1_users where username = '" + username + "'and password = '" + password + "'" ; 
        
        ResultSet myRS = myS.executeQuery(qry) ;
        
        
        
        if (myRS.next() ) {
            myConn.close() ;
            return true ;             
        } 
        
        myConn.close() ;
        return false ;

        
    }
    
    // Getting the Id of the user from their password . returning 0 if username doesnt exist 
    public static int getIdFromUser(String username) throws ClassNotFoundException, SQLException {
        

        Class.forName("com.mysql.jdbc.Driver") ;
       
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DATA2" , "mmm" , "1111" ) ;
        
        Statement myS = myConn.createStatement() ; 
                
        String qry = "select id from cms1_users where username = '" + username + "'" ; 
        
        ResultSet myRS = myS.executeQuery(qry) ;
        
        int id = 0 ; 
        
        while (myRS.next() ) {
            id = myRS.getInt("id") ;
        }
        
        myConn.close() ;
        return id ;
        
    }
    
    public static String getUserFromId(int id) throws ClassNotFoundException, SQLException {
     
        Class.forName("com.mysql.jdbc.Driver") ;
       
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DATA2" , "mmm" , "1111" ) ;
        
        Statement myS = myConn.createStatement() ; 
                
        String qry = "select username from cms1_users where id = " + id ; 
        
        ResultSet myRS = myS.executeQuery(qry) ;
        
        String username = "Getting username Failed" ;
        
        while (myRS.next() ) {
            username = myRS.getString("username") ;
            myConn.close() ;
            return username ;
        } 
        
        myConn.close() ;
        return username ;
        
    }
    
    
    public static boolean checkEdit(int post_id , int user_id) throws ClassNotFoundException, SQLException {
        
        Class.forName("com.mysql.jdbc.Driver") ;
       
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DATA2" , "mmm" , "1111" ) ;
        
        Statement myS = myConn.createStatement() ; 
        
        String qry = "select * from cms1_posts where id = '" + post_id + "' and user_id = '" + user_id + "'" ; 
        
        ResultSet myRS = myS.executeQuery(qry) ;
        
        while (myRS.next() ) {
            myConn.close() ;
            return true ;
        }
        
        myConn.close() ;
        return false ;
        
    }
    
    
    public static String getTitleFromId(int post_id) throws ClassNotFoundException, SQLException {
        
        Class.forName("com.mysql.jdbc.Driver") ;
       
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DATA2" , "mmm" , "1111" ) ;
        
        Statement myS = myConn.createStatement() ; 
        
        String qry = "select title from cms1_posts where id = " + post_id ; 
        
        ResultSet myRS = myS.executeQuery(qry) ;
        
        String title = null ;
        
        while (myRS.next() ) {
            title = myRS.getString("title") ;
        } 
        
        myConn.close() ;
        return title ;
        
    }
    
    public static String getContentFromId(int post_id) throws ClassNotFoundException, SQLException {
        
        Class.forName("com.mysql.jdbc.Driver") ;
       
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DATA2" , "mmm" , "1111" ) ;
        
        Statement myS = myConn.createStatement() ; 
        
        String qry = "select content from cms1_posts where id = " + post_id ; 
        
        ResultSet myRS = myS.executeQuery(qry) ;
        
        String content = null ;
        
        while (myRS.next() ) {
            content = myRS.getString("content") ;
        } 
        
        myConn.close() ;
        return content ;
        
    }
    
    public static boolean updateQry(int post_id , String title , String content) throws ClassNotFoundException, SQLException {
        
        Class.forName("com.mysql.jdbc.Driver") ;
       
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DATA2" , "mmm" , "1111" ) ;
        
        Statement myS = myConn.createStatement() ; 
        
        String qry = "update cms1_posts SET title = '" + title + "' , content = '" + content + "' where id = " + post_id ; 
        
        int IntRS = myS.executeUpdate(qry) ;
 
        boolean check = false ; 
        
        if (IntRS != 0) {
            myConn.close() ;
            return true ;
            
        }
        
        myConn.close() ;
        return false ;

    }
    
    
     public static boolean deleteQry(int post_id) throws ClassNotFoundException, SQLException {
        
        Class.forName("com.mysql.jdbc.Driver") ;
       
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DATA2" , "mmm" , "1111" ) ;
        
        Statement myS = myConn.createStatement() ; 
        
        String qry = "DELETE FROM cms1_posts where id = " + post_id  ; 
        
        int IntRS = myS.executeUpdate(qry) ;
 
        boolean check = false ; 
        
        if (IntRS != 0) {
            myConn.close() ;
            return true ;
            
        }
        
        myConn.close() ;
        return false ;

    }
     

     public static boolean addQry(String title , String content , int user_id) throws ClassNotFoundException, SQLException {
         
         
        Class.forName("com.mysql.jdbc.Driver") ;
       
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DATA2" , "mmm" , "1111" ) ;
        
        Statement myS = myConn.createStatement() ; 
        
        String qry = "INSERT INTO cms1_posts (title , content , user_id) VALUES ('" + title + "' , '" + title + "' , '" + user_id + "' )" ; 
        
        int IntRS = myS.executeUpdate(qry) ;
 
        boolean check = false ; 
        
        if (IntRS != 0) {
            myConn.close() ;
            return true ;
            
        }
        
        myConn.close() ;
        return false ;
        
     }
     
     
    
   
    
    
    
    
}
