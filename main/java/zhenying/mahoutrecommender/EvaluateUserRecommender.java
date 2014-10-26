package zhenying.mahoutrecommender;

import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class EvaluateUserRecommender {

	public static void main(String[] args) throws IOException, TasteException {
		String fileName = "music_rating"; 
		
		DataModel model = new FileDataModel(new File("data/" + fileName + ".csv")); 
		RecommenderEvaluator evaluator = 
				new AverageAbsoluteDifferenceRecommenderEvaluator(); 
		// Setup an Item-based Recommender
		RecommenderBuilder builder = new MyUserBasedRecommender();
		
		// Take 90% of data to training  
		double result = evaluator.evaluate(builder, null, model, 0.9, 1.0);
		
		System.out.println("User-based, " + fileName + " evaluate:" + result); 

	}
	
	private static class MyUserBasedRecommender implements RecommenderBuilder {

		public Recommender buildRecommender(DataModel dataModel)
				throws TasteException {
			UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel); 
			// Similarity > 0.1 
			UserNeighborhood neighborhood = 
					new ThresholdUserNeighborhood(0.1, similarity, dataModel); 
			return new GenericUserBasedRecommender(dataModel, neighborhood, similarity); 
		}
		
	}

}
