package com.qqgeogor.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.model.PlusAnonymousUserDataModel;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.RandomRecommender;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

public class AnonymousRecommenderTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws TasteException 
	 */
	public static void main(String[] args) throws IOException, TasteException {
		// TODO Auto-generated method stub
		Long t1 = System.currentTimeMillis();
		FileDataModel model = new FileDataModel(new File("D://ratings_upgrate.dat"));
		PlusAnonymousUserDataModel aModel = new PlusAnonymousUserDataModel(model);


		PreferenceArray arg0 = new GenericUserPreferenceArray(2);
		Preference pref0 = new GenericPreference(PlusAnonymousUserDataModel.TEMP_USER_ID, 1l, 1);
		Preference pref1 = new GenericPreference(PlusAnonymousUserDataModel.TEMP_USER_ID, 2l, 1);
		arg0.set(0, pref0 );
		arg0.set(1, pref1 );
		aModel.setTempPrefs(arg0);
		Recommender r = new GenericItemBasedRecommender(aModel, (ItemSimilarity) new TanimotoCoefficientSimilarity(aModel));
		//r = new RandomRecommender(aModel);
		List<RecommendedItem> list = r.recommend(PlusAnonymousUserDataModel.TEMP_USER_ID, 10);
		
		Long t2 = System.currentTimeMillis();
		System.out.println("t2-t1: "+(t2-t1));
		for(RecommendedItem item :list){
			System.out.println(item);
		}
	}

}
