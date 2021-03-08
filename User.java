package p;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

class User {

    private int userID;
    private String userName;
    private LabelingMechanism userType;
 // Creates a constructor
    
    User(int userID, String userName, LabelingMechanism userType) {
    	
        this.userID = userID;
        this.userName = userName;
        this.userType = userType;
    }
 public User() {
	super();
}
// Creates getter/setter methods
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LabelingMechanism getUserType() {
        return userType;
    }

    public void setUserType(LabelingMechanism userType) {
        this.userType = userType;
    }
    
public ArrayList<User> createAssignedUserList(File file) throws Exception {
		
		Object obj = new JSONParser().parse(new FileReader(file.getAbsolutePath()));
		JSONObject jo = (JSONObject) obj;
		JSONArray assignedUser = (JSONArray) jo.get("user assignment");

		JSONObject temp;
		ArrayList<User> userArrayList = new ArrayList<User>();
		
		User u;

		
		for (int i = 0; i < assignedUser.size(); i++) {
			temp = (JSONObject) assignedUser.get(i);
			long userID = (long) temp.get("id");
			String username ="user"+ String.valueOf((int)userID);
			LabelingMechanism random = new RandomLabelingMechanism();
			
			u = new User((int) userID, username,random);
			
			userArrayList.add(u);

		}
		
		return userArrayList;

	}


}