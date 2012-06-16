package controllers;

import java.io.IOException;

import models.SystemDescriptionHistory;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.BodyParser.Json;
import utils.SnmpUtils;

public class Snmp extends Controller {


	@BodyParser.Of(Json.class)
	public static Result get(String oid) {		
		try {
			return ok(play.libs.Json.toJson(SnmpUtils.SnmpGet(oid).getValue()));
		} catch (IOException e) {
			play.Logger.error("Error", e);
			return internalServerError(e.getMessage());
		}
	}

	@BodyParser.Of(Json.class)
	public static Result walk(String oid) {		
		try {
			return ok(play.libs.Json.toJson(SnmpUtils.SnmpWalk(oid)));
		} catch (IOException e) {
			play.Logger.error("Error", e);
			return internalServerError(e.getMessage());
		}
	}
}
