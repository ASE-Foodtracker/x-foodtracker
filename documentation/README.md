# Kapitel 1: Einführung

## Übersicht über die Applikation
Das Projekt "foodtracker" ist eine Fitness-Tracking-Anwendung, die Benutzern hilft, ihre Fitnessziele zu verfolgen und zu erreichen. Die Anwendung bietet verschiedene Funktionen, darunter die Verwaltung von Benutzern, das Erstellen und Verfolgen von Gym-Plänen, das Protokollieren von Mahlzeiten und das Verfolgen von Gewichtsveränderungen.

Funktionsweise der Anwendung:
1.	Benutzerverwaltung: Benutzer können sich registrieren, einloggen und ausloggen. Benutzerinformationen werden in einem Repository gespeichert und verwaltet.
2.	Gym-Pläne: Benutzer können Gym-Pläne basierend auf ihren Fitnesszielen erstellen. Gym-Pläne werden in einem Repository gespeichert und können geladen und angezeigt werden.
3.	Nährstoff Tracking: Benutzer können ihre täglichen Mahlzeiten protokollieren. Mahlzeiteninformationen werden in einem Repository gespeichert und können geladen und angezeigt werden.
4.	Gewichts Tracking: Benutzer können ihr Gewicht protokollieren und Veränderungen verfolgen. Gewichtsinformationen werden in einem Repository gespeichert und können geladen und angezeigt werden. Die "foodtracker" App löst das Problem der unorganisierten und ineffizienten Verfolgung von Fitness- und Ernährungszielen. Sie bietet eine zentrale Plattform, auf der Benutzer ihre Gym-Pläne, Mahlzeiten und Gewichtsveränderungen einfach und systematisch protokollieren und verfolgen können. Der Zweck der App ist es, Benutzern zu helfen, ihre Fitnessziele zu erreichen, indem sie ihnen Werkzeuge zur Verfügung stellt, um ihre Fortschritte zu überwachen und ihre Aktivitäten zu planen.

## Wie startet man die Applikation?
Voraussetzungen:
-	lokale Kopie des Repositories
-	lokale Maven Installation

Anleitung:
1.	Navigiere in das root Directory des Projekts
2.	Installation von Abhängigkeiten:
```shell
mvn clean install
```
3.	Navigation in das Main Projekt:
```shell
cd 0-foodtracker-plugin-main
```
4.	Ausführen der App:
```shell
mvn exec:java -Dexec.mainClass="de.jmf.Main"
```

## Wie testet man die Applikation?
Voraussetzungen:
-	lokale Kopie des Repositories
-	lokale Maven Installation

Anleitung:
1.	Navigiere in das root Verzeichnis des Projekts
2.	Installation von Abhängigkeiten:
```shell 
mvn clean install
```
3.	Ausführen der Tests:
```shell
mvn test
```

# Kapitel 2: Clean Architecture

## Was ist Clean Architecture?
Clean Architecture ist ein Architekturstil, der darauf abzielt, die Abhängigkeiten innerhalb einer Softwareanwendung zu minimieren und die Wartbarkeit und Testbarkeit zu maximieren. 
Die Hauptidee besteht darin, die Geschäftslogik von den Details der Implementierung zu trennen. 
Dies wird durch die Trennung der Anwendung in verschiedene Schichten erreicht, wobei jede Schicht eine spezifische Rolle spielt und nur auf die unmittelbar darunter liegende Schicht zugreift. 

## Analyse der Dependency Rule

### Positiv-Beispiel

### Positiv-Beispiel-2

## Analyse der Schichten

### Schicht:

### Schicht:
