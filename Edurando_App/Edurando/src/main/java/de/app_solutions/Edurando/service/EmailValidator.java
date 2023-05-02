package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmailValidator {
    private final UserProfileRepository userProfileRepository;

    public EmailValidator(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public boolean testMail(String s) {
        String mail = s.substring(s.indexOf("@") + 1);
        mail = mail.substring(0, mail.indexOf("."));
        List<String> emails = Arrays.asList("rauheshaus", "vwa-hochschule", "hdwm", "fhdw", "uni-goettingen", "hfk-bayreuth", "hs-ludwigsburg", "hfmdk-frankfurt", "hs-anhalt", "th-owl", "doz", "abk-stuttgart", "palucca", "mh-luebeck", "hs-osnabrueck", "admin", "uni-konstanz", "hfm-detmold", "hmtm", "pfh", "zhv", "azv-sh", "fhvd-sh", "th-luebeck", "hfg-karlsruhe", "eh-tabor", "thm", "uni-wuppertal", "e-b-z", "esmt", "fh-swf", "s-hochschule", "hfmt-koeln", "hfm-karlsruhe", "hochschule-rhein-waal", "uni", "mhb-fontane", "ku", "thi", "uni-saarland", "ph-heidelberg", "Uni-Konstanz", "thws", "ist-hochschule", "hbk-essen", "fh-diakonie", "th-nuernberg", "uni-jena", "wb-fernstudium", "thu", "hmdk-stuttgart", "uni-kl", "htwk-leipzig", "fom", "hspv", "uni-speyer", "bau", "staff", "fham", "kunstakademie-karlsruhe", "hs-esslingen", "uni-bayreuth", "uni-rostock", "uni-flensburg", "rwth-aachen", "fh-erfurt", "fhws", "europa-uni", "ijk", "ruhr-uni-bochum", "uni-due", "hhu", "udk-berlin", "hs-mittweida", "htw-dresden", "cbs", "addshs-koeln", "hs-emden-leer", "adm", "kh-berlin", "hs-wismar", "bundesbank", "dbuas", "media-hs", "uni-hannover", "hfm", "oth-regensburg", "escp", "diploma", "hfbk", "uni-freiburg", "ph-gmuend", "fh-westkueste", "allensbach-hochschule", "hs-koblenz", "ue-germany", "hfm-berlin", "sdi-muenchen", "hfpol-bw", "khm", "vp-uni", "uni-oldenburg", "uni-muenster", "uni-stuttgart", "hszg", "hawk", "hfmt-hamburg", "freie-hochschule-stuttgart", "fhf", "srh-gesundheitshochschule", "tu-freiberg", "lthh-oberursel", "htwg-konstanz", "hs-ruhrwest", "fhsv", "hs-bremerhaven", "hs-mannheim", "adbk", "jade-hs", "hs-sm", "uni-siegen", "the", "folkwang-uni", "ur", "the-klu", "hff-muc", "hmkw", "uni-bielefeld", "psychologische-hochschule", "hoev-rlp", "burg-halle", "th-ab", "hs-offenburg", "hs-gm", "law-school", "mh-trossingen", "hcu-hamburg", "euro-fh", "hchp", "berlin-international", "medicalschool-berlin", "hs-ansbach", "barenboimsaid", "tu-chemnitz", "hochschule-stralsund", "theologisches-seminar-elstal", "th-deg", "hs-flensburg", "bbw-hochschule", "uni-trier", "mediadesign", "rektorat", "hs-augsburg", "uni-leipzig", "hs-merseburg", "w-hs", "noa-hamburg", "tum", "baptisten", "hfbk-dresden", "macromedia", "fhr", "apollon-hochschule", "hs-schmalkalden", "ltg", "wlh-fuerth", "uni-vechta", "ihl", "hshl", "htwsaar", "hs-harz", "quadriga", "uni-erfurt", "praesidium", "hfk-bw", "thf-fulda", "dhge", "vw", "hs-nb", "fh-guestrow", "hsba", "hfbk-hamburg", "imai", "hep", "hs-rottenburg", "evh-bochum", "touroberlin", "reutlingen-university", "uni-tuebingen", "adbk-nuernberg", "hks-ottersberg", "tu-darmstadt", "hfs-berlin", "sankt-georgen", "medicalschool-hamburg", "akad", "berlin", "dshs-koeln", "health-and-medical-university", "khkt", "kolping-hochschule", "dhbw", "fra-uas", "ib-hochschule", "uni-koblenz", "thh-friedensau", "akademie", "uni-mannheim", "h-da", "hfg-offenbach", "polizei-studium", "constructor", "di-uni", "unibw", "nbs", "uni-luebeck", "hsf", "frankfurt-school", "stud", "hs-gesundheit", "uol", "umwelt-campus", "szrof", "hfoed", "gisma", "hs-nordhausen", "uni-kiel", "hs-mainz", "rptu", "th-ewersbach", "ph-karlsruhe", "kit", "oth-aw", "whu", "hs-heilbronn", "hs-niederrhein", "tu-ilmenau", "fs", "hs-worms", "freie-hochschule-suttgart", "hfmt", "hfjs", "hsu-hh", "hnee", "tiho-hannover", "hfk-heidelberg", "hfph", "th-elstal", "leuphana", "ksh-m", "lmu", "kunstakademie-muenster", "fh-muenster", "eh-darmstadt", "fhvr-zv", "tu-dresden", "rwu", "eh-freiburg", "fh-kiel", "bht", "hs-kempten", "fh-zwickau", "hmt-rostock", "hs-fresenius", "hochschule-bc", "rsh-duesseldorf", "hs-bochum", "mh-freiburg", "ehs-dresden", "hfk-bremen", "victoria-hochschule", "hs-duesseldorf", "brand-university", "hfm-wuerzburg", "mobile-university", "tu-clausthal", "kh-mz", "hfm-nuernberg", "hbksaar", "hbk-bs", "hs-kl", "provadis-hochschule", "hnu", "tu-dortmund", "fh-trier", "hs-aalen", "hof-university", "sw", "hfmdd", "hochschule-trier", "hfm-weimar", "ash-berlin", "hs-bremen", "th-brandenburg", "fu-berlin", "fh-dresden", "uni-kassel", "evlks", "th-reutlingen", "kirchenmusikhochschule", "fliedner-fachhochschule", "hs-fulda", "hochschule-kirchenmusik", "ThH-Friedensau", "hdm-stuttgart", "accadis", "dhfpg", "bzgth", "eh-berlin", "uni-hamburg", "mh-hannover", "kunstakademie-duesseldorf", "cs", "fh-mittelstand", "hs-pforzheim", "university-of-labour", "zu", "ehk-halle", "ph-freiburg", "hft-stuttgart", "th-wildau", "rub", "hochschule", "fhpolbb", "musikhochschule-muenchen", "uni-halle", "hwr-berlin", "verw", "hhl", "hs-rm", "h-ka", "kh-freiburg", "hsw-hameln", "zvw", "fh-hermannsburg", "iu", "staedelschule", "htw-berlin", "srh", "uni-bamberg", "tu-berlin", "bo", "hs-albsig", "karlshochschule", "dekrahochschule", "hs-hannover", "verwaltung", "b-tu", "hfgg", "ktp", "eh-ludwigsburg", "hfoev", "th-bingen", "merz-akademie", "ovgu", "hswt", "stakad", "haw-hamburg", "augustana", "katho-nrw", "hs-coburg", "dhsh", "KHSB-Berlin", "hm", "dhgs-hochschule", "bhh", "justiz", "tu-braunschweig", "hgb-leipzig", "leibniz-fh", "uni-paderborn", "gmx", "hsbi", "h2", "uni-frankfurt", "eufh", "munich-business-school", "h-brs", "arbeitsagentur", "fh-dortmund", "hfpv-hessen", "rfh-koeln", "fh-aachen", "muthesius", "fh-potsdam", "phwt", "tu", "piano4te", "uni-leuphana", "hu-berlin", "uni-marburg", "akkon-hochschule", "ebs", "hamburger-fh", "uni-osnabrueck", "thf-paderborn", "steinbeis-hochschule", "filmuniversitaet", "eah-jena", "uni-weimar", "uni-greifswald", "hfr", "jacobs-university", "uni-bremen", "ipu-berlin", "uni-hildesheim", "ostfalia", "khsb-berlin", "hertie-school", "tuhh", "bht-berlin", "uni-wuerzburg", "th-koeln", "nsi-hsvn", "fernuni-hagen", "hmtm-hannover", "uni-koeln", "uni-potsdam", "hs-doepfer", "uniklinik-freiburg", "hs21", "uni-mainz", "hfg-gmuend", "uni-hohenheim", "Reutlingen-University", "zv", "upb", "hsbund", "uni-wh", "cvjm-hochschule", "businessschool-berlin", "uv", "fh-wedel", "ism", "thga", "evhn", "polizei", "hfwu", "kirchenmusik-dresden", "hs-furtwangen", "ph-ludwigsburg", "nordakademie", "fau", "muho-mannheim", "uni-ulm", "uni-passau", "pth-muenster", "ph-weingarten", "dhpol", "code", "kiho-wuppertal", "uni-bonn", "uni-giessen", "germanistik", "fthgiessen", "zuv", "hwg-lu", "hfkm-regensburg", "hdbw-hochschule", "hmt-leipzig", "th-rosenheim", "hs-kehl", "hsap", "alanus", "haw-landshut", "hfh-fernstudium");

        for (String email : emails) {
            if (mail.contains(email)) return true;
        }
            return false;

    }

    public boolean mailExists(String email) {
        List<UserProfile> users = userProfileRepository.findAll();
        for (UserProfile user : users) {
            if (user.getUsername().equals(email)) return true;
        }
        return false;
    }
}
