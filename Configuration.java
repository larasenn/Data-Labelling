package p;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;
import com.google.gson.JsonElement;
import java.util.ArrayList;

class Configuration {
	public void configJson(User user) throws Exception {

		Object obj1 = new JSONParser().parse(new FileReader("config.json"));
		JSONObject jo1 = (JSONObject) obj1;
		JSONArray userList = (JSONArray) jo1.get("users");
		double consistencyCheckProbability = (double) jo1.get("consistencyCheckProbability");
		long currentDatasetID = (long) jo1.get("current dataset id");
		JSONObject ccp = new JSONObject();
		ccp.put("consistencyCheckProbability", consistencyCheckProbability);
		ccp.put("current dataset id", currentDatasetID);
		JSONObject newUser = new JSONObject();
		newUser.put("user name", user.getUserName());
		newUser.put("user id", user.getUserID());
		newUser.put("user type", "RandomBot");
		userList.add(newUser);
		ccp.put("users", userList);
		
		
		try (FileWriter file = new FileWriter("config.json")) {
			file.write(ccp.toJSONString());
			file.close();
		} catch (Exception e) {
			System.out.println("Can't open file");
		}
		UserAssignment ua = new UserAssignment();
		ua.getUserList().add(user);
		formatJsonString("config.json");
	}

	public void formatJsonString(String str) {// Make the output file more readable with GSON pretty printing
		try {
			String json = readFileAsString(str);
			JsonElement jsonElement = new JsonParser().parse(json);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json = gson.toJson(jsonElement);
			FileWriter file = new FileWriter(str);
			file.write(json);
			file.close();
		} catch (Exception e) {
		}
	}

	public String readFileAsString(String file) throws Exception {
		// Reads the output file and puts it into a string
		return new String(Files.readAllBytes(Paths.get(file)));
	}
}