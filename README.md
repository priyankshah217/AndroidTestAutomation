**Appium Automation**

This is implementation of PageFactory framework with Appium for Android device.

Current framework provide basic implementation of PageFactory frame and demonstrated on native android application.

Application to be used viu.apk


**Prerequisite:**

* Android SDK
* Appium (Appium to be installed using node package manager)
* Maven (For managing dependencies)

**Automation Flow:**

* Launch App -> Navigate to Music from left menu -> Download video -> Replay Downloaded video
* Launch App -> Search music video -> Download video -> Replay Downloaded video

**Installation:**

* Clone this project from git
* Run "mvn clean test" from project directory

**Note:** this apk is target to ARM architecture, so it may not install in x86 architecture emulators. 
Verified in real device -- Nexus 5/Marshmallow (6.0.1)/API 23 