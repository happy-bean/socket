package com.happybean;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @author wgt
 * @date 2018-03-03
 * @description
 **/
public class InetAddressDemo {

    public static void main(String[] args) {
        try {
            //获取本机inetAddress实例
            InetAddress inetAddress = InetAddress.getLocalHost();

            System.out.println("计算机名："+inetAddress.getHostName());
            System.out.println("IP地址："+inetAddress.getHostAddress());

            //获取字节形式的ip地址
            byte[] addressBytes = inetAddress.getAddress();
            System.out.println("字节形式的IP地址："+ Arrays.toString(addressBytes));
            System.out.println("直接输出："+addressBytes.toString());

            //根据计算机名获取inetAddress实例
            InetAddress inetAddress1 = InetAddress.getByName(inetAddress.getHostName());
            System.out.println("计算机名："+inetAddress1.getHostName());
            System.out.println("IP地址："+inetAddress1.getHostAddress());

            //根据IP地址获取inetAddress实例
            InetAddress inetAddress2 = InetAddress.getByAddress(inetAddress.getHostAddress(),addressBytes);
            System.out.println("计算机名："+inetAddress2.getHostName());
            System.out.println("IP地址："+inetAddress2.getHostAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
