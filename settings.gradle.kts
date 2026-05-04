pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // JitPack — needed if you consume libraries hosted there
        maven { url = uri("https://jitpack.io") }
    }
}

// Root project name is driven by the extName property in gradle.properties.
val extName: String by settings
rootProject.name = extName

include(":common")
include(":android")
include(":desktop")
