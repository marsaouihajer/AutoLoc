package esprit.tn.hajermarsaoui4cce11.web.controller;

import esprit.tn.hajermarsaoui4cce11.domain.Maintenance;
import esprit.tn.hajermarsaoui4cce11.service.IMaintenanceService;
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
@RequestMapping("/maintenances")
@RequiredArgsConstructor
@Tag(name = "Maintenances", description = "Gestion des maintenances de véhicules")
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;

    @GetMapping
    @Operation(summary = "Lister les maintenances")
    @ApiResponse(responseCode = "200", description = "Liste des maintenances récupérée avec succès")
    public List<Maintenance> findAll() {
        return maintenanceService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une maintenance")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maintenance trouvée"),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable")
    })
    public Maintenance findById(@Parameter(description = "Identifiant de la maintenance") @PathVariable Long id) {
        return maintenanceService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer une maintenance")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Maintenance créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Date de fin antérieure ou données invalides")
    })
    public Maintenance create(@RequestBody Maintenance maintenance) {
        return maintenanceService.create(maintenance);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une maintenance")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maintenance modifiée avec succès"),
            @ApiResponse(responseCode = "400", description = "Date de fin antérieure ou données invalides"),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable")
    })
    public Maintenance update(@Parameter(description = "Identifiant de la maintenance") @PathVariable Long id,
                              @RequestBody Maintenance maintenance) {
        return maintenanceService.update(id, maintenance);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer une maintenance")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Maintenance supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable")
    })
    public void deleteById(@Parameter(description = "Identifiant de la maintenance") @PathVariable Long id) {
        maintenanceService.deleteById(id);
    }
}
