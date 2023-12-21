package com.BLI.Services;

import com.BLI.Domain.Email;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private EmployeeService employeeService;
    public void sendMail(Email email){

        String matter =
                "\n" +
                "Greetings from BLUE LIGHT INNOVATIONS!\n" +
                "\n" +
                " \n" +
                "\n" +
                "Further to welcome mail communication, we would like to share the credentials for BLI platform. Request you to go through the instructions mentioned below and navigate to  portal.\n" +
                "\n" +
                " \n" +
                "\n" +
                "URL & Login Credentials for accessing the BLI Portal:\n" +
                "\n" +
                "\n" +
                "BLI URL:  http://localhost:4200  (Please use Chrome / Edge/ Firefox Browsers)\n" ;

           String endMatter =
                " \n" +
                "\n" +
                "Request you to kindly update your profile details under My Profile section after the first login.\n" +
                "\n" +

                "Still got queries. Please do write to feedback  at support@BLI.com and the team will assist you with any clarification. (Monday to Friday 10:00 hours to 18:30 hours IST).\n" +
                "\n" +
                " \n" +
                "\n" +
                " \n" +
                "\n" +
                " All the best.";

           String employeeName = employeeService.getEmployeeByEmailId(email.getSendTo()).getFirstName()+" "+ employeeService.getEmployeeByEmailId(email.getSendTo()).getLastName();
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom("bairaboinamahesh1316@gmail.com");
            helper.setTo(email.getSendTo());
            helper.setSubject("BLI LOGIN DETAILS");
        //    helper.setText("username: "+ employeeService.getEmployeeByEmailId(email.getSendTo()).getUserName() + " password: " + employeeService.getEmployeeByEmailId(email.getSendTo()).getPassword() );

            helper.setText("Dear "+ employeeName+ "\n" + "\n"+ matter+ "Login ID: " + employeeService.getEmployeeByEmailId(email.getSendTo()).getEmailId() + "\n" + "\n" + " password: " + employeeService.getEmployeeByEmailId(email.getSendTo()).getPhoneNumber()+ "\n" + "\n" + endMatter );




            javaMailSender.send(message);
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
}
