package com.fs.execute;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.XmlUtil;
import com.fs.http.PoolingHttpClientConnectionManager;
import com.fs.http.port.PortStrategy;
import com.fs.http.port.RangePortStrangy;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author 房帅
 */
public class QueryTest {

    public static void main(String[] args) throws IOException {
        Registry<ConnectionSocketFactory> sfr = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        PortStrategy portStrategy = new RangePortStrangy(3333, 4000);
        PoolingHttpClientConnectionManager mgr = new PoolingHttpClientConnectionManager(sfr, portStrategy);
        mgr.setDefaultMaxPerRoute(100);
        mgr.setMaxTotal(200);
        mgr.setValidateAfterInactivity(1000);
        HttpClient client = HttpClientBuilder.create().setConnectionManager(mgr)
                .build();
        Executor executor = Executor.newInstance(client);


        // 实际请求方法
        HttpResponse response = executor.execute(Request
                .Get("https://www.baidu.com")
        ).returnResponse();
        String responseText = EntityUtils.toString(response.getEntity());
        System.out.println(responseText);
    }


}
