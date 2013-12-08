package jp.radiocat.example.ormExample.model;

import android.content.Context;
import android.util.Log;
import com.j256.ormlite.dao.Dao;
import jp.radiocat.example.ormExample.db.DatabaseHelper;
import jp.radiocat.example.ormExample.entity.Word;

import java.util.List;

/**
 * jp.radiocat.example.ormExample.ormExample.model.
 * User: radiocat
 * Date: 2013/11/25
 * Time: 1:19
 */
public class WordModel {

	private static final String TAG = WordModel.class.getSimpleName();
	private Context context;

	public WordModel(Context context) {
		this.context = context;
	}

	/**
	 * insert or updateする
	 * @param word 対象のエンティティ
	 */
	public void save(Word word) {
		DatabaseHelper helper = new DatabaseHelper(context);
		try {
			Dao<Word, Integer> dao = helper.getDao(Word.class);
			dao.createOrUpdate(word);
		} catch (Exception e) {
			Log.e(TAG, "例外が発生しました", e);
		} finally {
			helper.close();
		}
	}

	/**
	 * deleteする
	 * @param word 対象のエンティティ
	 */
	public void delete(Word word){
		DatabaseHelper helper = new DatabaseHelper(context);
		try {
			Dao<Word, Integer> dao = helper.getDao(Word.class);
			dao.delete(word);
		} catch (Exception e) {
			Log.e(TAG, "例外が発生しました", e);
		} finally {
			helper.close();
		}
	}

	public List<Word> findAll() {
		DatabaseHelper helper = new DatabaseHelper(context);
		try {
			Dao<Word, Integer> dao = helper.getDao(Word.class);
			return dao.queryForAll();
		} catch (Exception e) {
			Log.e(TAG, "例外が発生しました", e);
			return null;
		} finally {
			helper.close();
		}
	}
}
