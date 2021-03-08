package p;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.io.File;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.util.ArrayList;

class DatasetAssignment {

	public Dataset DatasetAssign(File file) throws Exception {

		String datasetName = "";
		long datasetID = 0;
		long maxLabel = 0;
		ArrayList<Instance> instanceArrayList = new ArrayList<Instance>();
		ArrayList<Label> labelArrayList = new ArrayList<Label>();
		ArrayList<User> assignedUserList = new ArrayList<User>();
		Dataset d = new Dataset(0, "", 0, instanceArrayList, labelArrayList, assignedUserList);

		Object obj = new JSONParser().parse(new FileReader(file.getAbsolutePath()));
		JSONObject jo = (JSONObject) obj;
		maxLabel = (long) jo.get("maximum number of labels per instance");
		d.setMaxLabel((int) maxLabel);
		datasetName = (String) jo.get("dataset name");
		d.setDatasetName(datasetName);

		datasetID = (long) jo.get("dataset id");
		d.setDatasetID((int) datasetID);

		JSONObject temp;
		Instance ins = new Instance();
		instanceArrayList = ins.createInstanceList(file);

		d.setInstanceList(instanceArrayList);
		;

		Label l = new Label();

		ArrayList<Label> labelList = new ArrayList<Label>();
		labelList = l.createLabelList(file);

		d.setLabelList(labelList);

		User u = new User();
		assignedUserList = u.createAssignedUserList(file);
		d.setAssignedUserList(assignedUserList);

		return d;
	}

}