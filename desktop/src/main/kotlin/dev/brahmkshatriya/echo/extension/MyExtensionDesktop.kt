package dev.brahmkshatriya.echo.extension

import dev.brahmkshatriya.echo.common.settings.Setting

/**
 * Desktop (JVM) entry-point for your Echo extension.
 *
 * Add whichever echo-common client interfaces fit your extension type:
 *   - MusicClient     → music source (search, albums, playlists, radio)
 *   - LyricsClient    → lyrics provider
 *   - TrackerClient   → scrobbling / playback tracking
 *   - DownloadClient  → custom download pipeline
 *
 * Quick-start:
 *  1. Rename this class, then update `extClassName` in desktop/build.gradle.kts.
 *  2. Add your interface(s) to the class declaration below.
 *  3. Implement [onInitialize] and override [getSettingItems] as needed.
 */
class MyExtensionDesktop : MyExtensionBase() {

    override suspend fun onInitialize() {
        // TODO: initialise your clients / SDKs here.
        // Access settings via: settings.getString("my_key")
    }

    override suspend fun getSettingItems(): List<Setting> = emptyList()
}
