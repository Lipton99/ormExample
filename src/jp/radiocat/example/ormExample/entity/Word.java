package jp.radiocat.example.ormExample.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * jp.radiocat.example.ormExample.entity.
 * User: radiocat
 * Date: 2013/12/04
 * Time: 1:16
 */
@DatabaseTable(tableName = "word")
public class Word {

	@DatabaseField(generatedId = true)
	private Integer id;
	@DatabaseField
	private String value;

	public Word() {
	}

	public Word(String value) {
		this.value = value;
	}

	public Integer getId() {
		return this.id;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
