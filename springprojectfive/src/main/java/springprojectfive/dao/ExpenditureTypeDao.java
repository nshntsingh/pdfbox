package springprojectfive.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import springprojectfive.model.ExpenditureType;
import springprojectfive.model.User;


@Component
public class ExpenditureTypeDao {

	
		
		@Autowired
		private HibernateTemplate hibernateTemplate;

		// create
		@Transactional
		public void createExpenditureType(ExpenditureType expenditureType) {

			this.hibernateTemplate.saveOrUpdate(expenditureType);

		}

		// get all products
		public List<ExpenditureType> getExpenditureTypes() {
			List<ExpenditureType> expenditureTypes = this.hibernateTemplate.loadAll(ExpenditureType.class);
			return expenditureTypes;
		}
		

	// delete the single product
		@Transactional
		public void deleteExpenditureType(int etid) {
			ExpenditureType et = this.hibernateTemplate.load(ExpenditureType.class, etid);
			this.hibernateTemplate.delete(et);
		}

		// get the single product
		public ExpenditureType getExpenditureType(int etid) {
			return this.hibernateTemplate.get(ExpenditureType.class, etid);
		}
		
		

	}
