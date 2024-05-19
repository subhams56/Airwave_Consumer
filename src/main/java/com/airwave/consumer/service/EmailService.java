package com.airwave.consumer.service;

import com.airwave.consumer.model.EmailModel;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EmailService {


    @Value("${spring.mail.username}")
    private  String mailFrom;

    private final static String subject = "Airwave Consumer Report";
    @Value("${spring.mail.host}")
    private  String hostName;

    private static final String appName = "Airwave Consumer Service";

    @Autowired
    JavaMailSender javaMailSender;






    public void sendEmail(int recordsCount, Boolean status) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo("lucifersubham6@gmail.com");
        helper.setFrom(mailFrom);
        helper.setSubject(subject);
        helper.setText(setEmailBody(recordsCount, status), true);
        javaMailSender.send(message);

    }

    private String setEmailBody(int recordsCount, Boolean status) {

        EmailModel em = new EmailModel();
        em.setDate(new Date());
        em.setAppName(appName);
        em.setRecordsCount(recordsCount);
        em.setStatus(status);
        em.setHostName(hostName);
        return emailContentAsHtmlTable(em);
    }

    public String emailContentAsHtmlTable(EmailModel em){
        // Formatting the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Constructing the HTML table
        StringBuilder html = new StringBuilder();
        html.append("<html><body>");
        html.append("<h2>Application Status Report</h2>");
        html.append("<table border='1' style='border-collapse: collapse;'>");
        html.append("<tr><th>Date</th><th>Application Name</th><th>Records Count</th><th>Status</th><th>Host Name</th></tr>");
        html.append("<tr>");
        html.append("<td>").append(dateFormat.format(em.getDate())).append("</td>");
        html.append("<td>").append(em.getAppName()).append("</td>");
        html.append("<td>").append(em.getRecordsCount()).append("</td>");
        html.append("<td>").append(em.getStatus() ? "Success" : "Failure").append("</td>");
        html.append("<td>").append(em.getHostName()).append("</td>");
        html.append("</tr>");
        html.append("</table>");
        html.append("</body></html>");

        return html.toString();

    }


}
