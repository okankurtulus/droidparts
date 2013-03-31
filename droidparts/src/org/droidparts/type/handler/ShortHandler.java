/**
 * Copyright 2013 Alex Yanchenko
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.droidparts.type.handler;

import static org.droidparts.util.Arrays2.toPrimitive;

import java.util.ArrayList;

import org.droidparts.type.TypeHelper;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;

public class ShortHandler extends AbstractTypeHandler<Short> {

	@Override
	public boolean canHandle(Class<?> cls) {
		return TypeHelper.isShort(cls);
	}

	@Override
	public String getDBColumnType() {
		return INTEGER;
	}

	@Override
	public <V> Short readFromJSON(Class<Short> valType,
			Class<V> arrCollItemType, JSONObject obj, String key)
			throws JSONException {
		return parseFromString(valType, arrCollItemType, obj.getString(key));
	}

	@Override
	protected <V> Short parseFromString(Class<Short> valType,
			Class<V> arrCollItemType, String str) {
		return Short.valueOf(str);
	}

	@Override
	public <V> void putToContentValues(Class<Short> valueType,
			Class<V> arrCollItemType, ContentValues cv, String key, Short val) {
		cv.put(key, val);
	}

	@Override
	public <V> Short readFromCursor(Class<Short> valType,
			Class<V> arrCollItemType, Cursor cursor, int columnIndex) {
		return cursor.getShort(columnIndex);
	}

	@Override
	public Object parseTypeArr(Class<Short> valType, String[] arr) {
		ArrayList<Short> list = parseTypeColl(valType, arr);
		Short[] tArr = list.toArray(new Short[list.size()]);
		return (valType == short.class) ? toPrimitive(tArr) : tArr;
	}

}