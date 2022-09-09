package com.byq.applib.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * 带回复的粘性广播
 * 用于APP和系统框架通信
 *
 * 关于发送端和接收端的解释：
 * 废弃
 */
public abstract class CommunicateBroadcast extends BroadcastReceiver {
    public static final int OWING_SIDE_CLINT = 781;
    public static final int OWING_SIDE_SERVER = 254;
    public static final String BROADCAST_TYPE_NORMAL = "normal";
    public static final String BROADCAST_TYPE_REPLY_RECEIVER = "replayReceiver";
    private static final String mouldAction = "com.byq.systemcallback/broadcast";

    private List<String> mSupplyEvents = new ArrayList<>();
    private String broadcastName; //广播名
    private boolean isReceiveAllBroadcast; //是否接收广域广播
    private int owningSide; //所属端

    public CommunicateBroadcast(String mEvent) {
        this.mSupplyEvents.add(mEvent);
    }

    public CommunicateBroadcast(List<String> mSupplyEvents) {
        this.mSupplyEvents = mSupplyEvents;
    }

    public CommunicateBroadcast() {
    }

    public void addSupplyEvent(String event) {
        mSupplyEvents.add(event);
    }

    public void removeSupplyEvent(String event) {
        mSupplyEvents.remove(event);
    }


    /**
     * 构造广播Action
     * @param broadcastName 默认event
     * @return action
     */
    private static String generateAction(String broadcastName,String broadcastType) {
        return mouldAction + "/" + broadcastName + "/" + broadcastType;
    }

    public boolean isSender() {
        return false;
    }

    public void register(Context context) {
        context.registerReceiver(this, generateIntentFilter());
    }

    private IntentFilter generateIntentFilter() {
        if (mSupplyEvents.size() == 0) throw new RuntimeException("Must has one event.");
        IntentFilter intentFilter = new IntentFilter();
        for (String mSupplyEvent : mSupplyEvents) {
            intentFilter.addAction(generateAction(mSupplyEvent,getBroadcastType()));
        }
        return intentFilter;
    }

    public String getBroadcastType() {
        return BROADCAST_TYPE_NORMAL;
    }

    public void unregister(Context context) {
        try {
            context.unregisterReceiver(this);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public abstract void onReceive(Context context, Intent intent);

    public static void sendBroadcast(Context context,String event
            ,Intent intent) {
        sendBroadcast(context,event,BROADCAST_TYPE_NORMAL,intent,null);
    }

    public static void sendBroadcast(Context context, String event
            , Intent intent, CommunicatBroadcastForReplay replay) {
        sendBroadcast(context,event,BROADCAST_TYPE_NORMAL,intent,replay);
    }

    /**
     * 发送回复
     * @param context context
     * @param intent 只需要设置数据
     */
    public void sendReplay(Context context,String event,Intent intent) {
        eventSetToIntent(event,intent);
        intent.setAction(generateAction(event,BROADCAST_TYPE_REPLY_RECEIVER));
        context.sendBroadcast(intent);
    }

    private static void eventSetToIntent(String event,Intent intent) {
        intent.putExtra("broEvent",event);
    }

    public static String parseStringByAction(Intent intent) {
        return intent.getStringExtra("broEvent");
    }

    public static String getStringByExtra(Intent intent) {
        return intent.getStringExtra("broEvent");
    }

    public static void sendBroadcast(Context context, String event
            , String broadcastType
            , Intent intent
            , CommunicatBroadcastForReplay replay) {
        eventSetToIntent(event,intent);
        if (replay != null) {
            replay.register(context);
            replay.startTimer();
        }
        intent.setAction(generateAction(event,broadcastType));
        context.sendBroadcast(intent);
    }
}
