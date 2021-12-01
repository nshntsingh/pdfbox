package springprojectfive.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import springprojectfive.model.Organization;

@Component
public class OrganizationDao {

		
		@Autowired
		private HibernateTemplate hibernateTemplate;

		// create
		@Transactional
		public void createOrganization(Organization organization) {

			this.hibernateTemplate.saveOrUpdate(organization);

		}

		// get all products
		public List<Organization> getOrganizations() {
			List<Organization> organizations = this.hibernateTemplate.loadAll(Organization.class);
			return organizations;
		}

		 // delete the single product
		@Transactional
		public void deleteOrganization(int orgid) {
			Organization org = this.hibernateTemplate.load(Organization.class, orgid);
			this.hibernateTemplate.delete(org);
		}

		// get the single product
		public Organization getOrganization(int orid) {
			return this.hibernateTemplate.get(Organization.class, orid);
		}
		
	
		

	}

