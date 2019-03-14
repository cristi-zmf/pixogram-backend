package com.cristi.mentool.mentool.infra.dataset;

import com.cristi.mentool.mentool.domain.UniqueId;
import com.cristi.mentool.mentool.domain.mentor.Mentor;
import com.cristi.mentool.mentool.domain.mentor.MentorTraining;
import com.cristi.mentool.mentool.domain.user.EmailAddress;
import com.cristi.mentool.mentool.domain.user.PhoneNumber;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.cristi.mentool.mentool.infra.dataset.SkillsDataset.*;
import static java.util.Arrays.asList;
import static java.util.Collections.singleton;

public class MentorDataSet {
    private static final EmailAddress LINUS_EMAIL_ADDRESS = new EmailAddress("linus.coolguy@cool.com");
    public static Mentor LINUS = new Mentor(
            LINUS_EMAIL_ADDRESS, "Linus", "CoolGuy",
            new PhoneNumber("0722123456"), 10,
            "linkedingURL", linusTrainings(), 100
    );


    private static final EmailAddress EDWARD_ADDRESS = new EmailAddress("edward@edward.com");
    public final static Mentor EDWARD = new Mentor(
            EDWARD_ADDRESS, "Edward", "The last", new PhoneNumber("1234567890"),
            3, "www.linkedin.com", edwardTrainings(), 20
    );

    private static final EmailAddress JOHN_ADDRESS = new EmailAddress("john@john.com");
    public final static Mentor JOHN = new Mentor(
            JOHN_ADDRESS, "John", "The First", new PhoneNumber("4567891234"),
            5, "www.linkedin.com", johnTrainings(), 32
    );

    private static final EmailAddress HERCULES_ADDRESS = new EmailAddress("hercules@power.com");
    public final static Mentor HERCULES = new Mentor(
            HERCULES_ADDRESS, "Hercules", "Over 9000", new PhoneNumber("9000000000"),
            9, "www.linkedin.com", herculesTrainings(), 99
    );

    public static List<Mentor> getAllMentors() {
        return asList(EDWARD, JOHN, HERCULES, LINUS);
    }

    private static Set<MentorTraining> linusTrainings() {
        MentorTraining java = new MentorTraining(new UniqueId(),
                "Hands-on", JAVA.getId(), "Java mega core",
                20, LINUS_EMAIL_ADDRESS,
                new BigDecimal(100)
        );
        return singleton(java);
    }

    private static Set<MentorTraining> herculesTrainings() {
        MentorTraining ultraJava = new MentorTraining(
                new UniqueId(), "Ultra java, too overwhelming for most of us", JAVA.getId(),
                "all the java knowledge in thw world",
                30, MentorDataSet.HERCULES_ADDRESS, new BigDecimal(999)
        );
        MentorTraining ultraMicroservices = new MentorTraining(
                new UniqueId(), "Ultra microservices", MICROSERVICES.getId(), "ultra java",
                29, MentorDataSet.HERCULES_ADDRESS, new BigDecimal(9000)
        );
        MentorTraining ultraKotlin = new MentorTraining(
                new UniqueId(), "Ultra kotlin", KOTLIN.getId(), "ultra KOTLIN",
                9, MentorDataSet.HERCULES_ADDRESS, new BigDecimal(9009)
        );
        MentorTraining ultraPhp = new MentorTraining(
                new UniqueId(), "Ultra php", PHP.getId(), "ultra php",
                9, MentorDataSet.HERCULES_ADDRESS, new BigDecimal(909)
        );
        return new HashSet<>(asList(ultraJava, ultraMicroservices, ultraKotlin, ultraPhp));
    }

    private static Set<MentorTraining> johnTrainings() {
        MentorTraining java = new MentorTraining(
                new UniqueId(), "Pro java training", JAVA.getId(), "java intermediate",
                12, MentorDataSet.JOHN_ADDRESS, new BigDecimal(300)
        );
        MentorTraining microservices = new MentorTraining(
                new UniqueId(), "All about microservices", MICROSERVICES.getId(), "java pro",
                7, MentorDataSet.JOHN_ADDRESS, new BigDecimal(400)
        );
        return new HashSet<>(asList(java, microservices));

    }

    private static Set<MentorTraining> edwardTrainings() {
        MentorTraining javaTraining = new MentorTraining(
                new UniqueId(), "Cool java training", JAVA.getId(), "java core",
                10, MentorDataSet.EDWARD_ADDRESS, new BigDecimal(200)
        );
        return singleton(javaTraining);
    }


}
