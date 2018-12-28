package com.pghome.netty;

import io.netty.channel.Channel;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: tianws
 * @Date: 2018/12/27 11:18
 * @Description:
 */
public class DriverChannelRel {

    private static ConcurrentHashMap<String, Channel> manager = new ConcurrentHashMap<>();

    public static void put(String senderId, Channel channel) {
        manager.put(senderId, channel);
    }

    public static Channel get(String senderId) {
        return manager.get(senderId);
    }

    public static Set<Channel> getAll(){
        Set set = new HashSet();
        Collection<Channel> values = manager.values();
        Iterator<Channel> iterator = values.iterator();
        while (iterator.hasNext()){
            set.add(iterator.next());
        }
        return set;
    }

    public static void output() {
        for (HashMap.Entry<String, Channel> entry : manager.entrySet()) {
            System.out.println("UserId: " + entry.getKey()
                    + ", ChannelId: " + entry.getValue().id().asLongText());
        }
    }

}
