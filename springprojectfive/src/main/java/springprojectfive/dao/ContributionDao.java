package springprojectfive.dao;

	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.orm.hibernate5.HibernateTemplate;
	import org.springframework.stereotype.Component;
	import org.springframework.transaction.annotation.Transactional;

	import springprojectfive.model.Contribution;
import springprojectfive.model.Expenditure;
import springprojectfive.model.User;

@Component
public class ContributionDao {


		@Autowired
		private HibernateTemplate hibernateTemplate;

		// create
		@Transactional
		public void createContribution(Contribution contribution) {

	     	this.hibernateTemplate.saveOrUpdate(contribution);

			}

		// get all products
		public List<Contribution> getContributions() {
			List<Contribution> contributions = this.hibernateTemplate.loadAll(Contribution.class);
//			System.out.println("contributions"+contributions);
			return contributions;
		}

		// delete the single product
		
		@Transactional
		public void deleteContribution(int cid) {
			Contribution c = this.hibernateTemplate.load(Contribution.class, cid);
			this.hibernateTemplate.delete(c);
		}

		// get the single product
		    public Contribution getContributions(int cid) {
		    	
			  return this.hibernateTemplate.get(Contribution.class, cid);
		}
		    

		    
		    
	}

		


	

