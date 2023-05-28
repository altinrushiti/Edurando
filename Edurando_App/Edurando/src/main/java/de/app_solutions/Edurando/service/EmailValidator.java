package de.app_solutions.Edurando.service;

import de.app_solutions.Edurando.model.UserProfile;
import de.app_solutions.Edurando.repository.UserProfileRepository;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmailValidator {


    private final UserProfileRepository userProfileRepository;

    public EmailValidator(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public Pair<Boolean, String> testMail(String email) {

        boolean rs = true;
        StringBuilder sb = new StringBuilder();

        String[] split = email.split("@");
        List<String> allowedDomains = Arrays.asList("vp-uni.de", "uni-leipzig.de", "apollon-hochschule.de", "jacobs-university.de", "thi.de", "fhdw.de", "uni-tuebingen.de", "fau.de", "uni-konstanz.de", "intra.udk-berlin.de", "hfk-bayreuth.de", "escp.eu", "dhgs-hochschule.de", "htwsaar.de", "uni-muenster.de", "law-school.de", "hfmt-koeln.de", "ihl.eu", "hs-bochum.de", "ThH-Friedensau.de", "zuv.tu-freiberg.de", "tu-clausthal.de", "dhge.de", "eh-darmstadt.de", "reutlingen-university.de", "zvw.uni-goettingen.de", "infraserv.com", "rektorat.uni-heidelberg.de", "tiho-hannover.de", "hs-gm.de", "fh-potsdam.de", "uni-vechta.de", "kom.fra-uas.de", "hmt-leipzig.de", "hs-ansbach.de", "pth-muenster.de", "fernuni-hagen.de", "ash-berlin.eu", "uni-flensburg.de", "hsf.sachsen.de", "kunstakademie-duesseldorf.de", "rptu.de", "karlshochschule.org", "hfwu.de", "w-hs.de", "evh-bochum.de", "ku.de", "oth-aw.de", "pvw.uni-frankfurt.de", "folkwang-uni.de", "verwaltung.tu-chemnitz.de", "uni-heidelberg.de", "ur.de", "hft-stuttgart.de", "hochschule-bc.de", "uni-due.de", "tu-ilmenau.de", "hs-koblenz.de", "s-hochschule.de", "karlshochschule.de", "hmtm.de", "kunstakademie-\nduesseldorf.de", "hs-ruhrwest.de", "hfk-heidelberg.de", "hs-hannover.de", "hs-furtwangen.de", "unibw.de", "verw.uni-koeln.de", "iu.de", "hspv.nrw.de", "di-uni.de", "hfbk-dresden.de", "adbk.mhn.de", "khsb-berlin.de", "uni-hohenheim.de", "bht-berlin.de", "fh-zwickau.de", "hs-bremerhaven.de", "hs-duesseldorf.de", "hchp.de", "uni-greifswald.de", "hs-rottenburg.de", "fh-kiel.de", "hfjs.eu", "uni-siegen.de", "rwu.de", "ksh-m.de", "dshs-koeln.de", "hs-wismar.de", "uni-bamberg.de", "uni-luebeck.de", "uni-bayreuth.de", "dhfpg.de", "iu.org", "hs-nb.de", "ostfalia.de", "hfmdk-frankfurt.de", "thga.de", "hs-emden-leer.de", "hfg-offenbach.de", "kh-freiburg.de", "boulezsaal.de", "Fernuni-Hagen.de", "hswt.de", "hs-nordhausen.de", "hsu-hh.de", "rektorat.uni-halle.de", "mh-hannover.de", "uni-trier.de", "kiho-wuppertal.de", "cvjm.de", "ovgu.de", "kh-mz.de", "th-ab.de", "uv.uni-kiel.de", "hmkw.de", "hff-muc.de", "bo.drs.de", "hwr-berlin.de", "uni-weimar.de", "hbksaar.de", "hfmt.hamburg.de", "ism.de", "ph-ludwigsburg.de", "hfmdd.de", "media-gmbh.de", "serv1.hfbk-dresden.de", "zv.uni-paderborn.de", "mh-trossingen.de", "hs-heilbronn.de", "uni-koblenz-landau.de", "hs-mainz.de", "verw.thm.de", "h-ka.de", "hs-anhalt.de", "munich-business-school.de", "presse.uni-augsburg.de", "hszg.de", "htwk-leipzig.de", "hs-flensburg.de", "staedelschule.de", "hs-kl.de", "mh-luebeck.de", "ib-hochschule.de", "stud.th-luebeck.de", "esmt.org", "hs-coburg.de", "uni-potsdam.de", "hs-mittweida.de", "hochschule-stralsund.de", "hfm-detmold.de", "hbk-bs.de", "mhb-fontane.de", "haw-hamburg.de", "hwg-lu.de", "tuhh.de", "hfs-berlin.de", "htw-dresden.de", "alanus.edu", "hhl.de", "fh-dortmund.de", "uni-halle.de", "verw.hs-fulda.de", "htwg-konstanz.de", "hs-bremen.de", "kirchenmusikhochschule.de", "hfk-bw.de", "macromedia.de", "fs.de", "akad.de", "ph-gmuend.de", "nordakademie.de", "FH-Kiel.de", "hs-rm.de", "hs-niederrhein.de", "gusgermany.de", "akademie.polizei.niedersachsen.de", "fliedner-fachhochschule.de", "uni-giessen.de", "udk-berlin.de", "kunstakademie-muenster.de", "filmuniversitaet.de", "tu-dresden.de", "fh-wedel.de", "hs-fresenius.de", "vw.ph-heidelberg.de", "fh-aachen.de", "uni-hamburg.de", "augustana.de", "uni-saarland.de", "psychologische-hochschule.de", "univw.uni-saarland.de", "victoria-hochschule.de", "zv.uni-leipzig.de", "hbk-essen.de", "hs-mannheim.de", "hmtm-hannover.de", "akkon-hochschule.de", "hs-pforzheim.de", "hdbw-hochschule.de", "tu-darmstadt.de", "europa-uni.de", "uni-wuerzburg.de", "uni-bonn.de", "eh-berlin.de", "th-reutlingen.de", "ehs-dresden.de", "hks-ottersberg.de", "fra-uas.de", "bhh.hamburg.de", "th-owl.de", "hfm.saarland.de", "fthgiessen.de", "tum.de", "ipu-berlin.de", "hs-osnabrueck.de", "vw.ph-weingarten.de", "eah-jena.de", "hamburger-fh.de", "zhv.rwth-aachen.de", "hfm-karlsruhe.de", "hmdk-stuttgart.de", "hgb-leipzig.de", "khm.de", "presse.uni-siegen.de", "muthesius.de", "hs-merseburg.de", "fu-berlin.de", "thws.de", "hs-esslingen.de", "uni-bremen.de", "cbs.de", "th-rosenheim.de", "hdwm.org", "diakonieneuendettelsau.de", "haw-landshut.de", "hfm-berlin.de", "sdi-muenchen.de", "abk-stuttgart.de", "merz-akademie.de", "bethel.de", "rsh-duesseldorf.de", "zuv.uni-hannover.de", "uni-mannheim.de", "hkom.uni-stuttgart.de", "th-nuernberg.de", "hnu.de", "uni-osnabrueck.de", "tu-braunschweig.de", "hs-schmalkalden.de", "th-brandenburg.de", "hfk-bremen.de", "steinbeis-hochschule.de", "uni-jena.de", "mh-freiburg.de", "sankt-georgen.de", "kunstakademie-karlsruhe.de", "katho-nrw.de", "adbk-nuernberg.de", "the-klu.org", "kh-berlin.de", "oth-regensburg.de", "diploma.de", "wb-fernstudium.de", "hnee.de", "th-wildau.de", "uni-frankfurt.de", "fh-dresden.eu", "uni-ulm.de", "phwt.de", "hs21.de", "zu.de", "hfm-weimar.de", "hof-university.de", "ebs.edu", "uni-kassel.de", "EHK-Halle.de", "hdm-stuttgart.de", "hfg-karlsruhe.de", "kit.edu", "eh-ludwigsburg.de", "euro-fh.de", "hs-albsig.de", "lmu.de", "uni-bielefeld.de", "hs-worms.de", "touroberlin.de", "fh-mittelstand.de", "whu.edu", "uni-rostock.de", "hsbi.de", "hfbk.hamburg.de", "uni-wh.de", "ph-freiburg.de", "rfh-koeln.de", "phb.de", "htw-berlin.de", "eh-tabor.de", "vw.hcu-hamburg.de", "muho-mannheim.de", "palucca.eu", "fh-muenster.de", "uni-hildesheim.de", "thf-paderborn.de", "publiccologne.de", "pfh.de", "fh-swf.de", "hhu.de", "uni-hannover.de", "jade-hs.de", "hu-berlin.de", "pr.uni-freiburg.de", "fhsmp.de", "businessschool-berlin.de", "TU-Berlin.de", "nbs.de", "rauheshaus.de", "uni-marburg.de", "th-koeln.de", "tu-dortmund.de", "hfkm-regensburg.de", "rub.de", "fh-westkueste.de", "h2.de", "uni-goettingen.de", "hmt-rostock.de", "leuphana.de", "hfm-wuerzburg.de", "h-brs.de", "fh-erfurt.de", "eh-freiburg.de", "hawk.de", "berlin-international.de", "hs-gesundheit.de", "hs-kempten.de", "srh.de", "hsba.de", "uni-passau.de", "fham.de", "b-tu.de", "hs-harz.de", "th-bingen.de", "fom.de", "medicalschool-berlin.de", "fh-trier.de", "vw.ph-karlsruhe.de", "hfph.de", "hfg-gmuend.de", "evhn.de", "e-b-z.de", "freie-hochschule-stuttgart.de", "hs-offenburg.de", "thu.de", "hs-aalen.de", "uol.de", "khkt.de", "lthh-oberursel.de", "tu-chemnitz.de", "bbw-hochschule.de", "uni-mainz.de", "hsw-hameln.de", "hs-augsburg.de", "mediadesign.de", "hm.edu", "hertie-school.org", "uni-wuppertal.de", "medicalschool-hamburg.de", "mobile-university.de", "th-deg.de", "burg-halle.de", "uni-erfurt.de", "h-da.de", "ist.de", "hshl.de", "uni-speyer.de");
        List<UserProfile> users = userProfileRepository.findAll();

        if (!allowedDomains.contains(split[1])) {
            sb.append( "Email is not valid.");
            rs = false;
        }

        for (UserProfile user : users) {
            if (user.getUsername().equalsIgnoreCase(email)) {
                sb.append( "Email is not unique.");
                rs = false;
                break; // keine weitere Überprüfung erforderlich, wenn eine Übereinstimmung gefunden wurde
            }
        }

        return Pair.of(rs, sb.toString());

    }
}
