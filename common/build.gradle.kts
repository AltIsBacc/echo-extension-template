plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
    id("ext-convention")
}

dependencies {
    api(libs.echo.common)
    api(libs.kotlin.stdlib)
}
