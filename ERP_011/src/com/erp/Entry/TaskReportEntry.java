package com.erp.Entry;

import org.json.JSONException;
import org.json.JSONObject;

public class TaskReportEntry {
	ReportEntry reportEntry;
	AdviceEntry superEntry, leaderEntry;
	
	public TaskReportEntry() {
		// TODO Auto-generated constructor stub
		reportEntry = null;
		superEntry = null;
		leaderEntry = null;
	}
	
	public ReportEntry getReportEntry() {
		return reportEntry;
	}


	public void setReportEntry(ReportEntry reportEntry) {
		this.reportEntry = reportEntry;
	}


	public AdviceEntry getSuperEntry() {
		return superEntry;
	}


	public void setSuperEntry(AdviceEntry superEntry) {
		this.superEntry = superEntry;
	}


	public AdviceEntry getLeaderEntry() {
		return leaderEntry;
	}


	public void setLeaderEntry(AdviceEntry leaderEntry) {
		this.leaderEntry = leaderEntry;
	}


	public JSONObject write2Json() throws JSONException {
		JSONObject json = new JSONObject();
		if(reportEntry != null){
			json.put("report", reportEntry.write2Json());
		}
		if(superEntry != null){
			json.put("super", superEntry.write2Json());			
		}
		if(leaderEntry != null){
			json.put("leader", leaderEntry.write2Json());			
		}
		return json;
	}
}
