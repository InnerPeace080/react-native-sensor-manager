package com.sensormanager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class SensorManagerModule extends ReactContextBaseJavaModule {
    private static final String		REACT_CLASS = "SensorManager";
    private AccelerometerRecord		mAccelerometerRecord;
	private GyroscopeRecord 		mGyroscopeRecord;
	private MagnetometerRecord		mMagnetometerRecord;
	private StepCounterRecord		mStepCounterRecord;
	private ThermometerRecord		mThermometer;
	private MotionValueRecord		mMotionValue;

	private ReactApplicationContext	mReactContext;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    public SensorManagerModule(ReactApplicationContext reactContext) {
		super(reactContext);
		mReactContext = reactContext;
    }

    @ReactMethod
    public void startAccelerometer(int delay) {
        if(mAccelerometerRecord == null) {
            mAccelerometerRecord = new AccelerometerRecord(mReactContext, delay);
        }
        else{
            mAccelerometerRecord.start();
        }
    }

    @ReactMethod
    public void stopAccelerometer() {
      if(mAccelerometerRecord != null)
        mAccelerometerRecord.stop();
    }

    @ReactMethod
    public void startGyroscope(int delay) {
        if(mGyroscopeRecord == null) {
            mGyroscopeRecord = new GyroscopeRecord(mReactContext, delay);
        }
        else{
            mGyroscopeRecord.start();
        }
    }

    @ReactMethod
    public void stopGyroscope() {
      if(mGyroscopeRecord != null)
        mGyroscopeRecord.stop();
    }

    @ReactMethod
    public void startMagnetometer(int delay) {
        if(mGyroscopeRecord == null) {
            mMagnetometerRecord = new MagnetometerRecord(mReactContext, delay);
        }
        else{
            mMagnetometerRecord.start();
        }
    }

    @ReactMethod
    public void stopMagnetometer() {
      if(mMagnetometerRecord != null)
        mMagnetometerRecord.stop();
    }

    @ReactMethod
    public void startStepCounter(int delay) {
        if(mStepCounterRecord == null) {
            mStepCounterRecord = new StepCounterRecord(mReactContext, delay);
        }
        else{
            mStepCounterRecord.start();
        }
    }

    @ReactMethod
    public void stopStepCounter() {
      if(mStepCounterRecord != null)
        mStepCounterRecord.stop();
    }

    @ReactMethod
    public void startThermometer(int delay) {
        if(mThermometer == null) {
            mThermometer = new ThermometerRecord(mReactContext, delay);
        }
        else{
            mThermometer.start();
        }
    }

    @ReactMethod
    public void stopThermometer() {
      if(mThermometer!= null)
        mThermometer.stop();
    }

    @ReactMethod
    public void startMotionValue(int delay) {
        if(mMotionValue == null) {
            mMotionValue = new MotionValueRecord(mReactContext, delay);
        }
        else{
            mMotionValue.start();
        }
    }

    @ReactMethod
    public void stopMotionValue() {
      if(mMotionValue!=null)
        mMotionValue.stop();
    }


	/*
    @Override
    public ReactBarcodeScannerView createViewInstance(ThemedReactContext context) {
    }

    @Override
    public void onDropViewInstance(ReactBarcodeScannerView view) {
    }

    @Override
    public void onHostResume() {
    }

    @Override
    public void onHostPause() {
    }

    @Override
    public void onHostDestroy() {
    }
    */
}
