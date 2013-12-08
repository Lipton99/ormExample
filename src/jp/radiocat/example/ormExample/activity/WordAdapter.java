package jp.radiocat.example.ormExample.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import jp.radiocat.example.ormExample.R;
import jp.radiocat.example.ormExample.entity.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * jp.radiocat.example.ormExample.activity.
 * User: radiocat
 * Date: 2013/12/08
 * Time: 17:55
 */
public class WordAdapter extends ArrayAdapter<Word> {

	private List<Word> wordList = new ArrayList<Word>();

	private LayoutInflater inflater;

	private int layoutId;

	/**
	 * コンストラクタ
	 * @param context Context
	 * @param textViewResourceId TextViewResourceId
	 */
	public WordAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.layoutId = textViewResourceId;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		if (convertView == null) {
			view = this.inflater.inflate(this.layoutId, null);
		}
		Word word = this.wordList.get(position);

		TextView txtWord = (TextView) view.findViewById(R.id.txtWord);
		txtWord.setText(word.getValue());
		return view;
	}

	@Override
	public void add(Word obj) {
		super.add(obj);
		this.wordList.add(obj);
	}
}