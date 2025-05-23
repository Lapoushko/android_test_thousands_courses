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

rootProject.name = "android_test_thousands_courses"
include(":app")
include(":feature")
include(":feature:onboarding")
include(":common")
include(":common:ui")
include(":navigation")
include(":feature:auth")
include(":feature:main")
include(":feature:favourite")
include(":feature:profile")
include(":feature:detail")
include(":common:extension")
include(":domain")
include(":data")
include(":data:storage")
include(":data:network")
