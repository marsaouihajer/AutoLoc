# Atelier 3 — Notes sur la couche Repository (AutoLoc)

## 1. Choix d'interface

Toutes les interfaces étendent `JpaRepository<Entité, Long>` : CRUD complet, `findAll()` renvoie une `List`
(ListCrudRepository), tri et pagination (ListPagingAndSortingRepository), `flush()` / `saveAndFlush()`.

| Interface | Étend | Justification |
|---|---|---|
| IAgenceRepository | JpaRepository<Agence, Long> | CRUD complet, listes, tri et pagination. |
| IEmployeRepository | JpaRepository<Employe, Long> | CRUD complet, listes, tri et pagination. |
| IVehiculeRepository | JpaRepository<Vehicule, Long> | CRUD complet, listes, tri et pagination (catalogue paginé). |
| IEquipementRepository | JpaRepository<Equipement, Long> | CRUD complet, findAll renvoie une List. |
| IClientRepository | JpaRepository<Client, Long> | CRUD complet, tri et pagination de la liste des clients. |
| IReservationRepository | JpaRepository<Reservation, Long> | CRUD complet, saveAndFlush disponible. |
| IContratRepository | JpaRepository<Contrat, Long> | CRUD complet, findAll renvoie une List, saveAndFlush disponible. |
| IPaiementRepository | JpaRepository<Paiement, Long> | Lecture des paiements ; la création / suppression passe par le Contrat (composition). |
| IMaintenanceRepository | JpaRepository<Maintenance, Long> | CRUD complet, listes, tri et pagination. |

## 2. Points d'attention

- `save()` : id null → persist (INSERT) ; id renseigné → merge (SELECT puis UPDATE). Utiliser l'objet retourné.
- `deleteById()` : aucune exception si l'id n'existe pas (Spring Data 3).
- `deleteAllInBatch()` / `deleteAllByIdInBatch()` : contournent le contexte de persistance, donc ni cascade
  ni orphanRemoval (paiements orphelins ou violation de clé étrangère) → à éviter sur Contrat.
- `@Repository` inutile : Spring Data génère le proxy au démarrage (`Found 9 JPA repository interfaces`).

## 3. Anomalies SonarQube for IDE 

| Anomalie SonarQube for IDE | Règle / explication | Correction apportée |
|---|---|---|
| Import inutilisé (exemple) | Les imports non utilisés alourdissent le code. | Suppression de l'import. |
| `@Data` sur une entité (exemple) | `equals`/`hashCode`/`toString` générés peuvent déclencher le chargement des associations LAZY et des boucles infinies. | Remplacement par `@Getter` / `@Setter` ciblés. |
| Nom non conforme (exemple) | Les noms doivent respecter les conventions Java (camelCase, PascalCase). | Renommage conforme. |
