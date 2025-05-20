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

rootProject.name = "compose-app"
include(":app")
include(":route")
include(":feature_home")
include(":feature_search")
include(":core_domain")
project(":core_domain").projectDir = file("core/core_domain")
include(":core_ui")
project(":core_ui").projectDir = file("core/core_ui")
include(":core_data")
project(":core_data").projectDir = file("core/core_data")
include(":word_domain")
project(":word_domain").projectDir = file("word/word_domain")
include(":word_data")
project(":word_data").projectDir = file("word/word_data")
include(":word_ui")
project(":word_ui").projectDir = file("word/word_ui")
