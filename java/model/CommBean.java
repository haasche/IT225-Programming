/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import edu.ilstu.it.TextSenderService;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Seth
 */
@ManagedBean (name="commBean")
@SessionScoped
public class CommBean {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/TextSenderService.xml.wsdl")
    private TextSenderService service;
    
    String total;
    String email;
    String phone;
    
    public String getTotal() {
        return total;
    }
    
    public void setTotal(String total) {
        this.total = total;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void emailReceipt() {
        sendEmail();
    }
    
    public void textReceipt() {
        String msg = "Your total came out to: " + total + ".";
        sendSMS("AT&T", phone, msg);
        System.out.println("Sent a text to: " + phone + ",with the message: " + msg);
    }
    
    private void sendEmail() {
        String msg = "Your total came out to: " + total;
        String to = email;
        String from = "it353artshare@gmail.com";
        String host = "smtp.gmail.com";
            
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "587");
            
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("it353artshare@gmail.com", "it353Pass");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
                
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject("Email Receipt");
            message.setContent(msg, "text/html");

            Transport.send(message);
            System.out.println("Sent message successfully!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    private java.util.List<java.lang.String> getCarriers() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        edu.ilstu.it.TextSender port = service.getTextSenderPort();
        return port.getCarriers();
    }

    private void sendSMS(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        edu.ilstu.it.TextSender port = service.getTextSenderPort();
        port.sendSMS(arg0, arg1, arg2);
    }
    
    
}
