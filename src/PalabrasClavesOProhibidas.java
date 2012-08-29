import java.util.HashSet;


public class PalabrasClavesOProhibidas 
{
	HashSet<String> PalabrasProhibidas;
	HashSet<String> PalabrasClaves;
	HashSet<String>	PalabrasPrimitiva;
	
	public PalabrasClavesOProhibidas()
	{
		InicializarPalabrasProhibidas();
		InicializarPalabrasClaves();
		InicializarPalabrasPrimitiva();

	}

	
	
	public void InicializarPalabrasProhibidas()
	{
		PalabrasProhibidas = new HashSet <String>();
		
		
		PalabrasProhibidas.add("abstract");    
		PalabrasProhibidas.add("default");
		PalabrasProhibidas.add("long");
		PalabrasProhibidas.add("switch");
		PalabrasProhibidas.add("try");
		
		PalabrasProhibidas.add("byte");
		PalabrasProhibidas.add("do");
		PalabrasProhibidas.add("implements");
		PalabrasProhibidas.add("native");
		PalabrasProhibidas.add("synchronized");
		PalabrasProhibidas.add("volatile");
		
		PalabrasProhibidas.add("case");
		PalabrasProhibidas.add("double");
		PalabrasProhibidas.add("import");
		PalabrasProhibidas.add("goto");
		PalabrasProhibidas.add("throw");
				
		PalabrasProhibidas.add("catch");
		PalabrasProhibidas.add("final");
		PalabrasProhibidas.add("intaceof");
		PalabrasProhibidas.add("package");
		PalabrasProhibidas.add("throws");
		
		PalabrasProhibidas.add("const");
		PalabrasProhibidas.add("finally");
		PalabrasProhibidas.add("interface");
		PalabrasProhibidas.add("short");
		PalabrasProhibidas.add("transient");
		
		PalabrasProhibidas.add("break");
		PalabrasProhibidas.add("continue");
		PalabrasProhibidas.add("private");
		PalabrasProhibidas.add("protected");
		PalabrasProhibidas.add("public");
		PalabrasProhibidas.add("static");
		
		
		PalabrasProhibidas.add("byvalue");
		PalabrasProhibidas.add("none");
		PalabrasProhibidas.add("cast");
		PalabrasProhibidas.add("operator");
		PalabrasProhibidas.add("future");
		PalabrasProhibidas.add("outer");
		PalabrasProhibidas.add("generic");
		PalabrasProhibidas.add("rest");
		PalabrasProhibidas.add("inner");
		PalabrasProhibidas.add("var");
		PalabrasProhibidas.add("float");
		
		
		PalabrasProhibidas.add("~");
		PalabrasProhibidas.add("&");
		PalabrasProhibidas.add("+=");
		PalabrasProhibidas.add("^=");
		PalabrasProhibidas.add("?");
		PalabrasProhibidas.add("|");
		PalabrasProhibidas.add("-=");
		PalabrasProhibidas.add("&=");
		PalabrasProhibidas.add(":");
		PalabrasProhibidas.add("^");
		PalabrasProhibidas.add("*=");
		PalabrasProhibidas.add("<<=");
		PalabrasProhibidas.add("++");
		PalabrasProhibidas.add("<<");
		PalabrasProhibidas.add("/=");
		PalabrasProhibidas.add(">>=");
		PalabrasProhibidas.add("--");
		PalabrasProhibidas.add(">>");
		PalabrasProhibidas.add("&=");
		PalabrasProhibidas.add(">>>=");
		PalabrasProhibidas.add(">>>");
		PalabrasProhibidas.add("|=");
		PalabrasProhibidas.add("%=");
	
		
	}
	
	
	public boolean EsPalabraProhibida(String Lexema)
	{
		return PalabrasProhibidas.contains(Lexema);
	}
	
	public void InicializarPalabrasClaves()
	{
		PalabrasClaves = new HashSet <String>();
		
		
		PalabrasClaves.add("class");
		PalabrasClaves.add("else");
		PalabrasClaves.add("if");
		PalabrasClaves.add("return");
		PalabrasClaves.add("new");
		PalabrasClaves.add("super");
		PalabrasClaves.add("this");
		PalabrasClaves.add("extends");
		PalabrasClaves.add("while");
		PalabrasClaves.add("for");
		PalabrasClaves.add("classDef");
	}
	
	
	public boolean EsPalabraClave(String Lexema)
	{
		return PalabrasClaves.contains(Lexema);
	}
	
	
	public void InicializarPalabrasPrimitiva()
	{
		PalabrasPrimitiva = new HashSet <String>();
		
		
		PalabrasPrimitiva.add("boolean");
		PalabrasPrimitiva.add("char");
		PalabrasPrimitiva.add("int");
		PalabrasPrimitiva.add("void");
		PalabrasPrimitiva.add("String");
		
		
	}
	
	
	public boolean EsPalabraPrimitiva(String Lexema)
	{
		return PalabrasPrimitiva.contains(Lexema);
	}
	
	
	
	
}
