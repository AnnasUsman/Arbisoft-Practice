import java.io.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class queen2{
	
	int cases;
	int currcase = 1;
	String fileName;
	int lines;
	HashMap<String, String> men = new HashMap<String, String>();	// stores unique names
	HashMap<String, Integer> tribes = new HashMap<String, Integer>();	// stores tribe names and their count

	JSONArray tribeList = new JSONArray();
	
	public void registerMen(){	// this function registers the men excluding duplicate names
		
		JSONParser jsonParser = new JSONParser();
		
		try{
			FileReader reader = new FileReader(fileName);
			
            // Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray totalcases = (JSONArray) obj;
			
			totalcases.forEach(user -> parseCaseObject((JSONArray) user));
			
			writeFile();
		}
		catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	private void parseCaseObject(JSONArray case_) {
		case_.forEach(man -> parseObject((JSONObject) man));
		countMen();
		makeJson();
		men.clear();
		tribes.clear();
    }
	
	private void parseObject(JSONObject person) {
		String name = (String) person.get("name");
			
		String tribe = (String) person.get("tribe");
			
		men.put(name,tribe);
    }
	
	private void countMen(){	// this function counts men in each tribe
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
	
	private void makeJson(){
		JSONArray caseList = new JSONArray();
		for (Map.Entry mapElement : tribes.entrySet()) {	// this loop runs equal to number of tribes
			String key = (String)mapElement.getKey();
			Integer value = tribes.get(key);
			
			JSONObject case_ = new JSONObject();
			case_.put("tribe", key);
			case_.put("count", Integer.toString(value));
			caseList.add(case_);
		}
		tribeList.add(caseList);
	}
	
	private void writeFile(){
		try (FileWriter file = new FileWriter("output.json")) {

            file.write(tribeList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}