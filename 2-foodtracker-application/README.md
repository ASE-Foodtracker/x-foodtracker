Enthält die Anwendungsfälle (Use Cases)
    Resultiert direkt aus den Anforderungen
Implementiert die anwendungsspezifische Geschäftslogik
    Application-specific Business Rules
Steuert den Fluss der Daten und Aktionen von und zu den Entities
    Verwendet die Geschäftslogik, um den jeweiligen Anwendungsfall umzusetzen

Anwendungsspezifische Geschäftslogik
Regeln, die nur für den Anwendungsfall gelten
    Spezifisch für die Anwendung
    Nicht organisationsweit gültig
    z.B. Regeln für einen Workflow
Beispiele
    Ändern des Passworts erfordert Eingabe und Prüfung des alten Passworts
    Reihenfolge von Wahlentscheidungen in der Verkehrsmodellierung

Änderungen an dieser Schicht beeinflussen die Schicht 3 (v.a. die Entities) nicht
Isoliert von Änderungen an der Datenbank, der graphischen Benutzeroberfläche, etc.
Wenn sich Anforderungen ändern, hat das wahrscheinlich Auswirkungen auf diese Schicht
Wenn sich der konkrete Betrieb der Anwendung ändert, kann das hier Auswirkungen haben
