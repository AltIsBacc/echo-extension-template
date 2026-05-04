plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("ext-convention")
}

dependencies {
    api(libs.echo.common)
    api(libs.kotlin.stdlib)
}
