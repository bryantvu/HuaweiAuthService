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

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.huawei.agconnect.auth.AGConnectAuth;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.anonymous).setOnClickListener(view -> {
            startActivity(new Intent(this, AnonymousActivity.class));
        });
        findViewById(R.id.hwid).setOnClickListener(view -> {
            startActivity(new Intent(this, HWIDActivity.class));
        });
        findViewById(R.id.hwgame).setOnClickListener(view -> {
            startActivity(new Intent(this, HWGameActivity.class));
        });
        findViewById(R.id.weixin).setOnClickListener(view -> {
            startActivity(new Intent(this, WeixinActivity.class));
        });
        findViewById(R.id.qq).setOnClickListener(view -> {
            startActivity(new Intent(this, QQActivity.class));
        });
        findViewById(R.id.weibo).setOnClickListener(view -> {
            startActivity(new Intent(this, WeiboActivity.class));
        });
        findViewById(R.id.selfbuild).setOnClickListener(view -> {
            startActivity(new Intent(this, SelfBuildActivity.class));
        });
        findViewById(R.id.delete).setOnClickListener(view -> {
            AGConnectAuth.getInstance().deleteUser();
        });
        findViewById(R.id.email).setOnClickListener(view -> {
            startActivity(new Intent(this, EmailAuthActivity.class));
        });
        findViewById(R.id.google).setOnClickListener(view -> {
            startActivity(new Intent(this, GoogleActivity.class));
        });
        findViewById(R.id.play_game).setOnClickListener(view -> {
            startActivity(new Intent(this, PlayGameActivity.class));
        });
        findViewById(R.id.facebook).setOnClickListener(view -> {
            startActivity(new Intent(this, FacebookActivity.class));
        });
        findViewById(R.id.twitter).setOnClickListener(view -> {
            startActivity(new Intent(this, TwitterActivity.class));
        });
        findViewById(R.id.phone).setOnClickListener(view -> {
            startActivity(new Intent(this, PhoneActivity.class));
        });
    }
}
