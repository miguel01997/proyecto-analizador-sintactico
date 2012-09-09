import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AnalizLex 
{
	 BufferedReader Buffer;
	 private int estado;
	 private int LineaActual,ColumnaActual;
	 private char CaracterActual;  
	 PalabrasClavesOProhibidas PalabrasClavesOProhibidas;
 	 
	 public AnalizLex(String archivo) throws Exception
	 {
		 estado = 0;
		 LineaActual = 1;
		 ColumnaActual = 0;
		 CaracterActual = ' ';
		 PalabrasClavesOProhibidas = new PalabrasClavesOProhibidas();
		 Buffer = new BufferedReader(new FileReader("./"+archivo));
		 
	 }

	 
	 public void LeerCaracter() throws IOException
	 {	 
		 CaracterActual = ' ';
		 int Valor =  Buffer.read();
		 if (Valor != -1)
			 {
				 CaracterActual = (char) Valor;
				 if (CaracterActual=='\n')
				 { 
					 LineaActual++;
					 ColumnaActual = 0;
				 }
				 else ColumnaActual++;
			 }
		 else CaracterActual = '\f'; // Caracter de fin de linea 
	 }


	 
	  public Token GetToken() throws Exception
	    {
		  estado = 0;
		  Token Token = new Token(); 
		  boolean Fin = false;
		  while (!Fin) 
		  {
			  switch (estado)
			  {
			  	case 0: if (Character.isLetter(CaracterActual) || (CaracterActual == '_')) // Es letra o guion bajo
			  			{
			  				estado = 1;
			  				Token.Lexema += CaracterActual ; 
			  				Token.Linea = LineaActual;
			  				Token.Columna = ColumnaActual;
			  				LeerCaracter(); 
			  			}
			  			else if (Character.isDigit(CaracterActual))  // Es digito.
			  				 	{
			  						estado = 19;
			  						Token.Lexema += CaracterActual ; 
			  						Token.Linea = LineaActual;
			  						Token.Columna = ColumnaActual;
			  						LeerCaracter();
			  						
			  						if ((Token.Lexema.compareTo("0")==0) && (Character.isDigit(CaracterActual)))
			  							throw new Exception("Linea " + LineaActual + " Columna " + ColumnaActual+ ": ERROR: Numero mal formado '" + CaracterActual +"'");
			  							
			  						
			  				 	}
			  								  			
			  				 else switch (CaracterActual)   //Vemos cada caso y vamos al estado que corresponde.
			  				 								// Ya ponemos linea Actual y Columna Actual pues eso indica que ahi comenzo el identificador, operador, etc.
			  				 	  {
			  				 	    case '/': estado = 3 ;
			  				 		          Token.Lexema += CaracterActual ;
			  			  					  Token.Linea = LineaActual;
			  				 		          Token.Columna = ColumnaActual;
			  				 		          LeerCaracter(); 
			  				 		          break;
			  				 	
			  				 	    case '\'': estado = 9 ;  // Comillas simple que abre
			  				 			       Token.Lexema += CaracterActual ;
			  				 			       Token.Linea = LineaActual;
			  				 			       Token.Columna = ColumnaActual;
			  				 			       LeerCaracter(); 
			  				 			       break;
			  				 			   
			  				 	    case '"': estado = 17 ;  // Comillas doble que abre
	  				 			   		  	  Token.Lexema += CaracterActual ;
	  				 			   		  	  Token.Linea = LineaActual;
	  				 			   		  	  Token.Columna = ColumnaActual;
	  				 			   		  	  LeerCaracter(); 
	  				 			   		  	  break;
			  				 	    
			  				 	    case '{': case '}': case '(': case ')': case '[': case ']': case ',': case '.': 
			  				 	    case ';': estado = 21;  // Entra en caso de ser '{','}','(',')''[',']',',','.',';'
			  				 	    		  break;
			  				 	   case '=': estado = 22;
 				 	   			 			 Token.Lexema += CaracterActual ;
 				 	   			 			 Token.Linea = LineaActual;
 				 	   			 			 Token.Columna = ColumnaActual;
 				 	   			 			 LeerCaracter(); 
 				 	   			 			 break;
			  				 	   case '>': estado = 25;
		 	   			 			 		 Token.Lexema += CaracterActual ;
		 	   			 			 		 Token.Linea = LineaActual;
		 	   			 			 		 Token.Columna = ColumnaActual;
		 	   			 			 		 LeerCaracter(); 
		 	   			 			 		 break;
			  				 	   case '<': estado = 30;
	   			 			 		 		 Token.Lexema += CaracterActual ;
	   			 			 		 		 Token.Linea = LineaActual;
	   			 			 		 		 Token.Columna = ColumnaActual;
	   			 			 		 		 LeerCaracter(); 
	   			 			 		 		 break;
			  				 	   case '!': estado = 34;
		 			 		 		 		 Token.Lexema += CaracterActual ;
		 			 		 		 		 Token.Linea = LineaActual;
	   			 			 		 		 Token.Columna = ColumnaActual;
		 			 		 		 		 LeerCaracter(); 
		 			 		 		 		 break; 
			  				 	   case '+': estado = 37;
		 			 		 		 		 Token.Lexema += CaracterActual ;
		 			 		 		 		 Token.Linea = LineaActual;
	   			 			 		 		 Token.Columna = ColumnaActual;
		 			 		 		 		 LeerCaracter(); 
		 			 		 		 		 break;
			  				 	   case '*': estado = 41;
			 		 		 		 		 Token.Lexema += CaracterActual ;
			 		 		 		 		 Token.Linea = LineaActual;
			 		 		 		 		 Token.Columna = ColumnaActual;
			 		 		 		 		 LeerCaracter(); 
			 		 		 		 		 break;
			  				 	   case '-': estado = 44;
			  				 	   			 Token.Lexema += CaracterActual ;
			  				 	   			 Token.Linea = LineaActual;
			  				 	   			 Token.Columna = ColumnaActual;
			  				 	   			 LeerCaracter(); 
			  				 	   			 break;
			  				 	   case '&': estado = 48;
  				 	   			 		     Token.Lexema += CaracterActual ;
  				 	   			 		     Token.Linea = LineaActual;
  				 	   			 		     Token.Columna = ColumnaActual;
  				 	   			 		     LeerCaracter(); 
  				 	   			 		     break;
			  				 	   case '|': estado = 51;
  				 	   			 		   	 Token.Lexema += CaracterActual ;
  				 	   			 		   	 Token.Linea = LineaActual;
  				 	   			 		   	 Token.Columna = ColumnaActual;
  				 	   			 		   	 LeerCaracter(); 
  				 	   			 		   	 break;
			  				 	   case '%': estado = 54;
		 	   			 		   			 Token.Lexema += CaracterActual ;
		 	   			 		   			 Token.Linea = LineaActual;
		 	   			 		   			 Token.Columna = ColumnaActual;
		 	   			 		   			 LeerCaracter(); 
		 	   			 		   			 break;
			  				 	   case '^': estado = 57;
			  				 	   			 Token.Lexema += CaracterActual ;
			  				 	   			 Token.Linea = LineaActual;
				 	   			 		   	 Token.Columna = ColumnaActual;
			  				 	   			 LeerCaracter(); 
			  				 	   			 break;
			  				 	   case '~':  case '?': 
			  				 	   case ':': estado = 60;
		 	   			 			 		 Token.Lexema += CaracterActual ;
		 	   			 			 		 Token.Linea = LineaActual;
		 	   			 			 		 Token.Columna = ColumnaActual;
		 	   			 			 		 LeerCaracter(); 
		 	   			 			 		 break;
			  				 	   case '\f': Fin = true; 	// Caso de fin de archivo	  
			  				 	   case '\r': case '\n': case '\t':
			  				 	   case ' ': LeerCaracter();   // Caso retorno de carro, fin de linea, tabulador o espacio entonces lo salteamos.
			  				 	   			 break;
			  				 	   			 	// Cualquier otra cosa es un caracter no soportado por el lenguaje.. entonces error.
			  				 	   default: throw new Exception("Linea " + LineaActual + " Columna " + ColumnaActual+ ": ERROR: Caracter no soportado '" + CaracterActual +"'");
			  				 	  }
			  		    break;
			  	case 1: if (Character.isLetter(CaracterActual) || Character.isDigit(CaracterActual) || (CaracterActual == '_'))
	  					{
	  						estado = 1;  // Me mantengo en este estado para seguir formando el identificador.
			  			    Token.Lexema += CaracterActual; 
	  						LeerCaracter(); 
	  					}
			  			else estado = 2; // Va a queda un caracter guardado en Caracter actual que va a ser consumido en el proximo token.
			  			break;
			  	case 2: Fin = true;   // Me voy a fijar si el identificador formado es palabra prohibida, clave, primitiva o literal.. sino efectivamente es un identificador.
			  			if (!PalabrasClavesOProhibidas.EsPalabraProhibida(Token.Lexema))
			  					if (!PalabrasClavesOProhibidas.EsPalabraClave(Token.Lexema))
			  						if (!PalabrasClavesOProhibidas.EsPalabraPrimitiva(Token.Lexema))
			  							if ((Token.Lexema.compareTo("true")==0)||(Token.Lexema.compareTo("false")==0))
			  								Token.Token = "<"+ Token.Lexema +">"; // Literal Booleano
			  							else if (Token.Lexema.compareTo("null")==0)
			  									Token.Token = "<"+ Token.Lexema +">"; // Literal Nulo
			  								 else Token.Token = "<Identificador>";
			  						else Token.Token = "<"+ Token.Lexema +">"; // Tipo primitivo
			  					else  Token.Token = "<"+ Token.Lexema +">";  // Palabra clave
			  			else throw new Exception("Linea " + Token.Linea + " Columna " + Token.Columna+ ": ERROR: Palabra Prohibida '" + Token.Lexema +"'");
			  			break;
			  	case 3: switch (CaracterActual)
			  			{
			  				case '*': estado = 5;   // Comentario que abre "/*"
			  						  Token.Lexema += CaracterActual ;
			  						  LeerCaracter(); 
			  						  break;
				 	
			  				case '/': estado = 7;    // Comentario de una linea "//"
	  						  		  Token.Lexema += CaracterActual ;
	  						  		  LeerCaracter(); 
	  						  		  break;
			  				case '=': estado = 4;    // Operador /=
					  		  		  Token.Lexema += CaracterActual ;
					  		  		  LeerCaracter(); 
					  		  		  break;
			  				default: estado = 4;  // Si viene otra cosa entonces forme el operador '/'
			  			}
			  			break;
			  	case 4: Fin = true;
			  	 		if (!PalabrasClavesOProhibidas.EsPalabraProhibida(Token.Lexema))
			  	 			Token.Token = "<" + Token.Lexema +">";
			  	 		else throw new Exception("Linea " + LineaActual + " Columna " + Token.Columna+ ": ERROR: Operador Prohibido '" + Token.Lexema +"'");
	  					break;
			  	case 5: switch (CaracterActual)
	  					{
	  						case '*': estado = 6;   // Al ver un asterisco podria ser que el proximo caracter sea una / para poder cerrar el comentario.
	  								  Token.Lexema += CaracterActual ;
	  								  LeerCaracter(); 
	  								  break;
	  						// Si se acaba el archivo y no se cerro el comentario /* entonce error..
	  						case '\f': throw new Exception("Linea " + Token.Linea + " Columna " + Token.Columna+ ": ERROR: Falta */ de cierre para '" + Token.Lexema +"'");
	  						
	  						default: estado = 5;  // Sino me mantengo en este estado salteando las cadenas internas del comentario.	
	  						 		 LeerCaracter(); 
	  					}
			  			break;
			  	case 6: switch (CaracterActual)
						{
							case '/': estado = 8;     // Encontre la barra que finaliza el comentario.. 
									  Token.Lexema += CaracterActual ;
									  LeerCaracter();
									  break;
									  
							// Si se acaba el archivo y no se cerro el comentario /* entonce error..	  
							case '\f': throw new Exception("Linea " + Token.Linea + " Columna " + Token.Columna+ ": ERROR: Falta / de cierre para '" + Token.Lexema +"'");
							   		 
							// Si no vino una barra / entonces volvemos al estado 5 a saltear las cadenas internas del comentario.
							default: estado = 5;	
								     LeerCaracter(); 
						}
			  			break;
			  	case 7: switch (CaracterActual)
			  			{
			  				case '\n': 
			  				case '\f': estado = 8;  
									   break;
			  				default: estado = 7;   // nos mantenemos en este estado salteando caracteres hasta que venga un fin de linea o archivo..	
			  						 LeerCaracter(); 
			  			}
			  			break;
			  	case 8:	Token.Token = "";  // Limpio el token y lexema y vuelvo al estado cero para empezar de nuevo. Elimine por completo los comentarios.
			  			Token.Lexema = "";
			  			estado = 0;
			  			break;
				
			  	case 9: switch (CaracterActual)
	  					{
	  						case '\\': estado = 12;  
	  								   Token.Lexema += CaracterActual ;
							  		   LeerCaracter(); 
  									   break;

	  						default: estado = 10;	
	  								 Token.Lexema += CaracterActual ;
	  								 LeerCaracter(); 
	  					}
			  			break;
			  	case 10: switch (CaracterActual)
						 {
						 	case '\'': estado = 11; // Comilla de cierre
								   	   Token.Lexema += CaracterActual ;
								   	   LeerCaracter();
								   	   break;
						 	default: throw new Exception("Linea " + Token.Linea + " Columna " + Token.Columna+ ": ERROR: Falta comilla simple de cierre " + Token.Lexema); 
						 }
						 break;
			  	case 11: Fin = true;
			  			 Token.Token = "<Caracter, ascii>";
			  			 break;
			  	case 12: switch (CaracterActual)
			  			 {
			  			 	case 'n': estado = 13; 
						   	   		  Token.Lexema += CaracterActual ;
						   	   		  LeerCaracter(); 
						   	   		  break;
			  			 	case 't': estado = 15; 
				   	   		   		  Token.Lexema += CaracterActual ;
				   	   		   		  LeerCaracter(); 
				   	   		   		  break;
			  			 	default: estado = 10;	
			  			 		     Token.Lexema += CaracterActual ;
	  						 		 LeerCaracter(); 
			  			 }
			  		     break;
			  	case 13: switch (CaracterActual)
			 			 {
			 			 	case '\'': estado = 14; // Comilla de cierre
			 					   	   Token.Lexema += CaracterActual ;
			 					   	   LeerCaracter();
			 					   	   break;
			 			 	default: throw new Exception("Linea " + Token.Linea + " Columna " + Token.Columna+ ": ERROR: Falta comilla simple de cierre " + Token.Lexema );
			 			 }
			  			 break;
			  	case 14: Fin = true;
			  			 Token.Token = "<Caracter, Fin de Linea>";
			  			 break;
			  	case 15: switch (CaracterActual)
	 			 		 {
	 			 		 	case '\'': estado = 16; // Comilla de cierre
	 			 		 		 	   Token.Lexema += CaracterActual ;
	 			 		 		 	   LeerCaracter();
	 			 		 		 	   break;
	 			 		 	default: throw new Exception("Linea " + Token.Linea + " Columna " + Token.Columna+ ": ERROR: Falta comilla simple de cierre " + Token.Lexema );
	 			 		 }
			  			 break;
			  	case 16: Fin = true;
	  			 		 Token.Token = "<Caracter, Tabulador>";
	  			 		 break;
			  	case 17: switch (CaracterActual)
	  					 {
	  					  	case '"': estado = 18; // Comilla doble de cierre
	  					  			  Token.Lexema += CaracterActual ;
	  					  			  LeerCaracter(); 
	  					  			  break;
	  					  	case '\n': throw new Exception("Linea " + Token.Linea + " Columna " + Token.Columna+ ": ERROR: Falta comilla doble de cierre " + Token.Lexema );	  					  			  
	  					    case '\f': throw new Exception("Linea " + Token.Linea + " Columna " + Token.Columna+ ": ERROR: Falta comilla doble de cierre " + Token.Lexema ); 

	  					  	
	  					    default: estado = 17;
	  					    		 Token.Lexema += CaracterActual ;
	  					    		 LeerCaracter(); 
	  				
	  					 }
	  				 	 break;
			  	case 18: Fin = true;
			 		 	 Token.Token = "<Cadena Caracteres>";
			 		 	 break;

			  	case 19: if (Character.isDigit(CaracterActual))
				 			{
							  estado = 19;
							  Token.Lexema += CaracterActual ; 
							  LeerCaracter(); 
				 			}
			  			else if (!Character.isLetter(CaracterActual))
			  					estado = 20;  
			  				 else {
			  					 	Token.Lexema += CaracterActual ; 
			  					 	throw new Exception("Linea " + Token.Linea + " Columna " + Token.Columna+ ": ERROR: Numero mal formado " + Token.Lexema );
			  				 	  }

						break;
						
			  	case 20: Fin = true;
	 		 	 		 Token.Token = "<Numero Entero>";
	 		 	 		 break;
				case 21: Fin = true;
	 	   			 	 Token.Lexema += CaracterActual ;
	 	 		 		 Token.Token = "<" + CaracterActual + ">";
  	 	 		 		 Token.Linea = LineaActual;
		 	   			 Token.Columna = ColumnaActual;
		 	   			 LeerCaracter();
	 	 		 		 break;
				case 22: switch (CaracterActual)
					 	 {
					  		case '=': estado = 23; 
					  			  	  Token.Lexema += CaracterActual ;
					  			  	  LeerCaracter(); 
					  			  	  break;
					  		default: Token.Token = "<Operador asignacion>";
					  				 estado = 24;
					 	 }
				 	 	 break;
				case 23: estado = 24;
						 Token.Token = "<Operador Igualdad>";
						 break;
				case 24: Fin = true;
		 	 			 break;
				case 25: switch (CaracterActual)
			 	 		 {
			 	 		 	case '=': estado = 27; 
			  			  			  Token.Lexema += CaracterActual ;
			  			  			  LeerCaracter(); 
			  			  			  break;
			 	 		 	case '>': estado = 26; 
	  			  			  		  Token.Lexema += CaracterActual ;
	  			  			  		  LeerCaracter(); 
	  			  			  		  break;
			 	 		 	default: estado = 29;
			 	 		 }
						 break;
			 	 case 26: switch (CaracterActual)
	 	 		 		  {
	 	 		 		  	case '=': estado = 27; 
	 	 		 		  			  Token.Lexema += CaracterActual ;
	 	 		 		  			  LeerCaracter(); 
	 	 		 		  			  break;
	 	 		 		  	case '>': estado = 28; 
	 	 		 		  			  Token.Lexema += CaracterActual ;
	 	 		 		  			  LeerCaracter(); 
	 	 		 		  			  break;
	 	 		 		  	default: estado = 29;
	 	 		 		  }
			 	 		  break;
			 	 case 27: estado = 29;
			 	 	  	  break;
			 	 case 28: switch (CaracterActual)
			 	 		  {
			 	 		     case '=': estado = 27; 
			 	 		   	     	   Token.Lexema += CaracterActual ;
			 	 		   	  		   LeerCaracter(); 
			 	 		   	  		   break;
			 	 		   	 default: estado = 29;
			 	 		   }
			 	 		   break;
			 	 case 29: Fin = true;
			 	 		  if (!PalabrasClavesOProhibidas.EsPalabraProhibida(Token.Lexema))
			 	 			  Token.Token = "<" + Token.Lexema +">";
			 	 		  else throw new Exception("Linea " + LineaActual + " Columna " + Token.Columna+ ": ERROR: Operador Prohibido '" + Token.Lexema +"'");
	 		 		 	  break;
			 	 
			 	 case 30: switch (CaracterActual)
	 	 		 		  {
	 	 		 		  	case '=': estado = 32; 
	  			  			  		  Token.Lexema += CaracterActual ;
	  			  			  		  LeerCaracter(); 
	  			  			  		  break;
	 	 		 		  	case '<': estado = 31; 
			  			  		  	  Token.Lexema += CaracterActual ;
			  			  		  	  LeerCaracter(); 
			  			  		  	  break;
	 	 		 		  	default: estado = 33;
	 	 		 		  }
			 	 		  break;	
			 	 case 31: switch (CaracterActual)
	 	 		  		  {
	 	 		  		    case '=': estado = 32; 
	 	 		   	     	   		  Token.Lexema += CaracterActual ;
	 	 		   	     	   		  LeerCaracter(); 
	 	 		   	     	   		  break;
	 	 		  		    default: estado = 33;
	 	 		  		  }
	 	 		   		  break;
			 	case 32: estado = 33;
	 	 	  	  		 break;
			 	 		  
			 	case 33: Fin = true;
			 	 		 if (!PalabrasClavesOProhibidas.EsPalabraProhibida(Token.Lexema))
			 	 			 	Token.Token = "<" + Token.Lexema +">";
			 	 		 else throw new Exception("Linea " + LineaActual + " Columna " + Token.Columna+ ": ERROR: Operador Prohibido '" + Token.Lexema +"'");
	 		 	  		 break;
			 	case 34: switch (CaracterActual)
		  		  		 {
		  		  		 	case '=': estado = 35; 
		   	     	   		  		  Token.Lexema += CaracterActual ;
		   	     	   		  		  LeerCaracter(); 
		   	     	   		  		  break;
		  		  		 	default: estado = 36;
		  		  		 }
			 			 break;
			 	case 35: estado = 36;
			 			 break;
			 	case 36: Fin = true;
	 	  		 		 Token.Token = "<"+ Token.Lexema +">";
	 	  		 		 break;
			 	case 37: switch (CaracterActual)
		  		  		 {
		  		  		 	case '=': estado = 38; 
		   	     	   		  		  Token.Lexema += CaracterActual ;
		   	     	   		  		  LeerCaracter(); 
		   	     	   		  		  break;
		  		  		 	case '+': estado = 39; 
 	     	   		  		  		  Token.Lexema += CaracterActual ;
 	     	   		  		  		  LeerCaracter(); 
 	     	   		  		  		  break;
		  		  		 	default: estado = 40;
		  		  		 }
			 			 break;	 
			 	case 38: 
				case 39: estado = 40;
		 		 		 break;
				case 40: Fin = true;
				 		 if (!PalabrasClavesOProhibidas.EsPalabraProhibida(Token.Lexema))
				 			 	Token.Token = "<Suma>";
				 		 else throw new Exception("Linea " + LineaActual + " Columna " + Token.Columna+ ": ERROR: Operador Prohibido '" + Token.Lexema +"'");
						 break;
				case 41: switch (CaracterActual)
 		  		 		 {
 		  		 		 	case '=': estado = 42; 
 		  		 		 			  Token.Lexema += CaracterActual ;
 		  		 		 			  LeerCaracter(); 
 		  		 		 			  break;
 		  		 		 	default: estado = 43;
 		  		 		 }
	 			 		 break;	 
				case 42: estado = 43;
		 		 		 break;
				case 43: Fin = true;
				 		 if (!PalabrasClavesOProhibidas.EsPalabraProhibida(Token.Lexema))
				 			 Token.Token = "<Multiplicacion>";	
				 		 else throw new Exception("Linea " + LineaActual + " Columna " + Token.Columna+ ": ERROR: Operador Prohibido '" + Token.Lexema +"'");
				 		 break;	
				case 44: switch (CaracterActual)
 		  		 		 {
 		  		 		 	case '=': estado = 45; 
  	     	   		  		  		  Token.Lexema += CaracterActual ;
  	     	   		  		  		  LeerCaracter(); 
  	     	   		  		  		  break;
 		  		 		 	case '-': estado = 46; 
 	   		  		  		  		  Token.Lexema += CaracterActual ;
 	   		  		  		  		  LeerCaracter(); 
 	   		  		  		  		  break;
 		  		 		 	default: estado = 47;
 		  		 		 }
	 			 		 break;
				case 45:
				case 46: estado = 47;
		 		 		 break;
				case 47: Fin = true;
				 		 if (!PalabrasClavesOProhibidas.EsPalabraProhibida(Token.Lexema))
				 			Token.Token = "<Resta>";
				 		 else throw new Exception("Linea " + LineaActual + " Columna " + Token.Columna+ ": ERROR: Operador Prohibido '" + Token.Lexema +"'");
		 		 		 break;	
				case 48: switch (CaracterActual)
						 {
						 	case '&': estado = 49; 
						 			  Token.Lexema += CaracterActual ;
						 			  LeerCaracter(); 
						 			  break;
						 	case '=': estado = 50; 
				 			  		  Token.Lexema += CaracterActual ;
				 			  		  LeerCaracter(); 
				 			  		  break;
						 	default: estado = 50;
						 }
		 		 		 break;
				case 49: estado = 50;
		 		 		 break;
				case 50: Fin = true;
						 if (!PalabrasClavesOProhibidas.EsPalabraProhibida(Token.Lexema))
							 	Token.Token = "<And>";
						 else throw new Exception("Linea " + LineaActual + " Columna " + Token.Columna+ ": ERROR: Operador Prohibido '" + Token.Lexema +"'");
						 break;	
				case 51: switch (CaracterActual)
				 		 {
				 		 	case '|': 
				 		 	case '=': estado = 52; 
		 			  		  		  Token.Lexema += CaracterActual ;
		 			  		  		  LeerCaracter(); 
		 			  		  		  break;	
				 		 	default: estado = 53;
				 		 }
						 break;
				case 52: estado = 53;
		 		 		 break;	 
				case 53: Fin = true;
				 		 if (!PalabrasClavesOProhibidas.EsPalabraProhibida(Token.Lexema))
				 			 Token.Token = "<OR>";
				 		 else throw new Exception("Linea " + LineaActual + " Columna " + Token.Columna+ ": ERROR: Operador Prohibido '" + Token.Lexema +"'");
						 break;	
				case 54: switch (CaracterActual)
						 {
						 	case '=': estado = 55; 
				 			  		  Token.Lexema += CaracterActual ;
				 			  		  LeerCaracter(); 
				 			  		  break;
						 	default: estado = 56;
						 }
		 		 		 break;
				case 55: estado = 56;
						 break;	 
				case 56: Fin = true;
						 if (!PalabrasClavesOProhibidas.EsPalabraProhibida(Token.Lexema))
							 Token.Token = "<Modulo>";		
						 else throw new Exception("Linea " + LineaActual + " Columna " + Token.Columna+ ": ERROR: Operador Prohibido '" + Token.Lexema +"'");
				 		 break;	
				case 57: switch (CaracterActual)
				 		 {
				 		 	case '=': estado = 58; 
		 			  		  		  Token.Lexema += CaracterActual ;
		 			  		  		  LeerCaracter(); 
		 			  		  		  break;
				 		 	default: estado = 59;
				 		 }
		 		 		 break;
				case 58: estado = 59;
				 		 break;	 
				 		 
				case 59: Fin = true;
						 if (!PalabrasClavesOProhibidas.EsPalabraProhibida(Token.Lexema))
							 Token.Token = "<Operador OR Exclusivo>";
						 else throw new Exception("Linea " + LineaActual + " Columna " + Token.Columna+ ": ERROR: Operador Prohibido '" + Token.Lexema +"'");
						 break;	
		 		 		 
				case 60: estado = 61;
				 		 break;
				 		 
				case 61: Fin = true;
						 if (!PalabrasClavesOProhibidas.EsPalabraProhibida(Token.Lexema))
							 Token.Token = "<"+ Token.Lexema +">";
						 else throw new Exception("Linea " + LineaActual + " Columna " + Token.Columna+ ": ERROR: Operador Prohibido '" + Token.Lexema +"'");
		 		 		 break;	
			  }
	    }
		  return Token; 
	   }
	 
  
}    
