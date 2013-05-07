package com.example.harshrealmarmyeditor.controller;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsManager {

	private static final SettingsManager instance = new SettingsManager();

	private static final String FILE_NAME = ".settings";

	private static final int MODE = 0;
	
	private SharedPreferences settings;
	
	private boolean isInitialized = false;
	
	private SettingsManager() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isInitialized() {
		return isInitialized;
	}
	
	public static SettingsManager getInstance() {
		return instance;
	}
	
	public void setup(Context context) {
		settings = context.getSharedPreferences(FILE_NAME, MODE);
		isInitialized = true;
	}
	
	public void teardown() {
		saveSettings();
		isInitialized = false;
	}
	
	public void saveSettings() {
		if (isInitialized) {
			SharedPreferences.Editor editor = settings.edit();
			editor.commit();
		}
	}
	
	// is the db initialized?
	public boolean getDBInitialized() {
		return settings.getBoolean("db_initialized", false);
	}
	
	public void setDBInitialized(boolean isInitialized) {
		SharedPreferences.Editor editor  = settings.edit();
		editor.putBoolean("db_initialized", isInitialized);
		editor.commit();
	}
	
	
	
	

}
