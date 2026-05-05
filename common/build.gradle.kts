plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
    id("ext-convention")
}

dependencies {
    compileOnlyApi(libs.echo.common)
    compileOnlyApi(libs.kotlin.stdlib)
}
