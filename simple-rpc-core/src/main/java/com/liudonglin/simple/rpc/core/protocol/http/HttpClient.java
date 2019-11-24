package com.liudonglin.simple.rpc.core.protocol.http;

import com.liudonglin.simple.rpc.core.common.RpcInvocation;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpClient {

    public Object send(String hostname, Integer port, RpcInvocation invocation) {
        try {
            URL url = new URL("http", hostname, port, "/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);

            oos.writeObject(invocation);
            oos.flush();
            oos.close();

            InputStream inputStream = connection.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            Object result = ois.readObject();
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}
