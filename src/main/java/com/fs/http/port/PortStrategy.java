package com.fs.http.port;

import cn.hutool.core.net.NetUtil;

/**
 *
 * @author 房帅
 */
public abstract class PortStrategy {

    protected int[] ports;

    protected volatile int portUseIndex;

    /**
     * 获取一个可用的端口
     * @return
     */
    public synchronized int getPort() {
        return getPort(0);
    }

    private int getPort(int findCount) {
        if (findCount == this.ports.length) {
            throw new RuntimeException("没有可用端口，请等一会儿吧");
        }
        if (portUseIndex >= this.ports.length) {
            portUseIndex = 0;
        }
        int port = this.ports[portUseIndex++];
        if (NetUtil.isUsableLocalPort(port)) {
            return port;
        } else {
            return getPort(findCount + 1);
        }
    }

}
