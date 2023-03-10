public class userAccount {
    String username, email, password;
    mysql db = new mysql();
    
    public userAccount(String address, String name, String pass){
         username = name;
         password = pass;
         email = address;
    }

    /*
     * Attempts to add the user into the database
     */
    public void Add(){
        if (db.Insert(username, email))
            System.out.println("Account registered");
        else{
            System.out.println("Account Creation Failed");
        }
    }

    /*
     * Returns the user's buying power as an integer
     */
    public int getBuyingPower(){
        int bp = 0;
        
        //values contains all the information about the user
        String[] values = db.Select(username).split(",",4);
        String[] buying_power = values[2].split("\\$");
        bp = Integer.parseInt(buying_power[1]);

        return bp;
    }

    /*
     * Returns the user's account balance as an integer
     */
    public int getAccountBalance(){
        int ab = 0;

        //split off the commas to get 'key: value'
        String[] values = db.Select(username).split(",",4);
        //split off the $ to get the integer value
        String[] accountBal = values[3].split("\\$");
        //turn the string into an integer
        ab = Integer.parseInt(accountBal[1]);

        return ab;
    }

}
