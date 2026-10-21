# Chinese Municipalities

Practical Five is a Java and XML Android application for exploring Beijing, Shanghai, Chongqing, and Tianjin.

## Features

- Validates municipality names without case sensitivity through a reusable `TextProcessor` class.
- Opens a second Activity with an explicit Intent and a shared Intent extra key.
- Changes the municipality name, photograph, information, and colour scheme at runtime.
- Keeps the 80-120 word information section scrollable while the Visit site button remains accessible.
- Opens the selected municipal government's official website with an implicit `ACTION_VIEW` Intent.
- Handles empty, whitespace-only, unsupported, and missing Intent input safely.
- Supports modern edge-to-edge Android displays without overlapping system bars.

## Build and test

Open the project in Android Studio with JDK 17 and Android SDK 37 installed, or run on Windows:

```powershell
.\gradlew.bat testDebugUnitTest lintDebug assembleDebug
```

The debug APK is generated at `app/build/outputs/apk/debug/app-debug.apk`.

## Supported input

Enter Beijing, Shanghai, Chongqing, or Tianjin and press the keyboard Go/Enter action. Capitalisation and surrounding spaces do not affect validation.

The official sources used to write the municipality summaries are listed in [SOURCES.md](SOURCES.md).

