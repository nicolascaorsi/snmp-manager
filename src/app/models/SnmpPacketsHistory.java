package models;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class SnmpPacketsHistory extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@Required
	public long ammount;

	@Required
	public Date date;

	public static long totalAmmount;
	
	public static Finder<Long, SnmpPacketsHistory> find = new Finder<Long, SnmpPacketsHistory>(Long.class, SnmpPacketsHistory.class);

	public static List<SnmpPacketsHistory> all() {
		return find.all();
	}

	public static void create(SnmpPacketsHistory snmpPacketsHistory) {
		snmpPacketsHistory.save();
		totalAmmount += snmpPacketsHistory.ammount;
	}

}