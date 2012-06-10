package controllers;

import java.io.IOException;

import models.SnmpResponse;
import play.mvc.Controller;
import play.mvc.Result;
import utils.SnmpUtils;
import views.html.index;

public class Application extends Controller {

	public static Result index() throws IOException {
		SnmpResponse snmpResponse = SnmpUtils.SnmpGet("1.3.6.1.2.1.1.5.0");
		String tempo = String.valueOf(SnmpUtils.TimeInformationToSeconds(SnmpUtils.SnmpGet("1.3.6.1.2.1.25.1.1.0").getValue()));
			return ok(index.render(tempo
					));
					//snmpResponse.getOid() + ": " + snmpResponse.getValue()));
	}


}
