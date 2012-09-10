
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
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if (TokenActual.Token.compareTo("<classDef>") == 0)
			 {
			 	ClassDef();
			 	ListaClassDef();
			 }
		 //  Sino ListaClassDef ::=  <vacio>
	 }
	 
	 // ListaClass ::= Class ListaClass | <vacio>
	 private void ListaClass() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if (TokenActual.Token.compareTo("<class>") == 0)
			 {
			 	Class(); 
			 	ListaClass(); 
			 }
		 //  Sino ListaClass ::=  <vacio>
	 
	 }
	 
	 
 
	 
	 // CtorClassDefSinID :==  FormalArgs <;>  PreAnalisis_CtorClassDef_MethodClassDef | <vacio>
	 private void CtorClassDefSinID() throws Exception
	 {
		 if (TokenActual.Token.compareTo("<(>") == 0)
		 {
			 FormalArgs();
			 if (TokenActual == null)  
					TokenActual = Lex.GetToken();
			 if (TokenActual.Token.compareTo("<;>") == 0)
				 PreAnalisis_CtorClassDef_MethodClassDef();
		 	 else throw new Exception("ERROR: Se esperaba un ;");
		 }
		 // Sino  ListaCtorClassDefSinID :== <vacio>
	 }
	 
	 // PreAnalisis_CtorClassDef_MethodClassDef_Cont ::= CtorClassDefSinID | <identifier> FormalArgs <;> ListaMethodClassDef   
	 private void PreAnalisis_CtorClassDef_MethodClassDef_Cont() throws Exception
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
	 
	 
	 
	
	 // PreAnalisis_CtorClassDef_MethodClassDef ::= <identificador> PreAnalisis_CtorClassDef_MethodClassDef_Cont | ListaMethodClassDefSinID 
	 private void PreAnalisis_CtorClassDef_MethodClassDef() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 if (TokenActual.Token.compareTo("<Identificador>") == 0)
			 {
		 		TokenActual = null;
		 		PreAnalisis_CtorClassDef_MethodClassDef_Cont();
			 }
			 
		 else ListaMethodClassDefSinID();
	 }
	 
	
	 // ClassDef ::= <classDef> <identificador> SuperOpcional <{> PreAnalisis_CtorClassDef_MethodClassDef <}>
	 private void ClassDef() throws Exception
	 {
		 if (TokenActual == null)  
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
						
				 		PreAnalisis_CtorClassDef_MethodClassDef();
				 		
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
	 
	 
	 
	 // ListaStatement ::= Statement ListaStatement | <vacio>
	 private void ListaStatement()
	 {
		 //Statement();
		// ListaStatement();
		 
		 
	 }
	  
		 
	 // Block ::= <{> ListaStatement <}>
	 private void Block() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 	if (TokenActual.Token.compareTo("<{>") == 0)
		 	{
		 		TokenActual = null;
		 		ListaStatement();
		 		 if (TokenActual == null)  
						TokenActual = Lex.GetToken();
				 
				 	if (TokenActual.Token.compareTo("<}>") == 0)
				 	{
				 		TokenActual = null;
				 		// Estado Aceptador;
				 	}
		 	}
		 
		 
	 }
	 
	 
	 
	 // VarDeclaratorListCont ::= <,> VarDeclaratorList | <vacio>
	 private void VarDeclaratorListCont() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 	if (TokenActual.Token.compareTo("<,>") == 0)
		 	{
		 		VarDeclaratorList();
		 	}
		 	// Sino VarDeclaratorListCont ::= <vacio>
		 
	 }
	 
	 
	 
	 
	 // VarDeclaratorList ::= identifier VarDeclaratorListCont
	 private void VarDeclaratorList() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 	if (TokenActual.Token.compareTo("<Identificador>") == 0)
		 	{
		 		TokenActual = null;
		 		VarDeclaratorListCont();
		 	}
		 
	 }
	 
	 
	 
	 
	 // Field ::= Type VarDeclaratorList <;>
	 private void Field() throws Exception
	 {
		// Type();
		 VarDeclaratorList();
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 if (TokenActual.Token.compareTo("<;>") == 0)
		 	{
		 		TokenActual = null;
		 		// Estado Aceptador.
		 		
		 	}
		 else throw new Exception("ERROR: Se esperaba un ;");
	 }
	 
	 
	 
	 // PreAnalisis_Field_Method_Cont2 ::=  Field PreAnalisis_Field_Ctor_Method |  Method ListaMethod
	 
	 private void PreAnalisis_Field_Method_Cont2() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 	if (TokenActual.Token.compareTo("<,>") == 0)
		 	{
		 		TokenActual = null;
		 		Field();
		 		PreAnalisis_Field_Ctor_Method();
		 	}
		 	else if (TokenActual.Token.compareTo("<(>") == 0)
		 			{
		 				Method();
		 				ListaMethod();
		 			}
		 
	 }
	 
	 // ListaMethod ::= Method ListaMethod | <vacio>
	 private void ListaMethod() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 if ((TokenActual.Token.compareTo("<Identificador>") == 0) || (TokenActual.Token.compareTo("<boolean>") == 0) || (TokenActual.Token.compareTo("<char>") == 0) || (TokenActual.Token.compareTo("<int>") == 0) || (TokenActual.Token.compareTo("<String>") == 0) || (TokenActual.Token.compareTo("<void>") == 0))
		 	{
			 Method();
			 ListaMethod();
		 	}
		 // Sino ListaMethod ::= <vacio>
		 
	 }
	 
	 
	 
	 
	 // PreAnalisis_Field_Method_Cont::= <Identificador> PreAnalisis_Field_Method_Cont2
	 private void PreAnalisis_Field_Method_Cont() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 	if (TokenActual.Token.compareTo("<Identificador>") == 0)
		 	{
		 		TokenActual = null;
		 		PreAnalisis_Field_Method_Cont2();
		 	}
		 
	 }
	 
	  
	 
	 // CtorSinID :==  FormalArgs Block PreAnalisis_Ctor_Method | <vacio>
	 private void CtorSinID() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 if (TokenActual.Token.compareTo("<(>") == 0)
			 {
			 FormalArgs();
			 Block();
			 PreAnalisis_Ctor_Method();
			 }
		 // Sino CtorSinID :== <vacio>
	 }
	 
	 
	 
	 
	 // PreAnalisis_Ctor_Method_Cont ::= CtorSinID | <identifier> FormalArgs Block ListaMethod 
	 private void PreAnalisis_Ctor_Method_Cont() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 if (TokenActual.Token.compareTo("<(>") == 0)
			 {
			 	CtorSinID();
			 }
		 else {
			 	if (TokenActual == null)  
					TokenActual = Lex.GetToken();
			 
			 	if (TokenActual.Token.compareTo("<Identificador>") == 0)
			 		{
			 			FormalArgs();
			 			Block();
			 			ListaMethod();
			 		}
		 	   }
	 }
	 
	  
	 
	 
	 // Method ::= MethodType <identifier> FormalArgs Block
	 private void Method() throws Exception
	 {
		 MethodType();
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 if (TokenActual.Token.compareTo("<Identificador>") == 0)
		 	{
			 TokenActual = null;
			 FormalArgs();
			 Block();
		 	}
	  }
	 
	 
	 
	 // MethodSinID ::=  MethodTypeSinID <identifier> FormalArgs Block
	 private void MethodSinID() throws Exception
	 {
		 MethodTypeSinID();
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 if (TokenActual.Token.compareTo("<Identificador>") == 0)
		 	{
			 FormalArgs();
			 Block();
		 	}
	 }
	   
	 
	 
	 // c:== MethodSinID ListaMethod | <vacio>
	 private void ListaMethodSinID() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if ((TokenActual.Token.compareTo("<void>") == 0) || (TokenActual.Token.compareTo("<boolean>") == 0) || (TokenActual.Token.compareTo("<char>") == 0) || (TokenActual.Token.compareTo("<int>") == 0) || (TokenActual.Token.compareTo("<String>") == 0))
		 {
			 MethodSinID();
			 ListaMethod();
		 }
		 // Sino ListaMethod <vacio>
		 
	 }
	 
	  
	 
	// PreAnalisis_Ctor_Method ::=  <identificador> PreAnalisis_Ctor_Method_Cont | ListaMethodSinID
	 
	 private void PreAnalisis_Ctor_Method() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 if (TokenActual.Token.compareTo("<Identificador>") == 0)
		 	{
			 PreAnalisis_Ctor_Method_Cont(); 
			 
		 	}
		 else ListaMethodSinID();
		 
	 }
	 
	 
	 // Ctor ::= <identifier> FormalArgs Block
	 private void Ctor() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 if (TokenActual.Token.compareTo("<Identificador>") == 0)
			 {
			 	FormalArgs();
			 	Block();
			 }
		 
	 }
	 
	 // PreAnalisis_Field_Ctor_Method_Cont ::= CtorSinID  PreAnalisis_Ctor_Method | PreAnalisis_Field_Method_Cont
	 private void PreAnalisis_Field_Ctor_Method_Cont() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 if (TokenActual.Token.compareTo("<(>") == 0)
			 {
			 	CtorSinID();
			 	PreAnalisis_Ctor_Method();
			 }
		 else 
			 	PreAnalisis_Field_Method_Cont();

	 }
	 
	 
	 
	 // PreAnalisis_Field_Ctor_Method ::= <Identificador> PreAnalisis_Field_Ctor_Method_Cont | <void> Method ListaMethod | PreAnalisis_Field_Method_Cont
	 private void PreAnalisis_Field_Ctor_Method() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 if (TokenActual.Token.compareTo("<Identificador>") == 0)
			 {
		 		TokenActual = null;
		 		PreAnalisis_Field_Ctor_Method_Cont();
			 }
			 
		 else if (TokenActual.Token.compareTo("<void>") == 0)
		 		{
			 		Method();
			 		ListaMethod();
		 		}
		 	  else if ((TokenActual.Token.compareTo("<boolean>") == 0) || (TokenActual.Token.compareTo("<char>") == 0) || (TokenActual.Token.compareTo("<int>") == 0) || (TokenActual.Token.compareTo("<String>") == 0))
		 	  		  {
		 		  		TokenActual = null;	
		 		  		PreAnalisis_Field_Method_Cont();
		 	  		  }
		 
	 }
	 
	 
	 
	 // Class ::=  <class> <identifier> SuperOpcional <{> PreAnalisis_Field_Ctor_Method <}>
	 private void Class() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if (TokenActual.Token.compareTo("<class>") == 0)
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
						
				 		PreAnalisis_Field_Ctor_Method();
				 		
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
		 else throw new Exception("ERROR: Se esperaba por lo menos un class");
	 }
	 
 
}
