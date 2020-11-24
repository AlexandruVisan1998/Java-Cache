package com.example.Java.Cache;

import java.io.Serializable;

/**
 * O intrare ce este stocata in cache-ul de pe disc
 *
 */
public class WebCacheEntry implements Serializable {
	static final long serialVersionUID = 7471069187532416589L;
	
	// url-ul de unde a fost citita intrarea
	private final String url;
	// continutul paginii de web citita de la adresa data prin url
	private String content;
	// cand a fost facuta ultima actualizare a continutului acestei intrari din cache
	private long writeDate = -1;
	// cand a fost accesata ultima data aceasta intrare
	private long readDate = -1;
	
	/**
	 * Constructorul clasei.
	 * @param url Adresa corespunzatoare intrarii in cache
	 */
	public WebCacheEntry(String url) {
		this.url = url;
	}
	
	/**
	 * Metoda ce actualizeaza continutul intrarii din cache
	 * @param content Noul continut al intrarii cache
	 */
	public void setContent(String content) {
		this.content = content;
		// automat setam si data ultimei accesari si pe cea a ultimei actualizari
		writeDate = readDate = System.currentTimeMillis();
	}
	
	/**
	 * Metoda intoarce data ultimei accesari a intrarii din cache.
	 */
	public long getLastRead() {
		return readDate;
	}
	
	/**
	 * Metoda intoarce data ultimei actualizari a intrarii din cache.
	 * @return
	 */
	public long getLastWrite() {
		return writeDate;
	}
	
	/**
	 * Metoda prin care se realizeaza accesarea continutului intrarii din cache.
	 */
	public String getContent() {
		// se actualizeaza si data ultimei accesari
		readDate = System.currentTimeMillis();
		return content;
	}
	
	/**
	 * Intoarce adresa corespunzatoare intrarii din cache.
	 */
	public String getURL() {
		return url;
	}
	
} // end of class WebCacheEntry


