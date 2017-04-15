package contactList.app.service.messageSender.mail;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
/**
 * Created by комп on 15.04.2017.
 */
public class MailSending {
    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    public void placeOrder() {

        // Do the business calculations...

        // Call the collaborators to persist the order...

        // Create a thread safe "copy" of the template message and customize it
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("eg.krivokon@gmail.com");
        msg.setText(
                "Dear " + ", thank you for placing order. Your order number is ");
        try{
            new MailSender() {
                @Override
                public void send(SimpleMailMessage simpleMailMessage) throws MailException {

                }

                @Override
                public void send(SimpleMailMessage[] simpleMailMessages) throws MailException {

                }
            }.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }
}
