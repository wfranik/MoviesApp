pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MoviesApp"
include(":app")
include(":data-implementation")
include(":data-contract")
include(":domain-models")
include(":ui-common")
include(":datasource-api-implementation")
include(":datasource-api-contract")
include(":datasource-api-models")
include(":domain-usecase")
include(":ui-filters")
include(":ui-home")
include(":ui-common-navigation")
