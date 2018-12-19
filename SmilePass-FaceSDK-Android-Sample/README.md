# Auto Selfie Capture 
The SDK captures user's selfie automatically without pressing any button.
User can define one of the available gesture to perform image capture.

# Getting Started
To integrate library in your project-
1.	Paste "autoselfie-0.0.1-alpha.aar" file in your application level /libs folder
2.	Add these lines in app level build.gradle-	
	```gradle
    repositories {
        flatDir {
            dirs 'libs'
        }
    } 
    ```
3. Add the dependency in app level build.gradle-
```implementation(name:'autoselfie-0.0.1-alpha', ext:'aar')```
Replace ```autoselfie-0.0.1-alpha``` with AAR file name.
3.	Initialize Auto Selfie with API key in your activity using following method-
    ```AutoSelfie.init(AUTO_SELFIE_API_KEY)```
4.	Start camera screen using following code snippet-
	```java
    new AutoSelfie.CameraBuilder(context)
                    .setCameraFacing(CameraFacing.FRONT)
                    .setLivenessAction(LivenessAction.SMILE_ONLY)
                    .setStrictnessMode(StrictnessMode.LENIENT)
                    .build();
                    ```
5.	Get captured bitmap image using following code-
    ```java	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == com.autoselfie.activity.CameraActivity.RQ_CAPTURE_IMAGE) {
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(context
                            .openFileInput(data.getStringExtra(AutoSelfie.FILE_NAME)));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
```
### Download Sample App-
Download sample APK which demonstrate the functionalities of Auto Selfie SDK
[Download APK](https://rink.hockeyapp.net/apps/df7395ab597f4b9fb849b065fa442e79 "Auto Selfie Sample")
