package zhenying.mahoutrecommender;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ItemOutput {
	public static void writeFile(String fileName, List<ItemType> items, String memo) {
    	try {
    		File file = new File("data/result_"+ memo + "_" + fileName + ".txt");
    		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
    		for (ItemType item : items) {
    			writer.write(item.toString()); 
    			writer.newLine();
    		}
    		writer.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}

	}

}
