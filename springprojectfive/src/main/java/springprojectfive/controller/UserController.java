package springprojectfive.controller;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import springprojectfive.dao.UserDao;
import springprojectfive.model.User;

@Controller
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/")
	public String home(Model m)         // model object is used to add data to view page
	{
	java.util.List<User> users=userDao.getUsers();       // get data of userdao 
		m.addAttribute("users", users);                  // add data in ("key",value)pair
		return "indexuser";                              // return jsp page
		
	}
	
	
	//Show add user form
		@RequestMapping("/add-user")
		public String addUser(Model m)
		{
			m.addAttribute("title","Add-user");    // value given with help of "title"
			return "add_user_form";                // return jsp page
		}
		
		
		//handle add user form
		@RequestMapping(value = "/handle-user", method = RequestMethod.POST)
		public RedirectView handleUser(@ModelAttribute User user,HttpServletRequest request)
		{
			
			System.out.println(user);
			userDao.createUser(user);                                // add data in database
			RedirectView redirectView=new RedirectView();
			redirectView.setUrl(request.getContextPath() + "/");    
			return redirectView;
		}
		
		
		// delete handle
		@RequestMapping("/delete/{userId}")
		public RedirectView deleteUser(@PathVariable("userId") int userId, HttpServletRequest request)
		{
			this.userDao.deleteUser(userId);
			RedirectView redirectView=new RedirectView();
			redirectView.setUrl(request.getContextPath()+"/");
			return redirectView;
		}
		
		
		@RequestMapping("/update/{userId}")
		public String updateForm(@PathVariable("userId") int uid,Model model)
		{
			User user=this.userDao.getUser(uid);
			model.addAttribute("user",user);
			return "update_user_Form";
		}
		
		

        // create pdf
        @RequestMapping(value="/userPdf")    
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
                
            	java.util.List<User> users=userDao.getUsers();      // get data of userdao 
                for( User user :users){
                	  // access foo here

                	 String line1=user.getFirstname()+"   "+user.getLastname()+"  "+user.getEmail()+"  ";
                     cont.showText(line1);
                     cont.newLine();
              }
				              
               cont.endText();
             
               //Closing the contentStream
               cont.close();
              
               
               //doc.save("src/main/java/com/testng/annotations/pdfresource");//E:\Pdfbox
               //document.save("c://JavaInterviewPoint//Hello.pdf");
               
                   doc.save("e://Pdf//userPdf.pdf");
              
                   doc.close();
           }
           catch(Exception e)
           {
        	   e.printStackTrace();
           }
            return "userPdf";    
            
        }



		
		
		


}
