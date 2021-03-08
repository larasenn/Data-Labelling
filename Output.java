package p;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Output {
	@SuppressWarnings("unchecked")
	public void outputJSON(Dataset dataset, ArrayList<User> userList) {

		// create JSON object in order to write to output Json file
		JSONObject datasetJSON = new JSONObject();
		datasetJSON.put("dataset id", dataset.getDatasetID());
		datasetJSON.put("dataset name", dataset.getDatasetName());
		datasetJSON.put("maximum numbers of labels per instance", dataset.getMaxLabel());

		// create JSON array in order to write to output Json file
		JSONArray classLabels = new JSONArray();
		for (Label l : dataset.getLabelList()) {
			JSONObject labelDetail = new JSONObject();
			labelDetail.put("label id", l.getLabelID());
			labelDetail.put("label text", l.getLabelText());
			classLabels.add(labelDetail);
		}
		datasetJSON.put("class labels", classLabels);

		JSONArray instances = new JSONArray();
		for (Instance i : dataset.getInstanceList()) {
			JSONObject instanceDetail = new JSONObject();
			instanceDetail.put("id", i.getInstanceID());
			instanceDetail.put("instance", i.getInstanceContent());
			instances.add(instanceDetail);
		}
		datasetJSON.put("instances", instances);

		JSONArray classLabelAssignments = new JSONArray();
		int c = 0;
		// Writes all the assignment objects for each user to the output file
		for (User u : userList) {
			
			for (Instance i : dataset.getInstanceList()) {
				if (i.getAssignmentList().size() <= c)
					break;

				JSONObject assignmentDetail = new JSONObject();
				assignmentDetail.put("instance id", i.getInstanceID());
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
				assignmentDetail.put("datetime", i.getAssignmentList().get(c).getDateTime().format(dtf));
				JSONArray classLabelIDs = new JSONArray();

				for (Label l : i.getAssignmentList().get(c).getLabelList()) {
					classLabelIDs.add(l.getLabelID());
				}
				assignmentDetail.put("class label ids", classLabelIDs);
				assignmentDetail.put("user id", u.getUserID());
				classLabelAssignments.add(assignmentDetail);
			}
			c++;
		}
		datasetJSON.put("class label assignments", classLabelAssignments);

		try (FileWriter file = new FileWriter("output" + dataset.getDatasetID() + ".json")) {
			file.write(datasetJSON.toJSONString());
			file.close();
		} catch (Exception e) {
			System.out.println("Can't open file");
		}
	}

	public void formatJsonString(Dataset dataset) {
		try {
			String output = "output" + dataset.getDatasetID() + ".json";
			String json = readFileAsString(output);
			JsonElement jsonElement = new JsonParser().parse(json);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json = gson.toJson(jsonElement);
			FileWriter file = new FileWriter("output" + dataset.getDatasetID() + ".json");
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
