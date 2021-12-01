package springprojectfive.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import springprojectfive.dao.OrganizationDao;
import springprojectfive.model.Organization;


@Controller
public class OrganizationController {
	
	
	@Autowired
	private OrganizationDao organizationDao;
	
	@RequestMapping("/org")
	public String home(Model m)         // model object is used to add data to view page
	{
	java.util.List<Organization> organizations=organizationDao.getOrganizations();      // get data of orgdao 
		m.addAttribute("organizations", organizations);    // add data in ("key",value)pair
		return "indexorganization";                    // return jsp page
		
	}
	
	
	//Show add organization form
		@RequestMapping("/add-organization")
		public String addOrganization(Model m)
		{
			m.addAttribute("title","Add-organization");    // value given with help of "title"
			return "add_organization_form";                // return jsp page
		}
		
		
		//handle add organization form
		@RequestMapping(value ="handle-organization", method = RequestMethod.POST)
		public RedirectView handleOrganization(@ModelAttribute Organization organization,HttpServletRequest request)
		{
			
			System.out.println(organization);
			organizationDao.createOrganization(organization);                                // add data in database
			RedirectView redirectView=new RedirectView();
			redirectView.setUrl(request.getContextPath() + "/");    
			return redirectView;
		}
		
		
	// delete handle
		@RequestMapping("/org/delete/{orgId}")
		public RedirectView deleteOrganization(@PathVariable("orgId") int orgId, HttpServletRequest request)
		{
			this.organizationDao.deleteOrganization(orgId);
			RedirectView redirectView=new RedirectView();
			redirectView.setUrl(request.getContextPath()+"/org");
			return redirectView;
		}
		
		@RequestMapping("/org/update/{organizationId}")
		public String organizationForm(@PathVariable("organizationId") int oreid,Model model)
		{
			Organization organization=this.organizationDao.getOrganization(oreid);
			model.addAttribute("organization",organization);
			return "update_organization_Form";
		}
		
		

		
		
		 // create pdf
        @RequestMapping(value="/organizationPdf")    
        public String downLoadPdf() { 
		
        	// A new PDDocument is created
    	    PDDocument doc1 = new PDDocument();
    	   
            
    	    //A new page is created and added to the document.
    	    PDPage myPage1 = new PDPage();
            doc1.addPage(myPage1);
          
           
           // To write to a PDF page, we have to create a PDPageContentStream object. 
           try
           {
            PDPageContentStream cont1 = new PDPageContentStream(doc1, myPage1);
       
             //Text is written between beginText() and endText() methods.
               cont1.beginText();
           
                cont1.setFont(PDType1Font.TIMES_ROMAN, 12);
                cont1.setLeading(14.5f);

                cont1.newLineAtOffset(25, 700);
                
            	java.util.List<Organization> organizations=organizationDao.getOrganizations();      // get data of userdao 
                for( Organization organization :organizations){
                	  // access foo here

                	 String line1=organization.getName()+"   "+organization.getCreatedate()+"  "+organization.getUpdatedate()+"  ";
                     cont1.showText(line1);
                     cont1.newLine();
              }
				              
               cont1.endText();
             
               //Closing the contentStream
               cont1.close();
              
               
              
                   doc1.save("e://Pdf1//organizationPdf.pdf");
              
                   doc1.close();
           }
           catch(Exception e)
           {
        	   e.printStackTrace();
           }
            return "organizationPdf";    

}
}
