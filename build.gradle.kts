/*
 * By listing all the plugins used throughout all subprojects in the root project build script, it
 * ensures that the build script classpath remains the same for all projects. This avoids potential
 * problems with mismatching versions of transitive plugin dependencies. A subproject that applies
 * an unlisted plugin will have that plugin and its dependencies _appended_ to the classpath, not
 * replacing pre-existing dependencies.
 *
 * It also prevents the Kotlin Gradle plugin from being loaded multiple times across subprojects,
 * which Gradle warns about and which can break the build.
 */
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library)     apply false
    alias(libs.plugins.kotlin.jvm)          apply false
    alias(libs.plugins.shadow)              apply false
}
