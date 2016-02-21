package org.GUICoder;

import java.util.*;

abstract class Coder{
	protected static final List<Character>chars=new ArrayList<Character>(255);
	
	Coder(){
		for(int i=0;i<255;i++)
			chars.add(new Character((char)i));
		//chars.add('\n');
		//chars.add('\t');
	}
	
	abstract void setParametr(Object var);
	
	abstract String code(String message, int lvl);
	
	abstract String decode(String coded, int lvl);
}
