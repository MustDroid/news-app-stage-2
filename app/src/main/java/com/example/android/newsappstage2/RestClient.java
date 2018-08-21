package com.example.android.newsappstage2;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient
{
    public static String doGETRequest(String url)
    {
        try
        {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                return copyStreamContent(connection.getInputStream());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return "";
    }

    public static String doDELETERequest(String url)
    {
        try
        {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                return copyStreamContent(connection.getInputStream());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return "";
    }

    public static String doPUTRequest(String url, String body)
    {
        try
        {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setFixedLengthStreamingMode(body.length());
            connection.getOutputStream().write(body.getBytes());
            connection.getOutputStream().flush();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                return copyStreamContent(connection.getInputStream());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return "";
    }

    public static String doPOSTRequest(String url, String body)
    {
        try
        {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setFixedLengthStreamingMode(body.length());
            connection.getOutputStream().write(body.getBytes());
            connection.getOutputStream().flush();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                return copyStreamContent(connection.getInputStream());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return "";
    }

    private static String copyStreamContent(InputStream in)
    {
        try
        {
            final int bufferSize = 4096;
            final char[] buffer = new char[bufferSize];
            final StringBuilder sb = new StringBuilder();
            Reader reader = new InputStreamReader(in, "UTF-8");
            while(true)
            {
                int nrCharsRead = reader.read(buffer, 0, buffer.length); // 4100 bytes => 4096 bytes => 4 bytes => 0 bytes
                if(nrCharsRead <= 0)
                    break;

                sb.append(buffer, 0, nrCharsRead);
            }
            return sb.toString();

        }
        catch (Exception ex)
        {
            return "";
        }
    }

}
