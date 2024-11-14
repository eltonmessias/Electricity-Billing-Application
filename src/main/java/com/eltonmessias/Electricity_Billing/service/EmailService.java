package com.eltonmessias.Electricity_Billing.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {


    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmailAddress;



    public void sendEmail(String toEmail, String customerName, String subject, Long readingId, double consumptionKWh, LocalDateTime dueDate, double amount) throws MessagingException, MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(fromEmailAddress);
        helper.setTo(toEmail);
        helper.setSubject("Fatura de Energia Elétrica - " + readingId);

        String htmlContent = "<html><body style='font-family: Arial, sans-serif;'>" +
                "<div style='max-width: 600px; margin: auto; padding: 20px; border: 1px solid #ddd; border-radius: 8px;'>" +
                "<h2 style='color: #00466a; text-align: center;'>Fatura de Energia Elétrica</h2>" +
                "<p>Prezado(a) <strong>" + customerName + "</strong>,</p>" +
                "<p>Segue abaixo a sua fatura referente ao consumo de energia elétrica:</p>" +

                "<table style='width: 100%; border-collapse: collapse; margin-top: 20px;'>" +
                "  <tr>" +
                "    <th style='background-color: #00466a; color: #ffffff; padding: 10px; border: 1px solid #ddd;'>Número da Fatura</th>" +
                "    <th style='background-color: #00466a; color: #ffffff; padding: 10px; border: 1px solid #ddd;'>Data de Vencimento</th>" +
                "    <th style='background-color: #00466a; color: #ffffff; padding: 10px; border: 1px solid #ddd;'>Valor</th>" +
                "    <th style='background-color: #00466a; color: #ffffff; padding: 10px; border: 1px solid #ddd;'>Consumo (kWh)</th>" +
                "  </tr>" +
                "  <tr>" +
                "    <td style='padding: 10px; border: 1px solid #ddd; text-align: center;'>" + readingId + "</td>" +
                "    <td style='padding: 10px; border: 1px solid #ddd; text-align: center;'>" + dueDate + "</td>" +
                "    <td style='padding: 10px; border: 1px solid #ddd; text-align: center;'>$ " + amount + "</td>" +
                "    <td style='padding: 10px; border: 1px solid #ddd; text-align: center;'>" + consumptionKWh + " kWh</td>" +
                "  </tr>" +
                "</table>" +

                "<p style='margin-top: 20px;'>Por favor, efetue o pagamento até a data de vencimento para evitar a interrupção do serviço.</p>" +

                "<div style='text-align: center; margin-top: 20px;'>" +
                "  <a href='http://www.example.com/pagamento/" + readingId + "' " +
                "     style='text-decoration: none; padding: 10px 20px; color: #ffffff; background-color: #28a745; border-radius: 5px; font-size: 16px;'>Pagar Fatura</a>" +
                "</div>" +

                "<p style='margin-top: 20px;'>Se precisar de ajuda, entre em contato com o nosso suporte.</p>" +
                "<p>Atenciosamente,</p>" +
                "<p><strong>Sua Empresa de Energia</strong></p>" +
                "<p style='font-size: 12px; color: #888888;'>Este é um e-mail automático. Por favor, não responda.</p>" +
                "</div></body></html>";

        helper.setText(htmlContent, true);

        mailSender.send(mimeMessage);
    }

}
