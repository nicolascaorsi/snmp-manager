package models;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class UptimeHistory extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@Required
	public long seconds;

	@Required
	public Date date;

	public static Finder<Long, UptimeHistory> find = new Finder<Long, UptimeHistory>(Long.class, UptimeHistory.class);

	public static UptimeHistory last() {
		return find.where().orderBy().desc("id").findList().get(0);
	}

	public static void create(UptimeHistory uptime) {
		uptime.save();
	}

}