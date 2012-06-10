package models;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class SystemDescriptionHistory extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@Required
	public String description;

	@Required
	public Date date;

	public static Finder<Long, SystemDescriptionHistory> find = new Finder<Long, SystemDescriptionHistory>(Long.class, SystemDescriptionHistory.class);

	public static List<SystemDescriptionHistory> all() {
		return find.all();
	}

	public static void create(SystemDescriptionHistory description) {
		description.save();
	}

}