public class Principal {

	
	public static void main(String[] args) 
	{
		 try 
		 	{
			   AnalizSint Sint = new AnalizSint("fer.txt");
			   System.out.println("OK!!");
		 	} 
		 catch (Exception e) 
		 {
			 System.out.println();
			 System.out.println(e.getMessage());
		 }
  		
	}

}
