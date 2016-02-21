package org.GUICoder;

class Cezar extends Coder{
	private int move;
	
	Cezar(){
		this.move=2;
	}
	
	Cezar(int move){
		this.move=move;
	}
	
	@Override
	void setParametr(Object move){
		this.move=(Integer)move;
	}
	
	@Override
	String code(String message, int lvl){
		StringBuilder result=new StringBuilder();
		for(char i:message.toCharArray())
			result.append(chars.get(getIndex((chars.indexOf(i)+move))));
		return lvl==1?result.toString():code(result.toString(), --lvl);
	}
	
	@Override
	String decode(String coded, int lvl){
		StringBuilder result=new StringBuilder();
		for(char i:coded.toCharArray())
			result.append(chars.get(getIndex(chars.indexOf(i)-move)));
		return lvl==1?result.toString():decode(result.toString(), --lvl);
	}
	
	private int getIndex(int index){
		return index<0?chars.size()+index:index%chars.size();
	}
	
	@Override
	public String toString(){
		return "Ceasar cipher";
	}
}
