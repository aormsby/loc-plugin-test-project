package com.example.locgentester

import android.widget.TextView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Locale


@RunWith(AndroidJUnit4::class)
class LocalePluginVerificationTest {

    private lateinit var device: UiDevice
    private lateinit var select: UiSelector

    @Before
    fun setup() {
        device = UiDevice.getInstance(getInstrumentation())
        select = UiSelector()

        device.executeShellCommand("settings put global window_animation_scale 0.0")
        device.executeShellCommand("settings put global transition_animation_scale 0.0")
        device.executeShellCommand("settings put global animator_duration_scale 0.0")
        device.executeShellCommand("settings put global development_settings_enabled 1")
    }

    @After
    fun teardown() {
        device.executeShellCommand("settings put global window_animation_scale 1.0")
        device.executeShellCommand("settings put global transition_animation_scale 1.0")
        device.executeShellCommand("settings put global animator_duration_scale 1.0")
        device.executeShellCommand("settings put global development_settings_enabled 0")
    }

    @Test
    fun verifyAppLanguageSelection() {
        device.pressHome()
        val startX = device.displayWidth / 2
        val startY = device.displayHeight - 100 // Start near the bottom
        val endX = device.displayWidth / 2
        val endY = 100 // Swipe upwards
        val steps = 100
        device.swipe(startX, startY, endX, endY, steps)

        device.findObject(select.text("LocGenTester")).longClick()
        device.findObject(select.text("App info")).click()

        val scrollOptions = UiScrollable(device.findObject(select.scrollable(true)).selector)
        val languageItem =
            scrollOptions.getChildByText(UiSelector().className(TextView::class.java), "Language")
        languageItem.click()

        val scrollLang = UiScrollable(device.findObject(select.scrollable(true)).selector)
        var keepScrolling = true
        val foundLanguages = mutableSetOf<String>()

        while (keepScrolling) {
            val options = device.findObjects(By.clazz(TextView::class.java).clickable(true))
            foundLanguages.addAll(options.mapNotNull { it.text })
            keepScrolling = scrollLang.scrollForward()
        }

        val supportedLocalesByName = BuildConfig.SUPPORTED_LOCALES
            .split(",")
            .map {
                with(Locale.forLanguageTag(it.replace("-r", "-").trim())) {
                    getDisplayName(this)
                }
            }

        with(foundLanguages.map { it.lowercase() }) {
            supportedLocalesByName
                .mapNotNull {
                    if (it.contains("Éñĝļîšĥ") || it.contains("\u061C\u202EArabic\u202C\u061C"))
                        null
                    else it.lowercase()
                }.forEach {
                    Assert.assertTrue(it.lowercase() in this)
                }
        }

        // can't assert pseudolocales, emulators don't seem to pick up on them
    }
}