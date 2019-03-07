package com.lnt.services.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProjectDetails {

	@Value("${project.name}")
	private String projectName;
	
	@Value("${cmdc.ipAddrres}")
	private String cmdcIpAddress;
	
	@Value("${cmdc.server.port.number}")
	private String cmdcServerPortNumber;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCmdcIpAddress() {
		return cmdcIpAddress;
	}

	public void setCmdcIpAddress(String cmdcIpAddress) {
		this.cmdcIpAddress = cmdcIpAddress;
	}

	public String getCmdcServerPortNumber() {
		return cmdcServerPortNumber;
	}

	public void setCmdcServerPortNumber(String cmdcServerPortNumber) {
		this.cmdcServerPortNumber = cmdcServerPortNumber;
	}

}