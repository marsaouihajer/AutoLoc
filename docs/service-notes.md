# Atelier 4 — Notes sur la couche Service (AutoLoc)

## A. Modes d'injection comparés (étape 2)

| Critère | Constructeur (`@RequiredArgsConstructor`, champ `final`) | Attribut (`@Autowired` sur le champ) |
|---|---|---|
| Champ `final` possible ? | Oui | Non |
| Dépendance visible dans la signature de la classe ? | Oui (paramètre du constructeur) | Non (dépendance cachée) |
| Utilisable avec `new` hors conteneur sans dépendance `null` ? | Oui : `new ContratServiceImpl(repo)` oblige à fournir le repository | Non : le champ reste `null` sans Spring |
| Signalé par SonarQube for IDE ? | Non | Oui (règle S6813 — à vérifier dans le panneau) |

## B. Services

| Service | Dépendances injectées | Mode | Justification |
|---|---|---|---|
| AgenceServiceImpl | IAgenceRepository | Constructeur | CRUD ; refuse nom, ville ou adresse vide. |
| EmployeServiceImpl | IEmployeRepository | Constructeur | CRUD ; refuse nom ou prénom vide, rôle absent. |
| VehiculeServiceImpl | IVehiculeRepository | Constructeur | CRUD ; refuse immatriculation vide ou tarif journalier négatif. |
| EquipementServiceImpl | IEquipementRepository | Constructeur | CRUD ; refuse un libellé vide. |
| ClientServiceImpl | IClientRepository | Constructeur | CRUD ; refuse nom, prénom ou e-mail vide. |
| ReservationServiceImpl | IReservationRepository | Constructeur | CRUD ; refuse une date de fin antérieure à la date de début. |
| ContratServiceImpl | IContratRepository | Constructeur | CRUD ; refuse un montant total négatif ; la suppression supprime les paiements (cascade ALL + orphanRemoval). |
| PaiementServiceImpl | IPaiementRepository | Constructeur | Lecture seule (findById, findAll) : création et suppression passent par le Contrat (composition). |
| MaintenanceServiceImpl | IMaintenanceRepository | Constructeur | CRUD ; refuse une date de fin antérieure à la date de début. |

Dans tous les cas : champ `final`, dépendance typée par une interface, service sans état, aucun `new` de bean,
aucune injection par attribut.

## C. Messages d'erreur du conteneur (étape 3)

**Message 1** — `NoSuchBeanDefinitionException` (enveloppée dans `UnsatisfiedDependencyException`) : aucun bean de type
`IContratService` pour `ContratController`.
Causes probables : (1) `@Service` oublié sur `ContratServiceImpl` ; (2) classe placée hors du package
`esprit.tn.hajermarsaoui4cce11` (non couverte par le scan). Correction : ajouter `@Service` / déplacer la classe.

**Message 2** — `NoUniqueBeanDefinitionException` : deux beans (`emailNotificateur`, `smsNotificateur`) du même type.
Correction 1 : `@Primary` **sur la classe** du bean à choisir par défaut.
Correction 2 : `@Qualifier("emailNotificateur")` **sur le paramètre du constructeur** du contrôleur.

**Message 3** — dépendance circulaire (`BeanCurrentlyInCreationException`) entre `clientServiceImpl` et
`reservationServiceImpl`. Avec l'injection par constructeur, chaque bean doit exister **complètement** avant d'être
fourni à l'autre : aucun des deux ne peut être créé en premier, le cycle ne peut pas être contourné (avec des setters
ou des attributs, Spring pourrait injecter une référence partielle, ce qui masque le défaut de conception).
Piste : extraire la responsabilité commune dans un troisième service, ou faire dépendre les deux services du
repository plutôt que l'un de l'autre.

## D. Anomalies SonarQube for IDE (à compléter avec VOS résultats d'analyse)

| Anomalie | Règle / explication | Correction apportée |
|---|---|---|
| Injection par attribut `@Autowired` (étape 2) | S6813 : champ non `final`, dépendance cachée, `null` hors conteneur. | Retour à l'injection par constructeur (`@RequiredArgsConstructor` + `final`). |
| `RuntimeException` levée à la place de l'exception métier (étape 4) | S112 : une exception générique ne permet pas de distinguer l'erreur métier. | Restauration de `ResourceNotFoundException`. |
| Troisième anomalie (exemple : import inutilisé) | Les imports inutilisés alourdissent le code. | Suppression de l'import. |

## E. Questions de compréhension

1. **Où est le `new` de `ContratServiceImpl` et qui l'exécute ?** Dans le conteneur Spring : au démarrage, le scan détecte
   `@Service`, puis le conteneur appelle le constructeur en lui fournissant le proxy de `IContratRepository`.
2. **Pourquoi dépendre de `IContratService` et non de `ContratServiceImpl` ?** Pour dépendre d'une abstraction : on peut
   changer d'implémentation ou fournir un remplaçant (test) sans modifier le contrôleur (couplage faible).
3. **Pourquoi un singleton doit-il rester sans état ?** Il est partagé par tous les threads. Exemple de bug : un attribut
   `contratCourant` modifié par une requête puis lu par une autre → un utilisateur voit ou modifie le contrat d'un autre.
4. **Pourquoi `update` charge-t-il l'existant avant `save` ?** Passer directement l'objet reçu écraserait toutes les colonnes
   (valeurs absentes = `null`) et, avec `orphanRemoval`, pourrait vider la collection de paiements ; charger puis recopier
   ne modifie que les attributs voulus et garde l'identifiant et les collections inverses.
5. **`@Component` vs `@Bean`** : `@Component` annote une classe qu'on possède (détectée par le scan) ; `@Bean` annote une méthode
   d'une classe `@Configuration` pour déclarer un bean qu'on ne peut pas annoter (bibliothèque, JDK) ou construire spécialement.
   **`@Primary` vs `@Qualifier`** : `@Primary` (sur la classe) désigne le candidat par défaut ; `@Qualifier("nom")` (à l'injection)
   impose un choix explicite. **`IPaiementService` en lecture seule** : le Paiement est en composition avec le Contrat, donc créé
   et supprimé uniquement via lui (cascade, `orphanRemoval`).
