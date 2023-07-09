# Dokumentation

## Inhaltsverzeichnis

- [Dokumentation](#dokumentation)
  - [Inhaltsverzeichnis](#inhaltsverzeichnis)
  - [Softwarearchitektur und „schwierige“ Implementierungsprobleme](#softwarearchitektur-und-schwierige-implementierungsprobleme)
  - [Testkonzept](#testkonzept)
  - [DoD](#dod)
  - [Branching Modell](#branching-modell)
  - [Test-, Buildautomatisierung, CI](#test--buildautomatisierung-ci)
  - [Schätzung am Anfang (steht im Sprint-Protokoll?)](#schätzung-am-anfang-steht-im-sprint-protokoll)
  - [Schätzung am Ende (steht im Sprint-Protokoll?)](#schätzung-am-ende-steht-im-sprint-protokoll)
  - [Burndown-Chart](#burndown-chart)
  - [Velocity des Teams](#velocity-des-teams)
  - [Reflektion über Probleme im Sprint (steht im Sprint-Protokoll?)](#reflektion-über-probleme-im-sprint-steht-im-sprint-protokoll)
  - [Gruppenprotokoll (steht im Sprint-Protokoll?)](#gruppenprotokoll-steht-im-sprint-protokoll)
  - [Usability](#usability)

## Softwarearchitektur und „schwierige“ Implementierungsprobleme

Die Softwarearchitektur des Projekts umfasst eine Vielzahl von Komponenten, die gemeinsam arbeiten, um eine robuste und leistungsstarke Anwendung zu ermöglichen. Das Fundament dieser Architektur bildet das Backend-Framework Spring Boot. Mit seiner Unterstützung können Entwickler effizient und schnell das Backend der Anwendung entwickeln. Spring Boot bietet eine Vielzahl von Funktionen und Erweiterungen, die die Entwicklung erleichtern und die Wartbarkeit der Anwendung verbessern.

Um die Daten der Anwendung zu speichern, wird die relationale Datenbank Postgresql verwendet. Diese bewährte Datenbanktechnologie bietet eine solide Grundlage für die Speicherung und Abfrage von Daten. Mit Postgresql können komplexe Datenmodelle effizient modelliert und verwaltet werden, um die Anforderungen der Anwendung zu erfüllen.

Für das Frontend-Design setzt die Architektur auf das CSS-Framework Tailwind. Mit Tailwind können Entwickler schnell und einfach attraktive Benutzeroberflächen gestalten. Das Framework bietet eine umfangreiche Sammlung von vorgefertigten Komponenten und Klassen, die es ermöglichen, das Erscheinungsbild des Frontends flexibel anzupassen.
Die Entwicklung des Frontends erfolgt unter Verwendung des JavaScript-Frameworks Vue.js. Vue.js ist eine leistungsfähige und flexible Plattform für die Entwicklung von interaktiven Benutzeroberflächen. Es ermöglicht die Erstellung von reaktiven Komponenten und erleichtert die Integration mit dem Backend der Anwendung. Vue.js bietet Entwicklern eine intuitive Syntax und umfangreiche Bibliotheken, um komplexe Frontend-Logik zu implementieren und eine nahtlose Benutzererfahrung zu gewährleisten.

Durch die Kombination dieser Komponenten in der Softwarearchitektur entsteht eine robuste und skalierbare Anwendung. Die klare Trennung von Backend und Frontend ermöglicht es den Entwicklern, sich auf ihre jeweiligen Aufgaben zu konzentrieren und effizient zusammenzuarbeiten. Die Verwendung bewährter Frameworks wie Spring Boot, Postgresql, Tailwind und Vue.js stellt sicher, dass die Anwendung auf einer soliden Basis aufbaut und den Anforderungen der Benutzer gerecht wird.

## Testkonzept

Das Testkonzept für das Projekt setzt sich aus mehreren wichtigen Punkten zusammen, um eine umfassende Testabdeckung und eine hohe Qualität der Anwendung zu gewährleisten.

Ein entscheidender Aspekt des Testkonzepts ist die Code-Coverage. Ziel ist es, sowohl im Backend als auch im Frontend eine Testabdeckung von mindestens 70% zu erreichen. Dies bedeutet, dass mindestens 70% des Quellcodes durch Tests abgedeckt werden sollen, um potenzielle Fehler und Lücken zu identifizieren und zu beseitigen.

Für das Backend werden zwei Arten von Tests durchgeführt. Zum einen werden Postman-Tests eingesetzt, um die Schnittstellen und Endpunkte der API zu überprüfen und sicherzustellen, dass sie korrekt funktionieren. Diese Tests ermöglichen es, Anfragen an die API zu senden und die erwarteten Antworten zu validieren. Zum anderen werden JUnit-Tests verwendet, um einzelne Komponenten und Funktionen im Backend auf ihre Funktionalität und Zuverlässigkeit zu prüfen. Dies umfasst Tests für spezifische Methoden, Verarbeitungsfunktionen und andere Backend-Logik.

Zusätzlich zu den genannten Tests wird das Projektteam auch manuelle Tests durchführen, um sicherzustellen, dass alle Funktionen aus Benutzersicht korrekt funktionieren und eine nahtlose Benutzererfahrung bieten.

Durch dieses Testkonzept wird das Projekt in der Lage sein, eine robuste, fehlerfreie und zuverlässige Anwendung bereitzustellen, die den Anforderungen und Erwartungen der Benutzer entspricht.

## DoD

Das Projekt hat erfolgreich alle gestellten Anforderungen vollständig implementiert und alle Funktionen der Anwendung funktionieren wie erwartet. Durch die umfassende Entwicklung von Unit Tests wurde sichergestellt, dass der Code zuverlässig und fehlerfrei arbeitet, da alle Tests erfolgreich durchlaufen wurden.

Sowohl die funktionalen als auch die nicht-funktionalen Anforderungen wurden erfüllt, und die Akzeptanzkriterien der User Stories wurden erfolgreich umgesetzt. Dadurch entspricht die Anwendung den Erwartungen und Anforderungen der Nutzer.

Der entwickelte Code wurde ordnungsgemäß im Versionskontrollsystem (Repository) eingecheckt und ist somit dokumentiert und für das gesamte Team verfügbar.

Es wurden keine kritischen Bugs oder offenen Fehler identifiziert, was darauf hinweist, dass das Projekt gründlich getestet wurde und potenzielle Probleme frühzeitig behoben wurden.

Der Code wurde nicht nur von einem, sondern von mehreren Teammitgliedern überprüft und genehmigt, um sicherzustellen, dass er den gemeinsamen Qualitätsstandards und den Best Practices entspricht.

Im Anschluss wurde der Code in einem stabilen Build oder einem entsprechenden Branch erfolgreich integriert, um sicherzustellen, dass alle Änderungen nahtlos zusammenarbeiten und keine Konflikte entstehen.

Schließlich wurde der Code von einem Continuous Integration (CI)-System automatisch gebaut und getestet. Dies ermöglicht eine automatisierte Überprüfung des Codes nach jeder Integration und trägt dazu bei, dass die Anwendung stets auf einem stabilen und funktionierenden Stand bleibt. Dadurch wird auch sichergestellt, dass das Projektteam sofort über potenzielle Probleme oder Fehler informiert wird und diese schnell beheben kann, bevor sie sich auf die Gesamtintegrität der Anwendung auswirken.

Insgesamt hat das Projektteam erfolgreich eine hochwertige, voll funktionsfähige und zuverlässige Anwendung entwickelt, die den hohen Qualitätsstandards des Testkonzepts entspricht.

## Branching Modell

In diesem Projekt haben wir uns für die Verwendung des Gitflow-Branching-Modells entschieden, um eine strukturierte und effiziente Arbeitsweise zu gewährleisten. Das Modell besteht aus mehreren Hauptzweigen, die klare Funktionen und Zwecke haben.

Der Hauptzweig "master" ist der zentrale Branch, der die stabile Produktionsversion unserer Anwendung enthält. Alle Änderungen, die in diesem Branch landen, müssen sorgfältig überprüft und getestet werden, um die Qualität und Stabilität der Anwendung zu gewährleisten. Neue Versionen werden von hier aus veröffentlicht.

Der Branch "develop" dient als Entwicklungsbranch. Von hier aus werden alle neuen Features, Verbesserungen und Änderungen abgezweigt. Der "develop"-Branch repräsentiert den neuesten Stand der Entwicklung und enthält die gesamte Arbeit, die für die nächste geplante Version geplant ist.

Für die Entwicklung neuer Funktionen verwenden wir den Namenskonvention "feature/{name}" für die Feature-Branches. Diese Branches werden von "develop" abgezweigt und enthalten alle notwendigen Änderungen, um die spezifische Funktionalität zu implementieren. Sobald ein Feature vollständig entwickelt, getestet und von anderen Teammitgliedern geprüft wurde, wird es in den "develop"-Branch zurückgeführt.

Im Falle von kritischen Fehlern oder Bugs in der Produktionsversion verwenden wir die Branches mit der Namenskonvention "hotfix/{name}". Diese Hotfix-Branches werden direkt vom "master"-Branch abgezweigt, um die Fehler zu beheben. Sobald der Hotfix entwickelt und getestet wurde, wird er direkt in den "master"- und den "develop"-Branch zurückgeführt, um sicherzustellen, dass die Fehlerbehebung sowohl in der aktuellen Produktionsversion als auch in der nächsten Version enthalten ist.

Durch dieses Gitflow-Branching-Modell gewährleisten wir eine klare Strukturierung und eine geordnete Entwicklung unserer Anwendung. Es hilft uns, neue Funktionen nahtlos in die Entwicklung zu integrieren, kritische Fehler schnell zu beheben und gleichzeitig eine stabile und zuverlässige Produktionsversion aufrechtzuerhalten.

## Test-, Buildautomatisierung, CI

1. Technologie-Stack:
   [Liste der verwendeten Technologien, Tools und Frameworks für Testautomatisierung, Build-Automatisierung und CI einfügen.]

2. Verzeichnisstruktur und Organisationsansatz:
   [Beschreibung der Struktur des Projekts, insbesondere in Bezug auf die automatisierten Tests und Build-Skripte.]

3. Testautomatisierung:
   - Automatisierte Testarten:
     [Erklärung, welche Arten von Tests automatisiert sind, z. B. Unit-Tests, Integrationstests, UI-Tests.]

   - Testframeworks und Ausführung:
     [Informationen darüber, wie automatisierte Tests geschrieben werden, welche Testframeworks verwendet werden und wie sie ausgeführt werden.]

4. Build-Automatisierung:
   - Build-Prozess:
     [Beschreibung des Build-Prozesses, wie z. B. Kompilieren, Verpacken und Bereitstellen der Anwendung.]

   - Build-Tools und Konfiguration:
     [Verwendung von Build-Tools (z. B. Maven, Gradle) und deren Konfiguration.]

5. CI-Konfiguration:
   [Erklärung, wie die Continuous Integration eingerichtet ist und wie sie funktioniert. Welche Build-Jobs werden automatisch ausgeführt, und in welcher Reihenfolge?]

6. Testsuite-Ausführung:
   [Beschreibung, wie die Tests in der CI-Pipeline ausgeführt werden und wie die Testergebnisse gesammelt und interpretiert werden. Was passiert im Fehlerfall?]

7. Testdatenmanagement:
   [Wie werden Testdaten erstellt und verwaltet, um wiederholbare und zuverlässige Tests sicherzustellen?]

8. Sicherheitsaspekte:
   [Berücksichtigung von Zugriffsrechten und Sicherheitsaspekten in der CI-Umgebung.]

9. Skalierung und Performance:
    [Falls relevant, wie wird die Skalierbarkeit der Test- und Buildautomatisierung sichergestellt?]

10. Troubleshooting und FAQs:
    [Häufige Probleme und Fehlerquellen bei der Test- und Buildautomatisierung und mögliche Lösungen.]

11. Ressourcen und Links:
    [Verweise auf externe Ressourcen, Tools oder Tutorials, die hilfreich sein könnten.]

12. Änderungsprotokoll:
    [Eine Übersicht über die Änderungen und Verbesserungen der Test- und Buildautomatisierung im Laufe der Zeit.]

## Schätzung am Anfang (steht im Sprint-Protokoll?)
1. Sprint:
- Projekt-Preparation(Backlog, Rest-API einrichten, Workflow definieren,...) = 70 SP

2. Sprint:
- Registrieren: 69 SP
- Profil bearbeiten: 63 SP

3. Sprint:
Anmelden: 45 SP
Profile durchstöbern: SP 46
Abmelden: 13 SP

4. Sprint:
Startseite durchsuchen: 52 SP
Passwort vergessen: 31 SP
Chatfenster zwischen Student und Lehrer: 55 SP


## Schätzung am Ende (steht im Sprint-Protokoll?)
1. Sprint:
Projekt-Preparation(Backlog, Rest-API einrichten, Workflow definieren,...) = 73 SP

2. Sprint:
Registrieren: 85 SP
Profil bearbeiten: 79 SP

3. Sprint:
Anmelden: 70 SP
Profile durchstöbern: SP 63
Abmelden: 21 SP

4. Sprint:
Startseite durchsuchen: 52 SP
Passwort vergessen: 60 SP
Chatfenster zwischen Student und Lehrer: 55 SP


## Burndown-Chart

## Velocity des Teams

## Reflektion über Probleme im Sprint (steht im Sprint-Protokoll?)

## Gruppenprotokoll (steht im Sprint-Protokoll?)

## Usability

Usability-Tests wurden durchgeführt, um die Benutzerfreundlichkeit unserer Anwendung zu bewerten und mögliche Probleme in der Benutzererfahrung zu identifizieren. Dazu wurde ein Aufgabenblatt erstellt (in unserem Repository zu finden als *Usability_Test_Aufgaben.md*), das die Anwendungstester durchgearbeitet haben. Währenddessen wurden Auffälligkeiten und Probleme von uns protokolliert. Dabei sind wir zu folgenden Ergebnissen gekommen:

Die Nutzer fanden sich insgesamt gut auf der Website zurecht. Funktionen waren über Icons, Titel und deren durchdachten Anordnungen leicht zu finden. Probleme in unserer Software ergaben sich jedoch vor allem in Fehlermeldungen. Bei der Registrierung eines neuen Nutzers enstanden Unklarheiten zu den Fehlermeldungen zu Passwortanforderungen. Diese haben den Testenden nicht klar genug aufgezeigt, welche Probleme in ihrem gewählten Passwort vorlagen. Auch gab es keinerlei Hinweise darauf, dass bei der Registrierung eine Studenten-E-Mail-Adresse statt einer *gewöhnlichen* verwendet werden muss.

Auch Fehlermeldungen zur Adresse waren uneindeutig und haben für viel Verwirrung gesorgt. Teilweise wurden auch Fehlermeldungen in der Profilpersonalisierung so kurz angezeigt, dass die Nutzer auf diese nicht rechtzeitig reagieren konnten. Außerdem fehlten auch Bestätigungsmeldungen, z. B. wenn man seine Rolle erfolgreich gewechselt hat (Student -> Teacher).

Auf der Startseite findet man Kärtchen zu den Lehrerprofilen. Hier war das Problem, dass das *X* zum Schließen einer solchen Karte zu klein war und Testende dabei teilweise mehere Versuche gebraucht haben.

Die Navigation bzw. die Suche nach der Funktion zum Hinzufügen von Fächern und Themen stellte sich als sehr versteckt heraus. Außerdem ist hierbei aufgefallen, dass eine zuvor schon heruntergecrollte Seiet dazu führt, dass man die Textfelder von Fächern und Themen nicht gesehen hat und dadurch die Navigation erschwert wurde.

Auch beim Ändern des Passworts fehlten klare Fehlermeldungen im Frontend - diese wurden lediglich vergessen vom Backend in den Frontend zu übertragen. Dieses Problem trat bei der Passowrt-Vergessen-Funktion erneut auf. Abschließend fiel auf, dass es für Nutzer keine Funktion gibt, um Bugs zu melden. Auch diese ist für gute Usability erforderlich.
