package T;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.pojoclass.DataArea;
import com.pojoclass.EverythingMappedExcludingDevices;
import com.pojoclass12.Properties;

public class JsonToObject {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		 // Creating object of Organisation 
        Organisation org = new Organisation(); 
        
        EverythingMappedExcludingDevices dataArea = new EverythingMappedExcludingDevices();
        
  
        // Converting json to object 
        dataArea = getOrganisationObject(); 
  
        // Displaying object 
        System.out.println(dataArea);
	}
	
	 private static EverythingMappedExcludingDevices getOrganisationObject() throws Exception 
	    { 
	        // Storing preprocessed json(Added slashes) 
	        String OrganisationJson = new String(Files.readAllBytes(Paths.get("C:\\Users\\mahravur\\Desktop\\jsonfile.json")));
	        
	        BufferedReader br = new BufferedReader(new FileReader("C:\\\\Users\\\\mahravur\\\\Desktop\\\\jsonfile.json"));
	  
	        
	        
	        // Creating a Gson Object 
	        Gson gson = new Gson(); 
	  
	        // Converting json to object 
	        // first parameter should be prpreocessed json 
	        // and second should be mapping class 
	        EverythingMappedExcludingDevices organisation 
	            = gson.fromJson(br, 
	            		EverythingMappedExcludingDevices.class); 
	  
	        // return object 
	        return organisation; 
	    } 
	
	

}
