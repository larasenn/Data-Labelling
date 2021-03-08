package p;

import java.io.FileReader;

import org.json.simple.parser.JSONParser;

import java.io.File;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.util.ArrayList;

public class HumanUser extends User {
	
	private String password;
	
	public HumanUser(int userID, String userName, LabelingMechanism labelingMechanism, String password) {
		super(userID, userName, labelingMechanism);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
