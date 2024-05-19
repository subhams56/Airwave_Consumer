package com.airwave.consumer.service;

import com.airwave.consumer.model.EmailModel;
import com.airwave.consumer.utils.ConstantUtils;
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

    private static final String AIRWAVE_CONSUMER_REPORT = "Airwave Consumer Report";
    @Value("${spring.mail.host}")
    private  String hostName;

    private static final String AIRWAVE_CONSUMER_SERVICE = "Airwave Consumer Service";


    JavaMailSender javaMailSender;


    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }






    public void sendEmail(int recordsCount, Boolean status) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo("lucifersubham6@gmail.com");
        helper.setFrom(mailFrom);
        helper.setSubject(AIRWAVE_CONSUMER_REPORT);
        helper.setText(setEmailBody(recordsCount, status), true);
        javaMailSender.send(message);

    }

    private String setEmailBody(int recordsCount, Boolean status) {

        EmailModel em = new EmailModel();
        em.setDate(new Date());
        em.setAppName(AIRWAVE_CONSUMER_SERVICE);
        em.setRecordsCount(recordsCount);
        em.setStatus(status);
        em.setHostName(hostName);
        return emailContentAsHtmlTable(em);
    }

    public String emailContentAsHtmlTable(EmailModel em){
        // Formatting the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");

        // Constructing the HTML table
        StringBuilder html = new StringBuilder();
        html.append("<html><body>");
        html.append("<h2>Application Status Report</h2>");
        html.append("<table border='1' style='border-collapse: collapse; border: 2px solid #000000;'>");  // Thicker border
        html.append("<tr style='background-color: #f2f2f2;'>");  // Header row color
        html.append("<th style='border: 2px solid #000000; padding: 8px;'>Date</th>");
        html.append("<th style='border: 2px solid #000000; padding: 8px;'>Application Name</th>");
        html.append("<th style='border: 2px solid #000000; padding: 8px;'>Records Count</th>");
        html.append("<th style='border: 2px solid #000000; padding: 8px;'>Status</th>");
        html.append("<th style='border: 2px solid #000000; padding: 8px;'>Host Name</th>");
        html.append("</tr>");
        html.append("<tr>");
        html.append(ConstantUtils.TDSTYLE).append(dateFormat.format(em.getDate())).append(ConstantUtils.TD);
        html.append(ConstantUtils.TDSTYLE).append(em.getAppName()).append(ConstantUtils.TD);
        html.append(ConstantUtils.TDSTYLE).append(em.getRecordsCount()).append(ConstantUtils.TD);
        html.append(ConstantUtils.TDSTYLE).append(Boolean.TRUE.equals(em.getStatus()) ? "Success" : "Failure").append(ConstantUtils.TD);
        html.append(ConstantUtils.TDSTYLE).append(em.getHostName()).append(ConstantUtils.TD);
        html.append("</tr>");
        html.append("</table>");
        html.append("<br><br>");
        html.append("<p style='font-size: smaller; color: gray;'>This is an auto-generated, non-monitored email. Please do not reply.</p>");
        html.append("</body></html>");

        return html.toString();

    }


}
