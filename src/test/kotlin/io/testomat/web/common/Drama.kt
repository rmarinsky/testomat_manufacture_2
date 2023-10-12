package io.testomat.web.common

import com.microsoft.playwright.*
import com.microsoft.playwright.assertions.LocatorAssertions
import com.microsoft.playwright.assertions.PlaywrightAssertions
import java.util.concurrent.ConcurrentHashMap
import java.util.regex.Pattern

data class DramaScene(val context: BrowserContext, val page: Page)
object Drama {

    private val dramaScene = ConcurrentHashMap<Long, DramaScene>()

    private fun drama(): DramaScene {
        val threadId = Thread.currentThread().id
        if (!dramaScene.containsKey(threadId)) {
            val playwright = Playwright.create()
            val context = playwright
                .chromium().launch(
                    BrowserType.LaunchOptions()
                        .setHeadless(Configuration.headless)
                        .setTimeout(Configuration.defaultTimeout)
                        .setDevtools(Configuration.devTools)
                        .setSlowMo(Configuration.poolingInterval)
                )
                .newContext()
            val page = context.newPage()
            dramaScene[threadId] = DramaScene(context, page)
        }

        return dramaScene[threadId]!!
    }

    fun open(url: String) {
        drama().page.navigate(url)
    }

    fun close() {
        val threadId = Thread.currentThread().id
        if (dramaScene.containsKey(threadId)) {
            val dramaContext = dramaScene[threadId]!!
            dramaContext.page.close()
            dramaContext.context.close()
            dramaScene.remove(threadId)
        }
    }

    fun find(selector: String): Locator {
        return drama().page.locator(selector)
    }

    fun find(selector: String, withText: String): Locator {
        return drama().page.locator(selector).filter(Locator.FilterOptions().setHasText(withText))
    }

    fun waitForURL(url: String) {
        drama().page.waitForURL(url)
    }

}

fun Locator.containsText(expectedText: String) = PlaywrightAssertions.assertThat(this).containsText(expectedText)

fun Locator.containsTexts(vararg text: String) = PlaywrightAssertions.assertThat(this).containsText(text)
fun Locator.hasValue(expectedValue: String) = PlaywrightAssertions.assertThat(this).hasValue(expectedValue)

fun Locator.containsClass(className: String) =
    PlaywrightAssertions.assertThat(this).hasClass(Pattern.compile(className))

fun Locator.shouldBeVisible() = PlaywrightAssertions.assertThat(this).isVisible()
fun Locator.shouldBeHidden() = PlaywrightAssertions.assertThat(this).isHidden()
fun Locator.shouldBeVisible(timeoutInMs: Int) = PlaywrightAssertions.assertThat(this)
    .isVisible(
        LocatorAssertions.IsVisibleOptions().setTimeout(timeoutInMs.toDouble())
    )

fun Locator.parent(): Locator = this.locator("xpath=..")

fun Locator.closest(selector: String): Locator = this.locator("xpath=ancestor::$selector")

fun Locator.setVal(targetTest: String): Locator {
    this.fill(targetTest)
    return this
}

fun Locator.pressKey(key: KeysPW) = this.press(key.key)

enum class KeysPW(val key: String) {

    ENTER("Enter"), TAB("Tab"), BACKSPACE("Backspace"), DELETE("Delete"), ARROW_DOWN("ArrowDown"), ARROW_UP("ArrowUp")

}

