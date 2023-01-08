package bank;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {
  
  public static Connection connect() {
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;
    
    try{
      connection = DriverManager.getConnection(db_file);
      //System.out.println("we're connected");
    } catch(SQLException e){
      e.printStackTrace();
    }
    return connection;

  }

//Construct customer from data source
  public static Customer getCustomer(String username) {
    String sql = "select * from customers where username =?";
    Customer customer = null;
    try(Connection connection = connect();
      PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setString(1, username);
        try(ResultSet resultSet =  statement.executeQuery()){
          customer = new Customer(
            resultSet.getInt("id"),
            resultSet.getString("name"), 
            resultSet.getString("username"),
            resultSet.getString("password"),
            resultSet.getInt("account_id"));
        }
      
    }catch(SQLException e){
      e.printStackTrace();
    }

    return customer;
  }

  // Construct account from data source
  public static Account getAccount(int accounId) {
    String sql = "select * from accounts where id = ?";
    Account account = null;
    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {
          statement.setInt(1, accounId);

      try(ResultSet resultSet = statement.executeQuery()) {
        account = new Account(
            resultSet.getInt("id"),
            resultSet.getString("type"),
            resultSet.getDouble("balance"));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return account;
  }


  public static void updateAccountBalance(int accounId, double balance){
    String sql = "update accounts set balance =? where id = ?";
    try{
      Statement.setDouble(1, balance);
      Statement.setInt(2, accounId);

      statement.executeUpdate();
    }catch(SQLException e){
      e.printStackTrace();
    }
  }


// removing this main method now, because it is created only for testing, now 
// we will use the main method of class menu 


  // public static void main(String args[]){
  //   //connect();
  //   Customer customer =  getCustomer("clillea8@nasa.gov");
  //   Account account = getAccount(customer.getAccountId());
  //   System.out.println(account.getBalance());
  // }

  
}
