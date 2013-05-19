package com.fcl.harshrealm.armyeditor.model;
//package com.example.harshrealmarmyeditor.model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import com.example.harshrealmarmyeditor.controller.SettingsManager;
//
///**
// * 
// * @author cyrex562
// *
// */
//public class DBHelper extends SQLiteOpenHelper {
//	private static final String DB_NAME = "harsh_realm_db.db";
//	private static final int DB_VERS = 1;
//	
//	
//	public DBHelper(Context context) {
//		super(context, DB_NAME, null, DB_VERS);
//	}
//
//	@Override
//	public void onCreate(SQLiteDatabase db) {
//		// module_types
//		db.execSQL((new ModuleTypesTableDef()).create);
//		// modules
////		db.execSQL(ModulesDBTableDef.createModuleTable);
//		// attribute_operators
//
//		// attribute_types
//		
//		// attributes
//		
//		// attributes_list
//		
//		// sub_modules_list
//		
//		// descriptors
//		
//		// descriptor_list
//		
//		// rule_types
//		
//		// rules
//		
//		// rules_list
//		SettingsManager.getInstance().setDBInitialized(true);
//	}
//
//	@Override
//	public void onUpgrade(SQLiteDatabase db, int oldVersion, 
//		int newVersion) {
//		String tag = getClass().getSimpleName() + ".onUpgrade";
//		Log.w(tag, String.format("upgrading database from v %d to v %d " +
//			"which will destroy all data", oldVersion, newVersion));
//		// TODO: add drop table statements
//	}
//	
//	public enum DBTypes {
//		TEXT_NOT_NULL("text not null"),
//		TEXT("text"),
//		INT("integer"),
//		INT_NOT_NULL("integer not null"),
//		INT_PK_AI("integer primary key autoincrement"),
//		TEXT_PK("text primary key");
//
//		public String type;
//		
//		DBTypes(String inType) {
//			type = inType;
//		}
//	}
//	
//	public enum DBStatements {
//		DROP_TABLE("drop table if exists");
//		public String statement;
//		
//		DBStatements(String inStatement) {
//			statement = inStatement;
//		}
//	}
//	
//	public class DBColumn {
//		public String name;
//		public DBTypes type;
//		public String constraint;
//		public DBColumn(String inName, DBTypes inType) {
//			name = inName;
//			type = inType;
//			constraint = "";
//		}
//		
//		public DBColumn(String inName, DBTypes inType, String foreignTable, 
//			String foreignCol) {
//			name = inName;
//			type = inType;
//			constraint = String.format("foreign key(%s) references %s(%s)",
//				name, foreignTable, foreignCol);
//		}
//	}
//	
//	/**
//	 * 
//	 * @author cyrex562
//	 *
//	 */
//	public static final class DBTableUtils {
//		/**
//		 * 
//		 * @param tableName
//		 * @param columns
//		 * @return
//		 */
//		public static final String makeCreateTableStatement(String tableName, 
//			List<DBColumn> columns) {
//			StringBuilder statement = new StringBuilder();
//			// add create statement
//			statement.append(String.format("create table %s(", tableName));
//			// add columns
//			for (int i = 0; i < columns.size(); i++) {
//				DBColumn col = columns.get(i);
//				statement.append(String.format("%s %s", col.name, col.type));
//				if (i < columns.size() - 1) {
//					statement.append(", ");
//				}
//			}
//			// add constraints
//			for (int i = 0; i < columns.size(); i++) {
//				DBColumn col = columns.get(i);
//				if (!col.constraint.isEmpty()) {
//					statement.append(String.format("%s", col.constraint));
//				}
//				if (i < columns.size() - 1) {
//					statement.append(", ");
//				}
//			}
//			//  add end of statement
//			statement.append(");");
//			
//			return statement.toString();
//		}
//		
//		/**
//		 * 
//		 * @return
//		 */
//		public static final String makeDropTableStatement(String tableName) {
//			return String.format("%s %s", DBStatements.DROP_TABLE, tableName);
//		}
//	}
//	
//	// Abstract DBTableDef
//	public abstract class DBTableDef {
//		public List<DBColumn> columns = new ArrayList<DBColumn>();
//		public String tableName = "";
//		public String create = "";
//		public String drop = "";
//	}
//	
//	// Module_Type
//	public class ModuleTypesTableDef extends DBTableDef {
//		public ModuleTypesTableDef() {
//			super();
//			// table name
//			super.tableName = "module_types";
//			// module_type
//			super.columns.add(new DBColumn("module_type", DBTypes.TEXT_PK));
//			// create table statement
//			super.create = DBTableUtils.makeCreateTableStatement(super.tableName, 
//				super.columns);
//			// drop table statement
//			super.drop = DBTableUtils.makeDropTableStatement(super.tableName);
//		}
//	}
//	
//	// Modules
//	public static final class ModulesTableDef extends DBTableDef {
//		public ModulesTableDef() {
//			// table name
//			super.tableName = "modules";
//			// module_id
//			super.columns.add(new DBColumn("module_id", DBTypes.INT_PK_AI));
//			// module_name
//			super.columns.add(new DBColumn("module_name", DBTypes.TEXT_NOT_NULL));
//			// parent_id
//			super.columns.add(new DBColumn("parent_id", DBTypes.INT, "modules", "module_id"));
//			// module_type
//			super.columns.add(new DBColumn("module_type", DBTypes.TEXT_NOT_NULL, "module_types", "module_typexxm"))
//			public static final String moduleTypeName = "module_type";
//			public static final String moduleTypeType = TEXT_TYPE_NOT_NULL;
//			public static final String moduleTypeConstraint = 
//				"foreign key(module_type) references module_types(module_type)";
//			// module_description
//			public static final String moduleDescriptionName = "module_description";
//			public static final String moduleDescriptionType = TEXT_TYPE;
//			// create statement
//			public static final String createModuleTable = String.format(
//				"create table %s(%s %s, %s %s, %s %s, %s %s, %s %s, %s, %s);",
//				tableName, moduleIDName, moduleIDType, moduleNameName, 
//				moduleNameType, parentIDName, parentIDType, moduleTypeName, 
//				moduleTypeType, moduleDescriptionName, moduleDescriptionType, 
//				parentIDConstraint, moduleTypeConstraint);
//			// drop table statement
//			public static final String dropModuleTable = 
//				String.format("%s %s", DROP_TABLE_STMT, tableName);
//		}
//	}
//	
//	// attribute_operators
//	public static final class AttributeOperatorsTableDef {
//		// table name
//		public static final String tableName = "attribute_operators";
//		// attribute_operator
//		public static final String attributeOperatorName = "attribute_operator";
//		public static final String attributeOperatorType = TEXT_TYPE_NOT_NULL;
//		// create statement
//		public static final String createTable = String .format(
//			"create table %s(%s %s);", tableName, attributeOperatorName, 
//			attributeOperatorType);
//		// drop statement
//		public static final String dropTable = String.format("%s %s",
//			DROP_TABLE_STMT, tableName);
//	}
//	
//	
//	}
//	// attribute_types
//	
//	// attributes
//	
//	// attributes_list
//	
//	// sub_modules_list
//	
//	// descriptors
//	
//	// descriptor_list
//	
//	// rule_types
//	
//	// rules
//	
//	// rules_list
//}