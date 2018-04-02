package com.groovycoder.atsea

import geb.junit4.GebTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.openqa.selenium.remote.DesiredCapabilities
import org.testcontainers.containers.BrowserWebDriverContainer

import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL

class GebExampleTest extends GebTest {

    @Rule
    public BrowserWebDriverContainer chrome = new BrowserWebDriverContainer()
            .withDesiredCapabilities(DesiredCapabilities.chrome())
            .withRecordingMode(RECORD_ALL, new File("build"))

    @Before
    void setUp() {
        browser.driver = chrome.webDriver
    }

    @Test
    void "go to login"() {
        go "http://gebish.org"

        assert title == "Geb - Very Groovy Browser Automation"
    }

}
