package com.sensormanager;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class StepCounterRecord implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mStepCounter;
    private long lastUpdate = 0;
    private int i = 0;
	private int delay;

	private ReactContext mReactContext;
	private Arguments mArguments;


    public StepCounterRecord(ReactApplicationContext reactContext, int delay) {
		this.delay = delay;
        mSensorManager = (SensorManager)reactContext.getSystemService(reactContext.SENSOR_SERVICE);
        mStepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mSensorManager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_NORMAL);
		mReactContext = reactContext;
    }

    public void start(){
        mSensorManager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop() {
        mSensorManager.unregisterListener(this);
    }

	private void sendEvent(String eventName, @Nullable WritableMap params)
	{
		try {
			mReactContext 
				.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class) 
				.emit(eventName, params);
		} catch (RuntimeException e) {
			Log.e("ERROR", "java.lang.RuntimeException: Trying to invoke JS before CatalystInstance has been set!");
		}
	}

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;
		WritableMap map = mArguments.createMap();

        if (mySensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            long curTime = System.currentTimeMillis();
            i++;
            if ((curTime - lastUpdate) > delay) {
                i = 0;
				map.putDouble("steps", sensorEvent.values[0]);
				sendEvent("StepCounter", map);
                lastUpdate = curTime;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
