package com.fs.http.port;

/**
 * 指定端口的策略
 * @author 房帅
 */
public class SpecifiedPortStrategy extends PortStrategy {

    public SpecifiedPortStrategy(int[] ports) {
        this.ports = ports;
        portUseIndex = 0;
    }
}
