package springprojectfive.controller;

	import javax.servlet.http.HttpServletRequest;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;

	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.servlet.view.RedirectView;

	import springprojectfive.dao.ContributionTypeDao;
	import springprojectfive.model.ContributionType;
import springprojectfive.model.User;

	@Controller
	public class ContributionTypeController {
		
		@Autowired
		private ContributionTypeDao contributionTypeDao;
		
		@RequestMapping("/ct")
		public String home(Model m)         // model object is used to add data to view page
		{
			java.util.List<ContributionType> contributionTypes=contributionTypeDao.getContributionTypes();      // get data of userdao 
			m.addAttribute("contributionTypes", contributionTypes);    // add data in ("key",value)pair
			return "indexcontributiontype";                    // return jsp page
			
		}
		
		
		//Show add user form
			@RequestMapping("/add-contributionType")
			public String addContributionType(Model m)
			{
				m.addAttribute("title","Add-contributionType");    // value given with help of "title"
				return "add_contributionType_form";                // return jsp page
			}
			
			
			//handle add user form
			@RequestMapping(value = "/handle-contributionType", method = RequestMethod.POST)
			public RedirectView handleContributionType(@ModelAttribute ContributionType contributionType, HttpServletRequest request)
			{
				
				System.out.println(contributionType);
				contributionTypeDao.createContributionType(contributionType);                                // add data in database
				RedirectView redirectView=new RedirectView();
				redirectView.setUrl(request.getContextPath() + "/");    
				return redirectView;
			}
			
			
/*			// delete handle
			@RequestMapping("/delete/{contributionTypeId}")
			public RedirectView deleteContributionType(@PathVariable("contributionTypeId") int contributionTypeId, HttpServletRequest request)
			{
				this.contributionTypeDao.deleteContributionType(contributionTypeId);
				RedirectView redirectView=new RedirectView();
				redirectView.setUrl(request.getContextPath()+"/");
				return redirectView;
			}
			
			@RequestMapping("/contributionType/{contributionTypeId}")
			public String contributionTypeForm(@PathVariable("contributionTypeId") int ctid,Model model)
			{
				ContributionType contributionType=this.contributionTypeDao.getContributionTypes(ctid);
				model.addAttribute("contributionType",contributionType);
				return "update_Form";
			}
			
			
	*/		


	}

	
