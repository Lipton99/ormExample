package jp.radiocat.example.ormExample.activity;

import android.app.Activity;
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
		for(Word word : model.findAll()) {
			Log.d(TAG, String.format("id=%s,value=%s", word.getId(), word.getValue()));
			adapter.add(word);
		}

		ListView listView = (ListView) findViewById(R.id.list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Word item = (Word) parent.getItemAtPosition(position);
				Log.d(TAG, String.format("selected position=%s,id=%s,value=%s",
						position, item.getId(), item.getValue()));
			}
		});

		Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
		btnSubmit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText txtInput = (EditText) findViewById(R.id.value);
				Word word = new Word(txtInput.getText().toString());
				WordModel model = new WordModel(getApplicationContext());
				model.save(word);
				txtInput.setText("");

				adapter.add(word);
				adapter.notifyDataSetChanged();
			}
		});
	}
}
