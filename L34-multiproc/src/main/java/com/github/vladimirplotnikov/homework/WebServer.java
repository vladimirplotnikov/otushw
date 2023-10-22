package com.github.vladimirplotnikov.homework;

import java.io.*;
import java.net.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

public class WebServer {
    public static void main(String[] args) throws IOException {
        String path = "L34-multiproc/src/main/resources";
        File resorceDirectory = new File(path);
        String absoluteResorucePath = resorceDirectory.getAbsolutePath()+File.separator;

        System.out.println(absoluteResorucePath);
        // Чтение файла конфигурации
        Properties config = new Properties();
        try (InputStream input = new FileInputStream(absoluteResorucePath+"config.properties")) {
            config.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int port = Integer.parseInt(config.getProperty("port")); // Получение порта из файла конфигурации

        // Чтение маппинга из файла конфигурации
        Map<String, String> mapping = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(absoluteResorucePath+File.separator+"mapping.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    mapping.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        ServerSocket serverSocket = new ServerSocket(port); // Указываем порт для прослушивания

        while (true) {
            Socket clientSocket = serverSocket.accept(); // Принимаем входящее соединение от клиента

            // Создаем новый поток для обработки запроса
            Thread thread = new Thread(() -> {
                try {
                    // Получаем входной и выходной потоки для чтения запроса и отправки ответа
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                    // Читаем запрос от клиента
                    String request = in.readLine();

                    // Анализируем URI запроса и проверяем его в маппинге
                    // Отправляем соответствующий файл или статусный код в ответ
                    if (request.startsWith("GET")) {
                        String uri = request.split(" ")[1];
                        if (mapping.containsKey(uri)) {
                            String filePath = mapping.get(uri);
                            File file = new File(absoluteResorucePath+filePath);
                            if (file.exists()) {
                                out.println("HTTP/1.1 200 OK");
                                out.println("Content-Type: text/html");
                                out.println();
                                try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                                    String line;
                                    while ((line = fileReader.readLine()) != null) {
                                        out.println(line);
                                    }
                                }
                            } else {
                                out.println("HTTP/1.1 404 Not Found");
                                out.println();
                            }
                        } else {
                            out.println("HTTP/1.1 404 Not Found");
                            out.println();
                        }
                    } else {
                        out.println("HTTP/1.1 500 Internal Server Error");
                        out.println();
                    }
                    //out.println(Thread.currentThread().getName().toString());
                    // Закрываем соединение с клиентом
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // Запускаем поток для обработки запроса
            thread.start();
        }
    }
}
