package com.szfission.wear.demo.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.ActionBar;

import com.fission.wear.sdk.v2.FissionSdkBleManage;
import com.fission.wear.sdk.v2.callback.FissionBigDataCmdResultListener;
import com.szfission.wear.demo.R;
import com.szfission.wear.sdk.AnyWear;
import com.szfission.wear.sdk.bean.param.SportsTargetPara;
import com.szfission.wear.sdk.ifs.BigDataCallBack;
import com.szfission.wear.sdk.ifs.OnSmallDataCallback;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.Objects;

public class SetTargetParaActivity extends BaseActivity {
    EditText etStep;
    EditText etCalorie;
    EditText etSportTime;
    EditText etDistance;
    Switch switchOpen;
    Switch switchOpen1;
    Switch switchOpen2;
    Switch switchOpen3;

    Button btn_send, btn_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_para);
        setTitle(R.string.FUNC_SET_TARGET_SET);
        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        etStep = findViewById(R.id.etStep);
        etCalorie = findViewById(R.id.etCalorie);
        etSportTime = findViewById(R.id.etSportTime);
        etDistance = findViewById(R.id.etDistance);
        switchOpen = findViewById(R.id.switch_open);
        switchOpen1 = findViewById(R.id.switch_open1);
        switchOpen2 = findViewById(R.id.switch_open2);
        switchOpen3 = findViewById(R.id.switch_open3);
        btn_send = findViewById(R.id.btn_send);
        btn_get = findViewById(R.id.btn_get);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                get();
            }
        });

        getData();
        showProgress();

        FissionSdkBleManage.getInstance().addCmdResultListener(new FissionBigDataCmdResultListener() {
            @Override
            public void sendSuccess(String cmdId) {

            }

            @Override
            public void sendFail(String cmdId) {

            }

            @Override
            public void onResultTimeout(String cmdId) {

            }

            @Override
            public void onResultError(String errorMsg) {
                showToast(errorMsg);
                dismissProgress();
            }

            @Override
            public void getTargetSet(SportsTargetPara sportsTargetPara) {
                super.getTargetSet(sportsTargetPara);
                dismissProgress();
                showSuccessToast(R.string.FUNC_SET_TARGET_SET);
                StringBuilder content = new StringBuilder();
                content.append("\n目标步数：").append(sportsTargetPara.getTargetStep());
                content.append("\n目标卡路里：").append(sportsTargetPara.getTargetCalorie());
                content.append("\n目标距离：").append(sportsTargetPara.getTargetDistance());
                content.append("\n目标运动时间：").append(sportsTargetPara.getTargetExTime());
                addLog(R.string.FUNC_SET_TARGET_SET, content.toString());
                etStep.setText(String.valueOf(sportsTargetPara.getTargetStep()));
                etCalorie.setText(String.valueOf(sportsTargetPara.getTargetCalorie()));
                etDistance.setText(String.valueOf(sportsTargetPara.getTargetDistance()));
                etSportTime.setText(String.valueOf(sportsTargetPara.getTargetExTime()));
                switchOpen.setChecked(sportsTargetPara.isStep());
                switchOpen1.setChecked(sportsTargetPara.isCalorie());
                switchOpen2.setChecked(sportsTargetPara.isDistance());
                switchOpen3.setChecked(sportsTargetPara.isExercise());
            }

            @Override
            public void setTargetSet() {
                super.setTargetSet();
                dismissProgress();
                showSuccessToast();
            }
        });
    }

    private void getData() {
        FissionSdkBleManage.getInstance().getTargetSet();
//        AnyWear.getTargetSet(new BigDataCallBack() {
//            @Override
//            public void OnSportTarget(SportsTargetPara sportsTargetPara) {
//                dismissProgress();
//                showSuccessToast(R.string.FUNC_SET_TARGET_SET);
//                StringBuilder content = new StringBuilder();
//                content.append("\n目标步数：").append(sportsTargetPara.getTargetStep());
//                content.append("\n目标卡路里：").append(sportsTargetPara.getTargetCalorie());
//                content.append("\n目标距离：").append(sportsTargetPara.getTargetDistance());
//                content.append("\n目标运动时间：").append(sportsTargetPara.getTargetExTime());
//                addLog(R.string.FUNC_SET_TARGET_SET, content.toString());
//                etStep.setText(String.valueOf(sportsTargetPara.getTargetStep()));
//                etCalorie.setText(String.valueOf(sportsTargetPara.getTargetCalorie()));
//                etDistance.setText(String.valueOf(sportsTargetPara.getTargetDistance()));
//                etSportTime.setText(String.valueOf(sportsTargetPara.getTargetExTime()));
//                switchOpen.setChecked(sportsTargetPara.isStep());
//                switchOpen1.setChecked(sportsTargetPara.isCalorie());
//                switchOpen2.setChecked(sportsTargetPara.isDistance());
//                switchOpen3.setChecked(sportsTargetPara.isExercise());
//            }
//
//            @Override
//            public void OnEmpty(String cmdId) {
//                dismissProgress();
//            }
//
//            @Override
//            public void OnError(String msg) {
//                showToast(msg);
//                dismissProgress();
//            }
//        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void get() {
        getData();
        showProgress();
    }

    private void send() {
        String step = etStep.getText().toString();
        String calorie = etCalorie.getText().toString();
        String distance = etDistance.getText().toString();
        String sportTime = etSportTime.getText().toString();
//        if (startTime.isEmpty()) {
//            Toast.makeText(this, "请输入开始时间", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (endTime.isEmpty()) {
//            Toast.makeText(this, "请输入结束时间", Toast.LENGTH_SHORT).show();
//            return;
//        }
        showProgress();
        SportsTargetPara sportsTargetPara = new SportsTargetPara();
        sportsTargetPara.setStep(switchOpen.isChecked());
        sportsTargetPara.setCalorie(switchOpen1.isChecked());
        sportsTargetPara.setDistance(switchOpen2.isChecked());
        sportsTargetPara.setExercise(switchOpen3.isChecked());
        sportsTargetPara.setTargetStep(Integer.parseInt(step));
        sportsTargetPara.setTargetCalorie(Integer.parseInt(calorie));
        sportsTargetPara.setTargetDistance(Integer.parseInt(distance));
        sportsTargetPara.setTargetExTime(Integer.parseInt(sportTime));
        FissionSdkBleManage.getInstance().setTargetSet(sportsTargetPara);

//        AnyWear.setTargetSet(sportsTargetPara, new OnSmallDataCallback() {
//                    @Override
//                    public void OnError(String msg) {
//                        showToast(msg);
//                        dismissProgress();
//                    }
//
//
//                    @Override
//                    public void OnEmptyResult() {
//                        addLog(R.string.FUNC_SET_TARGET_SET, "设置成功");
//                        dismissProgress();
//                        showSuccessToast();
//                    }
//                });
    }


}
