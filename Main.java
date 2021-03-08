package p;

import java.io.FileReader;
import java.io.File;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Main {
	public static void main(String[] args) throws Exception {

		Object obj1 = new JSONParser().parse(new FileReader("config.json"));
		long currentDatasetID = 0;
		JSONObject jo1 = (JSONObject) obj1;
		currentDatasetID = (long) jo1.get("current dataset id");
		int cdi = (int) currentDatasetID;
		String f = "input" + Integer.toString(cdi) + ".json";
		File file = new File("inputs/" + f);

		DatasetAssignment da = new DatasetAssignment();
		Dataset dataset = da.DatasetAssign(file);

		HumanUserInterface ui = new HumanUserInterface(dataset);
		ui.login();

		UserAssignment ua = new UserAssignment();
		ua.UserAssign(dataset);

	}
}