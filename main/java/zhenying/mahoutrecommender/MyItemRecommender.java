package zhenying.mahoutrecommender;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;


public class MyItemRecommender {

	public static void main(String[] args) throws IOException, TasteException {
		String fileName = "movies"; 
		int userId = 2; 
		int recommendAmount = 10;  
		
		DataModel model = new FileDataModel(new File("data/" + fileName + ".csv")); 
		ItemSimilarity similarity = new PearsonCorrelationSimilarity(model); 
		
		ItemBasedRecommender recommender = 
				new GenericItemBasedRecommender(model, similarity); 
		// Get recommendAmount items for user with userId. 
		List<RecommendedItem> recommendations = 
				recommender.recommend(userId, recommendAmount);
		List<ItemType> items = new ArrayList<ItemType>(); 
		for (RecommendedItem recommendation : recommendations) {
			items.add(new ItemType(recommendation.getItemID(), recommendation.getValue())); 
		}
		
		Collections.sort(items); 
		ItemOutput.writeFile(fileName, items, "item");
		
		System.out.println("done"); 
	}

}
