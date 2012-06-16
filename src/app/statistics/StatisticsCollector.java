package statistics;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import ch.qos.logback.core.pattern.FormattingConverter;

import com.sun.jmx.snmp.SnmpTimeticks;

import play.Logger;

import models.IcmpEchoRequestHistory;
import models.MemoryUsageHistory;
import models.ProcessesHistory;
import models.SnmpPacketsHistory;
import models.SystemDescriptionHistory;
import models.TcpSegmentsHistory;
import models.UptimeHistory;
import snmp.SnmpOid;
import utils.SnmpUtils;

public class StatisticsCollector {
	
	public static void CollectSnmpStatistics() {
		collectSystemDescription();
		collectUptime();
		collectProcesses();
		collectMemoryUsage();
		collectIcmpEchoRequest();
		collectTcpSegments();
		collectSnmpPackets();
	}
	
	private static void collectMemoryUsage() {
		//		try {
				MemoryUsageHistory memoryUsage = new MemoryUsageHistory();
				memoryUsage.id = new Date().getTime();
				memoryUsage.date = new Date();
				//TODO Coletar o uso real de memoria
		//		process.numberOfProcesses = Long.parseLong(SnmpUtils.SnmpGet("127.0.0.1 1.3.6.1.2.1.25.1.3.0").getValue());
				memoryUsage.amount = new Random().nextLong();
				Logger.debug("Memory usage collected ("+ memoryUsage.amount+"mb)");
				MemoryUsageHistory.create(memoryUsage);
				
		//	} catch (IOException e) {
		//		Logger.error("Error when reading processes counter!");
		//	}
	}
//

	private static void collectProcesses() {
		try {
			ProcessesHistory process = new ProcessesHistory();
			process.id = new Date().getTime();
			process.date = new Date();
//			process.numberOfProcesses = Long.parseLong(SnmpUtils.SnmpGet(SnmpOid.hrSystemProcesses).getValue());
			process.numberOfProcesses = (int) (Math.random() * 60D);
			
			Logger.debug("Running processes collected ("+ process.numberOfProcesses+")");
			ProcessesHistory.create(process);
		} catch (Exception e) {
			Logger.error("Error when reading processes counter! Message: "+ e.getMessage());
		}
	}

	private static void collectUptime() {
		try {
			UptimeHistory uptime = new UptimeHistory();
			uptime.id = new Date().getTime();
			uptime.date = new Date();
			uptime.seconds = SnmpUtils.TimeInformationToSeconds(SnmpUtils.SnmpGet("1.3.6.1.2.1.25.1.1.0").getValue());
			Logger.debug("Uptime collected ("+ uptime.seconds+"s)");
			UptimeHistory.create(uptime);
			
		} catch (IOException e) {
			Logger.error("Error when reading hrSystemUptime!");
		}
	}

	private static void collectSystemDescription() {
		try {
			SystemDescriptionHistory sysDescr = new SystemDescriptionHistory();
			sysDescr.id = new Date().getTime();
			sysDescr.date = new Date();
			sysDescr.description = SnmpUtils.SnmpGet(SnmpOid.sysDescr).getValue();
			SystemDescriptionHistory.create(sysDescr);
		} catch (IOException e) {
			Logger.error("Error when reading sysDescr!");
		}
		Logger.debug("sysDescr colllected");
	}

	private static void collectIcmpEchoRequest() {
		try {
			IcmpEchoRequestHistory echoRequest = new IcmpEchoRequestHistory();
			echoRequest.id = new Date().getTime();
			echoRequest.date = new Date();
			echoRequest.ammount = Long.parseLong(SnmpUtils.SnmpGet(SnmpOid.icmpInEchos).getValue()) - IcmpEchoRequestHistory.totalAmmount;
			echoRequest.ammount = echoRequest.ammount < 0 ? 0 : echoRequest.ammount;
			IcmpEchoRequestHistory.create(echoRequest);
		} catch (IOException e) {
			Logger.error("Error when reading icmpInEchos!");
		}
		Logger.debug("icmpInEchos colllected");
	}

	private static void collectTcpSegments() {
		try {
			TcpSegmentsHistory tcpSegment = new TcpSegmentsHistory();
			tcpSegment.id = new Date().getTime();
			tcpSegment.date = new Date();
			tcpSegment.ammount = Long.parseLong(SnmpUtils.SnmpGet(SnmpOid.tcpInSegs).getValue()) + Long.parseLong(SnmpUtils.SnmpGet(SnmpOid.tcpOutSegs).getValue()) - TcpSegmentsHistory.totalAmmount;
			tcpSegment.ammount = tcpSegment.ammount < 0 ? 0 : tcpSegment.ammount;
			TcpSegmentsHistory.create(tcpSegment);
		} catch (IOException e) {
			Logger.error("Error when reading tcpInSegs and tcpOutSegs!");
		}
		Logger.debug("tcpInSegs and tcpOutSegs colllected");
	}

	private static void collectSnmpPackets() {
		try {
			SnmpPacketsHistory snmpPackets = new SnmpPacketsHistory();
			snmpPackets.id = new Date().getTime();
			snmpPackets.date = new Date();
			snmpPackets.ammount = Long.parseLong(SnmpUtils.SnmpGet(SnmpOid.snmpInPkts).getValue()) + Long.parseLong(SnmpUtils.SnmpGet(SnmpOid.snmpOutPkts).getValue()) - SnmpPacketsHistory.totalAmmount;
			snmpPackets.ammount = snmpPackets.ammount < 0 ? 0 : snmpPackets.ammount;
			SnmpPacketsHistory.create(snmpPackets);
		} catch (IOException e) {
			Logger.error("Error when reading snmpInPkts and snmpOutPkts!");
		}
		Logger.debug("snmpInPkts and snmpOutPkts colllected");
	}
}
