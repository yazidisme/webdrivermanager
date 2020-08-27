/*
 * (C) Copyright 2020 Boni Garcia (http://bonigarcia.github.io/)
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
package io.github.bonigarcia.wdm.test.versions;

import static java.io.File.separator;
import static java.lang.invoke.MethodHandles.lookup;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;

import io.github.bonigarcia.wdm.cache.CacheHandler;
import io.github.bonigarcia.wdm.config.Config;

/**
 * FilterCacheBy test.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 3.8.0
 */
@RunWith(Parameterized.class)
public class VersionFilterTest {

    final Logger log = getLogger(lookup().lookupClass());

    @Parameter(0)
    public String version;

    @Parameter(1)
    public int expectedVersions;

    @Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {
        return asList(new Object[][] { { "74", 1 }, { "77", 1 }, { "79", 2 } });
    }

    @Test
    public void testFilterCacheBy() {
        CacheHandler cacheHandler = new CacheHandler(new Config());
        List<File> filteredList = cacheHandler.filterCacheBy(getInputFileList(),
                version, true);

        log.debug("Version {} -- Output {}", version, filteredList);
        assertEquals(filteredList.size(), expectedVersions);
    }

    private List<File> getInputFileList() {
        List<File> output = new ArrayList<>();
        File currentFolder = new File(".");
        String[] versions = { "74.0.3729.6", "75.0.3770.140", "75.0.3770.8",
                "75.0.3770.90", "76.0.3809.126", "76.0.3809.68", "77.0.3865.40",
                "78.0.3904.70", "79.0.3945.16", "79.0.3945.36" };
        for (String v : versions) {
            output.add(new File(currentFolder, v + separator + "chromedriver"));
        }
        return output;
    }

}
