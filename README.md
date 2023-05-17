# README

# Edurando - Nachhilfe-Software für Studierende

Edurando ist eine Software zum Anbieten von Nachhilfe für Studierende an Fachhochschulen bzw. Universitäten. Die Anwendung wird von einer Datenbank gestützt und bietet Studierenden Nachhilfe für alle möglichen Themengebiete. Dabei geht es darum, Studierende beim Lernen von Themen zu unterstützen bzw. Themen beizubringen.


## Inhaltsverzeichnis

- [Beschreibung](#beschreibung)
- [Installation](#installation)
- [Verwendung](#verwendung)
- [Definition of Done](#Definition)
- [Architektur](#architektur)
- [Branching Modell](#branching-modell)
- [Testkonzept](#testkonzept)


## Beschreibung

Dieses Projekt ist eine Anwendung, die mit Spring Boot, Postgresql, Tailwind, Vue.js und Quasar entwickelt wurde. Es handelt sich um eine webbasierte Anwendung, die verschiedene Funktionen bietet. Weitere Informationen finden Sie in der Dokumentation und im Quellcode.

## Installation

Um das Projekt lokal zu installieren, befolgen Sie bitte die folgenden Schritte:

1. Stellen Sie sicher, dass Sie NPM installiert haben.
2. Klone das Repository auf Ihren lokalen Computer.
3. Navigieren Sie in das Projektverzeichnis.
4. Führen Sie den Befehl `npm install` aus, um alle Abhängigkeiten zu installieren.
5. Stellen Sie sicher, dass Sie eine lokale PostgreSQL-Datenbank haben und die Verbindungsinformationen in der Konfigurationsdatei angeben.
6. Führen Sie den Befehl `gradle build` aus, um das Projekt zu erstellen und alle Abhängigkeiten herunterzuladen.

## Verwendung

Nach erfolgreicher Installation können Sie die Anwendung starten, indem Sie den Befehl `gradle bootRun` ausführen. Öffnen Sie dann Ihren Webbrowser und greifen Sie auf die Anwendung über die angegebene URL zu.

## Definiton of Done
1. Die Anforderungen sind vollständig implementiert und funktionstüchtig
2. Alle Unit Tests sind geschrieben und laufen grün
3. Funktionale und nicht-funktionale Anforderungen werden erfüllt
4. Die Akzeptanzkriterien der User Story sind erfüllt
5. Der Code ist im Repository erfolgreich eingecheckt
6. Es sind keine kritischen Bugs offen
7. Der Code wurde von einem oder mehreren Teammitgliedern geprüR und genehmigt.
8. Der Code wurde in einem stabileren Build oder Branch integriert.
9. Der Code wurde von einem Continuous Integration (CI)-System automatisch gebaut
und getestet.


## Architektur

Die Architektur des Projekts besteht aus folgenden Komponenten:

- Spring Boot: Das Backend-Framework für die Entwicklung der Anwendung.
- Postgresql: Die relationale Datenbank, die für die Speicherung der Daten verwendet wird.
- Tailwind: Das CSS-Framework zur Gestaltung des Frontends.
- Vue.js: Das JavaScript-Framework zur Entwicklung des Frontends.
- Quasar: Das UI-Framework für die Erstellung von responsiven Benutzeroberflächen.

## Branching Modell

In diesem Projekt verwenden wir das Gitflow-Branching-Modell. Es besteht aus den folgenden Hauptzweigen:

- **master**: Der Hauptzweig, der die stabile Produktionsversion enthält.
- **develop**: Der Entwicklungszweig, von dem aus Feature-Branches abgezweigt werden.
- **feature/{name}**: Feature-Branches für die Entwicklung neuer Funktionen.
- **hotfix/{name}**: Hotfix-Branches für die Behebung von kritischen Fehlern in der Produktionsversion.

## Testkonzept

Das Testkonzept für dieses Projekt umfasst die folgenden Punkte:

1. Code-Coverage: Es wird angestrebt, eine Testabdeckung von mindestens 70% sowohl im Backend als auch im Frontend zu erreichen.
2. Backend-Tests: Das Backend wird mit Postman-Tests und JUnit-Tests getestet, um die Funktionalität und Zuverlässigkeit sicherzustellen.
3. Frontend-Tests: Das Frontend wird mit Vite-Tests getestet,
