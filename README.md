<div align="center">

# Run Appium Java TestNG Tests on pCloudy

[![pCloudy](https://img.shields.io/badge/Made%20by-pCloudy-1a73e8?style=for-the-badge)](https://www.pcloudy.com)
[![Appium Java Client version](https://img.shields.io/maven-central/v/io.appium/java-client.svg?style=for-the-badge&label=Appium%20Java%20Client)](https://mvnrepository.com/artifact/io.appium/java-client)

</div>

## Getting Started

[pCloudy](https://www.pcloudy.com) is a mobile app testing platform that gives you single-click access to real Android and iOS devices directly from your browser. Use these real devices to perform manual and automated testing for your app, and integrate the platform with your CI pipeline for continuous testing across multiple real devices with every build.

pCloudy is fully integrated with Appium, letting you run your existing Appium test scripts against real devices on the pCloudy cloud with minimal changes.

This project is a plain Java + Maven Appium sample showing how to run Java Appium tests on the pCloudy Real Device Cloud with TestNG suite profiles for single-device and parallel execution.

## Prerequisites

- Java 8 or higher
- Maven
- A [pCloudy account](https://www.pcloudy.com) with your registered email and API key
- Your app (`.apk`/`.ipa`) uploaded to your pCloudy Cloud Drive

## Setup

```bash
git clone https://github.com/Pcloudy-Sample/PC-appium-java-testng
cd PC-appium-java-testng
mvn clean package
```

## Configure your credentials and app

This sample currently uses the pCloudy credentials and hub URL configured in `AndroidAppRunner.java` and `IOSAppRunner.java`.
If you prefer environment-based configuration, update the code to read from environment variables or edit the hardcoded values directly.

**macOS / Linux:**

```bash
export PCLOUDY_USERNAME="YOUR_EMAIL_ID"
export PCLOUDY_APIKEY="YOUR_API_KEY"
```

**Windows:**

```bat
set PCLOUDY_USERNAME=YOUR_EMAIL_ID
set PCLOUDY_APIKEY=YOUR_API_KEY
```

## Running the Sample

Each platform has its own runner class and TestNG suite profiles.

Android single run:

```bash
mvn clean test -P android-single
```

Android parallel run:

```bash
mvn clean test -P android-parallel
```

For iOS:

```bash
mvn clean test -P ios-single
```

```bash
mvn clean test -P ios-parallel
```

You can also open `AndroidAppRunner.java` / `IOSAppRunner.java` and hardcode values directly if needed.

The `pCloudy_ApplicationName` capability must match the name of the app already uploaded to your pCloudy Cloud Drive.

## Capabilities used in this sample

| Capability | Description |
|---|---|
| `pCloudy_Username` | Your pCloudy account email ID |
| `pCloudy_ApiKey` | Your pCloudy API key |
| `pCloudy_ApplicationName` | Name of the app already uploaded to your pCloudy Cloud Drive |
| `pCloudy_DurationInMinutes` | Booking duration for the device session |
| `pCloudy_DeviceManufacturer` | (Android) Device manufacturer to run the test on, e.g. `Samsung` |
| `pCloudy_DevicePlatformVersion` | (Android) OS version of the target device |
| `pCloudy_DeviceFullName` | Book a specific device by its full name, e.g. `SAMSUNG_GalaxyA10s_Android_11.0.0_99cde` |
| `automationName` | Appium automation engine — `uiautomator2` (Android) or `XCUITest` (iOS) |
| `appPackage` / `appActivity` | (Android) Package and launch activity of the app under test |
| `bundleId` | (iOS) Bundle identifier of the app under test |

> **Note:** `pCloudy_DeviceFullName` alone is enough to book a specific device. When it is set, `pCloudy_DeviceManufacturer` and `pCloudy_DevicePlatformVersion` are not required. Use `pCloudy_DeviceManufacturer`/`pCloudy_DevicePlatformVersion` instead only if you want pCloudy to select any available device matching that manufacturer/version rather than one specific device.
``

Test results are visible on your [pCloudy dashboard](https://www.pcloudy.com) once the session starts.

### Screenshots

Each step in `AndroidAppRunner.java` (and in `IOSAppRunner.java` once you add your own steps) calls `captureScreenShots()`, which saves a timestamped screenshot into a local `screenshot/` folder after every action.

## Project Structure

```
PC-appium-java-testng/
├── pom.xml
├── README.md
└── src
    ├── main
    │   └── java
    │       ├── AndroidAppRunner.java
    │       └── IOSAppRunner.java
    └── test
        └── java
            ├── android-single.xml
            ├── android-parallel.xml
            ├── ios-single.xml
            └── ios-parallel.xml
```

## How to extend this project

- Add new test methods to `AndroidAppRunner.java` and `IOSAppRunner.java` for additional app workflows.
- Store shared utility methods in a common helper class if you reuse steps across both Android and iOS.
- Add more TestNG suite XML files under `src/test/java` to support different device combinations or test groups.
- Update the `pom.xml` with custom profiles or reporting plugins as your CI workflow evolves.

## API Documentation

For the full pCloudy REST API reference (device booking, app upload, reports, and more), see:

- [pCloudy API Documentation](https://content.pcloudy.com/apidocs/index.html)

## Learn More

- [pCloudy Website](https://www.pcloudy.com)
- [Appium Integration on pCloudy](https://www.pcloudy.com/appium-integration-architecture-redefined-appium-runs-become-simpler-with-pcloudy/)

## Contributions

Contributions are welcome. Please open an issue to discuss your idea before submitting a pull request.
