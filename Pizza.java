
import java.sql.*;
import java.util.Scanner;





class InvalidUserNameException extends Exception
{

      InvalidUserNameException(String msg)
      {
         super(msg);
      }
}

class InvalidPasswordException extends Exception
{

      InvalidPasswordException(String msg)
      {
         super(msg);
      }
}


class Pizza
{
	
			
   String username;
   String password;
   // double costs;
   // String crustType;
   // int sizes;
   // String topping;

   Pizza()
   {
      username="";
      
   }
   
   Pizza(String username)
   {
       this.username=username;
   }
   // Pizza(String crustType,double costs,int sizes,String topping){
   //    this.costs = costs;
   //    this.crustType =crustType;
   //    this.sizes = sizes;
   //    this.topping =topping;
   // }
	public static void main(String args[])throws Exception
	{ 
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pizzaorder","root","Rasika@18119");
	  
	  
	    if(con!=null)
		{
			System.out.println("connection establish");
		}
		else
		{
			System.out.println("connection is not establish");
		}
		

  
      Scanner sc=new Scanner(System.in);
	   System.out.println("Signin or Login(1 or 2 ):");
      int num = sc.nextInt();




      String custdetails="insert into customer values(?,?,?,?)"; 
      PreparedStatement pstmt=con.prepareStatement(custdetails);


         if(num == 1){
            try{
            System.out.println("set your username:");
            String customer_name=sc.next();
            if(!customer_name.equals(customer_name))
                   {
                      throw new InvalidUserNameException("name is invalid:");
                 }
                 else{
                  System.out.println("user logged in ");
                 }
                 System.out.println("enter contact number:");
                 String contact=sc.next();
                
                 System.out.println("enter address:");
                 String address=sc.next();
                
                 System.out.println("enter pincode:");
                 String pincode=sc.next();
        
                 pstmt.setString(1,customer_name);
                 pstmt.setString(2, contact);
                 pstmt.setString(3,address);
                 pstmt.setString(4,pincode);
                
                 pstmt.executeUpdate();
               }
               catch(InvalidUserNameException iun)
                   {
               System.out.println(iun);
           }
         }
         else if(num ==2){
            try{
               System.out.println("enter your username:");
               String username1=sc.next();
               PreparedStatement ps = con.prepareStatement("select * from customer WHERE customer_name='" + username1+ "'");
               ResultSet rs = ps.executeQuery();
               
               if(rs.next()){
                  System.out.println("Logged in Succesfully");
               }
               else{
                  System.out.println("Failure");
               }
              }
              catch(Exception e ){
               System.out.println("error: " +e.getMessage());
              }
         }
   
      
      
  System.out.println("=====================================================================================================================================");
        //Sigin and logged in done 
         System.out.println();


     	System.out.println("CUSTOMER DETAILS");
      	 

   
   



	//This the next part. Above is just mess
      int amount =100;
      int size12 = 100;
      int size10 = 80;
      int size7 = 50;

      int allToppings = 50;
      int cost;


      String s2="insert into orders values(?,?,?,?)"; 
         PreparedStatement pstmt1=con.prepareStatement(s2);
		
		
		System.out.println("ORDER DETAILS");
	
		System.out.println("enter type (Thin Crust - TC),(Cheese Burst-CB), (Hand Tossed-HT):");
		String type=sc.next();


		System.out.println("enter size(12 inches),(10 inches),(7 inches):");
      int size=sc.nextInt();
      if (size ==10){
         cost = amount +allToppings+size12;
      }
      else if(size == 12){
         cost = amount + allToppings +size10;
      }
      else if(size == 7){
         cost = amount  +allToppings + size7;
      }
      else{
         cost = amount+allToppings;
      }
		System.out.println("enter toppings (Onion ,Tomato,Mushrooms):");
		String toppings=sc.next();
      // toppings = allToppings + amount;
      
		
		pstmt1.setString(1,type);
		pstmt1.setInt(2, size);
		pstmt1.setString(3,toppings);
		pstmt1.setInt(4,cost);
		
		pstmt1.executeUpdate();
      System.out.println();
      System.out.println("Your Order is successfully placed");

      System.out.println("Thank you for ordering." +"/n"+ "Your [BILLING DETAILS] are"  +"\n"+ "--------------------------------------- Type :" + type+ "," +"\n"+  "--------------------------------------- Size: " + size+ "," +"\n"+ "---------------------------------------   Topping: " +toppings+ ", " +"\n"+ "---------------------------------------  Total cost:" +cost);
      System.out.println();
      System.out.println("Your order will be delivered in 30 minutes");
		
		
		
	}
}


// class OrderDetails {
//   private  String crustType ;
//    private int cost; 
//    int size;
//    String Toppings;

//    // OrderDetails(char crustType,int cost,int size,String Toppings){
//    //    this.crustType = crustType;
//    //    this.cost = cost;
//    //    this.size= size; 
//    //    this.Toppings = Toppings;
//    // }

//    public void setCrust(String Type){
//       crustType = Type;
//    }
// }