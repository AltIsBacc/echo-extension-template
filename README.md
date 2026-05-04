# Echo Extension Template

A starting point for building **Echo** extensions.

> **Echo** is an open-source music/media player that supports community-built extensions for streaming sources, lyrics providers, trackers, and more.

---

## Table of Contents

- [Project layout](#project-layout)
- [Quick start](#quick-start)
- [Configuration](#configuration)
- [Extension types](#extension-types)
- [Module overview](#module-overview)
- [Building](#building)
- [Releasing](#releasing)
- [Dependency versions](#dependency-versions)
- [Contributing](#contributing)
- [License](#license)

---

## Project layout

```
echo-extension-template/
├── common/ # Shared code
│   └── src/main/kotlin/…/
│       └── MyExtensionBase.kt # extend this on each platform
│
├── android/
│   └── src/main/kotlin/…/
│       └── MyExtensionAndroid.kt # android specific implementation
│
├── desktop/
│   └── src/main/kotlin/…/
│       └── MyExtensionDesktop.kt # desktop specific implementation
│
├── buildSrc/
│   └── src/main/kotlin/
│       ├── ExtensionMetadata.kt
│       ├── ProguardUtils.kt
│       ├── ProjectConfig.kt
│       └── ext-convention.gradle.kts
│
├── gradle/
│   └── libs.versions.toml
├── gradle.properties # extension metadata (edit this!)
├── settings.gradle.kts
└── .github/workflows/build.yml # CI/CD
```

---

## Quick start

### 1. Use this template

Click **"Use this template"** on GitHub, or clone manually:

```bash
git clone https://github.com/AltIsBacc/echo-extension-template.git my-extension
cd my-extension
```

### 2. Edit `gradle.properties`

Fill in all the `ext*` fields:

```properties
extType=music           # music | tracker | lyrics | misc
extId=my-extension      # unique, lowercase, hyphen-separated
extName=My Extension
extDescription=Streams music from MyService.
extAuthor=YourName
extAuthorUrl=https://github.com/YourName
extIconUrl=https://example.com/icon.png
extRepoUrl=https://github.com/YourName/my-extension
extUpdateUrl=https://api.github.com/repos/YourName/my-extension/releases/latest
verCode=1
verName=0.1.0
```

### 3. Rename the extension class

In **both** `android/build.gradle.kts` and `desktop/build.gradle.kts`, change the `extClassName` parameter:

```kotlin
val meta = extensionMetadata(extClassName = "MyAwesomeExtension")
```

Then rename `MyExtension.kt` in both modules (and update the `class` name inside).

### 4. Implement your extension

- Add the interface(s) you need from `echo-common` to your class (e.g. `MusicClient`, `LyricsClient`).
- Bootstrap your client inside `onInitialize()`.
- Expose user settings via `getSettingItems()`.

### 5. Build and sideload

```bash
# Android debug APK
./gradlew :android:assembleDebug

# Desktop shadow JAR
./gradlew :desktop:shadowJar
```

Install the APK (rename it to EAPK) on Echo Android, or load the JAR into Echo Desktop.

---

## Configuration

All extension metadata lives in `gradle.properties`.

| Property | Description |
|---|---|
| `extType` | One of `music`, `tracker`, `lyrics`, `misc` |
| `extId` | Unique machine-readable identifier |
| `extName` | Display name shown in Echo |
| `extDescription` | Short description shown in Echo |
| `extAuthor` | Your name |
| `extAuthorUrl` | Link to your profile |
| `extIconUrl` | Square icon URL (264×264 px recommended) |
| `extRepoUrl` | GitHub / GitLab repo URL |
| `extUpdateUrl` | GitHub Releases API URL for auto-updates |
| `verCode` | Integer version code — increment on every release |
| `verName` | Semantic version string (e.g. `1.2.3`) |

---

## Extension types

| Type | What it does |
|---|---|
| `music` | Provides tracks, albums, playlists, search, radio |
| `tracker` | Scrobbles playback to a tracking service |
| `lyrics` | Provides synced or unsynced lyrics |
| `misc` | Anything else (downloaders, converters, …) |

---

## Module overview

### `common`

Pure Kotlin/JVM code shared between Android and Desktop.

- **`MyExtensionBase`** — implements `ExtensionClient` and handles the `Settings` injection lifecycle. Both platform `MyExtension` classes extend this, so neither has to repeat the boilerplate. Subclasses access settings via the `protected val settings` property inside `onInitialize` and beyond.

### `android`

Builds an APK sideloadable into Echo on Android.

- Implement echo-common client interfaces directly on `MyExtensionAndroid` (no imposed base class).

### `desktop`

Builds a shadow JAR loadable into Echo Desktop.

- Implement echo-common client interfaces directly on `MyExtensionDesktop` (no imposed base class).
- Published to Maven via the `maven-publish` plugin (JitPack-compatible).

---

## Building

```bash
# All modules
./gradlew build

# Android only
./gradlew :android:assembleDebug        # debug APK
./gradlew :android:assembleRelease      # release APK (requires signing config)

# Desktop only
./gradlew :desktop:shadowJar            # fat JAR with all dependencies

# Clean
./gradlew clean
```

Output locations:

- Android APK → `android/build/outputs/apk/`
- Desktop JAR → `desktop/build/libs/`

---

## Releasing

The included GitHub Actions workflow (`.github/workflows/build.yml`) will:

1. Build on every pull request to `main`.
2. On a `v*` tag push, build a release APK + JAR and attach them to a GitHub Release automatically.

To cut a release:

```bash
# Bump verCode and verName in gradle.properties, then:
git tag v0.1.0
git push origin v0.1.0
```

---

## Dependency versions

| Dependency | Version |
|---|---|
| Kotlin | `2.2.10` |
| Android Gradle Plugin | `8.12.1` |
| echo-common | `1.0` |
| Shadow plugin | `9.4.1` |

To update a version, edit `gradle/libs.versions.toml`.

---

## Contributing

Issues and pull requests are welcome.  
Please open an issue first for large changes so we can discuss the direction.

---

## License

This template is released under the **MIT License** — see [LICENSE](LICENSE) for details.
