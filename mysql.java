import java.sql.*;

/*
 * JDBC lets you ask MySQL queries in java, and receive the results back as a Java Object.
 * In whatever program you are running, you can instantiate a mysql() object which will establish a connection to the databse.
 * it will then contain methods for INSERT, UPDATE, SELECT, DELETE with parameters of the data to be changed/deleted/selected,
 * and the methods will return either a boolean for true or the value as an object
 */

public class mysql{

    public Connection conn;
    
    public mysql(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/stock_simulator_db";
            String username = "root";
            String password = "Sh!tf@rtPI55<3";
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database");

        } catch (Exception e){
            System.out.println(e);
        }
    }

    /*
     * Adds an element to the database
     * 
     * @Param: String name, String email -> values to be inserted into db
     * @Return: boolean: true if added, false otherwise
     */
    public boolean Insert(String name, String email){
        try{
            String query = "INSERT INTO users (name, email) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            System.out.println("User "+name+" added to database.");
            
            return true;

        } catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    /*
     * Selects a user given their name and returns their info from the data base as a string
     * 
     * @Params: String name -> who is being search up
     * @Return: String containing all their information
     * 
     * ##TODO -> come up with a better way to store their information?
     */
    public String Select(String name){
        String data = "";
        try{
            String query = "SELECT * FROM users WHERE name = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
    
            pstmt.setString(1, name);
    
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                String email = rs.getString(2);
                int buying_power = rs.getInt("paper-balance");
                int account_balance = rs.getInt("account-value");
                data = "name: "+name+", email: "+email+", Buying Power: $"+buying_power+", Account Balance: $"+account_balance;
            }
    
            return data;
        } catch (Exception e){
            System.out.println(e);
            return data;
        }
        
    }


    public static void main(String[] args){
        
        mysql db = new mysql();
        System.out.print(db.Select("neil"));
    }
}