package com.publicobject.http.proxy;

import com.squareup.okhttp.OkHttpClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class OkHttpExample {
  final OkHttpClient okHttpClient;

  public OkHttpExample(Proxy proxy) {
    okHttpClient = new OkHttpClient();
    okHttpClient.setProxy(proxy);
  }

  public void get(String url) throws IOException {
    HttpURLConnection connection = okHttpClient.open(new URL(url));
    printResponse(connection.getInputStream());
  }

  private void printResponse(InputStream in) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
    for (String line; (line = reader.readLine()) != null; ) {
      System.out.println(line);
    }
  }

  public static void main(String[] args) throws IOException {
    // Charles Web Debugging Proxy runs on port 8888.
    // Use 'localhost' to run on a desktop JVM or '10.0.2.2' to run in an emulator.
    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8888));
    String url = "http://localhost:8910/rfc2616.txt";
    new OkHttpExample(proxy).get(url);
  }
}
