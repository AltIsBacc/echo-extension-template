pluginManagement {
    includeBuild("build-logic")
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
        maven { url = uri("https://jitpack.io") }
    }
}

// Root project name is driven by the extName property in gradle.properties.
val extName: String by settings
rootProject.name = extName

include(":common")
include(":android")
include(":desktop")

check(JavaVersion.current().isCompatibleWith(JavaVersion.VERSION_17)) {
    """
    This template requires JDK 17+ but it is currently using JDK ${JavaVersion.current()}.
    Java Home: [${System.getProperty("java.home")}]
    """.trimIndent()
}
