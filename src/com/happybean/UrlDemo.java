package com.happybean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author wgt
 * @date 2018-03-03
 * @description
 **/
public class UrlDemo {

    //Url统一资源定位符，表示某一资源的地址
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.baidu.com");
            //表示https://www.baidu.com下的资源
            URL urlDes = new URL(url, "/s?wd=url&rsv_spt=1&rsv_iqid=0xe090ee90000117ad&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_enter=1&oq=uel&rsv_t=4f466EFzWopqoQcGpMpUDFpwa6E4wmwpEOr15rAsXVy%2BZjVbFEVNmSDZMVBdE6odc%2BYn&rsv_pq=db30b1160000aaeb&inputT=3944238&rsv_sug3=52&rsv_sug1=50&rsv_sug7=100&rsv_slog=ipt_change&bs=uel");

            System.out.println("协议：" + urlDes.getProtocol());
            System.out.println("主机：" + urlDes.getHost());
            //如果未指定访问端口，则使用默认端口，getPort()返回值是-1
            System.out.println("端口：" + urlDes.getPort());
            System.out.println("文件路径：" + urlDes.getPath());
            System.out.println("文件名：" + urlDes.getFile());
            System.out.println("文件相对路径：" + urlDes.getRef());
            System.out.println("查询字符串：" + urlDes.getQuery());

            //使用url读取网络资源
            InputStream urlStream = url.openStream();
            InputStreamReader streamReader = new InputStreamReader(urlStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String data = bufferedReader.readLine();
            while (data != null) {
                System.out.println(data);
                data = bufferedReader.readLine();
            }
            bufferedReader.close();
            streamReader.close();
            urlStream.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
