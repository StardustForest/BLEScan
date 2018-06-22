package com.example.a64148.blescan;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

public class BlueUtils {
    //蓝牙适配器
    private BluetoothAdapter mBluetoothAdapter;
    //搜索状态的标示
    private boolean mScanning = true;
    //蓝牙适配器List
    private List<BluetoothDevice> mBlueList;
    //上下文
    private Context context;
    //单例模式
    private static BlueUtils blueUtils;
    //蓝牙的回调地址
    private BluetoothAdapter.LeScanCallback mLesanCall;
    //扫描执行回调
    private BlueUtils.Callbacks callback;


    public static BlueUtils getBlueUtils(){
        if(blueUtils == null){
            blueUtils = new BlueUtils();
        }
        return blueUtils;
    }

/*
            * 初始化蓝牙的一些信息
*/
    /***
            * 初始化蓝牙的一些信息
 */
    public void getInitialization(Context context){
        this.context = context;
        //初始化蓝牙适配器
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        //初始化蓝牙
        mBluetoothAdapter = bluetoothManager.getAdapter();
        //初始化List
        mBlueList = new ArrayList<>();
        //实例化蓝牙回调
        //实例化蓝牙回调
        mLesanCall = new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bytes) {
                //返回三个对象 分类别是 蓝牙对象 蓝牙信号强度 以及蓝牙的广播包
                if(!mBlueList.contains(bluetoothDevice)){//重复的则不添加
                    mBlueList.add(bluetoothDevice);
                    //接口回调
                    callback.CallbackList(mBlueList);
                }
            }
        };
    }

    /**
     * 开启蓝牙
     */
    public void startBlue(){
        if(mScanning){
            mScanning = false;
            //开始扫描并设置回调
            mBluetoothAdapter.startLeScan(mLesanCall);
        }
    }

    /**
     * 停止蓝牙扫描
     */
    public void stopBlue(){
        if(!mScanning){
            //结束蓝牙扫描
            mBluetoothAdapter.stopLeScan(mLesanCall);
        }
    }

    /**
     * 接口回调
     */
    public interface Callbacks{
        void CallbackList(List<BluetoothDevice> mBlueLis);
    }

    /**
     * 设置接口回调
     * @param callback 自身
     */
    public void setCallback(Callbacks callback) {
        this.callback = callback;
    }

    private BluetoothDevice bluetoothDevice;

    /**
     * 判断是否支持蓝牙
     * @return
     */
    public boolean isSupportBlue(){
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 返回蓝牙对象
     * @return
     */
    public BluetoothAdapter getmBluetoothAdapter() {
        return mBluetoothAdapter;
    }

}
