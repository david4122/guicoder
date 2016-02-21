package org.GUICoder;

import java.util.*;

class Vigenere extends Coder{
	private static final ArrayList<ArrayList<Character>>codeTab=new ArrayList<ArrayList<Character>>();
	private ArrayList<Character>key=new ArrayList<Character>();
	
	Vigenere(){
		for(int i=0;i<chars.size();i++)
			codeTab.add(createRow(i));
		setParametr("thisIsKey");
	}
	
	Vigenere(String key){
		for(int i=0;i<chars.size();i++)
			codeTab.add(createRow(i));
		setParametr(key);
	}
	
	void setParametr(Object key){
		for(Character i:((String)key).toCharArray())
			this.key.add(i);
	}
	
	private ArrayList<Character>createRow(int move){
		ArrayList<Character>result=new ArrayList<Character>(chars.size());
		for(int i=0;i<chars.size();i++)
			result.add(chars.get((i+move)%chars.size()));
		return result;
	}
	
	private ArrayList getRow(Character c, int index){
		for(ArrayList<Character>i:codeTab)
			if(i.get(index).equals(c))
				return i;
		return null;
	}
	
	@Override
	String code(String message, int lvl){
		StringBuilder result=new StringBuilder();
		for(int i=0;i<message.length();i++)
			result.append(getRow(message.charAt(i), 0).get(codeTab.get(0).indexOf(key.get(i%key.size()))));
		return lvl==1?result.toString():code(result.toString(), --lvl);
	}
	
	@Override
	String decode(String coded, int lvl){
		StringBuilder result=new StringBuilder();
		for(int i=0;i<coded.length();i++){
			result.append(codeTab.get(0).get(getRow(key.get(i%key.size()), 0).indexOf(coded.charAt(i))));
		}
		return lvl==1?result.toString():decode(result.toString(), --lvl);
	}
	
	@Override
	public String toString(){
		return "Vigenere cipher";
	}
}
