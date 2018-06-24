package com.tabcorp.domain;

public enum BetTypes {

	WIN , 
	PLACE ,
	TRIFECTA , 
	DOUBLE , 
	QUADDIE;
	
	public static boolean contains(String s)
	  {
	      for(BetTypes choice:values())
	           if (choice.name().equals(s)) 
	              return true;
	      return false;
	  } 
	
}
