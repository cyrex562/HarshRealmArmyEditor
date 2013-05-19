package com.fcl.harshrealm.armyeditor.services;


import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.IBinder;

public class HRAE_Service extends android.app.Service {
	private final IBinder binder = new DataModelSvcBinder();
//	private DBHelper dbHelper;
	private SQLiteDatabase db;

	public class DataModelSvcBinder extends Binder {
		public HRAE_Service getService() {
			return HRAE_Service.this;
		}
	}
	
	@Override 
	public int onStartCommand(Intent intent, int flags, int startId) {
//		dbHelper = new DBHelper(this);
		openDB();
		return Service.START_NOT_STICKY;
	}
	
	public void openDB() {
//		db = dbHelper.getWritableDatabase();
	}
	
	public void close() {
//		dbHelper.close();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * TODO.
	 */
	@Override
	public void onCreate() {
		// TODO: add service start stuff here
	}
	
	/**
	 * TODO.
	 */
	@Override
	public void onDestroy() {
		close();
	}
}
