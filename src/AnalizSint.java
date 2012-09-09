
public class AnalizSint 
{
	 private AnalizLex Lex; 
	 Token TokenActual = null;
	 
	 public AnalizSint(String Archivo) throws Exception
	 {
		 Lex = new AnalizLex(Archivo);
		 Start();
	 }
	
	 // Start ::= ClassDef ListaClassDef Class ListaClass
	 private void Start() throws Exception
     {
		ClassDef();
		ListaClassDef();
		Class();
		ListaClass();
     }
	 
	 
	 // Super ::= extends identifier
	 private void Super() throws Exception
	 {
		if (TokenActual.Token.compareTo("<extends>") == 0) 
			{ 
				TokenActual = Lex.GetToken();
				if (TokenActual.Token.compareTo("<Identificador>") == 0) 
					{	
						// Estado Aceptador.
						TokenActual = null;
					}
				else throw new Exception("ERROR: Se esperaba un identificador");
			}
		 // No hay else por se cero o una vez.
	 }
	 
	 
	 // SuperOpcional ::= Super | <vacio>
	 private void SuperOpcional() throws Exception
	 {
		if (TokenActual == null)  
			TokenActual = Lex.GetToken();
		
		if (TokenActual.Token.compareTo("<extends>") == 0) 
			Super();
		// Sino SuperOpcional ::= <vacio>
	 }
	 
	 
	 private void FormalArgs() 
	 {
		 
		 
	 }
	 
	 
	 
	 // CtorClassDef ::= <identifier> FormalArgs <;>
	 private void CtorClassDef() throws Exception
	 {
		 if (TokenActual.Token.compareTo("<Identificador>") == 0)
		 	{
			 TokenActual = null;
			 FormalArgs();
			 
			 if (TokenActual == null)  
					TokenActual = Lex.GetToken();
			 
			 if (TokenActual.Token.compareTo("<;>") == 0)
			 	{
				  // Estado Aceptador.
				  TokenActual = null;  
			 	}
			 else throw new Exception("ERROR: Se esperaba un ;");
		 	}
	 }
	 
	 
	 
	 // ListaCtorClassDef :== CtorClassDef ListaCtorClassDef | <vacio>
	 private void ListaCtorClassDef() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
			
		 if (TokenActual.Token.compareTo("<Identificador>") == 0)   // Si viene identificador se que es para un cosntrucctor.. 
		 	{
			 	CtorClassDef();
			 	ListaCtorClassDef();
		 	}
		 // Sino ListaCtorClassDef :== <vacio>
	 }
	 
	 
	 
	 private void MethodClassDef() 
	 {
		 
		 
	 }
	 
	 
	 
	 // ListaMethodClassDef :== MethodClassDef ListaMethodClassDef | <vacio>
	 private void ListaMethodClassDef() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
			
		 if (TokenActual.Token.compareTo("<Identificador>") == 0) // 
		 	{
			 	MethodClassDef();
			 	ListaMethodClassDef();
		 	}
		 // Sino ListaMethodClassDef :== <vacio>
		 
		 
	 }
	 
	 // ListaClassDef ::= ClassDef ListaClassDef | <vacio>
	 private void ListaClassDef() throws Exception
	 {
		 Token Token = Lex.GetToken();
		 if (Token.Token.compareTo("<classDef>") == 0)
			 {
			 	ClassDef();
			 	ListaClassDef();
			 }
		 //  Sino ListaClassDef ::=  <vacio>
	 }
	 
	 // ListaClass ::= Class ListaClass | <vacio>
	 private void ListaClass() throws Exception
	 {
		
		 Class(); 
		 ListaClass(); 
	 }
	
	 
	
	 // ClassDef ::= <classDef> <identificador> SuperOpcional <{> ListaCtorClassDef ListaMethodClassDef <}>
	 private void ClassDef() throws Exception
	 {
		 TokenActual = Lex.GetToken();
		 if (TokenActual.Token.compareTo("<classDef>") == 0)
			{
			 TokenActual = Lex.GetToken();
			 if (TokenActual.Token.compareTo("<identificador>") == 0)
			 	{
				 	 SuperOpcional(); 
				 	 
				 	 if (TokenActual == null)  
						TokenActual = Lex.GetToken();
					
				 	
				 	 if (TokenActual.Token.compareTo("<{>") == 0)
				 	  {
				 		
				 		TokenActual = null;
						
				 		ListaCtorClassDef();
				 		ListaMethodClassDef();
				 		TokenActual = Lex.GetToken();
				 		if (TokenActual.Token.compareTo("<}>") == 0)
				 			{
				 				// Estado Aceptador.
				 			}
				 		else throw new Exception("ERROR: Se esperaba una } ");
				 	  }
				 	 else throw new Exception("ERROR: Se esperaba una { ");
			 	}
			 else throw new Exception("ERROR: Se esperaba un identificador");
			}
		 else throw new Exception("ERROR: Se esperaba por lo menos un classDef");
	 }
	 
	 private void Class() throws Exception
	 {
		
	 }
	 
 
}
