package com.qqgeogor.test;

import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class TestSaveDBOject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mongo mg=null;
		try {
			mg = new Mongo();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB db =(mg.getDB("test"));
		DBCollection dbc = db.getCollection("testSave");
		String c="{\"f\":{\"a\":\"b\"}}";
		JSONObject obj = null;
		obj = new JSONObject();
		obj = JSONObject.fromObject(c);
		System.out.println(obj);
		//System.out.println(obj);
		DBObject dbo = new BasicDBObject();
		
		dbo =(DBObject) JSONObject.toBean(obj, BasicDBObject.class);
		System.out.println(dbo.get("f"));
		
		//System.out.println(	dbo.get("a"));
		dbc.insert(dbo);
		
	}

}
