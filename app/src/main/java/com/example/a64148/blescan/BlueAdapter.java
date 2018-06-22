package com.example.a64148.blescan;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.a64148.blescan.R;

import java.util.List;


public class BlueAdapter extends BaseAdapter{
    private List<BluetoothDevice> mBluelist;
    private LayoutInflater layoutInflater;
    public BlueAdapter(List<BluetoothDevice> list, Context context) {
        mBluelist = list;
        layoutInflater = LayoutInflater.from(context);//context :要使用当前的Adapter的界面对象 layoutInflater: 布局装载器对象
    }

    @Override
    public int getCount() {
        return mBluelist.size();
    }

    @Override
    public Object getItem(int i) {
        return mBluelist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.list_item,null);
            viewHolder.deviceName = (TextView) view.findViewById(R.id.device_name);
            viewHolder.deviceAddress = (TextView) view.findViewById(R.id.device_address);
            viewHolder.deviceRssi = (TextView) view.findViewById(R.id.device_rssi);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        BluetoothDevice blueDevice = mBluelist.get(i);
        final String deviceName = blueDevice.getName();
        if (deviceName != null && deviceName.length() > 0) {
            viewHolder.deviceName.setText(blueDevice.getName());
        } else {
            viewHolder.deviceName.setText("未知设备");
        }
        viewHolder.deviceAddress.setText("MAC地址："+blueDevice.getAddress());
        viewHolder.deviceRssi.setText("MAC地址："+blueDevice.EXTRA_RSSI.toString());
        return view;

    }
    class ViewHolder {

        TextView deviceName;
        TextView deviceAddress;
        TextView deviceRssi;
    }

}