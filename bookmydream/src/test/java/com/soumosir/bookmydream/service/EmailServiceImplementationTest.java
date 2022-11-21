package com.soumosir.bookmydream.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmailServiceImplementation.class})
@ExtendWith(SpringExtension.class)
class EmailServiceImplementationTest {
    @Autowired
    private EmailServiceImplementation emailServiceImplementation;

    @MockBean
    private JavaMailSender javaMailSender;

    /**
     * Method under test: {@link EmailServiceImplementation#sendEmail(String, String, String)}
     */
    @Test
    void testSendEmail() throws MailException {
        doNothing().when(this.javaMailSender).send((MimeMessage) any());
        when(this.javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        this.emailServiceImplementation.sendEmail("janedoe", "jane.doe@example.org", "Code");
        verify(this.javaMailSender).createMimeMessage();
        verify(this.javaMailSender).send((MimeMessage) any());
    }

    /**
     * Method under test: {@link EmailServiceImplementation#sendEmail(String, String, String)}
     */
    @Test
    void testSendEmail2() throws MailException {
        doThrow(new RuntimeException("An error occurred")).when(this.javaMailSender).send((MimeMessage) any());
        when(this.javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        assertThrows(RuntimeException.class,
                () -> this.emailServiceImplementation.sendEmail("janedoe", "jane.doe@example.org", "Code"));
        verify(this.javaMailSender).createMimeMessage();
        verify(this.javaMailSender).send((MimeMessage) any());
    }

    /**
     * Method under test: {@link EmailServiceImplementation#sendEmail(String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSendEmail3() throws MailException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at org.springframework.mail.javamail.MimeMessageHelper.setFrom(MimeMessageHelper.java:567)
        //       at org.springframework.mail.javamail.MimeMessageHelper.setFrom(MimeMessageHelper.java:577)
        //       at com.soumosir.bookmydream.service.EmailServiceImplementation.sendEmail(EmailServiceImplementation.java:29)
        //   In order to prevent sendEmail(String, String, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   sendEmail(String, String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        doNothing().when(this.javaMailSender).send((MimeMessage) any());
        when(this.javaMailSender.createMimeMessage()).thenReturn(null);
        this.emailServiceImplementation.sendEmail("janedoe", "jane.doe@example.org", "Code");
    }

    /**
     * Method under test: {@link EmailServiceImplementation#sendEmail(String, String, String)}
     */
    @Test
    void testSendEmail4() throws MailException {
        doNothing().when(this.javaMailSender).send((MimeMessage) any());
        when(this.javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        assertThrows(RuntimeException.class,
                () -> this.emailServiceImplementation.sendEmail("janedoe", "Online Movie Booking Application Support", "Code"));
        verify(this.javaMailSender).createMimeMessage();
    }
}

