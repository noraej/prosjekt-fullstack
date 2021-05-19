package idatt2105.hamsterGroup.fullstackProject.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Component for sending mails
 */
@Component
public class EmailComponent {
    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(
            String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("team.hamster@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    /**
     * Creates a formatted "from-to" string with actual date and times
     * @param dateTime start time
     * @param durationMinutes duration of the activity
     * @return formatted String
     */
    private String getTimeAndDateString(LocalDateTime dateTime, int durationMinutes){
        DateTimeFormatter initialDateTimeFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy 'fra' HH:mm 'til' ");
        DateTimeFormatter endingDateTimeFormat = DateTimeFormatter.ofPattern("HH:mm");

        String format1 = dateTime.format(initialDateTimeFormat);
        String format2 = dateTime.plusMinutes(durationMinutes).format(endingDateTimeFormat);

        return format1 + format2;
    }
}
