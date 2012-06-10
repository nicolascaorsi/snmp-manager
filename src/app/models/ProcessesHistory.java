package models;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class ProcessesHistory extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@Required
	public long numberOfProcesses;

	@Required
	public Date date;

	public static Finder<Long, ProcessesHistory> find = new Finder<Long, ProcessesHistory>(Long.class, ProcessesHistory.class);

	public static List<ProcessesHistory> all() {
		return find.all();
	}

	public static ProcessesHistory getLast() {
		return find.where().orderBy().desc("id").findList().get(0);
	}

	public static List<ProcessesHistory> getLast(int count) {
		return find.where()
				.orderBy()
				.asc("id")
				.findPagingList(count)
				.getPage(1)
				.getList();
	}

	public static void create(ProcessesHistory uptime) {
		uptime.save();
	}

}