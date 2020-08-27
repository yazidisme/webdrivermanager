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
package io.github.bonigarcia.wdm.test.other;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.online.HttpClient;
import io.github.bonigarcia.wdm.online.S3NamespaceContext;

/**
 * Namespace tests.
 *
 * @author andruhon and Boni Garcia (boni.gg@gmail.com)
 * @since 4.1.0
 */

public class NamespaceContextTest {

    public static final S3NamespaceContext S_3_BUCKET_LIST_NAMESPACE_CONTEXT = new S3NamespaceContext();

    public static final String S3_URI = "http://doc.s3.amazonaws.com/2006-03-01";

    @Test
    public void testS3BucketListNamespaceContextUrls() throws IOException {
        TestWebDriverManager testManager = new TestWebDriverManager();
        List<URL> urls = testManager.getDriverUrls();
        assertThat(urls, is(not(empty())));
    }

    @Test
    public void testS3BucketListNamespaceContextPrefixes() {
        assertThat(S_3_BUCKET_LIST_NAMESPACE_CONTEXT.getNamespaceURI("s3"),
                equalTo(S3_URI));
        assertThat(S_3_BUCKET_LIST_NAMESPACE_CONTEXT.getPrefix(S3_URI),
                equalTo("s3"));
        Iterator<String> prefixes = S_3_BUCKET_LIST_NAMESPACE_CONTEXT
                .getPrefixes(S3_URI);
        assertThat(prefixes.next(), equalTo("s3"));
        assertThat(prefixes.hasNext(), equalTo(false));
    }

    @Test
    public void testS3BucketListNamespaceContextInvalidPrefixes() {
        try {
            S_3_BUCKET_LIST_NAMESPACE_CONTEXT.getNamespaceURI("xmlns");
            fail("IllegalArgumentException should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), equalTo("Unsupported prefix"));
        }
        try {
            S_3_BUCKET_LIST_NAMESPACE_CONTEXT
                    .getPrefix("http://www.w3.org/2000/xmlns/");
            fail("IllegalArgumentException should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), equalTo("Unsupported namespace URI"));
        }
        assertThat(
                S_3_BUCKET_LIST_NAMESPACE_CONTEXT
                        .getPrefixes("http://www.w3.org/2000/xmlns/").hasNext(),
                is(false));
    }

    private static final class TestWebDriverManager extends WebDriverManager {

        @Override
        protected List<URL> getDriverUrls() throws IOException {
            httpClient = new HttpClient(config());
            return getDriversFromXml(getDriverUrl(), "//s3:Contents/s3:Key",
                    getS3NamespaceContext());
        }

        @Override
        protected Optional<String> getBrowserVersionFromTheShell() {
            return Optional.empty();
        }

        @Override
        protected String getDriverName() {
            return null;
        }

        @Override
        protected String getDriverVersion() {
            return null;
        }

        @Override
        protected void setDriverVersion(String driverVersion) {

        }

        @Override
        protected String getBrowserVersion() {
            return null;
        }

        @Override
        protected void setBrowserVersion(String browserVersion) {

        }

        @Override
        protected URL getDriverUrl() {
            return getDriverUrlCkeckingMirror(config().getChromeDriverUrl());
        }

        @Override
        protected void setDriverUrl(URL url) {

        }

        @Override
        protected Optional<URL> getMirrorUrl() {
            return Optional.empty();
        }

        @Override
        protected Optional<String> getExportParameter() {
            return Optional.empty();
        }

        @Override
        public DriverManagerType getDriverManagerType() {
            return null;
        }

    }

}
