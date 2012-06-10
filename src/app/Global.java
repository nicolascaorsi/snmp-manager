import java.util.concurrent.TimeUnit;

import play.Application;
import play.GlobalSettings;
import play.libs.Akka;
import statistics.StatisticsCollector;
import akka.util.Duration;


public class Global extends GlobalSettings{

	@Override
	public void onStart(Application app) {
		super.onStart(app);		
		Akka.system().scheduler().schedule(
				  Duration.create(0, TimeUnit.SECONDS),
				  Duration.create(10, TimeUnit.SECONDS),
				  new Runnable() {
				    public void run() {
				      StatisticsCollector.CollectSnmpStatistics();
				    }
				  }
				); 		
		
	}
}
