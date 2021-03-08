package p;

import java.io.FileReader;
import java.io.File;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UserAssignment {

	private ArrayList<User> userList = new ArrayList<User>();

	public void UserAssign(Dataset dataset) throws Exception {
		
		Logger logger = Logger.getLogger("Log");
		FileHandler fileHandling;
		fileHandling = new FileHandler("ProjectLogFile1.log");
		logger.addHandler(fileHandling);
		SimpleFormatter formatter = new SimpleFormatter();
		fileHandling.setFormatter(formatter);
		Object obj1 = new JSONParser().parse(new FileReader("config.json"));
		
		JSONObject jo1 = (JSONObject) obj1;
		JSONArray userList = (JSONArray) jo1.get("users");
		JSONObject temp1;
		double consistencyCheckProbability = (double) jo1.get("consistencyCheckProbability");
		LabelConsistency lc = new LabelConsistency(consistencyCheckProbability);
		ArrayList<User> userArrayList = new ArrayList<User>();
		ArrayList<Label> listLabel = dataset.getLabelList();
		
		for (int counter = 0; counter < userList.size(); counter++) {
			for (int j = 0; j < dataset.sizeofUser(); j++) {
				temp1 = (JSONObject) userList.get(counter);

				long userID1 = (long) temp1.get("user id");
				String type2 = temp1.get("user type").toString();
				
				LabelingMechanism random=null;
				if (type2.contains("Random")) {
					random = new RandomLabelingMechanism();
				}
				else {
					 random = new SubjectLabelingMechanism();	
				}
				if ((int) userID1 == dataset.userID(j)) {
					
					User user = new User((int) userID1, temp1.get("user name").toString(), random);
					userArrayList.add(user);
				}
			}
		}
		Output out = new Output();
		ArrayList<User> userArray = new ArrayList<User>();
		
		for (int k = 0; k < userArrayList.size(); k++) {
			userArray.add(userArrayList.get(k));
			User u = userArrayList.get(k);
			CompletenessPercentage cp=new CompletenessPercentage(0,u);
		
			int n = dataset.instanceListSize();
			ArrayList<Long> time = new ArrayList<Long>(n);
			long y = 0;
			long timeElapsed = 0;
			for (Instance i : dataset.getInstanceList()) {
				long startTime = System.nanoTime();
				Assignment a = u.getUserType().makeAssignment(i, dataset, u, lc);
				System.out.println("Number of datasets: " + dataset.getDatasetID());
				IsAssigned ia=new IsAssigned(true);
				ia.setInstance(i);
				ia.setUser(u);
				
				i.setIsAsssigned(ia);
				System.out.println(i.assigned());
				double c =dataset.calculateCompletenessPercentage();
				cp.setPercentage(c);
				dataset.setCompletenessPercentage(cp);
				long averageTimeElapsed = timeElapsed / dataset.getInstanceList().size();
				System.out.println("Instance Number: " + dataset.getInstanceList().size());
				System.out.println("Unique Instance Number: "
						+ (float) ((double) dataset.instanceListSize() / (double) userArrayList.size()) * 10
								/ 10);
				System.out.println("Average time: " + averageTimeElapsed + " nanoseconds");
				System.out.println("Std. dev: " + (float) calculateSD(time) * 10 / 10);
				System.out.println(
						"Total Label Assignment Number: " + dataset.instanceListSize() * userArrayList.size());
				System.out.println("Unique Label Assignment Number: " + y);
				System.out.println("Unique User Number: " + userList.stream().distinct().count());
				System.out.println("Percentage: " + (double) ((double) getPopularElement(listLabel).getCount()
						/ (double) (dataset.getInstanceList().size() * userArrayList.size() * dataset.getMaxLabel()))
						* 100);
				double x;
				x = (100.0 / userArrayList.size()) * (k + 1);
				System.out.println("Dataset Percentage: " + x);
				System.out.println("Number of users assigned to this dataset: " + dataset.getAssignedUserList().size());
				System.out.println("List number of unique instances for each class label: "
						+ (double) dataset.getInstanceList().size() / (double) dataset.getLabelList().size());
				System.out.println("List of users assigned and their completeness percentage: " + "(user"
						+ dataset.getAssignedUserList().get(k) + " , " + dataset.calculateCompletenessPercentage()
						+ ")");
				long endTime = System.nanoTime();
				
				i.getAssignmentList().add(a);
				
				out.outputJSON(dataset, userArray);
				out.formatJsonString(dataset);
				try {
					for (Label lf : a.getLabelList()) {
						logger.info("User ID: " + u.getUserID() + " " + u.getUserName() +" "+u.getUserType().toString()+ " " + " Tagged instance ID: "
								+ i.getInstanceID() + " With class label " + lf.getLabelID() + ":" + lf.getLabelText()
								+ "Instance:\"" + i.getInstanceContent() + "\"");
						timeElapsed = endTime - startTime;
						y = a.getLabelList().stream().distinct().count();
					}
					time.add(timeElapsed);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		this.setUserList(userArrayList);
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}

	public double calculateSD(ArrayList<Long> time) {
		double sum = 0.0, standardDeviation = 0.0;
		int length = time.size();
		for (double num : time) {
			sum += num;
		}
		double mean = sum / length;

		for (double num : time) {
			standardDeviation += Math.pow(num - mean, 2);
		}
		return Math.sqrt(standardDeviation / length);
	}

	public Label getPopularElement(ArrayList<Label> list) {
		int count = 1, tempCount;
		Label popular = list.get(0);
		Label temp;
		for (int i = 0; i < (list.size()); i++) {
			temp = list.get(i);
			tempCount = 0;
			for (int j = 1; j < list.size(); j++) {
				if (temp == list.get(j))
					tempCount++;
			}
			if (tempCount > count) {
				popular = temp;
				count = tempCount;
			}
		}
		return popular;
	}
}