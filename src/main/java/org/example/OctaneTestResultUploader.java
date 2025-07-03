package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class OctaneTestResultUploader {

    // Replace these with your actual values
    private static final String OCTANE_BASE_URL = "https://your-octane-url";
    private static final String SPACE_ID = "your_space_id";
    private static final String WORKSPACE_ID = "your_workspace_id";
    private static final String CLIENT_ID = "your_client_id";
    private static final String CLIENT_SECRET = "your_client_secret";

    public static void main(String[] args) throws IOException {
        String token = authenticate();
        String xmlPayload = buildTestResultXML();
        postTestResult(token, xmlPayload);
    }

    // 🔐 Step 1: Authenticate to get a bearer token
    private static String authenticate() throws IOException {
        String auth = CLIENT_ID + ":" + CLIENT_SECRET;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        URL url = new URL(OCTANE_BASE_URL + "/authentication/sign_in");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Basic " + encodedAuth);

        int status = conn.getResponseCode();
        if (status == 200) {
            System.out.println("✅ Authentication successful");
            return conn.getHeaderField("LWSSO_COOKIE_KEY"); // or use full token
        } else {
            throw new IOException("Authentication failed. Status: " + status);
        }
    }

    // 🧾 Step 2: Build the XML payload
    private static String buildTestResultXML() {
        return "<test_result>"+
                 "<build>"+
                "<build_name>Build_2025_07_04</build_name>"+
                "</build>"+
                "<test_runs>"+
                "<test_run>"+
                "<module_name>com.mayank.tests</module_name>"+
                "<package_name>LoginTests</package_name>"+
                "<class_name>LoginTest</class_name>"+
                "<test_name>ValidLoginScenario</test_name>"+
                "<status>Passed</status>"+
                "<duration>4</duration>"+
                "</test_run>"+
                "</test_runs>"+
                "</test_result>";
    }

    // 📤 Step 3: Submit the test result
    private static void postTestResult(String token, String xmlPayload) throws IOException {
        String endpoint = OCTANE_BASE_URL + "/api/shared_spaces/" + SPACE_ID + "/workspaces/" + WORKSPACE_ID + "/test-results";

        HttpURLConnection conn = (HttpURLConnection) new URL(endpoint).openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/xml");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + token);
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(xmlPayload.getBytes(StandardCharsets.UTF_8));
        }

        int responseCode = conn.getResponseCode();
        if (responseCode == 202) {
            System.out.println("📦 Test result submitted to Octane successfully.");
        } else {
            InputStream error = conn.getErrorStream();
            String response = new BufferedReader(new InputStreamReader(error))
                    .lines().reduce("", (a, b) -> a + b);
            throw new RuntimeException("❌ Submission failed: HTTP " + responseCode + "\n" + response);
        }
    }
}