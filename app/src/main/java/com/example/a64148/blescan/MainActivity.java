package com.example.a64148.blescan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.bluetooth.BluetoothDevice;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a64148.blescan.BlueAdapter;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //搜索BUTTON
    private Button scan,end;
    //搜索结果List
    private ListView resultList;
    //蓝牙工具类
    private BlueUtils blueUtils;
    //蓝牙的Adapter
    private BlueAdapter blueAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        blueUtils = new BlueUtils();
        //初始化工具类
        blueUtils.getInitialization(this);
        //判断是否支持蓝牙
        if (!blueUtils.isSupportBlue()) {
            Toast.makeText(this, "设备支持蓝牙4.0", Toast.LENGTH_SHORT).show();
            //BlueUtils.getmBluetoothAdapter().enable();
        }else {
            Toast.makeText(this, "设备不支持蓝牙4.0", Toast.LENGTH_SHORT).show();
            //静默开启蓝牙
        }
        inint();
    }

    /**
     * 初始化数据
     */
    private void inint() {
        scan = findViewById(R.id.scan);
        end = findViewById(R.id.end);
        resultList = findViewById(R.id.result);
        scan.setOnClickListener(this);
        end.setOnClickListener(this);
        blueUtils.setCallback(new BlueUtils.Callbacks() {
            @Override
            public void CallbackList(List<BluetoothDevice> mBlueLis) {
                //我们在此处设置Adapter
                if(blueAdapter == null){
                    blueAdapter = new BlueAdapter(mBlueLis,MainActivity.this);
                    resultList.setAdapter(blueAdapter);
                }else {
                    blueAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.scan:
                blueUtils.startBlue();
                Toast.makeText(this, "开启成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.end:
                blueUtils.stopBlue();
                Toast.makeText(this, "关闭成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}