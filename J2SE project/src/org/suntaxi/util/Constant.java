package org.suntaxi.util;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	
	
	public static Map map01 = new HashMap();


	public Constant() {
		
		this.map01.put("0","39.96738512724442,116.3943099975586");
		this.map01.put("1","39.96738512724442,116.3943099975586");
		this.map01.put("2","39.96738512724442,116.3943099975586");
		this.map01.put("3","39.96738512724442,116.3943099975586");
		this.map01.put("4","39.96738512724442,116.3943099975586");
		this.map01.put("5","39.96738512724442,116.3943099975586");
		this.map01.put("6","39.96738512724442,116.3943099975586");
		this.map01.put("7","39.96738512724442,116.3943099975586");
		this.map01.put("8","39.96738512724442,116.3943099975586");
		this.map01.put("9","39.96738512724442,116.3943099975586");
		
	}


	public static Map getMap01() {
		return map01;
	}


	public static void setMap01(Map map01) {
		Constant.map01 = map01;
	}

}
