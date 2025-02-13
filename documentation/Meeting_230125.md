Datum: 23.01.2025 - 9:00 bis 9:40 | Teilnehmer: Briem, Lars; Bents, Jeremie; Lindner, Marvin

Changes:
- [ ] Mehr Use Cases integrieren (min 2 !!!)
  z.B. Progress Tracker, Auswählen der Datenbank/Daten (csv)
- [ ] Tests ergänzen
- [/] repositories und file_management zu Outputs + umschreiben zu einzelnen plugins
- [ ] Neue Instanz die sich um outputs und inputs kümmert (indirektion)
  Grund: Application layer ist unabhängig von resource
- [ ] Exceptions sollen aus dem application layer raus und in die plugin Schicht
- [ ] das return new UserDTO kann im mapper geschehen (keine Ahnung?)
- [x] Entities und Value Objects mergen (kein wirklicher unterschied)
- [x] Main soll ganz außen liegen (layer: 0)
- [x] Auslegen der CSV Dateien in einen weiteren Ordner (layer: -1)

Anmerkungen:
- Menge an Klassen passt
- /.../DTO ist mapper
- /use_case ist application layer
- /domain ist domain layer
- plugin ... 