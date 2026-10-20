# Atelier 5 — Notes sur l'API REST, Postman et Swagger (AutoLoc)

## A. Endpoints de l'API REST

Préfixe commun défini via `server.servlet.context-path=/api` dans `application.properties`.

| Contrôleur | Verbe et Route | Service appelé | Code succès | Codes d'erreur | Description |
|---|---|---|---|---|---|
| ContratController | GET /api/contrats | IContratService.findAll() | 200 OK | — | Lister tous les contrats |
| ContratController | GET /api/contrats/{id} | IContratService.findById(id) | 200 OK | 404 Not Found | Consulter un contrat par ID |
| ContratController | POST /api/contrats | IContratService.create(contrat) | 201 Created | 400 Bad Request | Créer un contrat (avec paiements éventuels) |
| ContratController | PUT /api/contrats/{id} | IContratService.update(id, contrat) | 200 OK | 400 Bad Request, 404 Not Found | Modifier un contrat existant |
| ContratController | DELETE /api/contrats/{id} | IContratService.deleteById(id) | 204 No Content | 404 Not Found | Supprimer un contrat (cascade sur paiements) |
| PaiementController | GET /api/paiements | IPaiementService.findAll() | 200 OK | — | Lister tous les paiements (lecture seule) |
| PaiementController | GET /api/paiements/{id} | IPaiementService.findById(id) | 200 OK | 404 Not Found | Consulter un paiement par ID |
| PaiementController | POST /api/paiements | — | — | 405 Method Not Allowed | Création directe refusée (passe par Contrat) |
| AgenceController | GET /api/agences | IAgenceService.findAll() | 200 OK | — | Lister toutes les agences |
| AgenceController | GET /api/agences/{id} | IAgenceService.findById(id) | 200 OK | 404 Not Found | Consulter une agence par ID |
| AgenceController | POST /api/agences | IAgenceService.create(agence) | 201 Created | 400 Bad Request | Créer une agence (nom, ville, adresse obligatoires) |
| AgenceController | PUT /api/agences/{id} | IAgenceService.update(id, agence) | 200 OK | 400 Bad Request, 404 Not Found | Modifier une agence existante |
| AgenceController | DELETE /api/agences/{id} | IAgenceService.deleteById(id) | 204 No Content | 404 Not Found | Supprimer une agence |
| EmployeController | GET /api/employes | IEmployeService.findAll() | 200 OK | — | Lister tous les employés |
| EmployeController | GET /api/employes/{id} | IEmployeService.findById(id) | 200 OK | 404 Not Found | Consulter un employé par ID |
| EmployeController | POST /api/employes | IEmployeService.create(employe) | 201 Created | 400 Bad Request | Créer un employé (agence imbriquée) |
| EmployeController | PUT /api/employes/{id} | IEmployeService.update(id, employe) | 200 OK | 400 Bad Request, 404 Not Found | Modifier un employé existant |
| EmployeController | DELETE /api/employes/{id} | IEmployeService.deleteById(id) | 204 No Content | 404 Not Found | Supprimer un employé |
| VehiculeController | GET /api/vehicules | IVehiculeService.findAll() | 200 OK | — | Lister tous les véhicules |
| VehiculeController | GET /api/vehicules/{id} | IVehiculeService.findById(id) | 200 OK | 404 Not Found | Consulter un véhicule par ID |
| VehiculeController | POST /api/vehicules | IVehiculeService.create(vehicule) | 201 Created | 400 Bad Request | Créer un véhicule (tarif journalier >= 0) |
| VehiculeController | PUT /api/vehicules/{id} | IVehiculeService.update(id, vehicule) | 200 OK | 400 Bad Request, 404 Not Found | Modifier un véhicule existant |
| VehiculeController | DELETE /api/vehicules/{id} | IVehiculeService.deleteById(id) | 204 No Content | 404 Not Found | Supprimer un véhicule |
| EquipementController | GET /api/equipements | IEquipementService.findAll() | 200 OK | — | Lister tous les équipements |
| EquipementController | GET /api/equipements/{id} | IEquipementService.findById(id) | 200 OK | 404 Not Found | Consulter un équipement par ID |
| EquipementController | POST /api/equipements | IEquipementService.create(equipement) | 201 Created | 400 Bad Request | Créer un équipement (libellé obligatoire) |
| EquipementController | PUT /api/equipements/{id} | IEquipementService.update(id, equipement) | 200 OK | 400 Bad Request, 404 Not Found | Modifier un équipement existant |
| EquipementController | DELETE /api/equipements/{id} | IEquipementService.deleteById(id) | 204 No Content | 404 Not Found | Supprimer un équipement |
| ClientController | GET /api/clients | IClientService.findAll() | 200 OK | — | Lister tous les clients |
| ClientController | GET /api/clients/{id} | IClientService.findById(id) | 200 OK | 404 Not Found | Consulter un client par ID |
| ClientController | POST /api/clients | IClientService.create(client) | 201 Created | 400 Bad Request | Créer un client (nom, prénom, e-mail) |
| ClientController | PUT /api/clients/{id} | IClientService.update(id, client) | 200 OK | 400 Bad Request, 404 Not Found | Modifier un client existant |
| ClientController | DELETE /api/clients/{id} | IClientService.deleteById(id) | 204 No Content | 404 Not Found | Supprimer un client |
| ReservationController | GET /api/reservations | IReservationService.findAll() | 200 OK | — | Lister toutes les réservations |
| ReservationController | GET /api/reservations/{id} | IReservationService.findById(id) | 200 OK | 404 Not Found | Consulter une réservation par ID |
| ReservationController | POST /api/reservations | IReservationService.create(reservation) | 201 Created | 400 Bad Request | Créer une réservation (date fin >= début) |
| ReservationController | PUT /api/reservations/{id} | IReservationService.update(id, reservation) | 200 OK | 400 Bad Request, 404 Not Found | Modifier une réservation existante |
| ReservationController | DELETE /api/reservations/{id} | IReservationService.deleteById(id) | 204 No Content | 404 Not Found | Supprimer une réservation |
| MaintenanceController | GET /api/maintenances | IMaintenanceService.findAll() | 200 OK | — | Lister toutes les maintenances |
| MaintenanceController | GET /api/maintenances/{id} | IMaintenanceService.findById(id) | 200 OK | 404 Not Found | Consulter une maintenance par ID |
| MaintenanceController | POST /api/maintenances | IMaintenanceService.create(maintenance) | 201 Created | 400 Bad Request | Créer une maintenance (date fin >= début) |
| MaintenanceController | PUT /api/maintenances/{id} | IMaintenanceService.update(id, maintenance) | 200 OK | 400 Bad Request, 404 Not Found | Modifier une maintenance existante |
| MaintenanceController | DELETE /api/maintenances/{id} | IMaintenanceService.deleteById(id) | 204 No Content | 404 Not Found | Supprimer une maintenance |

---

## B. Annotations Jackson et Sérialisation

| Association | Annotation retenue | Justification |
|---|---|---|
| Contrat ↔ Paiement | `@JsonManagedReference("contrat-paiements")` sur `Contrat.paiements`<br>`@JsonBackReference("contrat-paiements")` sur `Paiement.contrat` | Vraie composition : permet de créer un contrat avec ses paiements dans le JSON du POST tout en coupant la boucle infinie de sérialisation. |
| Agence → Vehicule | `@JsonIgnore` sur `Agence.vehicules` | Agrégation simple : évite de renvoyer tout le parc de véhicules lors de la simple lecture d'une agence. |
| Agence → Employe | `@JsonIgnore` sur `Agence.employes` | Évite de surcharger la réponse agence et coupe la bidirectionnalité. |
| Client → Reservation | `@JsonIgnore` sur `Client.reservations` | Évite de charger tout l'historique des réservations lors de la lecture d'un client. |
| Equipement ↔ Vehicule | `@JsonIgnore` sur `Equipement.vehicules` | Relation N-N : coupe la circularité entre équipements et véhicules. |
| Vehicule → Maintenance | `@JsonIgnore` sur `Vehicule.maintenances` | Évite la récursion infinie lors de la sérialisation d'un véhicule. |
| Reservation ↔ Contrat | `@JsonIgnore` sur `Reservation.contrat` | Coupe la boucle bidirectionnelle 1-1. |
| Entités avec proxies LAZY | `@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})` | Évite l'exception Jackson `ByteBuddyInterceptor` lors de la sérialisation de proxies Hibernate. |

### Différence entre `@JsonBackReference` et `@JsonIgnore`
* `@JsonBackReference` fonctionne en **binôme** avec `@JsonManagedReference`. Le champ annoté est ignoré lors de la sérialisation (pas de boucle infinie en JSON sortant), mais il est réinjecté et reconnecté automatiquement lors de la désérialisation parent/enfant.
* `@JsonIgnore` ignore totalement le champ dans **les deux sens** (lecture et écriture). Si on le plaçait par exemple sur `Reservation.client`, le JSON envoyé pour créer une réservation ignorerait le client.

---

## C. Postman et Swagger

* **Postman** : Outil de test dynamique. Il permet d'automatiser des scénarios complets (créer un contrat, extraire son ID dans `{{contratId}}`, vérifier le code 201 via des scripts de test `pm.test`, puis rejouer des tests de mise à jour et suppression).
* **Swagger UI (SpringDoc)** : Outil de documentation interactive généré automatiquement à partir du code (`/api/swagger-ui.html`). Il permet aux développeurs frontend de découvrir la spécification OpenAPI (`/api/v3/api-docs`), les schémas d'objets JSON et d'exécuter des requêtes d'essai directes.
* **Complémentarité** : Swagger documente le contrat d'interface en temps réel ; Postman valide et automatise les suites de tests et cas d'erreur.

---

## D. Anomalies SonarQube for IDE

| Anomalie | Règle / explication | Correction apportée |
|---|---|---|
| Injection par attribut `@Autowired` | S6813 : Les dépendances doivent être explicites et les champs `final` pour assurer l'immutabilité et l'injection par constructeur. | Utilisation de champs `private final` avec `@RequiredArgsConstructor` de Lombok sur tous les contrôleurs. |
| Exposition directe des entités du domaine | S6856 : Les contrôleurs REST devraient retourner des DTOs plutôt que des entités JPA pour éviter le couplage fort avec la base. | Pris en compte comme choix provisoire de l'atelier 5 avant l'introduction des DTOs au chapitre 10 ; sécurisé avec les annotations Jackson ciblées. |
| Imports inutilisés | S1128 : Les imports non utilisés encombrent le code. | Nettoyage des imports inutilisés dans les classes de contrôleurs. |

---

## E. Questions de compréhension

1. **Quel composant reçoit toutes les requêtes, et comment retrouve-t-il la méthode du contrôleur à appeler ?**
   C'est le `DispatcherServlet` (le Front Controller de Spring MVC). Pour chaque requête entrante, il interroge le composant `HandlerMapping` (principalement `RequestMappingHandlerMapping`), qui associe l'URL demandée et la méthode HTTP (GET, POST, etc.) à la méthode correspondante dans un `@RestController`.

2. **Pourquoi `ContratController` dépend-il de `IContratService` et non de `ContratRepository` ni de `ContratServiceImpl` ?**
   Pour respecter le principe d'inversion des dépendances (SOLID) et le découplage des couches :
   - Le contrôleur ne doit pas accéder directement aux données (`ContratRepository`) car la logique métier et la validation appartiennent à la couche Service.
   - Il dépend de l'interface `IContratService` (abstraction) et non de l'implémentation `ContratServiceImpl` pour permettre le remplacement aisé de l'implémentation (ou l'utilisation de mocks pour les tests).

3. **Pourquoi un POST renvoie-t-il 201 et un DELETE 204 ? Pourquoi une ressource absente donne-t-elle 404 et non 500 ?**
   - **POST renvoie 201 (Created)** : Indique qu'une nouvelle ressource a bien été créée sur le serveur, souvent avec son identifiant attribué.
   - **DELETE renvoie 204 (No Content)** : La suppression a réussi, la ressource n'existe plus et il n'y a pas de contenu nécessaire à renvoyer dans le corps de réponse.
   - **404 vs 500** : Le code **404 (Not Found)** indique une situation normale côté client (la ressource demandée n'existe pas en base). Le code **500 (Internal Server Error)** est réservé à une erreur technique ou un plantage inattendu du serveur.

4. **Pourquoi `PaiementController` n'expose-t-il que des GET ? Quelle réponse reçoit un POST `/api/paiements` ?**
   - Parce que le `Paiement` est en **composition** stricte avec le `Contrat` : un paiement n'a pas de sens seul. Sa création et sa suppression sont pilotées par le `Contrat` via la cascade JPA (`CascadeType.ALL` et `orphanRemoval = true`).
   - Un POST sur `/api/paiements` reçoit une réponse HTTP **405 Method Not Allowed** car la méthode POST n'est pas gérée par ce contrôleur.

5. **Quels risques présente l'exposition directe des entités JPA ? Pourquoi `Contrat.paiements` utilise-t-il `@JsonManagedReference` alors que `Agence.vehicules` utilise `@JsonIgnore` ?**
   - **Risques** : Exposition de données internes/sensibles, boucles de sérialisation JSON (`StackOverflowError`), chargement intempestif de proxies LAZY hors transaction, et couplage rigide entre le modèle de données et le contrat de l'API.
   - `Contrat.paiements` utilise `@JsonManagedReference` car on souhaite que le JSON d'un contrat contienne ses paiements (composition), tout en coupant la référence inverse sur le contrat parent.
   - `Agence.vehicules` utilise `@JsonIgnore` car on ne veut pas rapatrier la liste complète des véhicules à chaque consultation d'une agence (agrégation simple où le véhicule a sa propre API `/api/vehicules`).
