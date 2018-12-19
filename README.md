# SmilePass FaceSDK Android

[ ![Latest Version](https://api.bintray.com/packages/smilepass-ltd/maven/com.smilepass.mobilesdk%3Afacesdk/images/download.svg) ](https://bintray.com/smilepass-ltd/maven/com.smilepass.mobilesdk%3Afacesdk/_latestVersion)

## Introduction
SmilePass is a face detection and autoselfie SDK for Android. This repo is the sample which demonstrate how to use SmilePass Face SDK in your app.

**SmilePass Face SDK can be used:**
* To detect whether a person is real or it's a still image or video which is called liveness detection.
* To detect face of a person.
* To capture a person's image automatically.
* To detect different gestures of a person i.e. smilepass, blink, wink.

The minimum Android SDK version required to use this SDK is `22`.

# Get Started

This guide is a quick start to add SmilePass Face SDK to an Android app. Android Studio is the recommended development environment for building an app with the SmilePass Face SDK for Android.


## Prerequisites

### SmilePass API key
Your application needs an API key to access the features of SmilePass Face SDK. You can use it with any of your applications that use SmilePass Mobile SDKs and Cloud APIs. It supports an unlimited number of users.
To get API KEY, [Contact SmilePass](https://smile-pass.com/contact).


## Add Face SDK to your app

### Step 1. Add Face SDK dependency
Add SmilePass Face SDK to your project. To do this, add the following dependency in your app level build.gradle file-
```gradle
implementation 'com.smilepass.mobilesdk:facesdk:{latest-version}'
```

The latest version can be found at the top of this file.
You can find all versions directly from [Bintray](https://bintray.com/smilepass-ltd/maven/com.smilepass.mobilesdk%3Afacesdk).

If gradle is unable to resolve library, please add repositories section in build.gradle-
```gradle
repositories {
    maven {
        url  "https://dl.bintray.com/smilepass-ltd/maven"
    }
}
```

### Step 2. Add permission
Add `android.permission.INTERNET` permission in your application's `AndroidManifest.xml`.

### Step 3. Add meta-data tag
Add following meta-data tag inside `<application></application>` tag of your application's `AndroidManifest.xml`-
```xml
<meta-data
    android:name="com.google.android.gms.vision.DEPENDENCIES"
    android:value="face" />
```
### Step 4. Initialize SDK
Initialize Face SDK with a valid API Key by calling following method-
```java
AutoSelfie.init("{{API_KEY}}", this);
```
The second argument of `init()` method is callback listener. Implement `OnApiKeyValidatedListener`
and override following method-
```java
@Override
    public void onApiKeyValidated(boolean isValidated, String params) {
        // your code here
    }
```
You are all set to use cutting-edge face detection features of the SmilePass. 

## Documents
For the detailed information on how to configure different options in
SmilePass Face SDK, read our detailed documents-
* [SDK Setup](https://github.com/SmilePass-ltd/SmilePass-FaceSDK-Android/wiki/Face-SDK-Setup)
* [Tutorials](https://github.com/SmilePass-ltd/SmilePass-FaceSDK-Android/wiki/Face-SDK-Tutorials)
* [Issues & Troubleshooting](https://github.com/SmilePass-ltd/SmilePass-FaceSDK-Android/wiki/Issues-&-Troubleshooting)

## Libraries Used
The core of this library is developed using [Google Vision](https://github.com/googlesamples/android-vision).

## License
SmilePass Face SDK sample application is licensed with the SmilePass License. For more details, see [LICENSE]().
