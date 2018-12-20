package com.pghome;

import com.pghome.netty.WSServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Auther: tianws
 * @Date: 2018/12/19 15:38
 * @Description: netty启动
 */

@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {
    //ApplicationListener应用监听器



    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null){
            try {
                WSServer.getInstance().start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
