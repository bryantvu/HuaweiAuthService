/*
 * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bvutest.agc.quickstart.auth;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.huawei.agconnect.auth.AGConnectAuth;
import com.huawei.agconnect.auth.AGConnectAuthCredential;
import com.huawei.agconnect.auth.AGConnectUser;
import com.huawei.agconnect.auth.PhoneAuthProvider;
import com.huawei.agconnect.auth.PhoneUser;
import com.huawei.agconnect.auth.SignInResult;
import com.huawei.agconnect.auth.VerifyCodeResult;
import com.huawei.agconnect.auth.VerifyCodeSettings;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskExecutors;

public class PhoneActivity extends AppCompatActivity {

    private static final String TAG = "PhoneActivity";
//    private static final String countryCode = "enter country code";
//    private static final String phoneNumber = "enter phone number";
    private static final String countryCode = "1";
    private static final String phoneNumber = "4088065178";
    private static final String password = "asdfasdf123";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        findViewById(R.id.btn_create_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    EditText code = findViewById(R.id.verification_code);
                    String codeString = code.getText().toString();
                    Log.d(TAG, "verification code >> [" + codeString+"]");

                    PhoneUser phoneUser = new PhoneUser.Builder()
                            .setCountryCode(countryCode)
                            .setPhoneNumber(phoneNumber)
                            .setVerifyCode(codeString)
                            .setPassword(password)
                            .build();

                    AGConnectAuth.getInstance().createUser(phoneUser)
                            .addOnSuccessListener(new OnSuccessListener<SignInResult>() {
                                @Override
                                public void onSuccess(SignInResult signInResult) {
                                    Toast.makeText(getApplicationContext(), "Signin success"  + signInResult.toString(), Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "Signin success >> " + signInResult.toString());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {
                                    Toast.makeText(getApplicationContext(), "error creating user >> " + e.toString(), Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "error creating user >> " + e.toString());
                                }
                            });
                }catch(Exception e){
                    Log.d(TAG, "link error >> "+e.toString());
                }


            }
        });

        findViewById(R.id.btn_code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVerificationCode();
            }
        });
    }

    public void getVerificationCode() {

        VerifyCodeSettings settings = VerifyCodeSettings.newBuilder()
                .action(VerifyCodeSettings.ACTION_REGISTER_LOGIN)
                .build();
        Task<VerifyCodeResult> task = PhoneAuthProvider.requestVerifyCode(countryCode, phoneNumber, settings);
        task.addOnSuccessListener(TaskExecutors.uiThread(), new OnSuccessListener<VerifyCodeResult>() {
            @Override
            public void onSuccess(VerifyCodeResult verifyCodeResult) {
                Toast.makeText(getApplicationContext(), "verification code sent", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "VerifyCodeResult >> " + verifyCodeResult.toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(getApplicationContext(), "verification code failed to send", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "VerifyCodeResult >> failure >> " + e);
            }
        });
    }

    public void userSignin(View view){
        AGConnectAuthCredential credential = PhoneAuthProvider.credentialWithPassword(countryCode, phoneNumber,password);
        AGConnectAuth.getInstance().signIn(credential)
                .addOnSuccessListener(new OnSuccessListener<SignInResult>() {
                    @Override
                    public void onSuccess(SignInResult signInResult) {
                        Toast.makeText(getApplicationContext(), "signin success >> " + signInResult, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "signin success >> " + signInResult);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(getApplicationContext(), "signin failed", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "signin failed >> "+ e);
                    }
                });

    }

    public void userSignout(View view){
        try{
            AGConnectAuth instance = AGConnectAuth.getInstance();
            if(instance!=null){
                Toast.makeText(getApplicationContext(), "signout success", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "signout success");
                instance.signOut();
            }else{
                Toast.makeText(getApplicationContext(), "user not signed in", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "user not signed in");
            }
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "signout failed", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "signout failed >> "+ e);
        }

    }

}
