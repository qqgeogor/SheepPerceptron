package com.qqgeogor.test;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.commons.collections.bag.SynchronizedBag;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;


public class Test {
	public static void main(String[] args) throws UnknownHostException, MongoException{
		
		
		
		
		//File f = new File("D:\\My Documents\\Recommendation\\ml-100k\\u1.base");
		File f  = new File("D:\\mymodel.dat");
		DataModel model = null;
		try {
			model = new FileDataModel(f);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ItemSimilarity sim = null;
		
			sim = new TanimotoCoefficientSimilarity(model);
		
		
		Recommender r=null;
		r = new GenericBooleanPrefItemBasedRecommender(model,sim);
		try {
			System.out.println(r.recommend(1L, 5));
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
