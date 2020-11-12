package Backend;


import java.util.Properties;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * 
 * class to:
 * 		~ create a Email by given Data 
 * 		 
 * 
 * @author luisa.thiel Mail: luisa.thiel@cideon.com
 * Company: Cideon Software & Services GmbH & Co. KG.
 * created at 04.11.2020
 */

public class Mail {
    
    String to="";
    String subject="";
    String content="";
    
    
    
   public Mail (String to, String subject, String content){

   this.to=to;
   this.subject= subject;
   this.content= content;
   createMail();
} 
   private void createMail(){

      // Sender's email ID needs to be mentioned
      String from = "MercuriusITGmbH@gmail.com";

      // Assuming you are sending email from localhost
      String host = "localhost";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         message.setSubject(subject);

         // Send the actual HTML message, as big as you like
         message.setContent(content, "text/html");

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      } catch (MessagingException mex) {
      }
   
   }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
  
   
   
} 