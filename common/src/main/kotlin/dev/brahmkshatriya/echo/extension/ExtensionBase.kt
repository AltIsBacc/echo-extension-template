package dev.brahmkshatriya.echo.extension

import dev.brahmkshatriya.echo.common.clients.ExtensionClient
import dev.brahmkshatriya.echo.common.settings.Setting
import dev.brahmkshatriya.echo.common.settings.Settings

/**
 * Shared base class for all platform variants of this extension.
 *
 * Handles the [Settings] injection lifecycle so each platform module
 * doesn't have to repeat the boilerplate. Subclasses access settings
 * via the [settings] property after [onInitialize] is called.
 *
 * Platform modules extend this and add whatever client interfaces they need:
 *
 * ```kotlin
 * class MyExtension : ExtensionBase(), MusicClient, TrackerClient {
 *     override suspend fun onInitialize() { … }
 * }
 * ```
 */
abstract class ExtensionBase : ExtensionClient {

    private var _settings: Settings? = null

    override fun setSettings(settings: Settings) {
        _settings = settings
    }

    /**
     * The settings instance injected by the Echo host.
     * Safe to access from [onInitialize] onwards.
     */
    protected val settings: Settings
        get() = _settings ?: error("Settings not injected — ensure setSettings() is called before use.")

    override suspend fun getSettingItems(): List<Setting> = emptyList()
}
