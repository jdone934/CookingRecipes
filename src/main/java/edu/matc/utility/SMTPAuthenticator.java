package edu.matc.utility;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * The type Smtp authenticator.
 */
public class SMTPAuthenticator extends Authenticator
{
    private PasswordAuthentication authentication;

    /**
     * Instantiates a new Smtp authenticator.
     *
     * @param login    the login
     * @param password the password
     */
    public SMTPAuthenticator(String login, String password)
    {
        authentication = new PasswordAuthentication(login, password);
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication()
    {
        return authentication;
    }
}