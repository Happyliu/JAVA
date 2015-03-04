package org.suntaxi.util;

public enum EnumX {
	//AMT:Activity Mark Type
	
	MAP01("1-0","39.962928,116.361061"),
	MAP02("1-1","39.968779,116.370133"),
	MAP03("1-2","39.990372,116.378024"),
	MAP04("1-3","40.029323,116.349206"),
	MAP05("1-4","40.080863,116.309896"),
	MAP06("1-5","40.091747,116.301355"),
	MAP07("1-6","40.097525,116.312556"),
	MAP08("1-7","40.098346,116.329443"),
	MAP09("1-8","40.099626,116.362928"),
	MAP10("1-9","40.104402,116.373147"),
	
	
	MAP11("2-0","39.912896,116.391478"),
	MAP12("2-1","39.907992,116.391778"),
	MAP13("2-2","39.901802,116.391692"),
	MAP14("2-3","39.900058,116.384096"),
	MAP15("2-4","39.899926,116.377144"),
	MAP16("2-5","39.89828,116.374397"),
	MAP17("2-6","39.889423,116.374397"),
	MAP18("2-7","39.886459,116.374397"),
	MAP19("2-8","39.881602,116.374269"),
	MAP20("2-9","39.878572,116.37399");
	
	
	private String input;
	private String out;

	
	private EnumX(String input,String out) {
		this.input = input;
		this.out = out;
	}
	
	
	public static String getOut(String input) {
		String rv = "";
		for (EnumX c : EnumX.values()) {
			String gvalue = c.getInput();
			if (null != gvalue && gvalue.equals(input.trim())) {
				rv = c.out;
				break;
			}
		}
		return rv;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public void setOut(String out) {
		this.out = out;
	}

}