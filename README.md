# Epic 2 - Statistiques Github

## Contexte

L'objectif de ce projet est de récuperer des statistiques via l'API Github.
Une version est en production [ici](https://mediatheque-mrgueritte.herokuapp.com/)

## Technologies

La liste des technologies utilisées est :
    
  - Play 2 : 2.6
  - Slick : 3.2
  - Circe
  - Joda

## Scénarios de test

Disponible sous forme de projet postman dans le dossier test

## Deploiement

#### Génération de clé secrète

Pour mettre en production l'application, il faut renseigner une clé secrète
```sh
$ sbt
$ playUpdateSecret
```