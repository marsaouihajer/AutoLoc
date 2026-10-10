# AutoLoc
Plateforme de gestion de location de véhicules multi-agences.

> Projet réalisé dans le cadre du module UP ASI (Architecture des Systèmes d'Information) à ESPRIT.

## Objectifs du projet

- Gérer le parc de véhicules de plusieurs agences de location.
- Permettre aux clients de consulter les véhicules disponibles et de réserver en ligne.
- Faciliter le travail des agents d'agence : contrats, retours de véhicules, facturation.
- Offrir aux responsables d'agence et à l'administrateur des outils de suivi et d'administration.

## Acteurs identifiés

| Acteur | Rôle |
|--------|------|
| **Client** | Consulte les véhicules, réserve, suit ses locations |
| **Agent d'agence** | Enregistre les locations, les retours et les paiements |
| **Responsable d'agence** | Supervise son agence : véhicules, agents, statistiques |
| **Administrateur** | Gère les agences, les utilisateurs et la configuration globale |

## Cas d'utilisation (première liste)

**Client**
- S'inscrire / se connecter
- Rechercher un véhicule disponible
- Réserver un véhicule
- Annuler une réservation
- Consulter l'historique de ses locations

**Agent d'agence**
- Créer un contrat de location
- Enregistrer le retour d'un véhicule
- Gérer les paiements et la facturation

**Responsable d'agence**
- Gérer les véhicules de son agence
- Gérer les agents
- Consulter les statistiques de l'agence

**Administrateur**
- Gérer les agences
- Gérer les comptes utilisateurs et les rôles
- Superviser l'ensemble de la plateforme

## Stack technique

- Java 17, Maven
- Spring Boot, Spring Data JPA, Spring MVC
- MySQL (base `autoloc_db`)
- Lombok, Postman, Git/GitHub, IntelliJ IDEA Ultimate

## Lancer le projet

1. Démarrer MySQL et créer la base :
```sql
   CREATE DATABASE autoloc_db CHARACTER SET utf8mb4;
```
2. Configurer `src/main/resources/application.properties` (URL, utilisateur, mot de passe).
3. Lancer l'application :
```bash
   mvnw spring-boot:run
```

## Équipe

- Hajer Marsaoui

## Preuve de l'environnement

Voir le dossier `docs/` : capture d'écran de l'environnement fonctionnel.