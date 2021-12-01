package springprojectfive.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import org.hibernate.mapping.Set;
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

import com.mysql.cj.result.Row;

import springprojectfive.dao.ContributionDao;
import springprojectfive.dao.ContributionTypeDao;
import springprojectfive.dao.OrganizationDao;
import springprojectfive.dao.UserDao;
import springprojectfive.model.Contribution;
import springprojectfive.model.ContributionType;
import springprojectfive.model.ExpenditureType;
import springprojectfive.model.Organization;
import springprojectfive.model.User;

@Controller
public class ContributionController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private OrganizationDao organizationDao;

	@Autowired
	private ContributionTypeDao contributionTypeDao;

	@Autowired
	private ContributionDao contributionDao;

	@RequestMapping("/c")
	public String home(Model m) // model object is used to add data to view page
	{
		java.util.List<Contribution> contributions = contributionDao.getContributions(); // get data of userdao
		m.addAttribute("contributions", contributions); // add data in ("key",value)pair

		java.util.List<User> users = userDao.getUsers(); // get data of userdao
		m.addAttribute("users", users);

		java.util.List<Organization> organizations = organizationDao.getOrganizations(); // get data of orgdao
		m.addAttribute("organizations", organizations);

		java.util.List<ContributionType> contributionTypes = contributionTypeDao.getContributionTypes(); 
																											
		m.addAttribute("contributionTypes", contributionTypes);

		return "indexcontribution"; // return jsp page

	}

	// Show add user form
	@RequestMapping("/add-contribution")
	public String addContribution(Model m) {
		m.addAttribute("title", "Add-contribution");

		java.util.List<User> users = userDao.getUsers();
		m.addAttribute("users", users);

		java.util.List<Organization> organizations = organizationDao.getOrganizations();
		m.addAttribute("organizations", organizations);

		java.util.List<ContributionType> contributionTypes = contributionTypeDao.getContributionTypes();
		m.addAttribute("contributionTypes", contributionTypes);

		// value given with help of "title"
		return "add_contribution_form"; // return jsp page
	}

	// handle add product form
	@RequestMapping(value = "/handle-contribution", method = RequestMethod.POST)
	public RedirectView handleContribution(@ModelAttribute Contribution contribution, HttpServletRequest request) {

		System.out.println(contribution);
		contributionDao.createContribution(contribution); // add data in database
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/c");
		return redirectView;
	}

	// delete handle
	@RequestMapping("/c/delete/{contributionId}")
	public RedirectView deleteContribution(@PathVariable("contributionId") int contributionId,
			HttpServletRequest request) {
		this.contributionDao.deleteContribution(contributionId);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/c");
		return redirectView;
	}

	// update handle
	@RequestMapping("/c/update/{contributionId}")
	public String contributionForm(@PathVariable("contributionId") int cid, Model model) {
		Contribution contribution = this.contributionDao.getContributions(cid);
		model.addAttribute("contribution", contribution);

		java.util.List<User> users = userDao.getUsers();
		model.addAttribute("users", users);

		java.util.List<Organization> organizations = organizationDao.getOrganizations();
		model.addAttribute("organizations", organizations);

		java.util.List<ContributionType> contributionTypes = contributionTypeDao.getContributionTypes();
		model.addAttribute("contributionTypes", contributionTypes);

		return "update_contribution_Form";
	}

	// create pdf
	@RequestMapping(value = "/contributionPdf")
	public String downLoadPdf() {

		// A new PDDocument is created
		PDDocument doc = new PDDocument();

		// A new page is created and added to the document.
		PDPage myPage = new PDPage();
		doc.addPage(myPage);

		// To write to a PDF page, we have to create a PDPageContentStream object.
		try {
			PDPageContentStream cont = new PDPageContentStream(doc, myPage);

			// Text is written between beginText() and endText() methods.
			cont.beginText();

			cont.setFont(PDType1Font.TIMES_ROMAN, 12);
			cont.setLeading(14.5f);

			cont.newLineAtOffset(25, 700);

			java.util.List<User> users = userDao.getUsers();
 			java.util.List<Organization> organizations = organizationDao.getOrganizations();
 			java.util.List<ContributionType> contributionTypes = contributionTypeDao.getContributionTypes();

			String headerline="user"+ "  " +"organization"+"  "+"Amount" +"  "+"ContributionDate"+"  "+"PurposeofDisnursement"+"  "+"ContributionType"+"  "+"Createdate"+"  "+"Updatedate";
              cont.showText(headerline);
			cont.newLine();
			java.util.List<Contribution> contributions = contributionDao.getContributions(); // get data of userdao
			for (Contribution contribution : contributions) {
				
				
				
				String user1="";
				String organization1="";
				String contributionType1="";
				
				
	 		
	 			for(User user:users)
	 			{
	 				if(user.getId()==contribution.getUser())
	 				{
	 				   user1= user.getFirstname();
	 				} 			
	 			}
	 			
	 			
	 	 		for(Organization organization:organizations)
	 	 			{
	 	 				if(organization.getId()== contribution.getOrganization())
	 	 				{
	 	 					organization1=organization.getName();
	 	 				}
	 	 			}
	 	 	 	for(ContributionType contributionType:contributionTypes)
	 	 	 	{
	 	 	 		if(contributionType.getId()==contribution.getContributionType())
	 	 	 		{
	 	 	 			contributionType1=contributionType.getName();
	 	 	 		}
	 	 	 	}
				

				String line1 = user1 + "  " + organization1 + "  "
						+ contribution.getAmount() + "  " + contribution.getContributedDate() + "  "
						+ contribution.getPurposeofdisbursement() + "  " + contributionType1 + "  "
						+ contribution.getCreatedate() + "  " + contribution.getUpdatedate();

				cont.showText(line1);
				cont.newLine();
			}

			cont.endText();

			// Closing the contentStream
			cont.close();

			doc.save("e://Pdf2//contributionPdf.pdf");

			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "contributionPdf";
	}

	// for expenditure excel
	@RequestMapping(value = "/contributionExcel")
	public String downLoadExcelContribution() {

		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Contribution Data");

		// This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();

		data.put("1", new Object[] { "ID", "user", "Organization Name","Amount","contribution Date" ,"purpose","contribution Type",
				"Create Date", "Update Date" });

		java.util.List<Contribution> contributions = contributionDao.getContributions();

		int keyIndex = 2;
		for (Contribution contri : contributions) {
		
			String user1="";
			String organization1="";
			String contributionType1="";
			
			
			java.util.List<User> users = userDao.getUsers();
 			java.util.List<Organization> organizations = organizationDao.getOrganizations();
 			java.util.List<ContributionType> contributionTypes = contributionTypeDao.getContributionTypes();

 		
 			for(User user:users)
 			{
 				if(user.getId()==contri.getUser())
 				{
 				   user1= user.getFirstname();
 				} 			
 			}
 			
 			
 	 		for(Organization organization:organizations)
 	 			{
 	 				if(organization.getId()== contri.getOrganization())
 	 				{
 	 					organization1=organization.getName();
 	 				}
 	 			}
 	 	 	for(ContributionType contributionType:contributionTypes)
 	 	 	{
 	 	 		if(contributionType.getId()==contri.getContributionType())
 	 	 		{
 	 	 			contributionType1=contributionType.getName();
 	 	 		}
 	 	 	}

 	 	 				
			data.put(keyIndex + "",
					new Object[] { contri.getId(), user1, organization1,String.valueOf(contri.getAmount()),contri.getContributedDate(),contri.getPurposeofdisbursement(),
							contributionType1, contri.getCreatedate(),contri.getUpdatedate() });
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
			FileOutputStream out = new FileOutputStream(new File("e://Pdf2//contributionExcel.xlsx"));
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "contributionExcel";
	}

}
