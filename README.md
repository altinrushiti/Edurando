# Readme

Willkommen zum Projekt!

## Inhaltsverzeichnis

- [Beschreibung](#beschreibung)
- [Installation](#installation)
- [Verwendung](#verwendung)
- [Architektur](#architektur)
- [Branching Modell](#branching-modell)
- [Testkonzept](#testkonzept)
- [Anforderungen](#anforderungen)
- [Contributing](#contributing)
- [Lizenz](#lizenz)

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
