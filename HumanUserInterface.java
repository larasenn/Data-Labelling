package p;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.Scanner;

public class HumanUserInterface {
	
	private User human;
	private boolean loggedIn = false;
	private Dataset dataset;
	
	
	public HumanUserInterface(Dataset dataset) {
		super();
		this.dataset = dataset;
	}

	public void login() throws Exception {
		
		Object obj1 = new JSONParser().parse(new FileReader("config.json"));

		JSONObject jo1 = (JSONObject) obj1;
		JSONArray humanUserList = (JSONArray) jo1.get("human users");
		JSONObject user;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter user name: ");
		String userName = input.nextLine();
		System.out.println("Enter password: ");
		String password = input.nextLine();
		
		if (userName.equals(" ") && password.equals(" "))
			return;
		
		for (int i = 0; i < humanUserList.size(); i++) {
			user = (JSONObject) humanUserList.get(i);
			if (userName.equals(user.get("human user name").toString()) && password.equals(user.get("password").toString()) ) {
				System.out.println("Login succesful");
				this.setLoggedIn(true);
				long userID = (long) user.get("user id");
				
				LabelingMechanism labelingMechanism = new ManualLabelingMechanism();
				System.out.println("Human user id: " + userID);
				HumanUser hu = new HumanUser((int)userID, userName, labelingMechanism, password);
				this.setHuman(hu);
				break;
			}
		}
		
		if (!this.isLoggedIn()) {
			System.out.println("Login failed");
			return;
		}
		
		this.labelInstances();
		
		//System.out.println(user.get("human user name").toString());
		
	}
	
	private void labelInstances() throws Exception {
		/*Object obj1 = new JSONParser().parse(new FileReader("config.json"));
		JSONObject jo1 = (JSONObject) obj1;
		long currentDatasetID = (long) jo1.get("current dataset id");
		System.out.println(currentDatasetID); */
		System.out.println("DatasetID: " + dataset.getDatasetID());
		Scanner input = new Scanner(System.in);
		
		
		for (int i = 0; i < dataset.getInstanceList().size(); i++) {
			Instance ins = dataset.getInstanceList().get(i);
			System.out.println("Instance: " + ins.getInstanceContent());
			
			System.out.println("Labels: ");
			for (int j = 0; j < dataset.getLabelList().size(); j++) {
				Label l = dataset.getLabelList().get(j);
				System.out.println("LabelID: " + l.getLabelID() + " Label Name: " + l.getLabelText());
			}
			
			while (true) {
			System.out.print("Enter label ID's one by one or type next to skip instance: ");
			String inputString = input.next();
			if (inputString.equals("next")) 
				break;
			int assignmentLabelID = Integer.parseInt(inputString);
			System.out.println("assigned label: " + assignmentLabelID);
			((ManualLabelingMechanism)human.getUserType()).setLabel(dataset.getLabelList().get(assignmentLabelID - 1));
			this.human.getUserType().makeAssignment(ins, dataset, this.human, null);
			
			
			}
			
		}
		
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(this.human);
		
		Output o = new Output();
		o.outputJSON(dataset, userList);
		o.formatJsonString(dataset);
		System.exit(0);
		
		
	}
	
	//public ArrayList<Label> assignedLabel()
		
	public User getHuman() {
		return human;
	}

	public void setHuman(HumanUser human) {
		this.human = human;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}


	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}

	
		
}
