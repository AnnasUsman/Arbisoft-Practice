import java.io.*;
import java.util.*;

public class queen{
	
	int cases;
	int currcase = 1;
	String fileName;
	int lines;
	HashMap<String, String> men = new HashMap<String, String>();	// stores unique names
	HashMap<String, Integer> tribes = new HashMap<String, Integer>();	// stores tribe names and their count
	
	void registerMen(){	// this function registers the men excluding duplicate names
		try {
			clearFile();
			File myObj = new File(fileName);
			Scanner myReader = new Scanner(myObj);
			cases = Integer.parseInt(myReader.nextLine());
			
			while (currcase <= cases) {	// this loop runs equal to the number of cases
				lines = Integer.parseInt(myReader.nextLine());
				
				while (lines > 0) {	// this loop runs for each case
					String man = myReader.nextLine();
					String tribe = man.substring(0,man.indexOf(" "));
					String name = man.substring(man.indexOf(" ")+1,man.length());
					men.put(name,tribe);	// registering unique names
					lines--;
				}
				countMen();
				outputCase();
				currcase++;
				men.clear();
				tribes.clear();
			}
			myReader.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	void countMen(){	// this function counts men in each tribe
		for (Map.Entry mapElement : men.entrySet()) {	// this loop runs equal to number of men
            String key = (String)mapElement.getKey();
			String value = (String)men.get(key);
			
			if (tribes.containsKey(value)){	// counting men of each tribe
				tribes.put(value, tribes.get(value) + 1);
			}
			else{
				tribes.put(value, 1);
			}
        }
	}
	
	void outputCase(){	// this function writes to the files
		try {
			FileWriter myWriter = new FileWriter("output.txt",true);
			myWriter.write("Case: " + Integer.toString(currcase));
			
			for (Map.Entry mapElement : tribes.entrySet()) {	// this loop runs equal to number of tribes
				String key = (String)mapElement.getKey();
				Integer value = tribes.get(key);
				myWriter.write("\n" + key + " " + value);	// writing tribe and its count
			}
			myWriter.write("\n");
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	void clearFile(){
		try {
			FileWriter myWriter = new FileWriter("output.txt");
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}