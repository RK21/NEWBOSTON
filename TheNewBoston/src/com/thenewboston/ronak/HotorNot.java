package com.thenewboston.ronak;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;
import android.widget.Toast;

public class HotorNot {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAMES = "person_names";
	public static final String KEY_RATES = "people_rates";
	
	private static final String DATABASE_NAME = "HotOrNotdb";
	private static final String DATABASE_TABLE = "DataBaseTable";
	private static final int DATABASE_VERSION = 1;
	
	private  DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase arg0) {
			// TODO Auto-generated method stub
			arg0.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + 
			              KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
					      KEY_NAMES + " TEXT NOT NULL, " + 
			              KEY_RATES + " TEXT NOT NULL);"
					);
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			arg0.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(arg0);
		}
		
		}
		
	public HotorNot(Context c){
		
		ourContext = c;
		
	} 
	
	public HotorNot open () throws SQLException{
		
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
		
	}
	public void close(){
		
		ourHelper.close();
		
	}

	public long createEntry(String name, String rate) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAMES, name);
		cv.put(KEY_RATES, rate);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID,KEY_NAMES,KEY_RATES};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAMES);
		int iRates = c.getColumnIndex(KEY_RATES);
		
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			
			result = result + c.getString(iRow) + " " + c.getString(iName) + "      " + c.getString(iRates) + "\n";
			
		}
		
		return result;
	}

	public String getName(long l)throws SQLException {
		// TODO Auto-generated method stub
		
		String[] columns = new String[]{KEY_ROWID,KEY_NAMES,KEY_RATES};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
		if(c!= null){
			c.moveToFirst();
			String name  = c.getString(1);
			return name;
			
		}
		return null;
	}

	public String getRates(long l) throws SQLException{
		// TODO Auto-generated method stub
	
		String[] columns = new String[]{KEY_ROWID,KEY_NAMES,KEY_RATES};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
		if(c!= null){
			c.moveToFirst();
			String rates  = c.getString(2);
			return rates;
			
		}
		return null;
	}

	public void modify(long lMod, String nameMod, String rateMod) throws SQLException{
		// TODO Auto-generated method stub
		
		ContentValues cvUpdated = new ContentValues();
		cvUpdated.put(KEY_NAMES, nameMod);
		cvUpdated.put(KEY_RATES, rateMod);
		ourDatabase.update(DATABASE_TABLE, cvUpdated, KEY_ROWID + "=" + lMod, null);
	}

	public void deleteEntry(long lDelete)throws SQLException {
		// TODO Auto-generated method stub
		
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lDelete, null);
	}
}
	
	

