
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
	 
	 
	 // Super ::= Super ::= <extends> <identifier>
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
		 else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un identificador");
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
			 else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un )");
		 	}
		 else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un (");
		 
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
		 else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un tipo primitivo");
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
	 
	 
	 
	 // NewExpr ::= Literal
	 
	 
	 // ExpressionUnary :: = UnaryOp Expression
	 private void ExpressionUnary() throws Exception
	 {
		 UnaryOp();
		 Expression();
	 }
	 
	 
	 // Primary ::= <identifier> | NewExpr
	 private void Primary() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if (TokenActual.Token.compareTo("<identifier>") == 0)
		 {
			 TokenActual = null;
			 // Estado Aceptador.
		 }
		 else NewExpr();	 
	 }
	 
	 
	 // UnaryOp ::= <!> | <Suma> | <Resta>
	 private void UnaryOp() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if ((TokenActual.Token.compareTo("<!>") == 0)||(TokenActual.Token.compareTo("<Suma>") == 0)||(TokenActual.Token.compareTo("<Resta>") == 0))
		 {
			 TokenActual = null;
			 // Estado Aceptador.
		 }
		 else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un operador ! o uno de suma o resta ");	 
	 }
	 
	 
	 
	 // BinaryOpPrecedence4 ::= <Operador Igualdad> | <!=>
	 private void BinaryOpPrecedence4() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if ((TokenActual.Token.compareTo("<Operador Igualdad>") == 0)||(TokenActual.Token.compareTo("<!=>") == 0))
		 {
			 TokenActual = null;
			 // Estado Aceptador.
		 }
		 else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un operador de igualdad o un != ");	 
	 }
	 
	 
	 // BinaryOpPrecedence3 ::= <<> | <>> |  <<=> | <>=>
	 private void BinaryOpPrecedence3() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if ((TokenActual.Token.compareTo("<<>") == 0)||(TokenActual.Token.compareTo("<>>") == 0)||(TokenActual.Token.compareTo("<<=>") == 0)||(TokenActual.Token.compareTo("<>=>") == 0))
		 {
			 TokenActual = null;
			 // Estado Aceptador.
		 }
		 else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un simbolo > o < o <= o >= ");	 
	 }
	 
	 
	 
	 // BinaryOpPrecedence2 ::= <Suma> | <Resta>
	 private void BinaryOpPrecedence2() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if ((TokenActual.Token.compareTo("<Suma>") == 0)||(TokenActual.Token.compareTo("<Resta>")==0))
		 {
			 TokenActual = null;
			 // Estado Aceptador.
		 }
		 else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un simbolo de suma o resta");
		 	 
	 }
	 
	 
	 
	 // BinaryOpPrecedence1 ::=  <Multiplicacion> | </> | <Modulo>
	 private void BinaryOpPrecedence1() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if ((TokenActual.Token.compareTo("<Multiplicacion>") == 0)||(TokenActual.Token.compareTo("</>") == 0)||(TokenActual.Token.compareTo("<Modulo>") == 0))
		 {
			 TokenActual = null;
			 // Estado Aceptador.
		 }
		 else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un simbolo de multiplicacion, division o modulo");
		 	 
	 }
	 
	 
	 
	 
	 // E7 ::=  ExpressionUnary | Primary
	 private void E7() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if ((TokenActual.Token.compareTo("<!>") == 0)|| (TokenActual.Token.compareTo("<Suma>") == 0)|| (TokenActual.Token.compareTo("<Resta>") == 0))
			 ExpressionUnary();
		 else if ((TokenActual.Token.compareTo("<Idetificador>")==0) || (TokenActual.Token.compareTo("<null>")==0) || (TokenActual.Token.compareTo("<true>")==0)|| (TokenActual.Token.compareTo("<false>")==0)|| (TokenActual.Token.compareTo("<Numero_Entero>")==0)|| (TokenActual.Token.compareTo("<Carácter>")==0)|| (TokenActual.Token.compareTo("<Cadena Caracteres>")==0)|| (TokenActual.Token.compareTo("<this>")==0)|| (TokenActual.Token.compareTo("<.>")==0)||(TokenActual.Token.compareTo("<(>")==0)|| (TokenActual.Token.compareTo("<new>")==0)||(TokenActual.Token.compareTo("<super>")==0))
			 	Primary();
		 	   else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un simbolo de expresion unaria o uno primario");
		 	 
	 }
	 
	 
	 
	 // E6’ ::= BinaryOpPrecedence1 E7 E6’
	 private void E6Prima() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if ((TokenActual.Token.compareTo("<*>") == 0)|| (TokenActual.Token.compareTo("</>") == 0)||(TokenActual.Token.compareTo("<Modulo>") == 0))
		 	{
			 BinaryOpPrecedence1();
			 E7(); 
			 E6Prima();
		 	}
		 // Sino E1’ ::= <vacio>
	 }
	 
	 
	 
	 
	 // E6 ::= E7 E6’
	 private void E6() throws Exception
	 {
		E7();
		E6Prima();
	 }
	 
	  
	 // E5’ ::= BinaryOpPrecedence2 E6 E5’ | <vacio>
	 private void E5Prima() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if ((TokenActual.Token.compareTo("<+>") == 0)|| (TokenActual.Token.compareTo("<->") == 0))
		 	{
			 BinaryOpPrecedence2();
			 E6(); 
			 E5Prima();
		 	}
		 // Sino E1’ ::= <vacio>
	 }
	 
	 // E5 ::= E6 E5’
	 private void E5() throws Exception
	 {
		E6();
		E5Prima();
	 }
	 
	 
	 
	 // E4 :: = E5 BinaryOpPrecedence3 E5 | E5
	 private void E4() throws Exception
	 {
		 E5();
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if ((TokenActual.Token.compareTo("<<>") == 0)|| (TokenActual.Token.compareTo("<>>") == 0)|| (TokenActual.Token.compareTo("<<=>") == 0)|| (TokenActual.Token.compareTo("<>=>") == 0))
		 	{
			 BinaryOpPrecedence3();
			 E5();
		 	}

	 }
	 
	 
	 
	 
	 // E3’ ::= BinaryOpPrecedence4 E4 E3’ | <vacio>
	 private void E3Prima() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if ((TokenActual.Token.compareTo("<==>") == 0)|| (TokenActual.Token.compareTo("<!=>") == 0))
		 	{
			 BinaryOpPrecedence4();
			 E4(); 
			 E3Prima();
		 	}
		 // Sino E1’ ::= <vacio>
	 }
	 
	 
	 //  E3 ::= E4 E3’
	 private void E3() throws Exception
	 {
		E4();
		E3Prima();
	 }
	 
	 
	 
	 
	 // E2’ ::= <And> E3 E2’ | <vacio>
	 private void E2Prima() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if (TokenActual.Token.compareTo("<And>") == 0)
		 	{
			 E3(); 
			 E2Prima();
		 	}
		 // Sino E1’ ::= <vacio>
	 }
	 
	 
	 //  E2 ::= E3 E2’
	 private void E2() throws Exception
	 {
		E3();
		E2Prima();
	 }
	 
	 // E1’ ::= <OR> E2 E1’| <vacio>
	 private void E1Prima() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if (TokenActual.Token.compareTo("<OR>") == 0)
		 	{
			 E2(); 
			 E1Prima();
		 	}
		 // Sino E1’ ::= <vacio>
	 }
	 
	 
	 
	// E1 ::= E2 E1’
	 private void E1() throws Exception
	 {
		 E2();
		 E1Prima();
	 }
	 
	 
	 
	 // Expression ::= E1 <Operador Asignación> Expression | E1
	 private void Expression() throws Exception
	 {
		 E1();
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if (TokenActual.Token.compareTo("<Operador Asignación>")==0)
			{
			 	TokenActual = null;
			 	Expression();
			}
	 }
	 
	 
	 
	 
	 // ExpressionOpcional ::= Expression | <vacio>
	 private void ExpressionOpcional() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if ((TokenActual.Token.compareTo("<!>")==0) || (TokenActual.Token.compareTo("<Suma>")==0) || (TokenActual.Token.compareTo("<Resta>")==0) || (TokenActual.Token.compareTo("<Idetificador>")==0) || (TokenActual.Token.compareTo("<null>")==0) || (TokenActual.Token.compareTo("<true>")==0)|| (TokenActual.Token.compareTo("<false>")==0)|| (TokenActual.Token.compareTo("<Numero_Entero>")==0)|| (TokenActual.Token.compareTo("<Carácter>")==0)|| (TokenActual.Token.compareTo("<Cadena Caracteres>")==0)|| (TokenActual.Token.compareTo("<this>")==0)|| (TokenActual.Token.compareTo("<.>")==0)||(TokenActual.Token.compareTo("<(>")==0)|| (TokenActual.Token.compareTo("<new>")==0)||(TokenActual.Token.compareTo("<super>")==0))  
			 Expression();
		 // Sino ExpressionOpcional ::= <vacio>
	 }

	 
	 // StatementCont ::= <else> Statement | <vacio>
	 private void StatementCont() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if (TokenActual.Token.compareTo("<else>")==0)
		 	{
			 	TokenActual = null;
			 	Statement();
		 	}
		 // Sino  StatementCont ::= <vacio>
	 }
	 
	  
	 
	 
	 // Statement ::= <;> | <if> <(> Expression <)> Statement StatementCont | <return> ExpressionOpcional <;> | Block | <for> <(> Expression <;> Expression <;> Expression <)> Statement |  <while> <(> Expression <)> Statement
	 private void Statement() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 if (TokenActual.Token.compareTo("<;>")==0)
				 {
			 	  TokenActual =null;
			 	  // Estado Aceptador.
				 }
		 else if (TokenActual.Token.compareTo("<if>")==0)
				 {
  				  TokenActual = Lex.GetToken();
			 	  if (TokenActual.Token.compareTo("<(>")==0)
			 		{
			 		    TokenActual =null;
			 		  	Expression();
			 		  	if (TokenActual == null)  
							TokenActual = Lex.GetToken();
			 		  	if (TokenActual.Token.compareTo("<)>")== 0 )
			 		  		{
			 		  			TokenActual =null;
			 		  			Statement();
			 		  			StatementCont();
			 		  		}
			 		  	else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un )");
			 		}
			 	 else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un (");
				 }
		 	  else if (TokenActual.Token.compareTo("<return>")== 0 )
		 			  {
		 		  		TokenActual =null;
		 		  		ExpressionOpcional(); 
		 		  		if (TokenActual == null)  
							TokenActual = Lex.GetToken();
		 		  		if (TokenActual.Token.compareTo("<;>")== 0 )
		 		  			{
		 		  				TokenActual =null;
		 		  				// Estado Aceptador.
		 		  			}
		 		  		else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un ;");
		 			  }
		 	  	  else  if (TokenActual.Token.compareTo("<{>") == 0) 
						 	Block();
		 	  	  		else // <for> <(> Expression <;> Expression <;> Expression <)> Statement 
		 	  	  			if (TokenActual.Token.compareTo("<for>") == 0)
		 	  	  				{
		 	  	  				  TokenActual = Lex.GetToken();
		 	  	  				  if (TokenActual.Token.compareTo("<(>")==0)
		 	  	  				  	{
		 	  	  					  TokenActual =null;
		 	  	  					  Expression();
		 	  	  					  if (TokenActual == null)  
		 	  	  						  TokenActual = Lex.GetToken();
		 	  	  					  if (TokenActual.Token.compareTo("<;>")== 0 )
		 	  	  					  	{
		 	  	  						  TokenActual =null;
		 	  	  						  Expression();
		 	  	  						  if (TokenActual == null)  
		 	  	  							  TokenActual = Lex.GetToken();
		 	  	  						  if (TokenActual.Token.compareTo("<;>")== 0 )
		 	  	  						  	{
		 	  	  							  TokenActual =null;
				 	  	  					  Expression();
				 	  	  					  if (TokenActual == null)  
				 	  	  						  TokenActual = Lex.GetToken();
				 	  	  					  if (TokenActual.Token.compareTo("<)>")== 0 )
				 	  	  					  	{
				 	  	  						  TokenActual =null;
				 	  	  						  Statement();
				 	  	  					  	}
				 	  	  					  else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un )");
				 	  	  					 }
		 	  	  						  else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un ;");
		 	  	  						 }
		 	  	  					 else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un ;");
		 	  	  				  	}
		 	  	  				  else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un (");
		 	  	  				}
		 	  	  			else // <while> <(> Expression <)> Statement
	 	 						if (TokenActual.Token.compareTo("while") == 0)
	 	 							{
	 	 							  TokenActual = Lex.GetToken();
	 	 							  if (TokenActual.Token.compareTo("<(>")==0)
	 	 							  	{
	 	 								  TokenActual =null;
		 	  	  						  Expression();
		 	  	  						  if (TokenActual == null)  
		 	  	  							  TokenActual = Lex.GetToken();
		 	  	  						  if (TokenActual.Token.compareTo("<)>")== 0 )
		 	  	  						  	 {	
		 	  	  							   TokenActual =null;
		 	  	  							   Statement();
		 	  	  						  	 }
		 	  	  						  else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un )");
	 	 								  	
	 	 							  	}
	 	 							  else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un (");
	 	 							}
	 }
	 // ListaStatement ::= Statement ListaStatement | <vacio>
	 private void ListaStatement() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 if ((TokenActual.Token.compareTo("<;>") == 0)|| (TokenActual.Token.compareTo("<if>") == 0)|| (TokenActual.Token.compareTo("<return>") == 0) || (TokenActual.Token.compareTo("<for>") == 0)|| (TokenActual.Token.compareTo("<while>") == 0))
		 	{
			 Statement();
			 ListaStatement();
		 	}
		 else if (TokenActual.Token.compareTo("<{>") == 0) 
				 Block();
		 	  
		 	  // Sino ListaStatement ::= <vacio>

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
		 		else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un }");
		 	}
		 	else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba una {");
		 
		 
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
	 
	 
	 
	 
	 // Field ::= VarDeclaratorList <;>
	 private void Field() throws Exception
	 {
		 VarDeclaratorList();
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 if (TokenActual.Token.compareTo("<;>") == 0)
		 	{
		 		TokenActual = null;
		 		// Estado Aceptador.
		 		
		 	}
		 else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un ;");
	 }
	 
	 
	 
	 
	 // MethodSinTypeSinID ::= FormalArgs Block
	 private void MethodSinTypeSinID() throws Exception
	 {
		 FormalArgs();
		 Block();
	 }
	 
	 // PreAnalisis_Field_Method_Cont2 ::=  Field PreAnalisis_Field_Ctor_Method |  MethodSinTypeSinID ListaMethod
	 
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
		 				MethodSinTypeSinID();
		 				ListaMethod();
		 			}
		 		 else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba una , o un (");
		 
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
		 else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un identificador"); 
		 
	 }
	 
	  
	 
	 // CtorSinID :==  FormalArgs Block | <vacio>
	 private void CtorSinID() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 if (TokenActual.Token.compareTo("<(>") == 0)
			 {
			 FormalArgs();
			 Block();
			 }
		 // Sino CtorSinID :== <vacio>
	 }
	 
	 
	 
	 
	 // PreAnalisis_Ctor_Method_Cont ::= CtorSinID PreAnalisis_Ctor_Method  | <identifier> FormalArgs Block ListaMethod 
	 private void PreAnalisis_Ctor_Method_Cont() throws Exception
	 {
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 if (TokenActual.Token.compareTo("<(>") == 0)
			 {
			 	CtorSinID();
			 	PreAnalisis_Ctor_Method();
			 }
		 else {
			 	if (TokenActual == null)  
					TokenActual = Lex.GetToken();
			 
			 	if (TokenActual.Token.compareTo("<Identificador>") == 0)
			 		{
			 			TokenActual = null;  
			 			FormalArgs();
			 			Block();
			 			ListaMethod();
			 		}
			 	else throw new Exception("Lineas " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un identificador");
			 		
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
		 else throw new Exception("Lineas " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un identificador");
	  }
	 
	 
	 
	 // MethodSinID ::=  MethodTypeSinID <identifier> FormalArgs Block
	 private void MethodSinID() throws Exception
	 {
		 MethodTypeSinID();
		 if (TokenActual == null)  
				TokenActual = Lex.GetToken();
		 
		 if (TokenActual.Token.compareTo("<Identificador>") == 0)
		 	{
			 TokenActual = null;  
			 FormalArgs();
			 Block();
		 	}
		 else throw new Exception("Lineas " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un identificador");
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
			 TokenActual = null;
			 PreAnalisis_Ctor_Method_Cont(); 
			 
		 	}
		 else ListaMethodSinID();
		 
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
	 
	 
	 
	 // PreAnalisis_Field_Ctor_Method ::= <Identificador> PreAnalisis_Field_Ctor_Method_Cont | <void> Method ListaMethod | PreAnalisis_Field_Method_Cont | <vacio>
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
		 	  	   // Sino  PreAnalisis_Field_Ctor_Method ::= <vacio>
		 
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
				 		else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba una } ");
				 	  }
				 	 else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba una { ");
			 	}
			 else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba un identificador");
			}
		 else throw new Exception("Linea " + TokenActual.Linea + " Columna " + TokenActual.Columna+ ": ERROR: Se esperaba por lo menos un class");
		 
	 }
	 
 
}
