package p;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

class Label {
	private int labelID;
	private String labelText;
	int count=0;
	private Label popular;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	// Creates a constructor
	public Label(int labelID, String labelText) {
		
		
		super();
		this.labelID = labelID;
		this.labelText = labelText;
	}

	public Label() {
		super();
	}

	// Creates getter/setter methods
	public int getLabelID() {
		return labelID;
	}

	public void setLabelID(int labelID) {
		this.labelID = labelID;
	}

	public String getLabelText() {
		return labelText;
	}

	public void setLabelText(String labelText) {
		this.labelText = labelText;
	}
	
	public ArrayList<Label> createLabelList(File file) throws Exception {
		
		Object obj = new JSONParser().parse(new FileReader(file.getAbsolutePath()));
		JSONObject jo = (JSONObject) obj;
		JSONArray labelList = (JSONArray) jo.get("class labels");
		JSONObject temp;
		ArrayList<Label> labelArrayList = new ArrayList<Label>();
		
		Label l;

		for (int i = 0; i < labelList.size(); i++) {
			temp = (JSONObject) labelList.get(i);
			long labelID = (long) temp.get("label id");
			l = new Label((int) labelID, temp.get("label text").toString());
			labelArrayList.add(l);
			
		}
		
		return labelArrayList;

	}
	


	
}