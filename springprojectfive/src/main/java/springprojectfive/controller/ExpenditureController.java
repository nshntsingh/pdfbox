package springprojectfive.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import springprojectfive.model.Contribution;
import springprojectfive.model.ContributionType;
import springprojectfive.model.Expenditure;
import springprojectfive.model.ExpenditureType;
import springprojectfive.model.Organization;
import springprojectfive.model.User;
import springprojectfive.dao.ExpenditureDao;
import springprojectfive.dao.ExpenditureTypeDao;
import springprojectfive.dao.OrganizationDao;
import springprojectfive.dao.UserDao;



@Controller
public class ExpenditureController {
	
	@Autowired
	private ExpenditureDao expenditureDao;  
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OrganizationDao organizationDao;
	
	@Autowired
	private ExpenditureTypeDao expenditureTypeDao;
	 
	
	@RequestMapping("/e")
	public String home(Model m)         
	{
	java.util.List<Expenditure> expenditures=expenditureDao.getExpenditures();      
		m.addAttribute("expenditures", expenditures); 
		
		java.util.List<User> users=userDao.getUsers();       // get data of userdao 
		m.addAttribute("users", users); 
		
		java.util.List<Organization> organizations=organizationDao.getOrganizations();      // get data of orgdao 
		m.addAttribute("organizations", organizations); 
		
		java.util.List<ExpenditureType> expenditureTypes=expenditureTypeDao.getExpenditureTypes();      // get data of userdao 
		m.addAttribute("expenditureTypes", expenditureTypes);
		
		return "indexexpenditure";                    
		
	}
	
	//Show add product form
	@RequestMapping("/add-expenditure")
	public String addExpenditure(Model m)
	{
		m.addAttribute("title","Add-expenditure");
		
		java.util.List<User> users=userDao.getUsers();      // get data of userdao 
		m.addAttribute("users", users);
		
		
		java.util.List<Organization> organizations=organizationDao.getOrganizations();      // get data of orgdao 
		m.addAttribute("organizations", organizations);
		
		
		java.util.List<ExpenditureType> expenditureTypes=expenditureTypeDao.getExpenditureTypes();       
		m.addAttribute("expenditureTypes", expenditureTypes);
		
		
		return "add_expenditure_form";                
	}
	
	//handle add product form
	@RequestMapping(value = "/handle-expenditure", method = RequestMethod.POST)
	public RedirectView handleExpenditure(@ModelAttribute Expenditure expenditure,HttpServletRequest request)
	{
		
		System.out.println(expenditure);
		expenditureDao.createExpenditure(expenditure);              // add data in database
		RedirectView redirectView=new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");    
		return redirectView;
	}
	
	
	// delete handle
	@RequestMapping("/e/delete/{expenditureId}")
	public RedirectView deleteExpenditure(@PathVariable("expenditureId") int expenditureId, HttpServletRequest request)
	{
		this.expenditureDao.deleteExpenditure(expenditureId);
		RedirectView redirectView=new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/");
		return redirectView;
	}
	
	
	// update
	
	@RequestMapping("/e/update/{expenditureId}")
	public String updateForm(@PathVariable("expenditureId") int eid,Model model)
	{
		Expenditure expenditure=this.expenditureDao.getExpenditure(eid);
		model.addAttribute("expenditure",expenditure);
		
		java.util.List<User> users=userDao.getUsers();      
		model.addAttribute("users", users);
		
		java.util.List<Organization> organizations=organizationDao.getOrganizations();      
		model.addAttribute("organizations", organizations);
		
		java.util.List<ExpenditureType> expenditureTypes=expenditureTypeDao.getExpenditureTypes();      
		model.addAttribute("expenditureTypes", expenditureTypes);
		
		return "update_expenditure_Form";
	}
	

	 // create pdf
    @RequestMapping(value="/expenditurePdf")    
    public String downLoadPdf() { 
	
    	// A new PDDocument is created
	    PDDocument doc = new PDDocument();
	   
        
	    //A new page is created and added to the document.
	    PDPage myPage = new PDPage();
        doc.addPage(myPage);
      
       
       // To write to a PDF page, we have to create a PDPageContentStream object. 
       try
       {
        PDPageContentStream cont = new PDPageContentStream(doc, myPage);
   
         //Text is written between beginText() and endText() methods.
           cont.beginText();
       
            cont.setFont(PDType1Font.TIMES_ROMAN, 12);
            cont.setLeading(14.5f);

            cont.newLineAtOffset(25, 700);
       
            
            String headerline="user"+ "  " +"organization"+"  "+"Amount" +"  "+"ExpenditureDate"+"  "+"PurposeofDisnursement"+"  "+"ExpenditureType"+"  "+"Createdate"+"  "+"Updatedate";
            cont.showText(headerline);
			cont.newLine();
         
            
            
        	java.util.List<Expenditure> expenditures=expenditureDao.getExpenditures();      // get data of userdao 
           
        	for( Expenditure expenditure :expenditures){
        		
        		
        		String user1="";
				String organization1="";
				String expenditureType1="";
				
				
				java.util.List<User> users = userDao.getUsers();
	 			java.util.List<Organization> organizations = organizationDao.getOrganizations();
	 			java.util.List<ExpenditureType> expenditureTypes = expenditureTypeDao.getExpenditureTypes();

	 		
	 			for(User user:users)
	 			{
	 				if(user.getId()==expenditure.getUser())
	 				{
	 				   user1= user.getFirstname();
	 				} 			
	 			}
	 			
	 			
	 	 		for(Organization organization:organizations)
	 	 			{
	 	 				if(organization.getId()== expenditure.getOrganization())
	 	 				{
	 	 					organization1=organization.getName();
	 	 				}
	 	 			}
	 	 	 	for(ExpenditureType expenditureType:expenditureTypes)
	 	 	 	{
	 	 	 		if(expenditureType.getId()==expenditure.getExpenditureType())
	 	 	 		{
	 	 	 			expenditureType1=expenditureType.getName();
	 	 	 		}
	 	 	 	}

       String line1=user1+"   "+organization1+"  "+expenditure.getAmount()+"  "+expenditure.getExpendituredate()+"  "+expenditure.getPurposeofdisbursement()+"  "+expenditureType1+"  "+expenditure.getCreatedate()+"  "+expenditure.getUpdatedate();
            	 
                 cont.showText(line1);
                 cont.newLine();
          }
			              
           cont.endText();
         
           //Closing the contentStream
           cont.close();
          
         
           
               doc.save("e://Pdf3//expenditurePdf.pdf");
          
               doc.close();
       }
       catch(Exception e)
       {
    	   e.printStackTrace();
       }
        return "expenditurePdf";
}
    


 	// for expenditure excel
 	@RequestMapping(value = "/expenditureExcel")
 	public String downLoadExcelExpenditure() {

 		// Blank workbook
 		XSSFWorkbook workbook = new XSSFWorkbook();

 		// Create a blank sheet
 		XSSFSheet sheet = workbook.createSheet("Expenditure Data");

 		// This data needs to be written (Object[])
 		Map<String, Object[]> data = new TreeMap<String, Object[]>();

 		data.put("1", new Object[] { "ID", "user", "Organization Name", "Amount","Expenditure Date","purpose of disbursement","Expenditure Type"," create date","update date"});
 	

 		java.util.List<Expenditure> expenditures = expenditureDao.getExpenditures();
 		

 		int keyIndex = 2;
 		for (Expenditure exp1 : expenditures) {
 			
 			String user2="";
			String organization2="";
			String expenditureType2="";
			
			
			java.util.List<User> users = userDao.getUsers();
 			java.util.List<Organization> organizations = organizationDao.getOrganizations();
 			java.util.List<ExpenditureType> expenditureTypes = expenditureTypeDao.getExpenditureTypes();

 		
 			for(User user:users)
 			{
 				if(user.getId()==exp1.getUser())
 				{
 				   user2= user.getFirstname();
 				} 			
 			}
 			
 			
 	 		for(Organization organization:organizations)
 	 			{
 	 				if(organization.getId()== exp1.getOrganization())
 	 				{
 	 					organization2=organization.getName();
 	 				}
 	 			}
 	 		
 	 	 	for(ExpenditureType expenditureType:expenditureTypes)
 	 	 	{
 	 	 		if(expenditureType.getId()==exp1.getExpenditureType())
 	 	 		{
 	 	 			expenditureType2=expenditureType.getName();
 	 	 		}
 	 	 	}

 	
 	 			
 			data.put(keyIndex + "",
 					new Object[] { exp1.getId(), user2, organization2,String.valueOf(exp1.getAmount()),exp1.getExpendituredate(),exp1.getPurposeofdisbursement(),expenditureType2,exp1.getCreatedate(),
 							exp1.getUpdatedate() });
 			keyIndex++;
 		}

 		System.out.println(data);

 		// Iterate over data and write to sheet
 		java.util.Set<String> keyset = data.keySet();
 		int rownum = 0;
 		for (String key : keyset) {
 			XSSFRow row = sheet.createRow(rownum++);
 			Object[] objArr = data.get(key);
 			int cellnum = 0;
 			for (Object obj : objArr) {
 				Cell cell = ((org.apache.poi.ss.usermodel.Row) row).createCell(cellnum++);
 				if (obj instanceof String)
 					cell.setCellValue((String) obj);
 				else if (obj instanceof Integer)
 					cell.setCellValue((Integer) obj);
 			}
 		}
 		try {
 			// Write the workbook in file system
 			FileOutputStream out = new FileOutputStream(new File("e://Pdf3//expenditureExcel.xlsx"));
 			workbook.write(out);
 			out.close();
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		return "expenditureExcel";
 	}
}
