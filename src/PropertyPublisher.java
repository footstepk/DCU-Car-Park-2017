package EstateAgent;

import javax.xml.ws.Endpoint;

/**
  * implemented by Kok Heng and Rupak
  */

/**
  * the property publisher for the endpoints
  * invoke the main method here
  */

public class PropertyPublisher {

	public static void main(String[]args) {

		// initial the property implementation object
		PropertyImplementation agent = new PropertyImplementation();
		Endpoint.publish("http://localhost:8080/PropertyAgent", agent);
	}
}