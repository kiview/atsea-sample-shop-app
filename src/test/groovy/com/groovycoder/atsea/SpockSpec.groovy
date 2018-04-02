package com.groovycoder.atsea

import geb.spock.GebSpec
import org.junit.runner.Description
import org.openqa.selenium.remote.DesiredCapabilities
import org.testcontainers.containers.BrowserWebDriverContainer

import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL


class SpockSpec extends GebSpec {

    BrowserWebDriverContainer chrome = new BrowserWebDriverContainer()
            .withDesiredCapabilities(DesiredCapabilities.chrome())
            .withRecordingMode(RECORD_ALL, new File("./videos"))

    def setup() {
        chrome.start()
        browser.driver = chrome.webDriver
    }

    def cleanup() {
        chrome.succeeded(Description.createTestDescription(this.class, "foo"))
    }

    def "go to login"() {
        when:
        go "http://gebish.org"

        then:
        title == "Geb - Very Groovy Browser Automation2"
    }

}
