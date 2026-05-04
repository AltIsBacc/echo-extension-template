package dev.brahmkshatriya.echo.extension

import android.annotation.SuppressLint
import android.app.Application
import dev.brahmkshatriya.echo.common.settings.Setting

/**
 * Android entry-point for your Echo extension.
 *
 * Add whichever echo-common client interfaces fit your extension type:
 *   - MusicClient     → music source (search, albums, playlists, radio)
 *   - LyricsClient    → lyrics provider
 *   - TrackerClient   → scrobbling / playback tracking
 *   - DownloadClient  → custom download pipeline
 *
 * Quick-start:
 *  1. Rename this class, then update `extClassName` in android/build.gradle.kts.
 *  2. Add your interface(s) to the class declaration below.
 *  3. Implement [onInitialize] and override [getSettingItems] as needed.
 */
@SuppressLint("PrivateApi")
class MyExtensionAndroid : MyExtensionBase() {

    /** Host application context — available after [onInitialize] is called. */
    private val app: Application by lazy {
        Class.forName("android.app.ActivityThread")
            .getMethod("currentApplication")
            .invoke(null) as Application
    }

    override suspend fun onInitialize() {
        // TODO: initialise your clients / SDKs here.
        // Access settings via: settings.getString("my_key")
        // Access app context via: app
    }

    override suspend fun getSettingItems(): List<Setting> = emptyList()
}
