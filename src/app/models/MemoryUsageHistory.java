package models;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class MemoryUsageHistory extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@Required
	public long amount;

	@Required
	public Date date;

	public static Finder<Long, MemoryUsageHistory> find = new Finder<Long, MemoryUsageHistory>(Long.class, MemoryUsageHistory.class);

	public static List<MemoryUsageHistory> all() {
		return find.all();
	}

	public static void create(MemoryUsageHistory memoryUsage) {
		memoryUsage.save();
	}

}