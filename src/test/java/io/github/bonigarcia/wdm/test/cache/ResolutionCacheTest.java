/*
 * (C) Copyright 2018 Boni Garcia (http://bonigarcia.github.io/)
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
package io.github.bonigarcia.wdm.test.cache;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Test for preferences.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 3.0.0
 */
public class ResolutionCacheTest {

    @Test
    public void testEmptyTtl() {
        WebDriverManager.main(new String[] { "clear-resolution-cache" });
        chromedriver().ttl(0).ttlBrowsers(0).setup();
        File driver = new File(chromedriver().getDownloadedDriverPath());
        assertTrue(driver.exists());
    }

}
