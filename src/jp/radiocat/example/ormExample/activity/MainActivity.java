package jp.radiocat.example.ormExample.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import jp.radiocat.example.ormExample.R;
import jp.radiocat.example.ormExample.entity.Word;
import jp.radiocat.example.ormExample.model.WordModel;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();

//	private ListView listView;
//	private Button btnSubmit;

	ArrayAdapter<Word> adapter;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		adapter = new WordAdapter(MainActivity.this, R.layout.row);

		// 登録されているWordを取得する
		WordModel model = new WordModel(this);
		for (Word word : model.findAll()) {
			Log.d(TAG, String.format("id=%s,value=%s", word.getId(), word.getValue()));
			adapter.add(word);
		}

		// wordテーブルに保存されている内容をListViewで表示する
		ListView listView = (ListView) findViewById(R.id.list);
		listView.setAdapter(adapter);
		// ListViewの要素をクリックすると編集用のダイアログを表示する
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Word item = (Word) parent.getItemAtPosition(position);
				Log.d(TAG, String.format("Normal selected position=%s,id=%s,value=%s",
						position, item.getId(), item.getValue()));
				showUpdateDialog(item);
			}
		});
		//長押しした場合は削除用の確認ダイアログを表示する
		listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				Word item = (Word) parent.getItemAtPosition(position);
				Log.d(TAG, String.format("Long selected position=%s,id=%s,value=%s",
						position, item.getId(), item.getValue()));
				showDeleteDialog(item);
				return false;
			}
		});

		// 入力内容をwordテーブルに保存する
		Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
		btnSubmit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText txtInput = (EditText) findViewById(R.id.value);
				String value = txtInput.getText().toString();
				if (value.isEmpty()) {
					// 未入力のときは登録しないで処理を終わる
					return;
				}
				Word word = new Word(value);
				WordModel model = new WordModel(getApplicationContext());
				model.save(word);
				txtInput.setText("");

				adapter.add(word);
				adapter.notifyDataSetChanged();
			}
		});
	}

	/**
	 * wordの編集ダイアログ
	 *
	 * @param word 更新対象のエンティティ
	 */
	private void showUpdateDialog(final Word word) {
		// テキスト入力するViewを作成してwordの値を初期値に設定する
		final EditText editText = new EditText(MainActivity.this);
		editText.setText(word.getValue());
		// AlertDialogにEditTextをセットする
		new AlertDialog.Builder(MainActivity.this)
				.setIcon(android.R.drawable.ic_dialog_info)
				.setTitle("編集ダイアログ")
				.setMessage("内容を変更してOKを押してください")
				.setView(editText)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						word.setValue(editText.getText().toString());
						WordModel model = new WordModel(getApplicationContext());
						model.save(word);
						Log.d(TAG, String.format("Updated id=%s,value=%s",
								word.getId(), word.getValue()));
						adapter.notifyDataSetChanged();
					}
				})
				.setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				})
				.show();
	}

	/**
	 * wordの削除確認ダイアログ
	 * OKの場合はwordテーブルから削除します
	 *
	 * @param word 削除対象のエンティティ
	 */
	private void showDeleteDialog(final Word word) {
		// AlertDialogにEditTextをセットする
		new AlertDialog.Builder(MainActivity.this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("削除ダイアログ")
				.setMessage("削除していいですか？")
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						WordModel model = new WordModel(getApplicationContext());
						model.delete(word);
						Log.d(TAG, String.format("Deleted id=%s,value=%s",
								word.getId(), word.getValue()));
						// adapterからも削除してリストをリフレッシュする
						adapter.remove(word);
						adapter.notifyDataSetChanged();
					}
				})
				.setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				})
				.show();
	}

}
