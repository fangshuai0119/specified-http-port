package com.fs.http.port;

import java.util.stream.IntStream;

/**
 *
 * @author 房帅
 */
public class RangePortStrangy extends PortStrategy {

    private int startInclusive;

    private int endInclusive;

    public RangePortStrangy(int startInclusive, int endInclusive) {
        this.startInclusive = startInclusive;
        this.endInclusive = endInclusive;
        this.ports = IntStream.rangeClosed(startInclusive, endInclusive).toArray();
    }
}
