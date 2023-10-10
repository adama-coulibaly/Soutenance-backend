package com.apiRegion.springjwt.security;

import com.apiRegion.springjwt.models.Commande;
import com.apiRegion.springjwt.models.Formation;
import com.apiRegion.springjwt.models.User;
import com.google.firebase.database.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



@Component
public class EmailConstructor {

    @Autowired
    private Environment env;


    @Autowired
    private TemplateEngine templateEngine;

    public MimeMessagePreparator newformationEmail(User user, Formation formation) {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("formation", formation);
        String text = templateEngine.process("newformationTemplates", context);
        return getMimeMessagePreparator(user, text);
    }

    @NotNull
    private MimeMessagePreparator getMimeMessagePreparator(User user, String text) {
        return new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setPriority(1);
                email.setTo(user.getEmail());
                email.setSubject("Bienvenue dans l'Application My farmed");
                email.setText(text, true);
                email.setFrom(new InternetAddress(env.getProperty("support.email")));
            }
        };
    }

    public MimeMessagePreparator constructResetPasswordEmail(User user, String password) {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("password", password);
        String text = templateEngine.process("resetPasswordEmailTemplate", context);
        return new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setPriority(1);
                email.setTo(user.getEmail());
                email.setSubject("New Password - Orchard");
                email.setText(text, true);
                email.setFrom(new InternetAddress(env.getProperty("support.email")));
            }
        };
    }

    public MimeMessagePreparator constructUpdateUserProfileEmail(User user) {
        Context context = new Context();
        context.setVariable("user", user);
        String text = templateEngine.process("updateUserProfileEmailTemplate", context);
        return mimeMessage -> {
            MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
            email.setPriority(1);
            email.setTo(user.getEmail());
            email.setSubject("Profile Update - Orchard");
            email.setText(text, true);
            email.setFrom(new InternetAddress(env.getProperty("support.email")));
        };
    }

    //=========================================================== COMMANDE

    public MimeMessagePreparator sendMailCommande(User user, Commande commande) {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("commande", commande);
        String text = templateEngine.process("commandes", context);
        return getMimeMessagePreparator(user, text);
    }
}