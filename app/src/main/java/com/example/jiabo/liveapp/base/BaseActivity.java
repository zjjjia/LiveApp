package com.example.jiabo.liveapp.base;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.jiabo.liveapp.constant.OtherCode;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiabo
 * Data : 2019/4/5
 * Description :
 * Modify by
 */

public class BaseActivity extends AppCompatActivity {

    private List<String> permissionList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);

        /*KeyFactory keyFactory;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            try {
                keyFactory = KeyFactory.getInstance("RSA");     //适配Android P及以后版本，否则报错NoSuchAlgorithmException
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else {
            try {
                keyFactory = KeyFactory.getInstance("RSA", "BC");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            }
        }*/
        initPermissionList();
        getPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                         @NonNull int[] grantResult){
        if(requestCode == OtherCode.REQUEST_PERMISSION_RESULTCODE && grantResult.length > 0){
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
                            OtherCode.REQUEST_PERMISSION_RESULTCODE);
                }
            }
        }
    }

    private void initPermissionList(){
        permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
    }


}
