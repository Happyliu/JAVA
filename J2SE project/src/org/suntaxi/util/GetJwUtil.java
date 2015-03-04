package org.suntaxi.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

public class GetJwUtil {

	//speed£¬k£ºtotal distance£¬sT£ºstarted time
	public static int findJw(double speed,double k,Date sT){
		double tT = k/speed;//
		double rT = DateUtil.xj(new Date(),sT)/60;//
		System.out.println("-->"+tT+":"+rT);
		BigDecimal p = new BigDecimal((rT/tT)*10).setScale(0, BigDecimal.ROUND_HALF_UP);
		return Integer.parseInt(p.toString());
	}
	
	public static void main(String[] args) throws ParseException {
		System.out.println(findJw(10,20.00,DateUtil.parseDatetime("2013-03-17 16:15:00")));
	}
}
