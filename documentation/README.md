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

### Positiv-Beispiel
UML:

    _______________________________________
    | **ConsoleWriter**                    |
    | + void printGymPlan(List<String[]> gp)|
    ---------------------------------------
    | **CSVReader**                        |
    | + List<String[]> readAll()           |
    ---------------------------------------
Begründung: <br>
Die Klassen ConsoleWriter und CSVReader sind perfekte Beispiele für die geringe Kupplung. Sie sind unabhänging voneinander aber haben ihre klar definierten Verantwortlichkeiten. ConsoleWriter beschäftigt sich nur um die Ausgabe des GymPlans und CSVReader nur um die Einlesung.
### Negativ-Beispiel
UML:

    ___________________________________
    | **ProgressHandler**               |
    | + void loadProgress()             |
    | + void newWeightEntry()           |
    | + void saveWeight()               |
    -----------------------------------
    | **CSVReader**                     |
    | + List<String[]> readAll()        |
    -----------------------------------
    | **CSVWriter**                     |
    | + void saveAll(List<String[]> data)|
    -----------------------------------
Begründung:<br>
Die Klasse 'ProgressHandler' hat eine starke Kopplung zu den Klassen CSVReader und CSVWriter. Jede Änderung in den Methoden readAll oder saveAll können zu Fehlern in der ProgressHandler-Klasse führen.

*Lösung:*<br>
Um die Kopplung zu lösen, sollte der ProgressHanlder von DataReader und DataWriter erben.

## Analyse GRASP: Hohe Kohäsion
UML:

    _______________________________________
    | **NutritionLog**                     |
    | + void addMeal(Meal meal)            |
    | + List<Meal> getMeals()              |
    | + double getTotalCalories()          |
    ---------------------------------------
Die Klasse NutritionLog hat eine hohe Kohäsion, da alle ihre Methoden eng miteinander verbunden sind und sich auf die Verwaltung von Mahlzeiten und Nährwerten konzentrieren. Jede Methode trägt zur Hauptverantwortung der Klasse bei, was die Kohäsion erhöht und die Klasse leichter verständlich und wartbar macht.

## Don’t Repeat Yourself (DRY)

Von Beginn keine Duplizierungen verwendet :o <br>
Was mir sonst nur einfällt ist der CSVWriter. Dank ihm haben wir diese Logik ausgelagert und können diese immerwieder von egal wo aufrufen, da wir jeweils einen eigenen Path für den Writer definieren. Er kann also alle Daten einer String List[] in ein beliebiges Verzeichnis laden.

# Kapitel 5: Unit Tests
## 10 Unit Tests
### Application Layer
1. GymPlanTest
    - testCreateGymPlanForGainWeight<br>
        **Beschreibung**: Dieser Test überprüft, ob für das Fitnessziel "Muskelaufbau" (gain) ein Fitnessplan für Montag, Mittwoch und Freitag mit insgesamt 9 Übungen bereitstellt.
    - testCreateGymPlanForLooseWeight<br>
        **Beschreibung:** Dieser Test überprüft, ob für das Fitnessziel "Abnehmen" (loose) ein Fitnessplan für jeden Tag mit einer Cardioübung zur Verfügung stellt.
    - testGetGymPlan<br>
        **Beschreibung:** Dieser Test überprüft, ob der zurückgegebene GymPlan die richtige Gesamtlenge entspricht.
    - testSetGymPlan<br>
        **Beschreibung:** Dieser Test überprüft, ob der zurückgegebene Gymplan dem gesetzten Gymplan entspricht.
2. MealsTest
    - testSaveMeal<br>
        **Beschreibung:** Dieser Test überprüft, ob das Gericht, welches gespeichert wird, auch wieder zurückgerufen werden kann.
    - testGetTodaysMeals<br>
        **Beschreibung:** Dieser Test überprüft, ob heute gespeicherte Gerichte zurückgegeben werden können.
    - testRemoveMeal<br>
        **Beschreibung:** Dieser Test überprüft, ob das gespeicherte Essen wieder entfernt werden kann.
    - testGetAllMeals<br>
        **Beschreibung:** Dieser Test überprüft, ob alle gespeicherten Gerichte ausgegeben werden können.
3. UserTest
    - testCreateUser<br>
        **Beschreibung:** Dieser Test überprüft, ob ein Benutzer erstellt werden kann.
    - testLoginUser<br>
        **Beschreibung:** Dieser Test überprüft, ob ein angemeldeter Nutzer auch als aktiver Nutzer gilt.
    - testLogOutUser<br>
        **Beschreibung:** Dieser Test überprüft, ob nach dem ausloggen der aktive Nutzer auf Null gesetzt wird.
    - testSaveUser<br>
        **Beschreibung:** Dieser Test überprüft, ob der Nutzer richtig abgespeichert wird.
4. Weight Test
    - …
### Domain Layer
1. …	
   
## ATRIP: Automatic
Die Tests werden automatisch mit Maven ausgeführt, indem der Befehl mvn test verwendet wird. Dies stellt sicher, dass die Tests bei jeder Änderung des Codes ausgeführt werden und dass keine manuellen Schritte erforderlich sind.

### ATRIP: Thorough
**Positives Beispiel:**

    @Test
    public void testSaveUser() {
        FitnessGoal goal = new FitnessGoal("gain", new Weight(70));
        createUser.execute("john.doe@example.com", "John Doe", 25, goal);

        List<String[]> users = saveUser.execute();
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("John Doe", users.get(0)[0]);
        assertEquals("25", users.get(0)[1]);
        assertEquals("john.doe@example.com", users.get(0)[2]);
        assertEquals("gain", users.get(0)[3]);
        assertEquals("70.0", users.get(0)[4]);
    }
**Begründung:**<br>Dieser Test aus der Klasse ```2-foodtracker-application/src/test/java/de/jmf/UserTest.java``` überprüft gründlich, ob die Methode korrekt funktioniert, indem sichergestellt wird, dass die Benutzerdaten korrekt gespeichert werden und die erwarteten Werte wieder ausgegeben werden.

**Negatives Beispiel:**

    @Test
    public void testSaveUser() {
        userHandler.saveUser();
    }
**Begründung:**<br> Hier fehlt bspw. die Verifikation des Verhaltens.

### ATRIP: Professional
**Positives Beispiel:**

    @Test
    public void testCreateUser() {
        doNothing().when(createUser).execute(anyString(), anyString(), anyInt(), any(FitnessGoal.class));

        boolean result = userHandler.createUser();

        assertFalse(result);
        verify(createUser, times(1)).execute(anyString(), anyString(), anyInt(), any(FitnessGoal.class));
    }
**Begründung:**<br> Dieser Test wäre professionell, da er auch sicherstellt, dass die execute Methode nur ein einziges Mal aufgerufen wird.

**Negatives Beispiel:**

    @Test
    public void testCreateUser() {
        userHandler.createUser();
    }
**Begründung:**<br> Dieser Test ist nicht professionell, da weder überprüft ob die Methode aufgerufen wurde, noch die Verifikation des Verhaltens überprüft.
## Code Coverage
Die Code Coverage im Projekt wird durch die Ausführung der Unit-Tests mit Maven gemessen. Eine hohe Code Coverage stellt sicher, dass der größte Teil des Codes durch Tests abgedeckt ist, was die Wahrscheinlichkeit von Fehlern reduziert und die Wartbarkeit des Codes erhöht. Bei uns läuft jeder Test grün durch :)

## Fakes und Mocks
Wir haben nur einen Mock erstellt.<br>
**UML:**

     _________________________________________
    | **ProgressRepository**                  |
    | + ProgressTracker getProgress()         |
    | + void addWeight(Weight weight)         |
    | + void loadWeight(List<WeightLog> logs) |
     -----------------------------------------

**Begründung:**<br> Der Mock für die Klasse 'ProgressRepository' wird verwendet, um die Methoden *getProgress*, *addWeight* und *loadWeight* zu simulieren. Dadurch wird sichergestellt, dass diese korrekt aufgerufen werden, ohne dabei die tatsächliche Implementierung auszuführen.


# Kapitel 6: Domain Driven Design
## Ubiquitous Language
### Bezeichnung: Benutzer
**Bedeutung:** Eine Person, die die Anwendung nutzt, um ihre Fitnessziele zu verfolgen.<br>
**Begründung:** Der Begriff "Benutzer" ist zentral für die Anwendung und wird in der gesamten Domäne verwendet, um die Hauptakteure zu beschreiben.

### Bezeichnung: GymPlan
**Bedeutung:** Ein Trainingsplan, der Übungen und deren Häufigkeit für einen bestimmten Zeitraum enthält.<br>
**Begründung:** Der Begriff "GymPlan" ist wichtig, um die Struktur und den Inhalt der Trainingspläne zu definieren, die Benutzer erstellen und verfolgen können.

### Bezeichnung: Mahlzeit
**Bedeutung:** Eine Nahrungsaufnahme, die von einem Benutzer protokolliert wird.<br>
**Begründung:** Der Begriff "Mahlzeit" ist entscheidend für das Nährstoff-Tracking und wird verwendet, um die einzelnen Einträge im Ernährungstagebuch zu beschreiben.

### Bezeichnung: Gewicht
**Bedeutung:** Das Körpergewicht eines Benutzers, das regelmäßig protokolliert wird.<br>
**Begründung:** Der Begriff "Gewicht" ist zentral für das Gewichts-Tracking und wird verwendet, um die Fortschritte der Benutzer bei der Erreichung ihrer Fitnessziele zu überwachen.

        
        
## Entities
[UML, Beschreibung und Begründung des Einsatzes einer Entität; falls keine Entität vorhanden: ausführliche Begründung, warum es keines geben kann/hier nicht sinnvoll ist]
### Benutzer
**UML:**

    _______________________________________
    | **Benutzer**                         |
    | + String email                       |
    | + String name                        |
    | + int age                            |
    | + FitnessGoal fitnessGoal            |
    | + void updateFitnessGoal(FitnessGoal)|
    ---------------------------------------
**Beschreibung:** Die Entität "Benutzer" repräsentiert einen Benutzer der Anwendung mit seinen persönlichen Daten und Fitnesszielen.<br>
**Begründung:** Diese Entität ist notwendig, um die Benutzerinformationen und deren Fitnessziele zu verwalten.

### GymPlan
**UML:**

     _______________________________________
    | **GymPlan**                           |
    | + String userMail                     |
    | + List<String> exercises              |
    | + void addExercise(String exercise)   |
    | + void removeExercise(String exercise)|
     ---------------------------------------

**Beschreibung:** Die Entität "GymPlan" repräsentiert einen Trainingsplan eines Benutzers.<br>
**Begründung:** Diese Entität ist notwendig, um die Struktur und den Inhalt der Trainingspläne zu verwalten.

### Mahlzeit
**UML:**

     ______________________________________
    | **Mahlzeit**                         |
    | + String name                        |
    | + int calories                       |
    | + void updateCalories(int calories)  |
     --------------------------------------

**Beschreibung:** Die Entität "Mahlzeit" repräsentiert eine protokollierte Mahlzeit eines Benutzers.<br>
**Begründung:** Diese Entität ist notwendig, um die Nahrungsaufnahme der Benutzer zu verwalten.

### Gewicht
**UML:**

     ______________________________________
    | **Gewicht**                          |
    | + double value                       |
    | + Date date                          |
    | + void updateWeight(double value)    |
     --------------------------------------

**Beschreibung:** Die Entität "Gewicht" repräsentiert das Körpergewicht eines Benutzers zu einem bestimmten Zeitpunkt.<br>
**Begründung:** Diese Entität ist notwendig, um die Gewichtsveränderungen der Benutzer zu verwalten.

## Value Objects
### FitnessGoal
**UML:**

     ______________________________________
    | **FitnessGoal**                      |
    | + String goal                        |
    | + double targetWeight                |
    | + void updateTargetWeight(double)    |
     --------------------------------------

**Beschreibung:** Das Value Object "FitnessGoal" repräsentiert das Fitnessziel eines Benutzers.<br>
**Begründung:** Dieses Value Object ist notwendig, um die Fitnessziele der Benutzer zu verwalten und sicherzustellen, dass sie unveränderlich sind.

## Repositories
### BenutzerRepository
**UML:**

     ______________________________________
    | **BenutzerRepository**               |
    | + Optional<Benutzer> findByEmail(String email)|
    | + void save(Benutzer benutzer)       |
    | + List<Benutzer> findAll()           |
     --------------------------------------
     
**Beschreibung:** Das Repository "BenutzerRepository" verwaltet die Speicherung und den Zugriff auf Benutzerinformationen.<br>
**Begründung:** Dieses Repository ist notwendig, um die Benutzerinformationen persistent zu speichern und darauf zuzugreifen.

## Aggregates
### Warum keine Aggregate?
In unserer aktuellen Implementierung haben wir keine Aggregate definiert. Ein Aggregate ist ein Cluster von zusammengehörigen Objekten, die als eine Einheit behandelt werden. In unserem Fall könnten wir theoretisch ein BenutzerAggregate haben, das Benutzer, GymPlan, Mahlzeit und Gewicht umfasst. 

Allerdings haben wir uns entschieden, diese Entitäten separat zu behandeln und über Repositories zu verwalten. Das vereinfacht die Implementierung und vermeidet Komplexitäten, die mit der Verwaltung von Aggregates einhergehen. Jede Entität hat ihre eigenen Verantwortlichkeiten und wird unabhängig voneinander verwaltet.


# Kapitel 7: Refactoring
## Code Smells

### Long Method
```https://vscode.dev/github/ASE-Foodtracker/x-foodtracker/blob/documentation/1-foodtracker-adapters/src/main/java/de/jmf/adapters/ConsoleAdapter.java#L75```
```Java    
    public void running()
        while
            try
                switch
                    case
                        while
                            else
                                if
```

Diese Methode ist viel zu lang und viel zu sehr in sich selbst verschachtelt. Dafür ist ein Refactoring definitiv von Nöten.<br>
**Lösungsweg:**<br> Die Methode in mehrere kleine Methoden aufteilen.
```Java    
public void running(){
    boolean running = true;
    init();
    while(running){
        printMenu();
        int option = getInt("Please enter the number of the action you want to perform: ");
        running = handleMenu(option)
    }
}
private void handleMenu(int option){
    case 0:
        …
    case 1:
        …
}
```
### Large Class
```1-foodtracker-adapters/src/main/java/de/jmf/adapters/handlers/UserHandler.java```
```Java    
public class UserHandler{
    …
    public UserHandler(){}
    public boolean saveUser(){}
    public User logUser(){}
    public String getUserMail(){}
    public String getUserFitnessGoal(){}
    public boolean login(){}
    public boolean createUser(){}
    public void logOut(){}
    private int getInt(){}
    private String getString(){}
    private Double getDouble(){}
}
```

Diese Klasse hat definitiv zu viele Verantwortlichkeiten.<br>
**Lösungsweg:**<br>Wir können die Methoden zur Benutzereingabe in eine separate Klasse auslagern: 
```Java    
public class UserHandler{
    …
    public UserHandler(){}
    public boolean saveUser(){}
    public User logUser(){}
    public String getUserMail(){}
    public String getUserFitnessGoal(){}
    public boolean login(){}
    public boolean createUser(){}
    public void logOut(){}
}

public lcass InputHandler{
    public int getInt(){}
    public String getString(){}
    public Double getDouble(){}
}
```
## 2 Refactorings
### Rename Method
**Commit:**https://github.com/ASE-Foodtracker/x-foodtracker/commit/aca842f75f4fc81a0d29ac42a03d60f5a1557aba <br>
**Begründung:**<br> Durch dieses renaming wurde die Lesbarkeit und das Verständnis des Codes erhöht. Wer nichts mit dem Methodennamen anfangen kann, hat es deutlich schwerer sich durch den Code zu lesen und ihn zu verstehen.

### Extract Method (Nicht gemacht, sollten wir aber)
**Begründung:**<br>
Durch das auslagern von Methoden wird die Lesbarkeit im Code deutlich verbessert. Somit verringert man das Risiko von Duplicated Code und Long Methods. Mein Betreuer (Architekt) meinte einst: **"Wenn ich meine Brille abnehme und sehen kann, dass der Code über den halben Bildschirm verschachtelt ist, dann muss dort auf jeden Fall gerefactored werden!"**



# Kapitel 8: Entwurfsmuster
## Entwurfsmuster: Dekorator
### Einsatz
Das Dekorator-Muster wird verwendet, um einem Objekt zur Laufzeit zusätzliche Verantwortlichkeiten hinzuzufügen, ohne seine Struktur zu ändern. In unserem Projekt können wir das Dekorator-Muster verwenden, um zusätzliche Funktionen zu den Benutzerdaten hinzuzufügen, wie z.B. das Protokollieren von Änderungen oder das Validieren von Eingaben.

### Beispiel
Wir haben eine Benutzerklasse, die wir mit zusätzlichen Funktionen wie Validierung und Protokollierung dekorieren möchten.

### Code
```java
// filepath: 1-foodtracker-adapters/src/main/java/de/jmf/adapters/decorator/User.java
public interface User {
    void save();
}

// filepath: 1-foodtracker-adapters/src/main/java/de/jmf/adapters/decorator/BasicUser.java
public class BasicUser implements User {
    @Override
    public void save() {
        // Grundlegende Speichern-Implementierung
    }
}

// filepath: 1-foodtracker-adapters/src/main/java/de/jmf/adapters/decorator/UserDecorator.java
public abstract class UserDecorator implements User {
    protected User decoratedUser;

    public UserDecorator(User decoratedUser) {
        this.decoratedUser = decoratedUser;
    }

    @Override
    public void save() {
        decoratedUser.save();
    }
}

public class ValidationUserDecorator extends UserDecorator {
    public ValidationUserDecorator(User decoratedUser) {
        super(decoratedUser);
    }

    @Override
    public void save() {
        validate();
        super.save();
    }

    private void validate() {
        // Validierungslogik
    }
}
public class LoggingUserDecorator extends UserDecorator {
    public LoggingUserDecorator(User decoratedUser) {
        super(decoratedUser);
    }

    @Override
    public void save() {
        log();
        super.save();
    }

    private void log() {
        // Protokollierungslogik
    }
}
```

     -------------------         -------------------
    |       User        |<------|   UserDecorator   |
    |-------------------|       |-------------------|
    | + save()          |       | - decoratedUser   |
     -------------------        | + save()          |
                                 -------------------
                                    /\
                                    ||
                                    ||
                                    ||
     -------------------        -------------------
    |    BasicUser      |       | ValidationUserDec |
    |-------------------|       |-------------------|
    | + save()          |       | + save()          |
     -------------------        | + validate()      |
                                 -------------------
                                    /\
                                    ||
                                    ||
                                    ||
                                 -------------------
                                | LoggingUserDec    |
                                |-------------------|
                                | + save()          |
                                | + log()           |
                                 -------------------

**Begründung:**<br>
Das Dekorator-Muster ermöglicht es uns, zusätzliche Funktionen wie Validierung und Protokollierung hinzuzufügen, ohne die grundlegende Benutzerklasse zu ändern. Dies erhöht die Flexibilität und Erweiterbarkeit des Codes.

## Entwurfsmuster: Erbauer
### Einsatz
Das Erbauer-Muster wird verwendet, um die Konstruktion eines komplexen Objekts zu trennen, sodass derselbe Konstruktionsprozess verschiedene Darstellungen erzeugen kann. In unserem Projekt können wir das Erbauer-Muster verwenden, um komplexe Benutzerobjekte mit verschiedenen Attributen zu erstellen.

### Beispiel
Wir haben eine Benutzerklasse, die viele Attribute hat. Mit dem Erbauer-Muster können wir Benutzerobjekte schrittweise und kontrolliert erstellen. Vorallem wenn wir vom Index, der gerade die Email ist, auf eine ID wechseln, dann kann die E-Mail auch optional sein.

### Code
```Java
public class User {
    private String email;
    private String name;
    private int age;
    private FitnessGoal fitnessGoal;

    private User(UserBuilder builder) {
        this.email = builder.email;
        this.name = builder.name;
        this.age = builder.age;
        this.fitnessGoal = builder.fitnessGoal;
    }

    public static class UserBuilder {
        private String email;
        private String name;
        private int age;
        private FitnessGoal fitnessGoal;

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder setFitnessGoal(FitnessGoal fitnessGoal) {
            this.fitnessGoal = fitnessGoal;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
```
     -------------------
    |       User        |
    |-------------------|
    | - email           |
    | - name            |
    | - age             |
    | - fitnessGoal     |
     -------------------
            /\
            ||
            ||
            ||
     ------------------- 
    |   UserBuilder     |
    |-------------------|
    | - email           |
    | - name            |
    | - age             |
    | - fitnessGoal     |
     -------------------
    | + setEmail()      |
    | + setName()       |
    | + setAge()        |
    | + setFitnessGoal()|
    | + build()         |
     -------------------
**Begründung:**<br>
Das Erbauer-Muster ermöglicht es uns, komplexe Benutzerobjekte schrittweise und kontrolliert zu erstellen. Dies erhöht die Lesbarkeit und Wartbarkeit des Codes, da die Konstruktion von Benutzerobjekten klar und strukturiert ist.