package jp.radiocat.example.ormExample.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import jp.radiocat.example.ormExample.entity.Word;

import java.lang.Override;import java.lang.String;import java.sql.SQLException;

/**
 * jp.radiocat.example.ormExample.ormExample.
 * User: radiocat
 * Date: 2013/11/25
 * Time: 0:54
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	//	private static final String DATABASE_NAME = Environment.getExternalStorageDirectory() + "/ormExample.db";
	private static final String DATABASE_NAME = "ormExample.db";
	private static final int DATABASE_VERSION = 1;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Word.class);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "データベースを作成できませんでした", e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
		//To change body of implemented methods use File | Settings | File Templates.
	}
}
