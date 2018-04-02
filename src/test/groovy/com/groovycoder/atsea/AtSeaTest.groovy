package com.groovycoder.atsea

import com.groovycoder.atsea.pages.LandingPage
import geb.junit4.GebTest
import org.junit.Before
import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test
import org.openqa.selenium.remote.DesiredCapabilities
import org.testcontainers.DockerClientFactory
import org.testcontainers.containers.BrowserWebDriverContainer
import org.testcontainers.containers.DockerComposeContainer

import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL

class AtSeaTest extends GebTest {

    @ClassRule
    public static BrowserWebDriverContainer chrome =
            new BrowserWebDriverContainer()
                .withDesiredCapabilities(DesiredCapabilities.chrome())
                .withRecordingMode(RECORD_ALL, new File("build"))

    @ClassRule
    public static DockerComposeContainer atSeaShop =
            new DockerComposeContainer("atsea", new File("docker-compose.yml"))
                .withLocalCompose(true)
                .withExposedService("appserver", 8080)

    @BeforeClass
    static void setUpClass() {
        String networkId = atSeaShop.listChildContainers()
                .find {it.image.contains("appserver")}
                .networkSettings.networks
                .find {it.key.contains("front")}
                .value.networkID

        DockerClientFactory.instance().client()
                .connectToNetworkCmd()
                .withContainerId(chrome.containerId)
                .withNetworkId(networkId)
                .exec()

        sleep 2000
    }

    @Before
    void setUp() {
        browser.driver = chrome.webDriver
        browser.baseUrl = "http://appserver:8080"
    }

    @Test
    void "can checkout"() {
        to LandingPage

        assert title == "AtSea"

        // register

        $('span', text: 'Create User').click()
        assert title == "checkout"

        // add to cart

        // checkout



    }


}
