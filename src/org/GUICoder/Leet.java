package org.GUICoder;

import java.util.*;

class Leet extends Coder{
	private static final HashMap<Character, Character>letters=new HashMap<Character, Character>();

	static {
		letters.put('a','@');
		letters.put('A','4');
		letters.put('b','8');
		letters.put('c','(');
		letters.put('C','[');
		letters.put('d',')');
		letters.put('D',']');
		letters.put('e','3');
		letters.put('g','9');
		letters.put('G','6');
		letters.put('h','#');
		letters.put('i','1');
		letters.put('I','|');
		letters.put('o','0');
		letters.put('s','5');
		letters.put('S','$');
		letters.put('t','+');
		letters.put('T','7');
		letters.put('z','2');
		letters.put('x','%');
	}

	private Character getSymbol(Character c){
		return letters.containsKey(c)?letters.get(c):(letters.containsKey(Character.toLowerCase(c))?letters.get(Character.toLowerCase(c)):c);
	}

	private Character getKey(Character c){
		for(Character i:letters.keySet())
			if(letters.get(i).equals(c))
				return i;
				
		return c;
	}

	@Override
	String code(String mes, int lvl){
		StringBuilder result=new StringBuilder();
		for(int i=0;i<mes.length();i++)
			result.append(getSymbol(mes.charAt(i)));
		return lvl==1?result.toString():code(result.toString(), --lvl);
	}
	
	@Override
	String decode(String coded, int lvl){
		StringBuilder result=new StringBuilder();
		for(int i=0;i<coded.length();i++)
			result.append(getKey(coded.charAt(i)));
		return lvl==1?result.toString():code(result.toString(), --lvl);
	}

	@Override
	void setParametr(Object var){
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString(){
		return "Leet code";
	}
}
