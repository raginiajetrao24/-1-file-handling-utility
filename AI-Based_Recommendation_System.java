/**
 * This program demonstrates a recommendation system in Java using Apache Mahout.
 * It suggests items to users based on their preferences using collaborative filtering.
 */

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.DataModelBuilder;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.IRStatistics;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AI-Based_Recommendation_System {

    public static void main(String[] args) {
        try {
            // Load data from a CSV file
            DataModel dataModel = new FileDataModel(new File("data.csv"));

            // Calculate similarity between users
            UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);

            // Create a neighborhood of users
            NearestNUserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, dataModel);

            // Build the recommender system
            GenericUserBasedRecommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);

            // Generate recommendations for a user (e.g., user ID 1)
            List<RecommendedItem> recommendations = recommender.recommend(1, 3);

            // Display recommendations
            System.out.println("Recommended items for user 1:");
            for (RecommendedItem recommendation : recommendations) {
                System.out.println(recommendation);
            }

        } catch (IOException | TasteException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
