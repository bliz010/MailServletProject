
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.Properties;



/**
 * Servlet implementation class MailServlet
 */

public class MailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        
        sendEmail(email, subject, message);
        
        response.getWriter().append("\nmail was sent from the MailServlet successfully");
	}

	
	protected void sendEmail(String email,String subject, String messg) {
	             
		//credentials for the sender
		final String from="your_email@example.com"; //placeholder 
		final String pass="YOUR_APP_PASSWORD"; 		//placeholder

		//object to store properties for the SMTP session
        // First - create a mail session
		Properties props=new Properties();
                
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.debug", "true");  // optional for logging
            
		//passes properties to the SMTP session
		jakarta.mail.Session mailSession=jakarta.mail.Session.getDefaultInstance(props, new Authenticator(){
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(from, pass);
			}
		});

		try{
            // Second - create a MimeMessage
			MimeMessage message=new MimeMessage(mailSession);
                        
            // Third - address the message				
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));              		
			message.setSubject(subject);
			message.setText(messg);
			
		    Transport.send(message);
                                                              
			// Fourth - send the message				

		}

		catch(MessagingException mex){
			mex.printStackTrace();
		}
	}
	
}