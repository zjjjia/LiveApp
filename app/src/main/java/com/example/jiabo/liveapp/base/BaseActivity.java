package com.example.jiabo.liveapp.base;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiabo
 * Data : 2019/4/5
 * Description :
 * Modify by
 */

public class BaseActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_CODE = 1111;
    private List<String> permissionList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        initPermissionList();
        getPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                         @NonNull int[] grantResult){
        if(requestCode == REQUEST_PERMISSION_CODE && grantResult.length > 0){
            if(grantResult[0] != PackageManager.PERMISSION_GRANTED){
                this.finish();
            }
        }
    }

    private void getPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            for(String permission : permissionList) {
                if (ContextCompat.checkSelfPermission(BaseActivity.this, permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(BaseActivity.this, new String[]{permission},
                            REQUEST_PERMISSION_CODE);
                }
            }
        }
    }

    private void initPermissionList(){
        permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }


}
