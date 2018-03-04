package com.happybean;

import java.io.IOException;
import java.net.*;

/**
 * @author wgt
 * @date 2018-03-04
 * @description
 **/
public class SocketUdpDemo {
    public static void main(String[] args) {

        try {
            //创建服务端DatagramSocket，绑定8888端口
            DatagramSocket datagramSocket = new DatagramSocket(8888);
            //创建数据报
            byte[] data = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
            //接收客户端发来的数据，会阻塞
            System.out.println("等待连接...");
            datagramSocket.receive(datagramPacket);
            //读取数据
            String message = new String(data, 0, datagramPacket.getLength());
            System.out.println("客户端消息：" + message);

            //定义客户端地址
            InetAddress inetAddress = datagramPacket.getAddress();
            int port = datagramPacket.getPort();
            byte[] data1 = "我是服务端消息".getBytes();
            //创建数据报
            DatagramPacket datagramPacket1 = new DatagramPacket(data1, data1.length, inetAddress, port);
            //响应客户端
            datagramSocket.send(datagramPacket1);
            //关闭资源
            datagramSocket.close();

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class SocketUdpClient {
    public static void main(String[] args) {
        try {
            //定义服务器地址，端口，数据
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            int port = 8888;
            byte[] data = "我是客户端消息".getBytes();
            //创建数据报。
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length, inetAddress, port);

            DatagramSocket datagramSocket = new DatagramSocket();
            //向服务端发送数据
            datagramSocket.send(datagramPacket);

            //创建数据报，接收服务端响应信息
            byte[] data1 = new byte[1024];
            DatagramPacket datagramPacket1 = new DatagramPacket(data1, 0, data1.length);
            //接收服务器响应
            datagramSocket.receive(datagramPacket1);
            //读取数据
            String message = new String(data1,0,datagramPacket1.getLength());
            System.out.println("服务端信息："+message);
            //关闭资源
            datagramSocket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}