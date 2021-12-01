package springprojectfive.model;

import javax.persistence.Entity;

@Entity
public class PdfModel {
	
	String user;	
	int userId;
	String name;
	String contributedDate;
	long amount;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContributedDate() {
		return contributedDate;
	}
	public void setContributedDate(String contributedDate) {
		this.contributedDate = contributedDate;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public PdfModel(String user, int userId, String name, String contributedDate, long amount) {
		super();
		this.user = user;
		this.userId = userId;
		this.name = name;
		this.contributedDate = contributedDate;
		this.amount = amount;
	}
	public PdfModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PdfModel [user=" + user + ", userId=" + userId + ", name=" + name + ", contributedDate="
				+ contributedDate + ", amount=" + amount + "]";
	}

	
	
	
	
	
	
	

}
