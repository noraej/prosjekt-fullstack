package idatt2105.hamsterGroup.fullstackProject.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import idatt2105.hamsterGroup.fullstackProject.Model.User;
import idatt2105.hamsterGroup.fullstackProject.Repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    public void createdUserMail(
            String to) {
        //Optional<User> user = userRepository.findUserByEmail(to);
        SimpleMailMessage message = new SimpleMailMessage();
        String text = "A user has been made on Hamster Roombooking Service for you by an admin. " +
                "You can log in with:\n\n" + /*user.get().getEmail() +*/
                "\n" + /*user.get().getHash() + */"\n\nOn www.localhost:3000 (link tbd)";
        String subject = "Created user on Hamster Roombooking Service";
        message.setFrom("hamster.lyfe@gmail.com");
        message.setTo("ida.trosdahl@gmail.com"); //Skal være to
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
