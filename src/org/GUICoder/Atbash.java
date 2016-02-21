package org.GUICoder;

class Atbash extends Coder{
	@Override
	String code(String message, int lvl){
		StringBuilder result=new StringBuilder();
		for(Character i:message.toCharArray())
			result.append(chars.get(chars.size()-1-chars.indexOf(i)));
		return lvl==1?result.toString():code(result.toString(), --lvl);
	}
	
	@Override
	void setParametr(Object var){
		throw new UnsupportedOperationException();
	}
	
	@Override
	String decode(String coded, int lvl){
		StringBuilder result=new StringBuilder();
		for(Character i:coded.toCharArray())
			result.append(chars.get(chars.size()-1-chars.indexOf(i)));
		return lvl==1?result.toString():decode(result.toString(), --lvl);
	}
	
	@Override
	public String toString(){
		return "Atbash cipher";
	}
}
