package springprojectfive.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import springprojectfive.model.Contribution;
import springprojectfive.model.Expenditure;
import springprojectfive.model.PdfModel;
import springprojectfive.model.PdfModel2;

public class Pdf {

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public double toGetTotalPeriodAmountController(List<PdfModel> listContributionName, String selectedStartDate,
			String selectedEndDate) {
		
//		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+contribution);
//		System.out.println("selectedstartdate"+selectedStartDate);
//		System.out.println("selectedstartdate"+selectedEndDate);
		double totalPeriodAmount = 0.0;
		try {
         		Date startDate = simpleDateFormat.parse(selectedStartDate);
			    Date endDate = simpleDateFormat.parse(selectedEndDate);

			for (PdfModel obj : listContributionName) {
				Date contributeDate = new SimpleDateFormat("yyyy-MM-dd")
						.parse(obj.getContributedDate());
				

				if (((contributeDate.after(startDate)) || (contributeDate.equals(startDate)))
						&& ((contributeDate.before(endDate)) || (contributeDate.equals(endDate)))) {
					totalPeriodAmount = obj.getAmount() + totalPeriodAmount;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalPeriodAmount;

	}

	public double toGetTotalPeriodAmountExpenditure(List<PdfModel2> listExpenditureName, String selectedStartDate,
			String selectedEndDate) {
		double totalPeriodAmount = 0.0;
		try {
			Date startDate = simpleDateFormat.parse(selectedStartDate);
		
			Date endDate = simpleDateFormat.parse(selectedEndDate);

			for (PdfModel2 obj : listExpenditureName) {
				Date expenditureDate = new SimpleDateFormat("yyyy-MM-dd")
						.parse(obj.getExpendituredate());

				if (((expenditureDate.after(startDate)) || (expenditureDate.equals(startDate)))
						&& ((expenditureDate.before(endDate)) || (expenditureDate.equals(endDate)))) {
					totalPeriodAmount = obj.getAmount() + totalPeriodAmount;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalPeriodAmount;

	}

	public double toGetTotalCumalltiveAmountContribution(List<PdfModel> listContributionName, String selectedStartDate,
			String selectedEndDate) {

		double totalCumaltiveAmount = 0.0;
		try {
			Date currentYear = simpleDateFormat.parse(selectedEndDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentYear);
			int year = calendar.get(Calendar.YEAR);
			String cumalativeyear = year + "-01-01";
			System.out.println("year" + cumalativeyear);
			Date endDate = simpleDateFormat.parse(cumalativeyear);

			for (PdfModel obj : listContributionName) {
				Date contributeDate = new SimpleDateFormat("yyyy-MM-dd")
						.parse(obj.getContributedDate());

				if ((contributeDate.after(endDate) || contributeDate.equals(endDate))) {
					totalCumaltiveAmount = obj.getAmount() + totalCumaltiveAmount;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCumaltiveAmount;
	}

	public double toGetTotalCumalltiveAmountExpenditure(List<PdfModel2> listExpenditureName , String selectedStartDate,
			String selectedEndDate) {

		double totalCumaltiveAmount = 0.0;
		try {
			Date currentYear = simpleDateFormat.parse(selectedEndDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentYear);
			int year = calendar.get(Calendar.YEAR);
			String cumalativeyear = year + "-01-01";
			System.out.println("year" + cumalativeyear);
			Date endDate = simpleDateFormat.parse(cumalativeyear);

			for (PdfModel2 obj : listExpenditureName) {
				Date expenditureDate = new SimpleDateFormat("yyyy-MM-dd")
						.parse(obj.getExpendituredate());

				if ((expenditureDate.after(endDate) || expenditureDate.equals(endDate))) {
					totalCumaltiveAmount = obj.getAmount() + totalCumaltiveAmount;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCumaltiveAmount;
	}
	
	
	
	
	

	public List<Map<String, String>> page2print(List<PdfModel> listContributionName, String selectedStartDate,
			String selectedEndDate) {

//		double totalPeriodAmount = 0.0;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			Date startDate = simpleDateFormat.parse(selectedStartDate);
			Date endDate = simpleDateFormat.parse(selectedEndDate);

			HashMap<Integer, Double> contributedTotal = new HashMap<Integer, Double>();
			for (PdfModel obj : listContributionName) {
				
//				System.out.println("objjjjjjjjjjjjjjjjjjjjjjjjjj"+obj);

				int userId = obj.getUserId();
				if (contributedTotal.containsKey(userId)) {
					contributedTotal.put(userId, contributedTotal.get(userId) + obj.getAmount());
				} else {
					contributedTotal.put(userId, (double) obj.getAmount());
				}

			}

			// pdf writing

			for (PdfModel obj : listContributionName) {
				
//				System.out.println("objjjjjjjjjjjjjjjjjjjjjjjjjj============="+obj);

				HashMap<String, String> finalData = new HashMap<String, String>();

				Date contributeDate = new SimpleDateFormat("yyyy-MM-dd")
						.parse(obj.getContributedDate());

				if ((contributeDate.after(startDate) || contributeDate.equals(startDate))
						&& (contributeDate.before(endDate) || contributeDate.equals(endDate))
						&& (obj.getAmount() >= 100)) {

					String contri = new SimpleDateFormat("yyyy-MM-dd").format(contributeDate);
					String amount = String.valueOf(obj.getAmount());
					String username=String.valueOf(obj.getUser());
					String contributedAmount = "";
					
				   if(contributedTotal.containsKey(obj.getUserId()))
				   {
					   contributedAmount=String.valueOf(contributedTotal.get(obj.getUserId()));
//					   System.out.println("contributedAmount================="+contributedAmount);
					   
				   }
			    finalData.put("username",username);
			    finalData.put("amount", amount);
			    finalData.put("contributedDate",contri);
			    finalData.put("contributedAmount",contributedAmount);
			    
			    list.add(finalData);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	
	
	
	
	public List<Map<String, String>> page3print(List<PdfModel2> listExpenditureName, String selectedStartDate,
			String selectedEndDate) {


		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			Date startDate = simpleDateFormat.parse(selectedStartDate);
			Date endDate = simpleDateFormat.parse(selectedEndDate);

			HashMap<Integer, Double> expendituredTotal = new HashMap<Integer, Double>();
			for (PdfModel2 obj : listExpenditureName) {
				
				System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"+obj);

				int userId = obj.getUserId();
				if (expendituredTotal.containsKey(userId)) {
					expendituredTotal.put(userId, expendituredTotal.get(userId) + obj.getAmount());
				} else {
					expendituredTotal.put(userId, (double) obj.getAmount());
				}

			}

			// pdf writing

			for (PdfModel2 obj : listExpenditureName) {
				
				System.out.println("ppppppppppppppppppppppppppppppppppp============="+obj);

				HashMap<String, String> finalData = new HashMap<String, String>();

				Date contributeDate = new SimpleDateFormat("yyyy-MM-dd")
						.parse(obj.getExpendituredate());

				if ((contributeDate.after(startDate) || contributeDate.equals(startDate))
						&& (contributeDate.before(endDate) || contributeDate.equals(endDate))
						&& (obj.getAmount() >= 100)) {

//					String contri = new SimpleDateFormat("yyyy-MM-dd").format(contributeDate);
					String amount = String.valueOf(obj.getAmount());
					String username=String.valueOf(obj.getUser());
//					String contributedAmount = "";
					
//				   if(expendituredTotal.containsKey(obj.getUserId()))
//				   {
//					   expenditureAmount=String.valueOf(expendituredTotal.get(obj.getUserId()));
////					   System.out.println("expenditureAmount================="+expenditureAmount);
//					   
//				   }
			    finalData.put("username",username);
			    finalData.put("amount", amount);
			    
			    
			    list.add(finalData);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
