package com.fernandaalmeida.waes;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import io.vertx.core.json.JsonObject;

public class Diff {

	private JsonObject left;
	private JsonObject right;

	public JsonObject getLeft() {
		return left;
	}

	public void setLeft(JsonObject left) {
		this.left = left;
	}

	public JsonObject getRight() {
		return right;
	}

	public void setRight(JsonObject right) {
		this.right = right;
	}

	public String checkDiff() {
		// for( left.)
		return null;
	}

	public String varre(JSONObject json, JSONObject json2) {

		String finalRes = "";
		try {
			String left = "";
			String right = "";
			Iterator<String> temp = json.keys();
			Iterator<String> temp2 = json2.keys();
			if (json.similar(json2)) {
				System.out.println("similar");
				return "similar";
			} else {
				while (temp.hasNext()) {
					String key = temp.next();
					Object value = json.get(key);
					if (json2.has(key)) {
						// String value2 = json2.getString(key); instafeof array
						String value2 = json2.getString(key);
						if (!value.equals(value2)) {
							left += key + "=" + value2;
						}
					} else {
						left += key + "=" + value;
					}

				}
			}
			left = "on left : " + left;
			while (temp2.hasNext()) {
				String key2 = temp2.next();
				Object value2 = json2.get(key2);
				if (!finalRes.contains(key2)) {
					if (json.has(key2)) {
						// String value2 = json2.getString(key); instafeof array
						String value1 = json.getString(key2);
						if (!value2.equals(value1)) {
							right += key2 + "=" + value1;
						}
					} else {
						right += key2 + "=" + value2;
					}
				}

			}
			right = "on right : " + right;
			System.out.println(left + right);
			finalRes = left + right;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return finalRes;
	}

}
