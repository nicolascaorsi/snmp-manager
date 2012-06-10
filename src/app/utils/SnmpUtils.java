package utils;
import java.io.IOException;

import models.SnmpResponse;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;


public class SnmpUtils {
	
	public static SnmpResponse SnmpGet(String oid) throws IOException {
		// 1. create SNMP session
		TransportMapping transport = new DefaultUdpTransportMapping();
		transport.listen();

		// 2. set the target which will be requested
		CommunityTarget target = new CommunityTarget();
		target.setCommunity(new OctetString("private"));
		target.setVersion(SnmpConstants.version1);
		target.setAddress(new UdpAddress("localhost/161"));

		// 3. create the PDU of the message. In this case a GET
		PDU request = new PDU();
		request.add(new VariableBinding(new OID(oid)));
		request.setType(PDU.GET);
		request.setRequestID(new Integer32(1));

		// 4. print output
		Snmp snmp = null;
		try {
			snmp = new Snmp(transport);
			return new SnmpResponse(snmp.get(request, target).getResponse());
		} finally {
			if (snmp != null)
				snmp.close();
			transport.close();
		}
	}
	
	public static long TimeInformationToSeconds(String info){
		//TODO Tratar para dias
		if(info.indexOf(':') > 0){
			String[] data = info.split(":");
			long hours = Long.parseLong(data[0]);
			long minutes = Long.parseLong(data[1]);
			long seconds = Long.parseLong(data[2].split("[.]")[0]);
			long milliseconds = Long.parseLong(data[2].split("[.]")[1]);
			return  (hours * 3600000) + (minutes * 60000) + (seconds * 1000) + milliseconds;
		}else{
		
		/*
		System.out.println(info.substring(0, info.indexOf("day")));
		System.out.println(info.substring(0, info.indexOf("day")));
		System.out.println(info.substring(info.indexOf(" "), info.indexOf(":")));
		System.out.println(info.substring(info.indexOf(':'), info.lastIndexOf(':')));
		System.out.println(info.substring(info.lastIndexOf(':'), info.indexOf('.')));
		System.out.println(info.substring(info.indexOf('.')));
/*		long days = Long.parseLong(info.substring(0, info.indexOf("day")));
		long hours = Long.parseLong(info.substring(info.indexOf(" "), info.indexOf(":")));
		long minutes = Long.parseLong(info.substring(info.indexOf(':'), info.lastIndexOf(':')));
		long seconds = Long.parseLong(info.substring(info.lastIndexOf(':'), info.indexOf('.')));
		long milliseconds = Long.parseLong(info.substring(info.indexOf('.')));
	*/	
		}
		return 1;//(days * 86400000) + (hours * 3600000) + (minutes * 60000) + (seconds * 1000) + milliseconds;
	}
	
}
