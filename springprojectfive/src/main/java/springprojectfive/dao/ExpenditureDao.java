
	package springprojectfive.dao;


	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.orm.hibernate5.HibernateTemplate;
	import org.springframework.stereotype.Component;
	import org.springframework.transaction.annotation.Transactional;

import springprojectfive.model.Contribution;
import springprojectfive.model.Expenditure;


	@Component
	public class ExpenditureDao {

		
			
			@Autowired
			private HibernateTemplate hibernateTemplate;

			// create
			@Transactional
			public void createExpenditure(Expenditure expenditure) {

				this.hibernateTemplate.saveOrUpdate(expenditure);

			}

			// get all products
			public List<Expenditure> getExpenditures() {
				List<Expenditure> expenditures = this.hibernateTemplate.loadAll(Expenditure.class);
				return expenditures;
			}

			// delete the single product
			@Transactional
			public void deleteExpenditure(int eid) {
				Expenditure e = this.hibernateTemplate.load(Expenditure.class, eid);
				this.hibernateTemplate.delete(e);
			}

	  	    // get the single product
			public Expenditure getExpenditure(int eid) {
				return this.hibernateTemplate.get(Expenditure.class, eid);
			}
			
			 // get the single product in list with particular id
		    public List<Expenditure> getExpenditureDatainList(int eid) {
			    List<Expenditure> expiList = (List<Expenditure>) this.hibernateTemplate.get(Expenditure.class, eid);
	                    return expiList;	
		    }  
			
			

		}
