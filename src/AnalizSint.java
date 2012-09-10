
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
		//Class();
		//ListaClass();
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
	 
	 
	 
	 // FormalArg ::= Type <identifier>
	 private void FormalArg() throws Exception
	 {
		 Type();
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if ((TokenActual.Token.compareTo("<Identificador>") == 0))
			{
			 // Estado Aceptador.
			 TokenActual = null;
			}
		 else throw new Exception("ERROR: Se esperaba un identificador");
	 }
	 
	 
	 
	 // FormalArgListCont ::=  <,> FormalArgList | <vacio>
	 private void FormalArgListCont() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if ((TokenActual.Token.compareTo("<,>") == 0))
		 {
			 FormalArgList();
		 }
		 // Sino FormalArgListCont ::= <vacio>
	 }
	 
	 
	 
	 // FormalArgList ::= FormalArg FormalArgListCont
	 private void FormalArgList() throws Exception
	 {
		 FormalArg();
		 FormalArgListCont();	 
	 }
	 
	 
	 
	//  FormalArgListOpcional ::= FormalArgList | <vacio>
	 private void FormalArgListOpcional() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if ((TokenActual.Token.compareTo("<Identificador>") == 0) || (TokenActual.Token.compareTo("<boolean>") == 0) || (TokenActual.Token.compareTo("<char>") == 0) || (TokenActual.Token.compareTo("<int>") == 0) || (TokenActual.Token.compareTo("<String>") == 0))
		 {
			 FormalArgList();
		 }
		 // Sino FormalArgListOpcional ::=  <vacio>
	 }
	 
	 // FormalArgs ::= <(> FormalArgListOpcional <)>
	 private void FormalArgs() throws Exception 
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken(); 
		 if (TokenActual.Token.compareTo("<(>") == 0)
		 	{
			 TokenActual = null;
			 FormalArgListOpcional();
			 if (TokenActual == null)  
					TokenActual = Lex.GetToken(); 
			 if (TokenActual.Token.compareTo("<)>") == 0)
			 	{
				  TokenActual = null;
				  // Estado Aceptador. 
			 	}
			 else throw new Exception("ERROR: Se esperaba un )");
		 	}
		 else throw new Exception("ERROR: Se esperaba un (");
		 
	 }
	 
	  	 	 
	 
	 // Type ::= <identifier> | PrimitiveType
	 private void Type() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if (TokenActual.Token.compareTo("<Identificador>") == 0)
		    {
			 TokenActual = null;
			 // Estado Aceptador. 
			}
		 else PrimitiveType();
	 }
	 
	 
	 
	 
	 
	 // MethodType ::= Type | <void>
	 private void MethodType() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if ((TokenActual.Token.compareTo("<Identificador>") == 0) || (TokenActual.Token.compareTo("<boolean>") == 0) || (TokenActual.Token.compareTo("<char>") == 0) || (TokenActual.Token.compareTo("<int>") == 0) || (TokenActual.Token.compareTo("<String>") == 0))
		 {
			 Type();
		 }
		 else if (TokenActual.Token.compareTo("<void>") == 0)
	 		{
			 TokenActual = null;
			 // Estado Aceptador
	 		}
	 	  else throw new Exception("ERROR: Se esperaba un un tipo primitivo o void");
		 
	 }
	 
	 
	 
	 // MethodClassDef ::= MethodType <identifier> FormalArgs <;>
	 private void MethodClassDef() throws Exception 
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 MethodType();
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if (TokenActual.Token.compareTo("<Identificador>") == 0) 
		 	{
			 TokenActual = null;
			 FormalArgs();
			 if (TokenActual == null)  
					TokenActual = Lex.GetToken();
			 if (TokenActual.Token.compareTo("<;>") == 0)
			 	{ 
				 TokenActual = null;
				 // Estado Aceptador.
			 	}
			 else throw new Exception("ERROR: Se esperaba un ;");
		 	}
		 else throw new Exception("ERROR: Se esperaba un identificador");
	 }
	 
	 
	 
	 // ListaMethodClassDef :== MethodClassDef ListaMethodClassDef | <vacio>
	 private void ListaMethodClassDef() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
			
		 if ((TokenActual.Token.compareTo("<Identificador>") == 0) || (TokenActual.Token.compareTo("<boolean>") == 0) || (TokenActual.Token.compareTo("<char>") == 0) || (TokenActual.Token.compareTo("<int>") == 0) || (TokenActual.Token.compareTo("<String>") == 0) || (TokenActual.Token.compareTo("<void>") == 0))
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
	 
	 
 
	 
	 // CtorClassDefSinID :==  FormalArgs <;>  PreAnalisis_ListaCtorClassDef_ListaMethodClassDef | <vacio>
	 private void CtorClassDefSinID() throws Exception
	 {
		 if (TokenActual.Token.compareTo("<(>") == 0)
		 {
			 FormalArgs();
			 if (TokenActual == null)  
					TokenActual = Lex.GetToken();
			 if (TokenActual.Token.compareTo("<;>") == 0)
				 PreAnalisis_ListaCtorClassDef_ListaMethodClassDef();
		 	 else throw new Exception("ERROR: Se esperaba un ;");
		 }
		 // Sino  ListaCtorClassDefSinID :== <vacio>
	 }
	 
	 // PreAnalisis_ListaCtorClassDef_ListaMethodClassDef_Cont ::= CtorClassDefSinID | <identifier> FormalArgs <;> ListaMethodClassDef  
	 private void PreAnalisis_ListaCtorClassDef_ListaMethodClassDef_Cont() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if (TokenActual.Token.compareTo("<(>") == 0)
		 {
			 CtorClassDefSinID();
		 }
		 else if (TokenActual.Token.compareTo("<Identificador>") == 0)
		 		 {
			 		TokenActual = null;
			 		FormalArgs();
			 		if (TokenActual == null)  
						TokenActual = Lex.GetToken();
			 		if (TokenActual.Token.compareTo("<;>") == 0)
			 			ListaMethodClassDef();
			 		else throw new Exception("ERROR: Se esperaba un ;");
		 		 }
		 	  else throw new Exception("ERROR: Se esperaba un ( para definir pametros formales o un identificador para los metodos de clase");
	 }
	 
	 // PrimitiveType ::= boolean | char | int | String
	 private void PrimitiveType() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if ((TokenActual.Token.compareTo("<boolean>") == 0) || (TokenActual.Token.compareTo("<char>") == 0) || (TokenActual.Token.compareTo("<int>") == 0) || (TokenActual.Token.compareTo("<String>") == 0))
		 {
			 TokenActual = null;
			 // Estado Aceptador.
		 }
		 else throw new Exception("ERROR: Se esperaba un tipo primitivo");
	 }
	 
	 
	 // MethodTypeSinID ::= PrimitiveType | <void>
	 private void MethodTypeSinID() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if ((TokenActual.Token.compareTo("<boolean>") == 0) || (TokenActual.Token.compareTo("<char>") == 0) || (TokenActual.Token.compareTo("<int>") == 0) || (TokenActual.Token.compareTo("<String>") == 0))
		 {
			 PrimitiveType();
		 }
		 else if (TokenActual.Token.compareTo("<void>") == 0)
		 		{
			 		TokenActual = null;
			 		// Estado Aceptador
		 		}
		 	  else throw new Exception("ERROR: Se esperaba un tipo primitivo o void");
	 }	
	 
	 
	 // MethodClassDefSinID ::=  MethodTypeSinID <identifier> FormalArgs <;>
	 private void MethodClassDefSinID() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 MethodTypeSinID();
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if (TokenActual.Token.compareTo("<Identificador>") == 0)
		 	{
			 TokenActual = null;
			 FormalArgs();
			 if (TokenActual == null)  
					TokenActual = Lex.GetToken();
			 if (TokenActual.Token.compareTo("<;>") == 0)
		 			{
				 	  TokenActual = null;		
				 	  // Estado Aceptador.
		 			}
			 else throw new Exception("ERROR: Se esperaba un ;");
			}
		 else throw new Exception("ERROR: Se esperaba un identificador");
	 }
	 
	 
	 

	 // ListaMethodClassDefSinID :== MethodClassDefSinID ListaMethodClassDef | <vacio> 
	 private void ListaMethodClassDefSinID() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 if ((TokenActual.Token.compareTo("<void>") == 0) || (TokenActual.Token.compareTo("<boolean>") == 0) || (TokenActual.Token.compareTo("<char>") == 0) || (TokenActual.Token.compareTo("<int>") == 0) || (TokenActual.Token.compareTo("<String>") == 0))
			 {
			 	MethodClassDefSinID();
			 	ListaMethodClassDef();
			 }
		 // Sino ListaMethodClassDefSinID :== <vacio> 
		 
		
	 }
	 
	 
	 
	
	 // PreAnalisis_ListaCtorClassDef_ListaMethodClassDef ::= <identificador> PreAnalisis_ListaCtorClassDef_ListaMethodClassDef_Cont | ListaMethodClassDefSinID 
	 private void PreAnalisis_ListaCtorClassDef_ListaMethodClassDef() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 if (TokenActual.Token.compareTo("<Identificador>") == 0)
			 {
		 		TokenActual = null;
			 	PreAnalisis_ListaCtorClassDef_ListaMethodClassDef_Cont();
			 }
			 
		 else ListaMethodClassDefSinID();
	 }
	 
	
	 // ClassDef ::= <classDef> <identificador> SuperOpcional <{> PreAnalisis_ListaCtorClassDef_ListaMethodClassDef <}>
	 private void ClassDef() throws Exception
	 {
		 TokenActual = Lex.GetToken();
		 if (TokenActual.Token.compareTo("<classDef>") == 0)
			{
			 TokenActual = Lex.GetToken();
			 if (TokenActual.Token.compareTo("<Identificador>") == 0)
			 	{
				 	TokenActual = null;
				 	 SuperOpcional(); 
				 	 
				 	 if (TokenActual == null)  
						TokenActual = Lex.GetToken();
					
				 	
				 	 if (TokenActual.Token.compareTo("<{>") == 0)
				 	  {
				 		TokenActual = null;
						
				 		PreAnalisis_ListaCtorClassDef_ListaMethodClassDef();
				 		
				 		if (TokenActual == null)  
								TokenActual = Lex.GetToken();
				 		
				 		if (TokenActual.Token.compareTo("<}>") == 0)
				 			{
				 				TokenActual = null;
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
