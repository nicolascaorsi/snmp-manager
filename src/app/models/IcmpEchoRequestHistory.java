package models;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class IcmpEchoRequestHistory extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@Required
	public long ammount;

	@Required
	public Date date;

	public static long totalAmmount;
	
	public static Finder<Long, IcmpEchoRequestHistory> find = new Finder<Long, IcmpEchoRequestHistory>(Long.class, IcmpEchoRequestHistory.class);

	public static List<IcmpEchoRequestHistory> all() {
		return find.all();
	}

	public static void create(IcmpEchoRequestHistory icmpEchoRequestHistory) {
		icmpEchoRequestHistory.save();
		totalAmmount += icmpEchoRequestHistory.ammount;
	}

}