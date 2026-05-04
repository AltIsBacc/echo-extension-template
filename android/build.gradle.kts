plugins {
    id("com.android.application")
    id("ext-convention")
}

// Change "MyExtension" to your extension's class name.
val meta = extensionMetadata(extClassName = "MyExtension")
val proguardTask = generateProguardRules(meta)

dependencies {
    compileOnly(libs.echo.common)
    compileOnly(libs.kotlin.stdlib)

    implementation(project(":common"))
    implementation(libs.smart.exception.java)

    // Uncomment if you need FFmpeg (also add the .aar to android/libs/):
    // implementation(files("libs/ffmpeg-kit.aar"))
}

android {
    namespace = "dev.brahmkshatriya.echo.extension"
    compileSdk = 35

    defaultConfig {
        applicationId = "dev.brahmkshatriya.echo.extension.${meta.id}"
        minSdk = 24
        targetSdk = 35

        versionCode = meta.verCode
        versionName = meta.verName

        manifestPlaceholders.putAll(
            mapOf(
                "type"         to "dev.brahmkshatriya.echo.${meta.type}",
                "id"           to meta.id,
                "class_path"   to "dev.brahmkshatriya.echo.extension.${meta.className}",
                "version"      to meta.verName,
                "version_code" to meta.verCode.toString(),
                "app_name"     to "Echo : ${meta.name} Extension",
                "name"         to meta.name,
                "author"       to meta.author,
                "icon_url"     to meta.iconUrl,
                "description"  to meta.description,
                "author_url"   to meta.authorUrl,
                "repo_url"     to meta.repoUrl,
                "update_url"   to meta.updateUrl,
            )
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                layout.buildDirectory.file("generated/proguard/generated-rules.pro").get().asFile.path
            )
        }

        debug {
            isMinifyEnabled = false
        }
    }

    splits {
        abi {
            isEnable = true
            reset()
            include("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
            isUniversalApk = false
        }
    }
}

tasks.named("preBuild") {
    dependsOn(proguardTask)
}
