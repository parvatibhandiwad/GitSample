package rehulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader 
{
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException
	{
		//read json to string
		String Jsoncontent=FileUtils.readFileToString(new File(System.getProperty("user.dir")
				+"//src//test//java//rehulshettyacademy//data//PurchaseOrder.json"),StandardCharsets.UTF_8);
		 
		//String to HashMap
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> data=mapper.readValue(Jsoncontent, new TypeReference<List<HashMap<String, String>>>(){});
			
		return data;
				
		
	}
}
