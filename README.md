CopyLocale
==========

Gradle plugin for Android that copies res values from one locale into another locales. It is really useful, when project contains same `strings.xml` files for different locales.

Setup
-----

Add the following to your `build.gradle`:

```gradle
buildscript {
    repositories {
        ...
        maven { url "https://plugins.gradle.org/m2/" }
    }
    dependencies {
        ...
        classpath "gradle.plugin.com.itexus:copylocale:0.1.1"
    }
}

...
apply plugin: 'com.android.application'
apply plugin: 'com.itexus.copylocale'
```

Usage
-----

To use CopyLocale add the following extension in your `build.gradle`:

```gradle
android {
    ...
}

copyLocale {
    from 'ru' // Any locale suffix
    into ['be', 'uk'] // List of your locale suffixes
}
```

If you want to see more detailed example, open [sample project](https://github.com/itexus/copylocale/tree/master/sample).