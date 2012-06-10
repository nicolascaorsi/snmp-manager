package models;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class TcpSegmentsHistory extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@Required
	public long ammount;

	@Required
	public Date date;

	public static long totalAmmount;
	
	public static Finder<Long, TcpSegmentsHistory> find = new Finder<Long, TcpSegmentsHistory>(Long.class, TcpSegmentsHistory.class);

	public static List<TcpSegmentsHistory> all() {
		return find.all();
	}

	public static void create(TcpSegmentsHistory tcpSegmentsHistory) {
		tcpSegmentsHistory.save();
		totalAmmount += tcpSegmentsHistory.ammount;
	}

}