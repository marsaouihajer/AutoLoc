package esprit.tn.hajermarsaoui4cce11.web.controller;

import esprit.tn.hajermarsaoui4cce11.domain.Employe;
import esprit.tn.hajermarsaoui4cce11.service.IEmployeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employes")
@RequiredArgsConstructor
@Tag(name = "Employés", description = "Gestion des employés")
public class EmployeController {

    private final IEmployeService employeService;

    @GetMapping
    @Operation(summary = "Lister les employés")
    @ApiResponse(responseCode = "200", description = "Liste des employés récupérée avec succès")
    public List<Employe> findAll() {
        return employeService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un employé")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employé trouvé"),
            @ApiResponse(responseCode = "404", description = "Employé introuvable")
    })
    public Employe findById(@Parameter(description = "Identifiant de l'employé") @PathVariable Long id) {
        return employeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un employé")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employé créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données obligatoires manquantes ou invalides")
    })
    public Employe create(@RequestBody Employe employe) {
        return employeService.create(employe);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un employé")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employé modifié avec succès"),
            @ApiResponse(responseCode = "400", description = "Données obligatoires manquantes ou invalides"),
            @ApiResponse(responseCode = "404", description = "Employé introuvable")
    })
    public Employe update(@Parameter(description = "Identifiant de l'employé") @PathVariable Long id,
                          @RequestBody Employe employe) {
        return employeService.update(id, employe);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un employé")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Employé supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Employé introuvable")
    })
    public void deleteById(@Parameter(description = "Identifiant de l'employé") @PathVariable Long id) {
        employeService.deleteById(id);
    }
}
