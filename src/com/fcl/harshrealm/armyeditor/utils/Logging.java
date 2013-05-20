package com.fcl.harshrealm.armyeditor.utils;

import android.util.Log;

public final class Logging {
	public static void logException(String tag, Exception e) {
		Log.e(tag, String.format("exception occurred: %s", e));
		
	}
}
