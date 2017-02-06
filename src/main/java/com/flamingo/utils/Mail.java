package com.flamingo.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * Created by Xhulio on 2/4/2016.
 */
public class Mail {

    private static final String USERNAME = "flamingofshn@gmail.com";
    private static final String PASSWORD = "flam1ngofshn";

    /**
     * Send email
     * @param from
     * @param message
     * @return
     * @throws EmailException
     */
    public static boolean sendMail(String name, String from, String message) throws EmailException {
        try {
            HtmlEmail userMail = new HtmlEmail();
            userMail.setHostName("smtp.gmail.com");
            userMail.setSmtpPort(587);
            userMail.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
            userMail.setStartTLSEnabled(true);
            userMail.getMailSession().getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
            userMail.setFrom(USERNAME);
            userMail.addTo(USERNAME);
            userMail.setSubject("Traffic Support");
            userMail.setHtmlMsg(getHTMLMessage(name, from, message));
            userMail.send();
            return true;
        } catch (EmailException e) {
            return false;
        }
    }

    /**
     * Creates html string
     * @param name Name
     * @param from From
     * @param message Message
     * @return html string
     */
    public static String getHTMLMessage(String name, String from, String message) {
        String body = "<div><b>Name:</b>" + name +
                "<br/><br/><div><b>From:</b> " + from +
                "<br/><br/><div><b>Message:</b> " + message + "<br/><br/>";
        return body;
    }
}
