package com.example.harshrealmarmyeditor.model;


import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Binder;
import android.os.IBinder;

/**
 * TODO.
 * @author cyrex562
 *
 */
public class DataModelService extends android.app.Service {
	private final IBinder binder = new DataModelSvcBinder();
	private DBHelper dbHelper;
	private SQLiteDatabase db;
	
	/**
	 * TODO.
	 * @author cyrex562
	 */
	public class DataModelSvcBinder extends Binder {
		public DataModelService getService() {
			return DataModelService.this;
		}
	}
	
	/**
	 * TODO.
	 * Called when the client explicitly starts the service.
	 * @param intent TODO.
	 * @param flags TODO.
	 * @param startId TODO.
	 * @return TODO.
	 */
	@Override 
	public int onStartCommand(Intent intent, int flags, int startId) {
		dbHelper = new DBHelper(this);
		openDB();
		return Service.START_NOT_STICKY;
	}
	
	public void openDB() {
		db = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	/**
	 * TODO.
	 * @param intent TODO.
	 */
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
