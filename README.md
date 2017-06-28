# Simple Android App  #

Simple Android App

### Tools ###

* Android Studio
* Git
* VS Code
* Vim

### Enviroment ###

* Deepin 15.04 (Base debian version)

### Build and Installation ###

* In command line execute 

Clone this repository

    git clone https://github.com/rodrigues882013/desafio-mobfiq.git
 
To debug mode

    ./gradlew assembleDebug

Unsingned mode

    ./gradlew assembleRelease

Install with follow commands

In android emulator

    adb install <path_to_your_bin>.apk

In your android device

    adb -d install path/to/your/app.apk

** Or alternatively you can execute all steps above in Android Studio**
