package com.groovycoder.atsea.pages

import geb.Page

class LandingPage extends Page {

    static url = "/index.html"

    static at = {
        $("div", class: "headerTitle").text() == "WELCOME TO THE ATSEA SHOP"
    }

}
