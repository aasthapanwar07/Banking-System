package bank;

import javax.security.auth.login.LoginException;

public class Authenticator {
  //creating two methods - login and logout
  public static Customer login(String username, String password){
    Customer customer = DataSource.getCustomer(username);
    if(customer== null){
      throw new LoginException("Username not found");
    }
    //comparing objects
    if(password.equals(customer.getPassword())){
      customer.setAuthenticated(true);
      return customer;
    }
    else throw new LoginException("Incorrect password");
  }

  public static void logout(Customer customer){
    customer.getAuthenticated(false);
  }
  
}
