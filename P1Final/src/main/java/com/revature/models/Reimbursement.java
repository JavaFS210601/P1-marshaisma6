package com.revature.models;

import java.sql.Blob;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Reimbursement {
	
	private int rId;
	private int amount;
	private String timeSubmission;
	private String reimb_resolved;
	private String description;
	private String email;
	private String password;
	private String fname;
	private String lname;
	private Blob image;
	private int submissionUId; //Aka the person who submitted it / author
	private int resolvedUId; //Aka the person who resolved it
	private int requestStatusId;
	private int requestTypeId;
	private int position_Id;
	private String rq_type;
	private String requestStatus;
	
	
	
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public Reimbursement(int rId, int amount, String timeSubmission, String reimb_resolved, String description,
			Blob image, int submissionUId, int resolvedUId, int requestStatusId, int requestTypeId) {
		super();
		this.rId = rId;
		this.amount = amount;
		this.timeSubmission = timeSubmission;
		this.reimb_resolved = reimb_resolved;
		this.description = description;
		this.image = image;
		this.submissionUId = submissionUId;
		this.resolvedUId = resolvedUId;
		this.requestStatusId = requestStatusId;
		this.requestTypeId = requestTypeId;
	}
	
	

	public Reimbursement(int rId, int amount, String timeSubmission, String reimb_resolved, String description,
			String email, String password, String fname, String lname, Blob image, int submissionUId, int resolvedUId,
			int requestStatusId, int requestTypeId, int position_Id, String rq_type, String requestStatus) {
		super();
		this.rId = rId;
		this.amount = amount;
		this.timeSubmission = timeSubmission;
		this.reimb_resolved = reimb_resolved;
		this.description = description;
		this.email = email;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.image = image;
		this.submissionUId = submissionUId;
		this.resolvedUId = resolvedUId;
		this.requestStatusId = requestStatusId;
		this.requestTypeId = requestTypeId;
		this.position_Id = position_Id;
		this.rq_type = rq_type;
		this.requestStatus = requestStatus;
	}

	
	
	
	
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getPosition_Id() {
		return position_Id;
	}

	public void setPosition_Id(int position_Id) {
		this.position_Id = position_Id;
	}

	public String getRq_type() {
		return rq_type;
	}

	public void setRq_type(String rq_type) {
		this.rq_type = rq_type;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}


	


	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getTimeSubmission() {
		return timeSubmission;
	}

	public void setTimeSubmission(String timeSubmission) {
		this.timeSubmission = timeSubmission;
	}

	public String getReimb_resolved() {
		return reimb_resolved;
	}

	public void setReimb_resolved(String reimb_resolved) {
		this.reimb_resolved = reimb_resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public int getSubmissionUId() {
		return submissionUId;
	}

	public void setSubmissionUId(int submissionUId) {
		this.submissionUId = submissionUId;
	}

	public int getResolvedUId() {
		return resolvedUId;
	}

	public void setResolvedUId(int resolvedUId) {
		this.resolvedUId = resolvedUId;
	}

	public int getRequestStatusId() {
		return requestStatusId;
	}

	public void setRequestStatusId(int requestStatusId) {
		this.requestStatusId = requestStatusId;
	}

	public int getRequestTypeId() {
		return requestTypeId;
	}

	public void setRequestTypeId(int requestTypeId) {
		this.requestTypeId = requestTypeId;
	}

	
	
	@Override
	public String toString() {
		return "Reimbursement [rId=" + rId + ", amount=" + amount + ", timeSubmission=" + timeSubmission
				+ ", reimb_resolved=" + reimb_resolved + ", description=" + description + ", image=" + image
				+ ", submissionUId=" + submissionUId + ", resolvedUId=" + resolvedUId + ", requestStatusId="
				+ requestStatusId + ", requestTypeId=" + requestTypeId + "]";
	} 
	
	
	
}

