package sg.edu.nus.iss.ft08.phoenix.http;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class RestClient {

  public String get(String uri) throws JSONException {
    HttpURLConnection connection = null;
    DataOutputStream dos = null;
    String responseData = "";

    try {
      URL url = new URL(uri.toString());

      connection = (HttpURLConnection) url.openConnection();
      connection.setInstanceFollowRedirects(false);
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Content-Type", "application/json; charset=utf8");

      int responseCode = connection.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {
        InputStream in = connection.getInputStream();
        Scanner scanner = new Scanner(in);
        scanner.useDelimiter("\\A");
        if (scanner.hasNext()) {
          responseData = scanner.next();
        }
      }
    } catch (MalformedURLException exception) {
      // log error
    } catch (IOException exception) {
      // log error
    } finally {
      if (dos != null) {
        try {
          dos.flush();
          dos.close();
        } catch (IOException exception) {
          // log error
        }
      }
      if (connection != null) connection.disconnect();
    }

    return responseData;
  }

  public String post(String uri, JSONObject json) throws JSONException {

    HttpURLConnection connection = null;
    DataOutputStream dos = null;
    String responseData = "";

    try {
      URL url = new URL(uri.toString());

      connection = (HttpURLConnection) url.openConnection();
      connection.setDoInput(true);
      connection.setInstanceFollowRedirects(false);
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/json; charset=utf8");
      connection.setDoOutput(true);
      dos = new DataOutputStream(connection.getOutputStream());
      dos.writeUTF(json.toString());
      dos.write(512);

      int responseCode = connection.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {
        InputStream in = connection.getInputStream();
        Scanner scanner = new Scanner(in);
        scanner.useDelimiter("\\A");
        if (scanner.hasNext()) {
          responseData = scanner.next();
        }
      }
    } catch (MalformedURLException exception) {
      // log error
    } catch (IOException exception) {
      // log error
    } finally {
      if (dos != null) {
        try {
          dos.flush();
          dos.close();
        } catch (IOException exception) {
          // log error
        }
      }
      if (connection != null) connection.disconnect();
    }

    return responseData;
  }

  public String put(String uri, JSONObject json) throws JSONException {
    HttpURLConnection connection = null;
    DataOutputStream dos = null;
    String responseData = "";

    try {
      URL url = new URL(uri.toString());

      connection = (HttpURLConnection) url.openConnection();
      connection.setDoInput(true);
      connection.setInstanceFollowRedirects(false);
      connection.setRequestMethod("PUT");
      connection.setRequestProperty("Content-Type", "application/json; charset=utf8");
      connection.setDoOutput(true);
      dos = new DataOutputStream(connection.getOutputStream());
      dos.writeUTF(json.toString());
      dos.write(512);

      int responseCode = connection.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {
        InputStream in = connection.getInputStream();
        Scanner scanner = new Scanner(in);
        scanner.useDelimiter("\\A");
        if (scanner.hasNext()) {
          responseData = scanner.next();
        }
      }
    } catch (MalformedURLException exception) {
      // log error
    } catch (IOException exception) {
      // log error
    } finally {
      if (dos != null) {
        try {
          dos.flush();
          dos.close();
        } catch (IOException exception) {
          // log error
        }
      }
      if (connection != null) connection.disconnect();
    }

    return responseData;
  }

  public String delete(String uri) throws JSONException {
    HttpURLConnection connection = null;
    DataOutputStream dos = null;
    String responseData = "";

    try {
      URL url = new URL(uri.toString());

      connection = (HttpURLConnection) url.openConnection();
      connection.setInstanceFollowRedirects(false);
      connection.setRequestMethod("DELETE");
      connection.setRequestProperty("Content-Type", "application/json; charset=utf8");

      int responseCode = connection.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) {
        responseData = ""; //delete successful
      }
    } catch (MalformedURLException exception) {
      // log error
    } catch (IOException exception) {
      // log error
    } finally {
      if (dos != null) {
        try {
          dos.flush();
          dos.close();
        } catch (IOException exception) {
          // log error
        }
      }
      if (connection != null) connection.disconnect();
    }

    return responseData;
  }

}
