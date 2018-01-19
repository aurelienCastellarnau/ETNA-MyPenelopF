package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

public class ContactUtils {
	
	public void getUsers() throws IOException{
		String project_path = System.getProperty("user.dir");
		BufferedReader bufferedReader = new BufferedReader(new FileReader(project_path + "/users.json"));

        Gson gson = new Gson();
        Object json = gson.fromJson(bufferedReader, Object.class);

        System.out.println(json.getClass());
        System.out.println(json.toString());
	}
}
