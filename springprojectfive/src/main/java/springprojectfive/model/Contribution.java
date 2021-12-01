package springprojectfive.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Contribution {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int user;

	private int organization;

	private long amount;

	private String contributedDate;

	private String purposeofdisbursement;

	private int contributionType;

	private String createdate=new Date().toString();
	private String updatedate=new Date().toString();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getOrganization() {
		return organization;
	}

	public void setOrganization(int organization) {
		this.organization = organization;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getContributedDate() {
		return contributedDate;
	}

	public void setContributedDate(String contributedDate) {
		this.contributedDate = contributedDate;
	}

	public String getPurposeofdisbursement() {
		return purposeofdisbursement;
	}

	public void setPurposeofdisbursement(String purposeofdisbursement) {
		this.purposeofdisbursement = purposeofdisbursement;
	}

	public int getContributionType() {
		return contributionType;
	}

	public void setContributionType(int contributionType) {
		this.contributionType = contributionType;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	
	
	@Override
	public String toString() {
		return "Contribution [id=" + id + ", user=" + user + ", organization=" + organization + ", amount=" + amount
				+ ", contributedDate=" + contributedDate + ", purposeofdisbursement=" + purposeofdisbursement
				+ ", contributionType=" + contributionType + ", createdate=" + createdate + ", updatedate=" + updatedate
				+ "]";
	}
	
	

	public Contribution(int id, int user, int organization, long amount, String contributedDate,
			String purposeofdisbursement, int contributionType, String createdate, String updatedate) {
		super();
		this.id = id;
		this.user = user;
		this.organization = organization;
		this.amount = amount;
		this.contributedDate = contributedDate;
		this.purposeofdisbursement = purposeofdisbursement;
		this.contributionType = contributionType;
		this.createdate = createdate;
		this.updatedate = updatedate;
	}

	public Contribution() {
		super();
		// TODO Auto-generated constructor stub
	}

}
