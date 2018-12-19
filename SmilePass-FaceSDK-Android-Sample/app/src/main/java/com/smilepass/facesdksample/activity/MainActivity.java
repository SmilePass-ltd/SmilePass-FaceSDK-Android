package com.smilepass.facesdksample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.smilepass.facesdksample.R;
import com.smilepass.facesdksample.utils.CommonUtils;
import com.smilepass.mobilesdk.activity.CameraActivity;
import com.smilepass.mobilesdk.datatype.CameraFacing;
import com.smilepass.mobilesdk.datatype.LivenessAction;
import com.smilepass.mobilesdk.datatype.RandomizationOption;
import com.smilepass.mobilesdk.datatype.StrictnessMode;
import com.smilepass.mobilesdk.exception.ClientException;
import com.smilepass.mobilesdk.listener.OnApiKeyValidatedListener;
import com.smilepass.mobilesdk.model.AutoSelfie;
import com.smilepass.mobilesdk.model.Configuration;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements OnApiKeyValidatedListener {

    private final static String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "{{API_KEY}}";
    private final static String SERVER_URL = "{{SERVER_URL}}";
    private Context context;
    private Spinner spinnerCameraFacing, spinnerLivenessAction, spinnerStrictnessMode, spinnerInstructionsPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        initView();
        AutoSelfie.init(API_KEY, SERVER_URL, this);
    }

    private void initView() {
        spinnerCameraFacing = findViewById(R.id.spinnerCameraFacing);
        spinnerLivenessAction = findViewById(R.id.spinnerLivenessAction);
        spinnerStrictnessMode = findViewById(R.id.spinnerStrictnessMode);
        spinnerInstructionsPopup = findViewById(R.id.spinnerInstructionsPopup);
        findViewById(R.id.btnLetsGo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCamera();
            }
        });
    }

    private void startCamera() {
        try {
            new AutoSelfie.CameraBuilder(context)
                    .setConfiguration(getConfiguration())
                    .build();
        } catch (ClientException e) {
            e.printStackTrace();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Configuration getConfiguration() {
        Set<LivenessAction> livenessActions = new HashSet<>();
        RandomizationOption randomizationOption = RandomizationOption.DONT_RANDOMIZE;
        String selectedLivenessAction = CommonUtils.convertToCapsWithUnderscore(spinnerLivenessAction.getSelectedItem().toString());
        int randomNumber = 0;
        if (getString(R.string.random).equalsIgnoreCase(selectedLivenessAction)) {
            randomizationOption = RandomizationOption.FROM_GIVEN_NUMBER;
            randomNumber = new Random().nextInt(LivenessAction.values().length - 1);
            if (randomNumber == 0) {
                randomNumber++;
            }
        } else {
            LivenessAction livenessAction = LivenessAction.valueOf(selectedLivenessAction);
            livenessActions.add(livenessAction);
        }

        Configuration.Builder configBuilder = new Configuration.Builder()
                .setCameraFacing(CameraFacing.valueOf(CommonUtils.convertToCapsWithUnderscore(spinnerCameraFacing.getSelectedItem().toString())))
                .setLivenessActions(livenessActions)
                .setStrictnessMode(StrictnessMode.valueOf(CommonUtils.convertToCapsWithUnderscore(spinnerStrictnessMode.getSelectedItem().toString())))
                .showInstructionPopup(Boolean.parseBoolean(spinnerInstructionsPopup.getSelectedItem().toString().toLowerCase()))
                .setRandomizationOption(randomizationOption)
                .setNumOfActionsToRandomize(randomNumber);

        Configuration configuration = null;
        try {
            configuration = configBuilder.build();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return configuration;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CameraActivity.RQ_CAPTURE_IMAGE) {
                ImageViewerActivity.start(context, data.getStringExtra(AutoSelfie.FILE_NAME));
            }
        }
    }

    @Override
    public void onApiKeyValidated(boolean isValidated, String params) {
        Log.d(TAG, "onApiKeyValidated(): isValidated=" + isValidated + ", params=" + params);
    }
}
