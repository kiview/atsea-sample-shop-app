package com.groovycoder.atsea

import geb.junit4.GebTest
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import org.openqa.selenium.remote.DesiredCapabilities
import org.testcontainers.containers.BrowserWebDriverContainer
import org.testcontainers.containers.DockerComposeContainer

import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL

class AtSeaTest extends GebTest {

    @ClassRule
    public static BrowserWebDriverContainer chrome = new BrowserWebDriverContainer()
            .withDesiredCapabilities(DesiredCapabilities.chrome())
            .withRecordingMode(RECORD_ALL, new File("build"))

    @ClassRule
    public static DockerComposeContainer atSeaShop = new DockerComposeContainer(new File("docker-compose.yml"))
            .withLocalCompose(true)

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
