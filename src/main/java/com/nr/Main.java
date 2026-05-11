package com.nr;

import com.newrelic.api.agent.Trace;

public class Main {
    public static void main(String[] args) {
        // Each iteration will sleep for 1 second. The loop iterates enough times to keep the
        // JVM alive for a few minutes so that the Java agent can harvest and send data to APM.
        for (int i = 1; i <= 300; i++) {
            try {
                System.out.println("Loading jars");
                loadJars();
            } catch (InterruptedException e) {
                System.out.println("An exception occurred while loading jars: " + e.getMessage());
            }
        }
    }

    @Trace(dispatcher = true)
    public static void loadJars() throws InterruptedException {
        try {
            // This will force the httpclient5-5.3.1.jar to load
            Class<?> base64Clazz = Class.forName("org.apache.hc.client5.http.utils.Base64");

            // This will force the httpcore5-5.2.4.jar to load
            Class<?> charsClazz = Class.forName("org.apache.hc.core5.http.Chars");

            // This will force the httpcore5-h2-5.2.4.jar to load
            Class<?> clazz = Class.forName("org.apache.hc.core5.http2.impl.BasicH2TransportMetrics");

            Thread.sleep(1000);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}