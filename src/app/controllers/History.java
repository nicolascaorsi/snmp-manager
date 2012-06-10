package controllers;

import java.io.IOException;

import models.IcmpEchoRequestHistory;
import models.MemoryUsageHistory;
import models.ProcessesHistory;
import models.SystemDescriptionHistory;
import models.UptimeHistory;
import play.api.Logger;
import play.mvc.BodyParser;
import play.mvc.BodyParser.Json;
import play.mvc.Controller;
import play.mvc.Result;
import snmp.SnmpOid;
import utils.SnmpUtils;
import views.html.defaultpages.error;

public class History extends Controller {

	@BodyParser.Of(Json.class)
	public static Result systemDescription() {		
		return ok(play.libs.Json.toJson(SystemDescriptionHistory.last()));
	}

	@BodyParser.Of(Json.class)
	public static Result memoryUsage() {		
		return ok(play.libs.Json.toJson(MemoryUsageHistory.all()));
	}

	@BodyParser.Of(Json.class)
	public static Result uptime() {		
		return ok(play.libs.Json.toJson(UptimeHistory.last()));
	}

	@BodyParser.Of(Json.class)
	public static Result processes() {		
		return ok(play.libs.Json.toJson(ProcessesHistory.getLast(20)));
	}

	@BodyParser.Of(Json.class)
	public static Result lastProcess() {		
		return ok(play.libs.Json.toJson(ProcessesHistory.getLast()));
	}

	@BodyParser.Of(Json.class)
	public static Result icmpEchoRequests() {
		return ok(play.libs.Json.toJson(IcmpEchoRequestHistory.all()));
	}

	@BodyParser.Of(Json.class)
	public static Result get(String oid) {		
		try {
			return ok(play.libs.Json.toJson(SnmpUtils.SnmpGet(oid).getValue()));
		} catch (IOException e) {
			play.Logger.error("Error", e);
			return internalServerError(e.getMessage());
		}
	}

}
