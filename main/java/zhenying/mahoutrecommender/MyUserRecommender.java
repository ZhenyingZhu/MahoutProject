package zhenying.mahoutrecommender;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;


public class MyUserRecommender {

	public static void main(String[] args) throws IOException, TasteException {
		String fileName = "movies";
		double simDegree = 0.1; 
		int userId = 2; 
		int recommendAmount = 10; 
		
    	DataModel model = new FileDataModel(new File("data/" + fileName + ".csv"));
    	UserSimilarity similarity = new PearsonCorrelationSimilarity(model); 
    	//  have a similarity greater than simDegree. 
    	UserNeighborhood neighborhood = 
    			new ThresholdUserNeighborhood(simDegree, similarity, model);

    	UserBasedRecommender recommender = 
    			new GenericUserBasedRecommender(model, neighborhood, similarity); 
    	// get recommendAmount items recommended for the user with userId. 
    	List<RecommendedItem> recommendations = 
    			recommender.recommend(userId, recommendAmount);
    	List<ItemType> items = new ArrayList<ItemType>(); 
		for (RecommendedItem recommendation : recommendations) {
			items.add(new ItemType(recommendation.getItemID(), recommendation.getValue())); 
		}
		
		Collections.sort(items); 
		ItemOutput.writeFile(fileName, items, "user");
		
		System.out.println("done");

	}

}
