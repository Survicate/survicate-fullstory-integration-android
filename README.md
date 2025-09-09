# Survicate - FullStory integration for Android

A thin library designed for seamless integration between [Survicate](https://survicate.com/)
and [FullStory](https://www.fullstory.com/) on Android. It automatically sends survey answers coming
from Survicate SDK as FullStory events that can be previewed directly in the FullStory panel.

## Adding the dependency

### Survicate

Make sure you have defined the Maven repository for Survicate:

```gradle
repositories {
    // ...
    maven { url 'https://repo.survicate.com' }
}
```

Add the Survicate dependencies to your app's `build.gradle` file:

```gradle
dependencies {
    // The integration library
    implementation 'com.survicate:survicate-fullstory-integration:<latest_version>'
    
    // Survicate SDK
    implementation 'com.survicate:survicate-sdk:<latest_version>'
}
```

### FullStory

Make sure you have defined the Maven repository for FullStory in your project-level `build.gradle`
file:

```gradle
repositories {
    // ...
    maven { url "https://maven.fullstory.com" }
}
```

Add the FullStory plugin to your project-level `build.gradle` file:

```gradle
buildscript {
    dependencies {
        // ...
        classpath 'com.fullstory:gradle-plugin-local:<latest_version>'
    }
}
```

Apply the FullStory plugin in your app-level `build.gradle` file:

```gradle
plugins {
    // ...
    id 'fullstory'
}
```

## Usage

> Note that for the plugin to function properly, the FullStory integration must be enabled in the
> Survicate panel.
> Also, it is essential that both the Survicate and FullStory SDKs have been initialized correctly
> as outlined in their respective documentation:
> - [Survicate developer docs](https://developers.survicate.com/mobile-sdk/android/)
> - [FullStory developer docs](https://help.fullstory.com/hc/en-us/articles/360040596093-Getting-Started-with-Android-Data-Capture)

In order to activate the integration library, go to the place in your app where you initialize the
Survicate SDK and register `SurvicateFullStoryIntegration` as an event listener:

```kotlin
Survicate.init(applicationContext)
Survicate.addEventListener(SurvicateFullStoryIntegration())
```

That's it. You can use all FullStory and Survicate features as usual. Every survey answer will be
automatically logged to the FullStory using the `FS.event` method.

## Issues

Got an Issue?

To make things more streamlined, we’ve transitioned our issue reporting to our customer support
platform. If you encounter any bugs or have feedback, please reach out to our customer support team.
Your insights are invaluable to us, and we’re here to help ensure your experience is top-notch!

Contact us via Intercom in the application, or drop us an email at: [support@survicate.com]

Thank you for your support and understanding!

## License

```
MIT License

Copyright (c) 2025 Survicate S.A.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

NOTE: This SDK integrates with the proprietary FullStory SDK, which is
distributed separately by FullStory, Inc. Use of the FullStory SDK is subject
to FullStory’s license and terms of service available at:
https://www.fullstory.com/legal/terms-and-conditions/ This SDK is licensed under the MIT License.

⚠️ Important: This SDK integrates with the proprietary FullStory SDK,
which is distributed separately by FullStory, Inc.

Use of the FullStory SDK is governed exclusively by FullStory’s own license
and terms of service, available at:
https://www.fullstory.com/legal/terms-and-conditions/.

This repository does not distribute or re-license the FullStory SDK.
```
