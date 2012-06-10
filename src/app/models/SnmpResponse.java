package models;

import org.snmp4j.PDU;

public class SnmpResponse {

	private int requestId;
	private int errorIndex;
	private int errorStatus;
	private String oid;
	private String value;

	public SnmpResponse(PDU response){
		this.requestId = response.getRequestID().toInt();
		this.errorIndex = response.getErrorIndex();
		this.errorStatus = response.getErrorStatus();
		String[] responseValue = response.getVariableBindings().get(0).toString().split(" = ");

		this.oid = responseValue[0];
		this.value = responseValue[1];
	}
	
	public int getRequestId() {
		return requestId;
	}

	public int getErrorIndex() {
		return errorIndex;
	}

	public int getErrorStatus() {
		return errorStatus;
	}

	public String getValue() {
		return value;
	}

	public String getOid() {
		return oid;
	}

}
