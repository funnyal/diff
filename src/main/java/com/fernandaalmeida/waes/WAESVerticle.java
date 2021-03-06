package com.fernandaalmeida.waes;

import java.util.HashMap;
import java.util.Map;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class WAESVerticle extends AbstractVerticle {
	
	private Map<Integer,Diff> jsons = new HashMap<>();

	 @Override
	  public void start(Future<Void> fut) {
		 
		 Router router = Router.router(vertx);
		 router.route().handler(BodyHandler.create());
		
		 router.post("/:apiversion/diff/:id/left").handler(this::handleLeft);
		 router.post("/:apiversion/diff/:id/right").handler(this::handleLeft);
		 router.get("/:apiversion/diff/:id").handler(this::handleLeft);

		    vertx.createHttpServer().requestHandler(router::accept).listen(8080, result -> {
	          if (result.succeeded()) {
	            fut.complete();
	          } else {
	            fut.fail(result.cause());
	          }
	        });
		    
	  }
	 
	 private void handleLeft(RoutingContext rc){
		 Buffer buffer = rc.getBody();
		 JsonObject requestBufferJson = new JsonObject(buffer.toString());
		 Integer id = new Integer(rc.request().getParam("id"));
		 
		 Diff diffObj = new Diff();
		 diffObj.setLeft(requestBufferJson);
		 
		 jsons.put(id, diffObj);
		
	      rc.response()
	          .putHeader("content-type", "application/json")
	          .putHeader("X-Version","v1")
	          .end(new JsonObject().put("server", "OK").encode());
	 }
	 
	 private void handleRight(RoutingContext rc){
		 Buffer buffer = rc.getBody();
		 JsonObject requestBufferJson = new JsonObject(buffer.toString());
		 Integer id = new Integer(rc.request().getParam("id"));
		 
		 Diff diffObj = new Diff();
		 diffObj.setRight(requestBufferJson);
		 
		 jsons.put(id, diffObj);
		
	      rc.response()
	          .putHeader("content-type", "application/json")
	          .putHeader("X-Version","v1")
	          .end(new JsonObject().put("greeting", "Hello WorldRight!").encode());
	 }
	 
	 private void returnDiff(RoutingContext rc){

		 Integer id = new Integer(rc.request().getParam("id"));
		 
		 Diff diffObj = jsons.get(id);
	 }
}
