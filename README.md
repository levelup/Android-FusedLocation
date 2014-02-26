Android-FusedLocation
=====================

This project aims to bring the Fused location provider to android apps without requiring the Play Services library.

## Summary

The Fused location provider is integrated in the OS (needs to be enabled on the device)
and can be used directly. This library uses it when it's available (Android 4.2+) using
the same API as the Android LocationManager.

## Usage

Just replace `android.location.LocationManager` with `com.levelup.LocationManager` in your code.
The rest of the LocationManager rules and permissions apply.

## Issues

 * the Fused provider is used even when it's marked as not available
 * the Fused provider is used even if it doesn't match the required Criteria
 * LocationProvider mocking is not supported yet
 * the GeoFencing API is not yet un-hidden
 
## License

    Copyright 2014 LevelUp Studios
    Copyright 2007 The Android Open Source Project

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
