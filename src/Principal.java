
public class Principal {

	
	public static void main(String[] args) 
	{
		 try 
		 	{
			   AnalizSint Sint = new AnalizSint(args[0]);
			   
		 	} 
		 catch (Exception e) 
		 {
			 System.out.println();
			 System.out.println(e.getMessage());
		 }
  		
	}

}
