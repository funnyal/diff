package com.fernandaalmeida.waes;

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

}
