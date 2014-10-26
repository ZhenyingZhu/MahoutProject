package zhenying.mahoutrecommender;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.svd.ALSWRFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

public class MyALSRecommender {

	public static void main(String[] args) throws IOException, TasteException {
		String fileName = "movie_rating"; 
		int userId = 2; 
		int recommendAmount = 300;  
		
		DataModel model = new FileDataModel(new File("data/" + fileName + ".csv")); 
		
		SVDRecommender recommender = 
				new SVDRecommender(model, new ALSWRFactorizer(model, 10, 0.05, 10)); 
		// Get recommendAmount items for user with userId. 
		List<RecommendedItem> recommendations = 
				recommender.recommend(userId, recommendAmount);
		List<ItemType> items = new ArrayList<ItemType>(); 
		for (RecommendedItem recommendation : recommendations) {
			items.add(new ItemType(recommendation.getItemID(), recommendation.getValue())); 
		}
		
		Collections.sort(items); 
		ItemOutput.writeFile(fileName, items, "ALS");
		
		System.out.println("done"); 
	}

}
