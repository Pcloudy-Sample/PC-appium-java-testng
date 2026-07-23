# Run Appium Tests with Java and TestNG on pCloudy

This repository is a pCloudy Appium Java TestNG sample using a LambdaTest-style project layout.
It shows how to run Android and iOS tests on the pCloudy Real Device Cloud with Maven and TestNG.

## About

[pCloudy](https://www.pcloudy.com) is a mobile app testing platform that gives you one-click access to real Android and iOS devices in the cloud.
Use this repo to run Appium Java tests against pCloudy devices, capture screenshots, and execute single or parallel TestNG suites.

## Project structure

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

## Prerequisites

- Java 8 or higher
- Maven
- A [pCloudy account](https://www.pcloudy.com)
- Your Android/iOS app uploaded to pCloudy Cloud Drive

## Setup

```bash
git clone https://github.com/Pcloudy-Sample/PC-appium-java-testng
cd PC-appium-java-testng
mvn clean package
```

## Configure credentials and app

This sample currently uses the pCloudy credentials and hub URL configured in `AndroidAppRunner.java` and `IOSAppRunner.java`.
If you prefer environment-based configuration, update the code to read from environment variables or edit the hardcoded values directly.

**Windows:**

```bat
set PCLOUDY_USERNAME=YOUR_EMAIL_ID
set PCLOUDY_APIKEY=YOUR_API_KEY

```

**macOS / Linux:**

```bash
export PCLOUDY_USERNAME="YOUR_EMAIL_ID"
export PCLOUDY_APIKEY="YOUR_API_KEY"

```

## Running the tests

Android single run:

```bash
mvn clean test -P android-single
```

Android parallel run:

```bash
mvn clean test -P android-parallel
```

iOS single run:

```bash
mvn clean test -P ios-single
```

iOS parallel run:

```bash
mvn clean test -P ios-parallel
```

## Capabilities used in this sample

This project sets capabilities using the Appium W3C style and pCloudy vendor options.

- `appium:platformName`
- `appium:platformVersion`
- `appium:automationName`
- `appium:appPackage` / `appium:appActivity` (Android)
- `appium:bundleId` (iOS)
- `appium:pCloudy_ApplicationName`
- `pcloudy:options`
  - `pCloudy_Username`
  - `pCloudy_ApiKey`
  - `pCloudy_DeviceManufacturer`
  - `pCloudy_DevicePlatformVersion`
  - `pCloudy_WildNet`
  - `pCloudy_EnableVideo`
  - `pCloudy_EnablePerformanceData`
  - `pCloudy_EnableDeviceLogs`
  - `appiumVersion`

The Appium hub URL is:

```text
https://device.pcloudy.com/appiumcloud/wd/hub
```

## Notes

- The Android and iOS tests are implemented in `AndroidAppRunner.java` and `IOSAppRunner.java`.
- TestNG suite XML files are under `src/test/java` and select run profiles for single or parallel execution.
- Screenshots are saved locally under the `screenshot/` folder during test execution.
- Ensure the `pCloudy_ApplicationName` value matches the app uploaded to your pCloudy Cloud Drive.

## Troubleshooting

- If the test fails with device availability errors, update the manufacturer/version to an available device or use a specific `pCloudy_DeviceFullName`.
- If you change credentials, make sure the values in the Java files are updated before rerunning tests.

## Learn more

- [pCloudy Website](https://www.pcloudy.com)
- [pCloudy Appium Integration](https://www.pcloudy.com/appium-integration-architecture-redefined-appium-runs-become-simpler-with-pcloudy/)
