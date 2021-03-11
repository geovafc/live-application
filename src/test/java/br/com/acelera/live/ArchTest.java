package br.com.acelera.live;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("br.com.acelera.live");

        noClasses()
            .that()
            .resideInAnyPackage("br.com.acelera.live.service..")
            .or()
            .resideInAnyPackage("br.com.acelera.live.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..br.com.acelera.live.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
