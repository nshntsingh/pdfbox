package springprojectfive.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExpenditureType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;
	private String createdate=new Date().toString();
	private String updatedate=new Date().toString();

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	
	public ExpenditureType(int id, String name, String createdate, String updatedate) {
		super();
		this.id = id;
		this.name = name;
		this.createdate = createdate;
		this.updatedate = updatedate;
	}

	public ExpenditureType() {
		super();
		// TODO Auto-generated constructor stub
	}
}
