package springprojectfive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springprojectfive.dao.ContributionDao;
import springprojectfive.dao.ExpenditureDao;
import springprojectfive.dao.OrganizationDao;
import springprojectfive.dao.UserDao;
import springprojectfive.model.Contribution;
import springprojectfive.model.ContributionType;
import springprojectfive.model.Expenditure;
import springprojectfive.model.Organization;
import springprojectfive.model.PdfModel;
import springprojectfive.model.PdfModel2;
import springprojectfive.model.User;

@Controller
public class NewPdfController {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private OrganizationDao organizationDao;

	@Autowired
	private ContributionDao contributionDao;

	@Autowired
	private ExpenditureDao expenditureDao;

	@RequestMapping(value = "/newPdf")
	public String editPdf(Model m) {

		java.util.List<Organization> organizations = organizationDao.getOrganizations(); // get data of orgdao
		m.addAttribute("organizations", organizations);

		return "pdf";
	}


	@RequestMapping(path="/handle", method=RequestMethod.POST)
	public String handle(Model m,@RequestParam("organization") int organizationId,@RequestParam("startDate") String selectedStartDate,
			@RequestParam("endDate") String selectedEndDate)
	{
		List<PdfModel > listContributionName=new ArrayList<PdfModel>();

		String username="";
		int userId=0;
		
		java.util.List<Contribution> contributions = contributionDao.getContributions(); // get data of userdao
		for (Contribution contribution : contributions) {
			
 			java.util.List<Organization> organizations = organizationDao.getOrganizations();

 	 		for(Organization organization:organizations)
 	 			{
 	 				if(organization.getId()== contribution.getOrganization())
 	 				{
 	 					
 	 					
 	 					java.util.List<User> users = userDao.getUsers();
 	 					
 	 					for(User user:users)
 	 					{
 	 						if(user.getId()==contribution.getUser())
 	 							
 	 							 username=user.getFirstname() + "" +user.getLastname();
 	 						
 	 						if(user.getId()==contribution.getUser())
 	 							
 	 							userId=user.getId();
 	 					}
 	 					
 	 			PdfModel pdfModel=new PdfModel(username,userId,organization.getName(),contribution.getContributedDate(),contribution.getAmount());		
 	 					listContributionName.add(pdfModel); 	
 	 				}
 	 			}
// 	 		System.out.println("listContributionName======================================="+listContributionName);
		}
		

		
		
		List<PdfModel2 > listExpenditureName=new ArrayList<PdfModel2>();

		
		java.util.List<Expenditure> expenditures = expenditureDao.getExpenditures(); // get data of userdao
		for (Expenditure expenditure : expenditures) {
			
 			java.util.List<Organization> organizations = organizationDao.getOrganizations();

 	 		for(Organization organization:organizations)
 	 			{
 	 				if(organization.getId()== expenditure.getOrganization())
 	 				{
 	 					System.out.println("heloooooooo");
 	 					java.util.List<User> users = userDao.getUsers();

 	 					for(User user:users)
 	 					{
 	 						if(user.getId()==expenditure.getUser())
 	 							
 	 							 username=user.getFirstname() + "" +user.getLastname();
 	 						
 	 						if(user.getId()==expenditure.getUser())
 	 							
 	 							userId=user.getId();
 	 					}
 	 					
 	 					
 	 			PdfModel2 pdfModel2=new PdfModel2(username,userId,organization.getName(),expenditure.getExpendituredate(),expenditure.getAmount());		
 	 			listExpenditureName.add(pdfModel2); 	
 	 				}
 	 			}
	 		System.out.println("listExpenditureName======================"+listExpenditureName);
		}
	

		

		Pdf pdf=new Pdf();
		
	
		String orgname="";
		try
		{
			double contributedTotalPeriodAmount=pdf.toGetTotalPeriodAmountController(listContributionName, selectedStartDate, selectedEndDate);
			double contributedTotalCumaltiveAmount=pdf.toGetTotalCumalltiveAmountContribution(listContributionName, selectedStartDate, selectedEndDate);
			double expendituredTotalPeriodAmount=pdf.toGetTotalPeriodAmountExpenditure(listExpenditureName, selectedStartDate, selectedEndDate);
			double expenditiredTotalPeriodCumlativeAmount=pdf.toGetTotalCumalltiveAmountExpenditure(listExpenditureName, selectedStartDate, selectedEndDate);
		
 			   
			Organization organizations=this.organizationDao.getOrganization(organizationId);
           			
     
	        
	        
	        String contributedTotalPeriodAmountString=String.valueOf(contributedTotalPeriodAmount);
	        String contributedTotalCumaltiveAmountString=String.valueOf(contributedTotalCumaltiveAmount);
	        String expendituredTotalPeriodAmountString=String.valueOf(expendituredTotalPeriodAmount);
	        String expenditiredTotalPeriodCumlativeAmountString=String.valueOf(expenditiredTotalPeriodCumlativeAmount);
	        
	        HashMap<String,String> finalData=new HashMap<String,String>();
	        
	        finalData.put("ssd",selectedStartDate);
	        finalData.put("sed",selectedEndDate);
	        finalData.put("ctps",contributedTotalPeriodAmountString);
	        finalData.put("ctcas",contributedTotalCumaltiveAmountString);
	        finalData.put("etpas",expendituredTotalPeriodAmountString);
	        finalData.put("etpcas",expenditiredTotalPeriodCumlativeAmountString);
	        finalData.put("orgname",organizations.getName());
	        
	        File file401=new File("E:\\PdfMap\\401.pdf");
	        PDDocument document401=PDDocument.load(file401);
	        document401.save("E:\\PdfMap\\402.pdf");
	        document401.close();
	        
	      
	       
	        for(String key: finalData.keySet())
	        {
	        	
	        File file =new File("E:\\PdfMap\\402.pdf");
	  	       PDDocument document=PDDocument.load(file);
	  	       
	  	       
	        
	        PDPage page=document.getPage(2);
	        PDPageContentStream contentStream=new PDPageContentStream(document,page, AppendMode.APPEND,true,true);
           contentStream.beginText();
           
           contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

           if(key == "ssd")
           {
        	   contentStream.newLineAtOffset(435, 503);
           }
           else if(key=="sed")
           {
        	   contentStream.newLineAtOffset(435, 480);
           }
           else if(key=="ctps")
           {
        	   contentStream.newLineAtOffset(430, 240);
           }
           else if(key=="ctcas")
           {
        	   contentStream.newLineAtOffset(550, 240);
           }
           else if(key=="orgname")
           {
        	   contentStream.newLineAtOffset(90, 420);
           }
           else if(key=="etpas")
           {
        	   contentStream.newLineAtOffset(430, 215);
           }
           else if(key=="etpcas")
           {
        	   contentStream.newLineAtOffset(550, 215);
           }
           
           contentStream.showText(finalData.get(key));
           contentStream.endText();
           contentStream.close();
           
           
           document.save("E:\\PdfMap\\402.pdf");
           document.close();
           
		}
	        
	        
	        
	        // for page number 5
	        
	        List<Map<String ,String>> contributionData=pdf.page2print(listContributionName, selectedStartDate, selectedEndDate);
	        
//	        System.out.println("contributionData=============="+contributionData);
	        int continueSheetCount=0; 
	        int count=0;
	        int y=350;
	        int pagenumber=4;
	        int pageRow=3;
	        
	        for(Map<String,String> obj : contributionData)
	        {
	        	
//	        	System.out.println("obj============"+obj);
	        	if(count==pageRow)
	        	{
	        		continueSheetCount++;
	        		pageRow=4;
	        		y=350;
	        		count=0;
	        		
	        		File file1=new File("E:\\PdfMap\\402.pdf");
	        		File file2=new File("E:\\PdfEditor\\Sample\\sample-7.pdf");
	        		PDDocument doc=PDDocument.load(file1);
	        		PDDocument doc1=PDDocument.load(file2);
	        		
	        		PDPageTree mergePD=doc.getPages();
	        		mergePD.insertAfter(doc1.getPage(0),doc.getPage(pagenumber));
	
	        		doc.save("E:\\PdfMap\\402.pdf");
	        		
	        		doc.close();
	        		pagenumber++;
	        	}
	        	for(String key : obj.keySet())
	        	{
//	        		System.out.println("key=" +key);
//	        		System.out.println("object.keySet="+obj.keySet());
	        		File oldFile=new File("E:\\PdfMap\\402.pdf");
	        		PDDocument document=PDDocument.load(oldFile);
	        		
	        		PDPage page= document.getPage(pagenumber);
	        		PDPageContentStream contentStream=new PDPageContentStream(document,page,AppendMode.APPEND,true,true);
	        		
	        		contentStream.beginText();
	        		contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
	        		
	        		if(key=="contributedDate")
	        		{
	        			contentStream.newLineAtOffset(29, y);
	        		}
	        		else if(key=="username")
	        		{
	        			contentStream.newLineAtOffset(150, y);
	        		}
	        		else if(key=="contributedAmount")
	        		{
	        			contentStream.newLineAtOffset(700, y);
	        		}
	        		else if(key=="amount")
	        		{
	        			contentStream.newLineAtOffset(645, y);
	        		}
	        			
	        		contentStream.showText(obj.get(key));
	        		contentStream.endText();
	        		contentStream.close();
	       
	        		 document.save("E:\\PdfMap\\402.pdf");
	        		 document.close();
	        	}
	        	y=y-80;
	        	count++;
	        }
	        
	        
	        
	        // for page number 7
	        
	        List<Map<String,String>> expenditureData=pdf.page3print(listExpenditureName,selectedStartDate,selectedEndDate);
	        
	        System.out.println("expenditureData======================"+expenditureData);
	        	
	        	 
	        	      int count1=0;
	        	      int pagenumbernext=continueSheetCount +7;
	        	      int pageRowNext=6;
	        	      int y1=430;
	        	       
	        	      for(Map<String,String> obj:expenditureData)
	        	      {
	        	    	  if(count1 == pageRowNext)
	        	    	  {
	        	    		  y1=430;
	        	    		  continueSheetCount++;
	        	    		  count1=0;
	        	    		  File file1=new File("E:\\PdfMap\\402.pdf");
	        	    		  File file2=new File("E:\\PdfEditor\\Sample\\sample-8.pdf");
	        	    		  PDDocument doc=PDDocument.load(file1);
	        	    		  PDDocument doc1=PDDocument.load(file2);
	        	    		  
	        	    		  PDPageTree mergePD=doc.getPages();
	        	    		  mergePD.insertAfter(doc1.getPage(0),doc.getPage(pagenumbernext));
	        	    		  doc.save("E:\\PdfMap\\402.pdf");
	        	    		  doc.close();
	        	    		  pagenumbernext++;
	        	    		}
	        	       for(String key : obj.keySet())
	        	       {
	        	    	 File oldFile= new File("E:\\PdfMap\\402.pdf");
	        	    	 PDDocument document=PDDocument.load(oldFile);
	        	    	 PDPage page=document.getPage(pagenumbernext);
	        	    	 PDPageContentStream contentStream=new PDPageContentStream(document, page,AppendMode.APPEND,true,true);
	        	    	 contentStream.beginText();
	        	    	 contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
	        	    	 
	        	    	 if(key=="username")
	        	    	 {
	        	    		 contentStream.newLineAtOffset(150, y1);
	        	    	 }
	        	    	 else if(key=="amount")
	        	    	 {
	        	    		 contentStream.newLineAtOffset(700, y1);
	        	    	 }
	        	    	 contentStream.showText(obj.get(key));
	 	        		contentStream.endText();
	 	        		contentStream.close();
	 	       
	 	        		 document.save("E:\\PdfMap\\402.pdf");
	 	        		 document.close();
	        	   
	        	       }
	        	       
	        	       y1=y1-50;
	        	      count1++;
	        	      }
	        		
		
	     	}
                       catch(Exception e)
	        	      {
	        	    	  e.printStackTrace();
	        	      }
	        	      
		
		return "sucess";
	}		

}
