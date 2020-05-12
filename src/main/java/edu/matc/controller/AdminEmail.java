package edu.matc.controller;

import edu.matc.entity.Role;
import edu.matc.persistence.GenericDao;
import edu.matc.utility.SMTPAuthenticator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

@WebServlet(
        urlPatterns = {"/adminOnly/email"}
)
public class AdminEmail extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private Properties properties;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        if (message != null) {
            req.setAttribute("message", message);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/adminEmail.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emailGroup = req.getParameter("emailGroup");
        String emailMessage = req.getParameter("message");
        String emailSubject = req.getParameter("subject");
        String errorMessage;

        GenericDao roleDao = new GenericDao(Role.class);

        List<Role> rolesOfGroup = roleDao.getByPropertyLike("roleName", emailGroup);
        Set<String> emails = new HashSet<>();

        for (Role role : rolesOfGroup) {
            emails.add(role.getUser().getEmail());
        }

        if (emails.size() > 0) {
            loadProperties();
            String fromEmail = properties.getProperty("fromEmail");

            String from = fromEmail;
            String subject = emailSubject;
            String message = emailMessage;
            String login = fromEmail;
            String password = properties.getProperty("password");

            Properties props = new Properties();
            props.setProperty("mail.host", properties.getProperty("host"));
            props.setProperty("mail.smtp.port", properties.getProperty("port"));
            props.setProperty("mail.smtp.auth", properties.getProperty("auth"));
            props.setProperty("mail.smtp.starttls.enable", properties.getProperty("tlsEnable"));

            try
            {
                Authenticator auth = new SMTPAuthenticator(login, password);

                Session session = Session.getInstance(props, auth);

                MimeMessage msg = new MimeMessage(session);

                try
                {
                    msg.setText(message);
                    msg.setSubject(subject);
                    msg.setFrom(new InternetAddress(from));
                    for (String email : emails) {
                        msg.addRecipient(Message.RecipientType.BCC,
                                new InternetAddress(email));
                    }
                    Transport.send(msg);
                    logger.info("Email sent to: " + emails);
                }
                catch (MessagingException ex)
                {
                    ex.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            errorMessage = "There were no email addresses to send to";
        }

        errorMessage = "Email message sent!";

        resp.sendRedirect("/CookingRecipes/adminOnly/email?message=" + errorMessage);
    }

    private void loadProperties() {
        properties = new Properties();
        try {
            properties.load (this.getClass().getResourceAsStream("/email.properties"));
        } catch (IOException ioe) {
            System.out.println("Database.loadProperties()...Cannot load the properties file");
            ioe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Database.loadProperties()..." + e);
            e.printStackTrace();
        }

    }
}
