# Pull Request 

## Checklist
- [x] Ist **htwb-steven** als Reviewer eingetragen?
- [x] Ist der Workflow `projects.yml` erfolgreich?
- [x] Hiermit versichere ich, dass ich die vorliegende Arbeit selbstständig und nur unter Verwendung der angegebenen Quellen und Hilfsmittel verfasst habe. 


## Für Beleg 2, 3, 4

### Datenbank

### Heroku ClearDB (mySQL) Database

| url      | jdbc:mysql://b0f25133578286:e925dc8e@eu-cdbr-west-01.cleardb.com/ |
|----------|--------|
| username | b0f25133578286    |
| password | e925dc8e   | 


### Anwendung

- Link zur Anwendung (falls vorhanden):

### Beispielanfragen

Jede **implementierte Anfrage** soll mit folgenden Template dokumentiert werden:

#### Template Für GET, DELETE Methoden
```
curl -X GET \
     -H "Accept: application/json" \
     -v "http://localhost:8080/hallo
```

#### Template für POST, PUT Methoden
```
curl -X POST \
     -H "Content-Type: application/json" \
     -v "http://localhost:8080/hallo" \
     -d '{"title":"Wrecking Ball"}'
```
