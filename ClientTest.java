package com.example.Java.Cache;

import java.util.Random;

/**
 * Clasa de test pentru cache-ul web.
 *
 */
public class ClientTest {

	// cateva adrese web predefinite
	private static final String urls[] = new String[] {
		"http://google.com", "http://www.yahoo.com", "http://www.pub.ro", "http://www.acs.pub.ro", "http://www.acasa.ro"
	};
	
	/**
	 * Metoda auxiliara ce converteste dimensiunea exprimata in diverse unitati de masura in valoarea corespunzatoare in B
	 * @param size 
	 * @return
	 */
	private static long convertSize(String size) {
		StringBuffer buf = new StringBuffer();
		int poz = 0;
		while (true) {
			char c = size.charAt(poz);
			if (!Character.isDigit(c)) break;
			buf.append(c);
			poz++;
			if (poz >= size.length()) break;
		}
		long nr = Long.parseLong(buf.toString());
		if (poz >= size.length()) return nr;
		String unit = size.substring(poz);
		if (unit.equals("B")) return nr;
		if (unit.equals("KB")) return nr * 1024l;
		if (unit.equals("MB")) return nr * 1024l * 1024l;
		if (unit.equals("GB")) return nr * 1024l * 1024l * 1024l;
		return nr;
	}
	
	/**
	 * Metoda ce converteste timpul exprimat in diverse unitati de masura in valoarea corespunzatoare exprimata in ms
	 * @param time
	 * @return
	 */
	private static long convertTime(String time) {
		StringBuffer buf = new StringBuffer();
		int poz = 0;
		while (true) {
			char c = time.charAt(poz);
			if (!Character.isDigit(c)) break;
			buf.append(c);
			poz++;
			if (poz >= time.length()) break;
		}
		long nr = Long.parseLong(buf.toString());
		if (poz >= time.length()) return nr;
		String unit = time.substring(poz);
		if (unit.equals("s")) return nr * 1000l;
		if (unit.equals("m")) return nr * 60l * 1000l;
		if (unit.equals("h")) return nr * 60l * 60l * 1000l;
		return nr;
		
	}
	
	/**
	 * Functia main.
	 * @param args
	 */
	public static void main(String args[]) {
		
		if (args.length < 2) {
			System.err.println("Usage: client size time");
			System.exit(-1);
		}
		long size = convertSize(args[0]);
		long time = convertTime(args[1]);
		System.out.println("Client started with maxStorageSpace="+size+" and maxTimeToKeep="+time);
		WebCache cache = new WebCache(size, time);
		Random r = new Random(System.currentTimeMillis());
		while (true) { // incercam cateva citiri aleatoare de adrese folosind cache-ul web
			int poz = r.nextInt(urls.length);
			String page = cache.requestURL(urls[poz]);
			System.out.println(page);
			try { Thread.sleep(1000); } catch (Throwable t) { }
		}
	}
	
} // end of class ClientTest


