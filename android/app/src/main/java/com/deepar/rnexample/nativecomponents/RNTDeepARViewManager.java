package com.deepar.rnexample.nativecomponents;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

@ReactModule(name = RNTDeepARViewManager.REACT_CLASS)
public class RNTDeepARViewManager extends SimpleViewManager<RNTDeepAR> {

    public static final String REACT_CLASS = "RNTDeepARView";

    private static final int SWITCH_CAMERA = 1;
    private static final int SWITCH_EFFECT = 3;
    private static final int SET_FLASH_ON = 4;
    private static final int PAUSE = 5;
    private static final int RESUME = 6;
    private static final int TAKE_SCREENSHOT = 7;
    private static final int START_RECORDING = 8;
    private static final int FINISH_RECORDING = 9;



    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected RNTDeepAR createViewInstance(ThemedReactContext reactContext) {
        return new RNTDeepAR(reactContext);
    }   

    @Override
    public @Nullable Map<String, Integer> getCommandsMap() {

        return new HashMap<String, Integer>() {
            private static final long serialVersionUID = 6422000319397326714L;
            {
                put("switchCamera", SWITCH_CAMERA );
                put("switchEffect", SWITCH_EFFECT);
                put("setFlashOn", SET_FLASH_ON);
                put("pause", PAUSE);
                put("resume", RESUME);
                put("takeScreenshot", TAKE_SCREENSHOT);
                put("startRecording", START_RECORDING);
                put("finishRecording", FINISH_RECORDING);
            }
        };
    }

    @Override
    public void receiveCommand(RNTDeepAR deepARView, int commandId, @Nullable ReadableArray args) {
        Assertions.assertNotNull(deepARView);
        //Assertions.assertNotNull(args);
        switch (commandId) {
            case SWITCH_CAMERA: {
                deepARView.switchCamera();
                return;
            }
            case SWITCH_EFFECT: {
                if (args != null) {
                    String maskName = args.getString(0);
                    String slot = args.getString(1);
                    deepARView.switchEffect(maskName, slot);
                }
                return;
            }

            case SET_FLASH_ON: {
                if (args != null) {
                    boolean isFlashOn = args.getBoolean(0);
                    deepARView.setFlashOn(isFlashOn);
                }
                return;
            }

            case PAUSE: {
                deepARView.pause();
                return;
            }
            case RESUME: {
                deepARView.resume();
                return;
            }

            case TAKE_SCREENSHOT: {
                deepARView.takeScreenshot();
                return;
            }

            case START_RECORDING: {
                deepARView.startRecording();
                return;
            }

            case FINISH_RECORDING: {
                deepARView.finishRecording();
                return;
            }

            default:
                throw new IllegalArgumentException(String.format(
                        "Unsupported command %d received by %s.",
                        commandId,
                        deepARView.getClass().getSimpleName()));
        }
    }


    @Nullable @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .put("onEventSent",
                        MapBuilder.of("registrationName", "onEventSent"))
                .build();
    }

}
