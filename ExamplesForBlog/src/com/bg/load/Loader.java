package com.bg.load;

import java.io.File;
import java.net.URISyntaxException;

public class Loader {

	public File load(String url){
		
		try {
			return new File(getClass().getResource(url).toURI());
		} catch (URISyntaxException e) {
			return null;
		}
		
	}
	
}
