package org.suntaxi.util;

import java.util.Random;

public class BestUtil {

	
	
	public int getRandom() {
		int num = 1;
		int n = 11;
		Random rand = new Random();

		for (int i = 1; i < n; i++) {
			num = rand.nextInt(n);
		}
		return num;
	}

}
