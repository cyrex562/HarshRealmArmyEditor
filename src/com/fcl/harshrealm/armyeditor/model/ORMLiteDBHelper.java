package com.fcl.harshrealm.armyeditor.model;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fcl.harshrealm.armyeditor.utils.Logging;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class ORMLiteDBHelper extends OrmLiteSqliteOpenHelper {
	private static final String DB_NAME = "hrae.db";
	private static final int DB_VERSION = 1;
	private Dao<Component, Integer> componentDAO = null;
	private RuntimeExceptionDao<Component, Integer> componentRuntimeDAO = null;
	
	public ORMLiteDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase database,
		ConnectionSource connectionSource) {
		String tag = "ORMLiteDBHelper.onCreate";
		try {
			Log.i(tag, "onCreate");
			TableUtils.createTable(connectionSource, Component.class);
		} catch  (Exception e) {
			Logging.logException(tag, e);
			throw new RuntimeException(e);
		}
	}


	@Override
	public void onUpgrade(SQLiteDatabase database,
		ConnectionSource connectionSource, int oldVersion, int newVersion) {
		String tag = "ORMLiteDBHelper.onUpgrade";
		try {
			TableUtils.dropTable(connectionSource, Component.class, true);
			onCreate(database, connectionSource);
		} catch (SQLException e) {
			Logging.logException(tag, e);
		}
		
	}
	
	public Dao<Component, Integer> getDAO() throws SQLException {
		if (null == componentDAO) {
			componentDAO = getDao(Component.class);
		}
		return componentDAO;
	}

	public RuntimeExceptionDao<Component, Integer> getComponentDAO() {
		if (null == componentRuntimeDAO) {
			componentRuntimeDAO = getRuntimeExceptionDao(Component.class);
		}
		return componentRuntimeDAO;
	}
	
	@Override
	public void close() {
		super.close();
		componentRuntimeDAO = null;
	}
}
