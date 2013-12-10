package jp.radiocat.example.ormExample.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import jp.radiocat.example.ormExample.R;
import jp.radiocat.example.ormExample.entity.Word;

/**
 * jp.radiocat.example.ormExample.activity.
 * User: radiocat
 * Date: 2013/12/08
 * Time: 17:55
 */
public class WordAdapter extends ArrayAdapter<Word> {

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
		Word word = super.getItem(position);

		TextView txtWord = (TextView) view.findViewById(R.id.txtWord);
		txtWord.setText(word.getValue());
		return view;
	}

	@Override
	public void add(Word obj) {
		super.add(obj);
	}

	@Override
	public void remove(Word word) {
		this.remove(word.getId());
	}

	/**
	 * 指定したidの要素を削除する
	 *
	 * @param id id
	 */
	public void remove(int id) {
		for (int i = 0 ; i < super.getCount(); i++) {
			Word word = getItem(i);
			if (id == word.getId()) {
				super.remove(word);
				return ;
			}
		}
	}
}