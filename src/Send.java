
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Send {
	static String name;
	static String pass;
	static String from;
	static String to;
	public Send(String Name , String Pass,String From, String to ) {
		this.name=Name;
		this.pass=Pass;
		this.from=From;
		this.to=to;
	}
	public static void main(String[] args) throws IOException {
		
		


	
	}

	public void sendSalary(int Basic, int Allowance , int Tax , int Leaves) {

		Properties pro= new Properties();
		pro.put("mail.smtp.auth", "true");
		pro.put("mail.smtp.starttls.enable", "true");
		pro.put("mail.smtp.host", "smtp.gmail.com");
		pro.put("mail.smtp.port", 587);
		Session session = Session.getInstance(pro, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(name,pass);
			}
		});
		int salary = SalaryCalculation(Basic, Allowance, Tax, Leaves);
		Date d  = new Date();
		String Months[] =  {"January" , "February" , "March" , "April" , "May" , "June" , "July" , "August" , "September" , "October" , "November" , "December"};
		int Month = d.getMonth();
		
		//Start our mail message
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(from));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject("Salary for the Month of "+Months[Month]);
			
			msg.setText("Your Salary has been sent to the your bank account. \n Basic Salary : "+Basic+" \n Allowance : "+Allowance+" \n Tax : "+Tax+" \n Leaves = "+Leaves+" \nWhich amounts to a total of PKR " +salary );
			System.out.println("Sent");
			Transport.send(msg);
			System.out.println("Sent message");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public int SalaryCalculation(int Basic, int Allowance , int Tax , int Leaves){
		
		
		int total = (Basic + Allowance)  - Tax  - (Leaves * 500);
		return total;
		
		
	}
}