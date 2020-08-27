/*
 * (C) Copyright 2017 Boni Garcia (http://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.github.bonigarcia.wdm.config;

/**
 * Types for driver managers.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 2.1.0
 */
public enum DriverManagerType {

    CHROME("org.openqa.selenium.chrome.ChromeDriver"),
    FIREFOX("org.openqa.selenium.firefox.FirefoxDriver"),
    OPERA("org.openqa.selenium.opera.OperaDriver"),
    EDGE("org.openqa.selenium.edge.EdgeDriver"),
    PHANTOMJS("org.openqa.selenium.phantomjs.PhantomJSDriver"),
    IEXPLORER("org.openqa.selenium.ie.InternetExplorerDriver"),
    SELENIUM_SERVER_STANDALONE(
            "org.openqa.selenium.remote.server.SeleniumServer"),
    CHROMIUM("org.openqa.selenium.chrome.ChromeDriver"),
    SAFARI("org.openqa.selenium.safari.SafariDriver");

    String browserClass;

    DriverManagerType(String browserClass) {
        this.browserClass = browserClass;
    }

    public String browserClass() {
        return browserClass;
    }

    @Override
    public String toString() {
        switch (this) {
        case CHROME:
            return "Chrome";
        case CHROMIUM:
            return "Chromium";
        case FIREFOX:
            return "Firefox";
        case OPERA:
            return "Opera";
        case EDGE:
            return "Edge";
        case PHANTOMJS:
            return "PhantomJS";
        case IEXPLORER:
            return "Internet Explorer";
        case SAFARI:
            return "Safari";
        case SELENIUM_SERVER_STANDALONE:
            return "Selenium Server Standalone";
        default:
            throw new WebDriverManagerException(
                    "Invalid driver manager type: " + this.name());
        }
    }

}
