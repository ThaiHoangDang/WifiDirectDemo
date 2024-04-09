package com.rmit.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.net.wifi.p2p.WifiP2pDevice;
import java.util.List;

public class DeviceListAdapter extends ArrayAdapter<WifiP2pDevice> {

    private LayoutInflater inflater;
    private List<WifiP2pDevice> devices;

    public DeviceListAdapter(Context context, List<WifiP2pDevice> devices) {
        super(context, R.layout.device_list_item, devices); // Changed layout resource ID
        this.devices = devices;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.device_list_item, parent, false); // Changed layout resource ID
        }

        WifiP2pDevice device = devices.get(position);
        if (device != null) {
            TextView deviceName = view.findViewById(R.id.deviceName);
            TextView deviceStatus = view.findViewById(R.id.deviceStatus);
            if (deviceName != null) {
                deviceName.setText(device.deviceName);
            }
            if (deviceStatus != null) {
                deviceStatus.setText(getDeviceStatus(device.status));
            }
        }

        return view;
    }

    private String getDeviceStatus(int status) {
        switch (status) {
            case WifiP2pDevice.AVAILABLE:
                return "Available";
            case WifiP2pDevice.INVITED:
                return "Invited";
            case WifiP2pDevice.CONNECTED:
                return "Connected";
            case WifiP2pDevice.FAILED:
                return "Failed";
            case WifiP2pDevice.UNAVAILABLE:
                return "Unavailable";
            default:
                return "Unknown";
        }
    }
}
