package com.odinese.cerpdashboard.service;

import com.odinese.cerpdashboard.util.JsonConverterUtil;
import org.json.JSONObject;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CerpDashboardService {

    private static final Logger logger = LoggerFactory.getLogger(CerpDashboardService.class);

    private String lastHealthStatusHash = null;
    private String token = null;
    private long tokenExpiry = 0;

    private final HttpClient client = createInsecureHttpClient();

    public String getMinimalHealthStatus(String username, String password, String url) {
        try {
            refreshTokenIfNecessary(username, password, url);
            return JsonConverterUtil.convertJson(fetchHealthStatus(url, token)).toString();
        } catch (Exception e) {
            logger.error("Error fetching minimal health status", e);
            return "Error fetching minimal health status: " + e.getMessage();
        }
    }

    public String getHealthDiff(String username, String password, String url) {
        try {
            refreshTokenIfNecessary(username, password, url);

            String currentHealthStatus = JsonConverterUtil.convertJson(fetchHealthStatus(url, token)).toString();
            String currentHealthStatusHash = hashUsingSHA1(currentHealthStatus);

            if (!currentHealthStatusHash.equals(lastHealthStatusHash)) {
                lastHealthStatusHash = currentHealthStatusHash;
                return currentHealthStatus;
            }
            return "";
        } catch (Exception e) {
            logger.error("Error fetching health status difference", e);
            return "Error fetching health status difference: " + e.getMessage();
        }
    }

    private void refreshTokenIfNecessary(String username, String password, String url) throws Exception {
        if (token == null || System.currentTimeMillis() >= tokenExpiry) {
            token = authenticate(username, password, url);
            tokenExpiry = System.currentTimeMillis() + (60 * 60 * 1000);
        }
    }

    private HttpClient createInsecureHttpClient() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() { return null; }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            return HttpClient.newBuilder()
                    .sslContext(sslContext)
                    .build();

        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException("Failed to create insecure HTTP client", e);
        }
    }

    private String fetchHealthStatus(String url, String token) throws Exception {
        HttpRequest healthRequest = buildHealthRequest(url, token);
        HttpResponse<String> healthResponse = client.send(healthRequest, HttpResponse.BodyHandlers.ofString());
        return healthResponse.body();
    }

    private String authenticate(String username, String password, String url) throws Exception {
        String authPayload = createAuthPayload(username, password);
        HttpRequest authRequest = buildAuthRequest(authPayload, url);
        HttpResponse<String> authResponse = client.send(authRequest, HttpResponse.BodyHandlers.ofString());
        return parseToken(authResponse.body());
    }

    private String createAuthPayload(String username, String password) {
        return new JSONObject()
                .put("username", username)
                .put("password", password)
                .toString();
    }

    private HttpRequest buildAuthRequest(String authPayload, String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url + "/api/auth"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/vnd.ceph.api.v1.0+json")
                .POST(HttpRequest.BodyPublishers.ofString(authPayload))
                .build();
    }

    private String parseToken(String responseBody) {
        JSONObject authJson = new JSONObject(responseBody);
        return authJson.getString("token");
    }

    private HttpRequest buildHealthRequest(String url, String token) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url + "/api/health/minimal"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/vnd.ceph.api.v1.0+json")
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();
    }

    private String hashUsingSHA1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error creating hash", e);
        }
    }
}
