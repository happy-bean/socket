package com.happybean;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wgt
 * @date 2018-03-03
 * @description
 **/
public class SocketTcpDemo {

    //客户端
    public static void main(String[] args) {
        try {
            //创建ServerSocket 绑定8888端口
            ServerSocket serverSocket = new ServerSocket(8888);

            //监听8888端口，等待客户端连接
            System.out.println("等待连接...");
            Socket socket = serverSocket.accept();

            //获取输入了，读取客户端信息
            InputStream inputStream = socket.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String data = bufferedReader.readLine();
            while (data != null) {
                System.out.println("客户端消息:" + data);
                data = bufferedReader.readLine();
            }
            socket.shutdownInput();

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("我是服务端消息");
            printWriter.flush();


            //关闭资源
            bufferedReader.close();
            streamReader.close();

            printWriter.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class SocketTcpClient {
    public static void main(String[] args) {
        try {
            //建立socket客户端，指定地址端口
            Socket socket = new Socket("127.0.0.1", 8888);

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("我是客户端消息");
            printWriter.flush();
            socket.shutdownOutput();

            InputStream inputStream = socket.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String data = bufferedReader.readLine();
            while (data != null) {
                System.out.println("服务端端消息:" + data);
                data = bufferedReader.readLine();
            }

            //关闭资源
            printWriter.close();
            outputStream.close();
            bufferedReader.close();
            streamReader.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
 class ServerTcpThread extends Thread {

    Socket socket = null;

    public ServerTcpThread(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) {
        //创建ServerSocket 绑定8888端口
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8888);
            //监听8888端口，等待客户端连接
            System.out.println("等待连接...");
            Socket socket = null;

            //循环监听
            while (true) {
                socket = serverSocket.accept();
                //创建一个新的线程
                ServerTcpThread serverThread = new ServerTcpThread(socket);
                serverThread.start();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run() {
        try {

            //获取输入了，读取客户端信息
            InputStream inputStream = socket.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String data = bufferedReader.readLine();
            while (data != null) {
                System.out.println("客户端消息:" + data);
                data = bufferedReader.readLine();
            }
            socket.shutdownInput();

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("我是服务端消息");
            printWriter.flush();


            //关闭资源
            bufferedReader.close();
            streamReader.close();

            printWriter.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}