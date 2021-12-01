package springprojectfive.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import springprojectfive.model.ContributionType;
import springprojectfive.model.User;


@Component
public class ContributionTypeDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	// create
	@Transactional
	public void createContributionType(ContributionType contributionType) {

		this.hibernateTemplate.saveOrUpdate(contributionType);

	}

	// get all products
	// get all products
		public List<ContributionType> getContributionTypes() {
			List<ContributionType> contributionTypes = this.hibernateTemplate.loadAll(ContributionType.class);
			return contributionTypes;
		}

/*	// delete the single product
	@Transactional
	public void deleteContributionType(int ctid) {
		ContributionType ct = this.hibernateTemplate.load(ContributionType.class, ctid);
		this.hibernateTemplate.delete(ct);
	}

	// get the single product
	public ContributionType getContributionTypes(int ctid) {
		return this.hibernateTemplate.get(ContributionType.class, ctid);
	}
	
	*/


}
