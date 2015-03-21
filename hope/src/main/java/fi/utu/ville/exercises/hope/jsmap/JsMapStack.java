package fi.utu.ville.exercises.hope.jsmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;

@com.vaadin.annotations.JavaScript({"public/initMaps.js" })

public class JsMapStack extends AbstractJavaScriptComponent {
	
	public JsMapStack() {
		addFunction("sendCountryAnswer", new JavaScriptFunction() { 

		      public void call(JSONArray arguments) throws JSONException {
		        JSONArray realArgs = arguments.getJSONArray(0); 
		        JSONObject args = realArgs.getJSONObject(0); 
		        /*getState().points = args.get("points").toString(); 
		        getState().tries = args.get("tries").toString(); 
		        points = getState().points; 
		        tries = getState().tries; */
		      }
		    });
	}
		
		
		
	

}
