package p;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

class Instance {

	private int instanceID;
	private String instanceContent;
	private ArrayList<Assignment> assignmentList = new ArrayList<Assignment>();
	private IsAssigned isAsssigned;
	
	
	public Instance() {
		super();
	}

	public Instance(int instanceID, String instanceContent, IsAssigned isAsssigned) {
		super();
		this.instanceID = instanceID;
		this.instanceContent = instanceContent;
		this.isAsssigned = isAsssigned;
	}

	public int getInstanceID() {
		return instanceID;
	}
	public void setInstanceID(int instanceID) {
		this.instanceID = instanceID;
	}
	public String getInstanceContent() {
		return instanceContent;
	}
	public void setInstanceContent(String instanceContent) {
		this.instanceContent = instanceContent;
	}
	public ArrayList<Assignment> getAssignmentList() {
		return assignmentList;
	}
	public void setAssignmentList(ArrayList<Assignment> assignmentList) {
		this.assignmentList = assignmentList;
	}
	public IsAssigned getIsAsssigned() {
		return isAsssigned;
	}
	public void setIsAsssigned(IsAssigned isAsssigned) {
		this.isAsssigned = isAsssigned;
	}
	
	public boolean assigned() {
		boolean s = isAsssigned.isAsssigned();
		
		return s;
	}
	
public ArrayList<Instance> createInstanceList(File file) throws Exception {
		
		Object obj = new JSONParser().parse(new FileReader(file.getAbsolutePath()));
		JSONObject jo = (JSONObject) obj;
		JSONArray instanceList = (JSONArray) jo.get("instances");

		JSONObject temp;
		ArrayList<Instance> instanceArrayList = new ArrayList<Instance>();
		
		Instance ins;

		IsAssigned ia= new IsAssigned(false);
		for (int i = 0; i < instanceList.size(); i++) {
			temp = (JSONObject) instanceList.get(i);
			String s = temp.get("instance").toString();

			long insID = (long) temp.get("id");

			ins = new Instance((int) insID, s,ia);

			instanceArrayList.add(ins);

		}
		
		return instanceArrayList;

	}
public ArrayList<String> words(){
	ArrayList<String> word = new ArrayList<String>();
	String temp=instanceContent;
	while(temp.contains(" ")) {
		word.add(temp.substring(0,temp.indexOf(" ")));
		temp=temp.substring(temp.indexOf(" ")+1);
		
	}
	
	word.add(temp);
	return word;
	}
}
	
