Diese Schicht vermittelt Aufrufe und Daten an die inneren Schichten
    Formatkonvertierungen
        Externes Format wird so umgewandelt, dass die Applikation gut zurecht kommt
        Internes Format wird so umgewandelt, dass die externen Plugins gut zurecht kommen
Oftmals nur einfache Datenstrukturen, die hin- und hergereicht werden
Ziel: Entkopplung von innen und außen

Anti-Corruption Layer
Beispiele:
    GUI: Enthält alle Klassen einer MVC-Struktur
    Datenbank: Wandelt Anfragen der Anwendung in SQL-Statements um
        Kein SQL in der Anwendung selbst!
    GUI: Direkt verwendbares Render-Model
        Key-Value-Paket
Diese Schicht hält die Applikation tauglich und die Plugins frisch