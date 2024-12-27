package com.wuzeyu.gateway.session;

import java.nio.channels.Channel;
import java.util.concurrent.Callable;

public class SessionServer implements Callable<Channel> {


    @Override
    public Channel call() throws Exception {
        Thread.sleep(200);
        return null;
    }
}
