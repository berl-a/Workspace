package com.bg.mySingleton;

public class Settings {
	
	private Settings(){}
	
	private static class SettingsHolder{
		
		private SettingsHolder(){}
		
		public static Settings newSettings = new Settings();
	}
	
	public static Settings getSettings(){
		return SettingsHolder.newSettings;
	}
	
	public static String name = "GAME!!!";
	
	

}
