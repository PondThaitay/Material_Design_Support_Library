package com.cm_smarthome.materialdesignsupportlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.LoginActivity;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;

import login.Login_activity;
import menu.Sqlite;

/**
 * Created by Admin on 04-06-2015.
 */
public class Seventh_Fragment extends Fragment {

    Sqlite sqlite;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.seventh_fragment, container, false);

        sqlite = new Sqlite(getActivity());
        sqlite.UpdateDataFlagLogin("1", "0");

        String[] arrData = sqlite.SelectData("1");

        if (arrData[0].length() > 0) {
            Log.e("Flag Logout", arrData[1] + "+" + arrData[3]);
        }

        Intent intent = new Intent(getActivity(), Login_activity.class);
        intent.putExtra("State", "out");
        startActivity(intent);

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sqlite.UpdateDataFlagLogin("1", "1");
        String[] arrData = sqlite.SelectData("1");
        if (arrData[0].length() > 0) {
            Log.e("Flag Logout1", arrData[1] + "+" + arrData[2]);
        }
    }
}
