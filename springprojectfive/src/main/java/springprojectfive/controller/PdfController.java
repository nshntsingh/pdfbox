package springprojectfive.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import springprojectfive.dao.ContributionDao;
import springprojectfive.dao.ExpenditureDao;
import springprojectfive.dao.OrganizationDao;
import springprojectfive.dao.UserDao;
import springprojectfive.model.Contribution;
import springprojectfive.model.Expenditure;
import springprojectfive.model.Organization;
import springprojectfive.model.User;

@Controller
public class PdfController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private OrganizationDao organizationDao;

	@Autowired
	private ContributionDao contributionDao;

	@Autowired
	private ExpenditureDao expenditureDao;

	// edit Pdf

	@RequestMapping(value = "/editorPdf")
	public String editPdf() {

		// Loading an existing document
		File file = new File("E:\\PdfEditor\\Sample\\sample-3.pdf");
		try {
			PDDocument document = PDDocument.load(file);

			// for page number 3

			PDPage page1 = document.getPage(0);
			{
				PDPageContentStream contentStream1 = new PDPageContentStream(document, page1,
						PDPageContentStream.AppendMode.APPEND, true, true);

				contentStream1.beginText();
				contentStream1.setFont(PDType1Font.TIMES_ROMAN, 12);
				contentStream1.newLineAtOffset(140, 430);

				java.util.List<Organization> list1 = organizationDao.getOrganizations(); // get data of userdao

				for (int i1 = 1; i1 < list1.size(); i1++) {
					System.out.println(list1.get(i1));
				}
				Organization organization = list1.get(1);
				String organization1 = organization.getName();
				contentStream1.showText(organization1);
				contentStream1.endText();

				System.out.println("Organization name added");

				contentStream1.beginText();
				contentStream1.setFont(PDType1Font.TIMES_ROMAN, 12);
				contentStream1.newLineAtOffset(430, 505);
				String text2 = "01/11/2020";
				contentStream1.showText(text2);
				contentStream1.endText();
				System.out.println("from statment cover period");

				contentStream1.beginText();
				contentStream1.setFont(PDType1Font.TIMES_ROMAN, 12);
				contentStream1.newLineAtOffset(430, 480);
				String text3 = "30/11/2021";
				contentStream1.showText(text3);
				contentStream1.endText();
				System.out.println("through statement cover perion");

				// for contribution

				contentStream1.beginText();
				contentStream1.setFont(PDType1Font.TIMES_ROMAN, 12);
				contentStream1.newLineAtOffset(420, 240);
				java.util.List<Contribution> list2 = contributionDao.getContributions();

				long z = 0;
				for (int i = 0; i < list2.size(); i++) {

					System.out.println(list2.get(i));
					Contribution contribution2 = list2.get(i);

					String x2 = contribution2.getContributedDate();
					Date todayDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(x2);

					String date2 = "2021-10-31";
					DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
					Date historyDate1 = df3.parse(date2);

					String date3 = "2021-12-01";
					DateFormat df4 = new SimpleDateFormat("yyyy-MM-dd");
					Date futureDate1 = df4.parse(date3);

					if (todayDate1.after(historyDate1) && todayDate1.before(futureDate1)) {
						long x = contribution2.getAmount();
						z += x;
					}
				}

				String text4 = String.valueOf(z);
				contentStream1.showText(text4);
				contentStream1.endText();

				System.out.println("total this period");

				// for contribution

				contentStream1.beginText();
				contentStream1.setFont(PDType1Font.TIMES_ROMAN, 12);
				contentStream1.newLineAtOffset(550, 240);
				java.util.List<Contribution> list3 = contributionDao.getContributions();
				long a1 = 0;
				for (int l = 0; l < list3.size(); l++) {
					Contribution contri2 = (list3.get(l));
					String x1 = contri2.getContributedDate();
					Date todayDate = new SimpleDateFormat("yyyy-MM-dd").parse(x1);

					String date1 = "2021-01-01";
					DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
					Date historyDate = df1.parse(date1);

					String date = "2021-11-18";
					DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
					Date futureDate = df2.parse(date);

					if (todayDate.after(historyDate) && todayDate.before(futureDate)) {

						long l1 = contri2.getAmount();
						a1 += l1;
					}
				}

				String text5 = String.valueOf(a1);
				contentStream1.showText(text5);
				contentStream1.endText();
				System.out.println("cumalative to date");

				// for expenditure

				contentStream1.beginText();
				contentStream1.setFont(PDType1Font.TIMES_ROMAN, 12);
				contentStream1.newLineAtOffset(420, 215);

				java.util.List<Expenditure> list5 = expenditureDao.getExpenditures();

				long z1 = 0;
				for (int l2 = 0; l2 < list5.size(); l2++) {

					System.out.println(list5.get(l2));
					Expenditure epxi = list5.get(l2);

					String x4 = epxi.getExpendituredate();
					Date todayDate4 = new SimpleDateFormat("yyyy-MM-dd").parse(x4);

					String date6 = "2021-10-31";
					DateFormat df6 = new SimpleDateFormat("yyyy-MM-dd");
					Date historyDate5 = df6.parse(date6);

					String date7 = "2021-12-01";
					DateFormat df7 = new SimpleDateFormat("yyyy-MM-dd");
					Date futureDate5 = df7.parse(date7);

					if (todayDate4.after(historyDate5) && todayDate4.before(futureDate5)) {
						long l3 = epxi.getAmount();
						z1 += l3;
					}
				}

				String text7 = String.valueOf(z1);
				contentStream1.showText(text7);
				contentStream1.endText();

				System.out.println("total this period for expenditure ");

				// for Expenditure

				contentStream1.beginText();
				contentStream1.setFont(PDType1Font.TIMES_ROMAN, 12);
				contentStream1.newLineAtOffset(550, 215);

				java.util.List<Expenditure> list4 = expenditureDao.getExpenditures();

				long a2 = 0;
				for (int l1 = 0; l1 < list4.size(); l1++) {
					Expenditure expendi3 = (list4.get(l1));
					String x3 = expendi3.getExpendituredate();
					Date todayDate3 = new SimpleDateFormat("yyyy-MM-dd").parse(x3);

					String date4 = "2021-01-01";
					DateFormat df4 = new SimpleDateFormat("yyyy-MM-dd");
					Date historyDate4 = df4.parse(date4);

					String date5 = "2021-11-18";
					DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
					Date futureDate4 = df2.parse(date5);

					if (todayDate3.after(historyDate4) && todayDate3.before(futureDate4)) {

						long l2 = expendi3.getAmount();
						a2 += l2;
					}
				}

				String text6 = String.valueOf(a2);
				contentStream1.showText(text6);
				contentStream1.endText();
				System.out.println("cumalative to date for expenditure");

				// Closing the content stream
				contentStream1.close();
			}

			// Saving the document
			document.save(new File("E:\\PdfEditor\\pdf\\sample-3.pdf"));

			// Closing the document
			document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		

//  for page number 5 SCHEUDLE A

		try {

				java.util.List<Contribution> list6 = contributionDao.getContributions();

				String y1 = "";
				String z1 = "";
				String z2=  "";
				int ys=0;
				for (int i = 0; i < list6.size();) {
					
				     int n=list6.size();
				     
					if(i <= 2)
					{
						File file1 = new File("E:\\PdfEditor\\Sample\\sample-5.pdf");
						PDDocument document = PDDocument.load(file1);
						// Retrieving the pages of the document
						PDPage page = document.getPage(0);
						PDPageContentStream contentStream = new PDPageContentStream(document, page,
								PDPageContentStream.AppendMode.APPEND, true, true);
						while(i<=2) {
					
 					
					Contribution contri6 = list6.get(i++);

					long x9 = 0;

					for (int j = 0; j < list6.size(); j++) {

						Contribution contri7 = list6.get(j);
						
						if (contri6.getUser() == contri7.getUser()) {

							String x = contri7.getContributedDate();
							Date todayDate = new SimpleDateFormat("yyyy-MM-dd").parse(x);

							String date2 = "2021-10-31";
							DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
							Date historyDate = df.parse(date2);

							String date3 = "2021-12-01";
							DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
							Date futureDate = df1.parse(date3);

							if (todayDate.after(historyDate) && todayDate.before(futureDate)) {
								x9 += contri7.getAmount();

							}

						}
					}

					if (x9 > 100) {

						System.out.println(x9);
						java.util.List<User> users = userDao.getUsers();
						for (User user : users) {
							
							if(contri6.getUser() == user.getId())
							{	
								
							
								String x = contri6.getContributedDate();
								Date todayDate = new SimpleDateFormat("yyyy-MM-dd").parse(x);

								String date2 = "2021-10-31";
								DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
								Date historyDate = df.parse(date2);

								String date3 = "2021-12-01";
								DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
								Date futureDate = df1.parse(date3);

								if (todayDate.after(historyDate) && todayDate.before(futureDate)) {

							     String y =contri6.getContributedDate() ;
						         String z =user.getFirstname();
	                             String z3 = String.valueOf(contri6.getAmount());
							           y1 = y;
							           z1=z;
							           z2=z3;
								}
							}
						}
					}
					
					contentStream.beginText();
					contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
					contentStream.setLeading(90.0f);
					contentStream.newLineAtOffset(38,370-ys);
					String text8 = String.valueOf(y1);
					contentStream.showText(text8);
					contentStream.newLine();
					contentStream.endText();
					
					contentStream.beginText();
					contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
					contentStream.setLeading(90.0f);
					contentStream.newLineAtOffset(130,370-ys);
					String text9 = String.valueOf(z1);
					contentStream.showText(text9);
					contentStream.newLine();
					contentStream.endText();
					
					contentStream.beginText();
					contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
					contentStream.setLeading(90.0f);
					contentStream.newLineAtOffset(630,370-ys);
					String text10 = String.valueOf(z2);
					contentStream.showText(text10);
					contentStream.newLine();
					contentStream.endText();
					
					ys += 70;
					}
					
					System.out.println("schedule");

					contentStream.close();
					// Saving the document
					document.save(new File("E:\\PdfEditor\\402.pdf"));

					
					// Closing the document
					document.close();
				
					
					}
					else {
						
						
						int pageno=0;
						
						Map<Integer, String> hm = new HashMap<Integer, String>();
						int filect=1;
						for(int i1 = 3; i1 < n;i1++, i++)
						{
							
							int x;
							
							if ((n-3)%4 ==0)
								
								x = 0;
							else
								x = 1;
							
							pageno=((n-3)/4)  + x; 
							
							
							for(int x1=1; x1<=pageno; x1++)
							{
								

								ys=0;
								String value="E:\\PdfEditor\\403_"+filect+".pdf";	
								filect++;
								
								
								if(i1 < n)
								{
								
							File file1 = new File("E:\\PdfEditor\\Sample\\sample-7.pdf");
							PDDocument document = PDDocument.load(file1);
							// Retrieving the pages of the document
							PDPage page = document.getPage(0);
							PDPageContentStream contentStream = new PDPageContentStream(document, page,
									PDPageContentStream.AppendMode.APPEND, true, true);

							java.util.List<Contribution> list7 = contributionDao.getContributions();
							for (int k = 4; k >= 1; k--) {
								if(i>=list7.size())
									continue;
								
								Contribution contri6 = list7.get(i);

								long x9 = 0;

								for (int j = 0; j < list6.size(); j++) {

									Contribution contri7 = list6.get(j);

									if (contri6.getUser() == contri7.getUser()) {

										String x3 = contri7.getContributedDate();
										Date todayDate = new SimpleDateFormat("yyyy-MM-dd").parse(x3);

										String date2 = "2021-10-31";
										DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
										Date historyDate = df.parse(date2);

										String date3 = "2021-12-01";
										DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
										Date futureDate = df1.parse(date3);

										if (todayDate.after(historyDate) && todayDate.before(futureDate)) {
											x9 += contri7.getAmount();
											System.out.println(x9);

										}

									}
								}

								if (x9 > 100) {

									System.out.println(x9);
									java.util.List<User> users = userDao.getUsers();
									for (User user : users) {

										if (contri6.getUser() == user.getId()) {

											String x2 = contri6.getContributedDate();
											Date todayDate = new SimpleDateFormat("yyyy-MM-dd").parse(x2);

											String date2 = "2021-10-31";
											DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
											Date historyDate = df.parse(date2);

											String date3 = "2021-12-01";
											DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
											Date futureDate = df1.parse(date3);

											if (todayDate.after(historyDate) && todayDate.before(futureDate)) {

												String y = contri6.getContributedDate();
												String z = user.getFirstname();
												String z3 = String.valueOf(contri6.getAmount());
												y1 = y;
												System.out.println(y1);
												z1 = z;
												System.out.println(z1);
												z2 = z3;
												System.out.println(z2);
											}
										}
									}
								}
                                 System.out.println("nishant");
								contentStream.beginText();
								contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
								contentStream.setLeading(90.0f);
								contentStream.newLineAtOffset(38, 350 - ys);
								String text8 = String.valueOf(y1);
								contentStream.showText(text8);
								contentStream.newLine();
								contentStream.endText();

								contentStream.beginText();
								contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
								contentStream.setLeading(90.0f);
								contentStream.newLineAtOffset(130, 350 - ys);
								String text9 = String.valueOf(z1);
								contentStream.showText(text9);
								contentStream.newLine();
								contentStream.endText();

								contentStream.beginText();
								contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
								contentStream.setLeading(90.0f);
								contentStream.newLineAtOffset(630, 350 - ys);
								String text10 = String.valueOf(z2);
								contentStream.showText(text10);
								contentStream.newLine();
								contentStream.endText();

								ys += 80;
							}
							
							
							
								contentStream.close();
								System.out.println("schedule");

								// Saving the document
								document.save(new File(value));

								// Closing the document
								document.close();
								
								
									}
								
								else
								{
								    System.out.println("hello");
									break;
								}
								
								hm.put(i1,value);
								
								
								
							}
							
							
					}
						
					
						
						
			    }
				

				}		
				
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "editpdf";
	}
}
