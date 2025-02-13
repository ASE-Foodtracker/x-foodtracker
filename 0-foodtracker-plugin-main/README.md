Diese Schicht greift grundsätzlich nur auf die Adapter zu
Enthält Frameworks, Datentransportmittel und andere Werkzeuge
    v.a. Datenbank, Benutzeroberfläche, Web
    Alle „Pure Fabrication“-Entscheidungen
Wir versuchen, hier möglichst wenig Code zu schreiben
    Hauptsächlich Delegationscode, der an die Adapter weiterleitet

Auf gar keinen Fall enthält diese Schicht Anwendungslogik
    Die Daten fallen mundfertig aus dem Adapter
    Alle Entscheidungen sind bereits gefallen
    Anfragen werden nicht uminterpretiert (das machen die Adapter)
Keine emotionale Bindung an diesen Code
    Jederzeitige Änderung möglich
    Auswirkungen nur auf die Adaptersicht
    Übersichtlicher Aufwand