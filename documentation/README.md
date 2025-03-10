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




# Kapitel 3: SOLID
## Analyse Single-Responsibility-Principle (SRP)
### Positiv-Beispiel
UML:

    _______________________________________
    | **CSVReader**                         |
    | +  List<String[]> readAll()           |
    ---------------------------------------

Begründung:<br>
Unsere Klasse 'CSVReader' hat eine einzige Verantwortlichkeit. Und zwar das Lesen von CSV-Dateien. Sie kümmert sich nur um das Einlesen der Daten und gibt diese dann in geeigneter Form wieder zurück.

### Negativ-Beispiel
UML:

    ___________________________________
    | **UserHandler**                   |
    | + boolean saveUser()              |
    | + User logUser()                  |
    | + String getUserMail()            |
    | + String getUserFitnessGoal()     |
    | + boolean login()                 |
    | + boolean createUser()            |
    | + void logOut()                   |
    | - int getInt(String msg)          |
    | - String getString(String msg)    |
    | - double getDouble(String msg)    |
    -----------------------------------
Begründung:<br>
Der 'UserHandler' hingegen hat mehrere Verantwortlichkeiten. Auch wenn der 'Handler' zunächst als Klasse gesehen werden kann, die ja nur für das Handling von Benutzeraktionen dient, verstößt sie dennoch gegen das SRP. Diese Klasse kümmert sich hier nämlich um Benutzereingaben, um die Geschäftslogik und um das Persistieren von Nutzerdaten. 

*Lösungsweg*:<br>
Man sollte einen dedizierten InputHandler für die Benutzereingaben einbauen.

## Analyse Open-Closed-Principle (OCP)
### Positiv-Beispiel
UML:

        ___________________________________________________
        | **CreateGymPlan**                                 |
        | + List<String> createPlan(..) throws IOException  |
        | - List<String> getRandomExercises (..)            |
        | - List<String> getCardioExercises (..)            |
        | - List<String> getRandomItems (..)                |
        | + List<String> getGymPlan(String userMail)        |
        ---------------------------------------------------
Begründung:<br>
Unsere 'CreateGymPlan' Klasse ist offen für Erweiterungen und zeitgleich geschlossen für Veränderungen. Das heißt, dass wir neue Fitnessziele durch neue Methoden oder Vererbung hinzufügen können, ohne dabei die bestehende Klasse zu verändern.

### Negativ-Beispiel
UML:

    _______________________________________________________    
    | **GymPlanHandler**                                    |
    | + createGymPlan(String fitnessGoal, String userMail)  |
    | + saveGymPlan(String userMail)                        |
    | + List<String> getGymPlan()                           |
    |+ printGymPlan()                                       |
    -------------------------------------------------------
Begründung:<br>
Der 'GymPlanHandler' wiederum ist nicht offen für Erweiterungen, da Änderungen an der Art und Weise wie Gym-Pläne erstellt oder gespeichert werden, eine Änderung an der Klasse selbst erfordern.

*Lösungsweg*: <br>
So abändern, dass neue Funktionalitäten durch bspw. Vererbungen hinzugefügt werden können.

## Analyse Liskov-Substitution-Principle (LSP)
Sei S ein von T abgeleiteter Typ, dann können Objekte des Typs T durch Objekte des Typs S ersetzt werden, ohne das Programm zu beschädigen.
[Barbara Liskov](https://www.assets.dpunkt.de/leseproben/12309/4_Das%20Liskovsche%20Substitutionsprinzip.pdf)
### Positiv-Beispiel
UML:

    _______________________________________________
    | **UserRepository**                            |
    | + Optional<User> findByEmail(String email)    |
    | + void save(User user)                        |
    | + List<User> findAll()                        | 
    -----------------------------------------------
Begründung:<br>
Die Klasse 'UserRepository' kann durch jede andere Implementierung eines Benutzer-Repositories ersetzt werden, ohne dass das Verhalten des Programms verändert wird.

### Negativ-Beispiel
UML:

    _______________________________________________ 
    | **GymPlanRepository**                         |
    | + List<String[]> getGymPlan(String userMail)  |
    |       throws Exception                        |
    | + void setGymPlan(List<String[]> gymPlan)     |
    -----------------------------------------------
Begründung:<br>
Die Klasse 'GymPlanRepository' hingegen kann nicht ohne Weiteres durch eine andere Implementierung ersetzt werden, da die Methode 'getGymPlan' eine Exception wirft, die andere Implementierungen ggf. nicht erwarten.

*Lösungsweg:*<br> 
Die Klasse sollte keine Methoden besizten, die eine Exception wirft. Demnach die Methode 'getGymPlan' umschreiben.

# Kapitel 4: Weitere Prinzipien
## Analyse GRASP: Geringe Kopplung
[jeweils eine bis jetzt noch nicht behandelte Klasse als positives und negatives Beispiel geringer Kopplung; jeweils UML Diagramm mit zusammenspielenden Klassen, Aufgabenbeschreibung und Begründung für die Umsetzung der geringen Kopplung bzw. Beschreibung, wie die Kopplung aufgelöst werden kann]
### Positiv-Beispiel
### Negativ-Beispiel

## Analyse GRASP: Hohe Kohäsion
[eine Klasse als positives Beispiel hoher Kohäsion; UML Diagramm und Begründung, warum die Kohäsion hoch ist]
## Don’t Repeat Yourself (DRY)
[ein Commit angeben, bei dem duplizierter Code/duplizierte Logik aufgelöst wurde; Code-Beispiele (vorher/nachher); begründen und Auswirkung beschreiben]

# Kapitel 5: Unit Tests
## 10 Unit Tests
[Nennung von 10 Unit-Tests und Beschreibung, was getestet wird]
### Unit Test	Beschreibung
Klasse#Methode	
   
## ATRIP: Automatic
[Begründung/Erläuterung, wie ‘Automatic’ realisiert wurde]
### ATRIP: Thorough
[jeweils 1 positives und negatives Beispiel zu ‘Thorough’; jeweils Code-Beispiel, Analyse und Begründung, was professionell/nicht professionell ist]
### ATRIP: Professional
[jeweils 1 positives und negatives Beispiel zu ‘Professional’; jeweils Code-Beispiel, Analyse und Begründung, was professionell/nicht professionell ist]

## Code Coverage
[Code Coverage im Projekt analysieren und begründen]

## Fakes und Mocks
[Analyse und Begründung des Einsatzes von 2 Fake/Mock-Objekten; zusätzlich jeweils UML Diagramm der Klasse]

# Kapitel 6: Domain Driven Design
## Ubiquitous Language
[4 Beispiele für die Ubiquitous Language; jeweils Bezeichung, Bedeutung und kurze Begründung, warum es zur Ubiquitous Language gehört]
Bezeichung	Bedeutung	Begründung
        
        
## Entities
[UML, Beschreibung und Begründung des Einsatzes einer Entity; falls keine Entity vorhanden: ausführliche Begründung, warum es keines geben kann/hier nicht sinnvoll ist]
### Value Objects
[UML, Beschreibung und Begründung des Einsatzes eines Value Objects; falls kein Value Object vorhanden: ausführliche Begründung, warum es keines geben kann/hier nicht sinnvoll ist]
### Repositories
[UML, Beschreibung und Begründung des Einsatzes eines Repositories; falls kein Repository vorhanden: ausführliche Begründung, warum es keines geben kann/hier nicht sinnvoll ist]
### Aggregates
[UML, Beschreibung und Begründung des Einsatzes eines Aggregates; falls kein Aggregate vorhanden: ausführliche Begründung, warum es keines geben kann/hier nicht sinnvoll ist]

# Kapitel 7: Refactoring
## Code Smells
[jeweils 1 Code-Beispiel zu 2 Code Smells aus der Vorlesung; jeweils Code-Beispiel und einen möglichen Lösungsweg bzw. den genommen Lösungsweg beschreiben (inkl. (Pseudo-)Code)]
## 2 Refactorings
[2 unterschiedliche Refactorings aus der Vorlesung anwenden, begründen, sowie UML vorher/nachher liefern; jeweils auf die Commits verweisen]

# Kapitel 8: Entwurfsmuster
[2 unterschiedliche Entwurfsmuster aus der Vorlesung (oder nach Absprache auch andere) jeweils sinnvoll einsetzen, begründen und UML-Diagramm]
## Entwurfsmuster: [Name]
## Entwurfsmuster: [Name]
