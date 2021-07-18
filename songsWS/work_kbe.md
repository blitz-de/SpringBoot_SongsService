# KBE Anforderungs Dokumentation

# Deploy

- deploy on online database heroku (x)
- deploy app (service) on heroku (x)

## Heroku App

Deployed: https://kbe0.herokuapp.com/

### Heroku ClearDB (mySQL) Database

| url      | jdbc:mysql://b0f25133578286:e925dc8e@eu-cdbr-west-01.cleardb.com/ |
|----------|--------|
| username | b0f25133578286    |
| password | e925dc8e   | 



# Requirements Done:
---
# Teil 1
- init two users: mmuster, eschuler
- get userid from auth-header (x)
- jwt baerer auth (x)
- init 10 songs (x)
- all following request needs jwt session key in header --> if not: 403 Forbidden (x)
## Song Reqests
- GET songs on `/songsWS-sakvis/rest/songs/` --> 201 | (x)
- POST add song on `/songsWS-sakvis/rest/songs` (x)
- PUT song update on `/songsWS-sakvis/rest/songs` (x)
- DELTE song on `/songsWS-sakvis/rest/songs` (x)
## User Reqests
- POST auth on `/songsWS-sakvis/rest/auth`: username, password  --> returns token (x)
- POST auth on `/songsWS-sakvis/rest/auth`: username=null, password  --> returns bad request (x)
## SongLists Reqests
- add (x)
- delete (x)
- get all for current user (x)
- get all public lists from foreign user (x)

## Error Handling (Test)
- add without name (x)
- delete with wrong user (x)
- add songlist with invalid song (x)
- ...

# Teil 2
- create database structure required for playlists (x)
- init playlist (x)
- create route `/songsWS-sakvis/rest/songLists/` (x)
- create models (x)
- create repo (x)
- create controller (x)

## songLists

### requirements
- get username from token - Principial (x)
- add songlist with id 22 - (x)
- init songlist for eschuler & mmuster (x)
    - private (x)
    - public (x)

## requests
1.) JSON, XML (x)
- GET `rest/songLists?userId=username`: 
    - if username = getUserIdFromToken(auth header) -> all songslists from username; 
    - else --> all public songlists from username; 
    - or (404) Not Found
- GET `rest/songLists/{songlist_id}`: 
    - if getSongList(id).owner_id = getUserIdFromToken(auth header) -> songlist; 
    - else if songlist.public --> songlist; 
    - else if songlist.privat --> (403) Forbidden; 
    - or (404) Not Found

2.) JSON (x)
- POST add songLists `/rest/songLists` with payload:
- in reallfe this would be bad practice (only id of song needed for adding) 
- songs had to be in db --> 201 OK; 
    - if one of them not existing --> 400 Bad Request 
```json
{
 "isPrivate": true,
 "name": "MaximesPrivate",
 "songList": [
    {
       "id": 5,
       "title": "We Built This City",
       "artist": "Starship",
       "label": "Grunt/RCA",
       "released": 1985
    },
    {
       "id": 4,
       "title": "Sussudio",
       "artist": "Phil Collins",
       "label": "Virgin",
       "released": 1985
    }
 ]
 }

```

3.) DELETE (x)

- user can only delete one songs (x)
- user can't delete public playlist of other users --> returns 403 Forbidden (x)


## Tests

- deploy test db (x)
- test for controller of songsList (x)





