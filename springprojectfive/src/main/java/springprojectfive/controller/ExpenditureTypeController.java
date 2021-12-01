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

import springprojectfive.model.ExpenditureType;
import springprojectfive.model.User;
import springprojectfive.dao.ExpenditureTypeDao;



@Controller
public class ExpenditureTypeController {
	
	@Autowired
	private ExpenditureTypeDao expenditureTypeDao;                                
	 
	
	@RequestMapping("/et")
	public String home(Model m)         // model object is used to add data to view page
	{
	java.util.List<ExpenditureType> expenditureTypes=expenditureTypeDao.getExpenditureTypes();      // get data of userdao 
		m.addAttribute("expenditureTypes", expenditureTypes);    // add data in ("key",value)pair
		return "indexexpenditureType";                    // return jsp page
		
	}
	
	
	//Show add user form
		@RequestMapping("/add-expenditureType")
		public String addExpenditure(Model m)
		{
			m.addAttribute("title","Add-expenditureType");    // value given with help of "title"
			return "add_expenditureType_form";                // return jsp page
		}
		
		
		//handle add user form
		@RequestMapping(value = "/handle-expenditureType", method = RequestMethod.POST)
		public RedirectView handleExpenditureType(@ModelAttribute ExpenditureType expenditureType,HttpServletRequest request)
		{
			
			System.out.println(expenditureType);
			expenditureTypeDao.createExpenditureType(expenditureType);                                // add data in database
			RedirectView redirectView=new RedirectView();
			redirectView.setUrl(request.getContextPath() + "/");    
			return redirectView;
		}
		
/*	// delete handle
	@RequestMapping("/delete/{expenditureTypeId}")
	public RedirectView deleteExpenditureType(@PathVariable("expenditureTypeId") int expenditureTypeId, HttpServletRequest request)
	{
		this.expenditureTypeDao.deleteExpenditureType(expenditureTypeId);
		RedirectView redirectView=new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/");
		return redirectView;
	}
	
	@RequestMapping("/update/{expenditureTypeId}")
	public String updateForm(@PathVariable("expenditureTypeId") int etid,Model model)
	{
		ExpenditureType expenditureType=this.expenditureTypeDao.getExpenditureType(etid);
		model.addAttribute("expenditureType",expenditureType);
		return "update_Form";
	}
*/
}
