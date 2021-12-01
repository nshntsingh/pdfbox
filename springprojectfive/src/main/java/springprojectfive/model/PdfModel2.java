package springprojectfive.model;


import javax.persistence.Entity;


@Entity
public class PdfModel2 {

	
	    String user;	
	    int userId;
	    
	    String name;
		String expendituredate;
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
		public String getExpendituredate() {
			return expendituredate;
		}
		public void setExpendituredate(String expendituredate) {
			this.expendituredate = expendituredate;
		}
		public long getAmount() {
			return amount;
		}
		public void setAmount(long amount) {
			this.amount = amount;
		}
		public PdfModel2(String user, int userId, String name, String expendituredate, long amount) {
			super();
			this.user = user;
			this.userId = userId;
			this.name = name;
			this.expendituredate = expendituredate;
			this.amount = amount;
		}
		public PdfModel2() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "PdfModel2 [user=" + user + ", userId=" + userId + ", name=" + name + ", expendituredate="
					+ expendituredate + ", amount=" + amount + "]";
		}
		
		
		
		

	}

