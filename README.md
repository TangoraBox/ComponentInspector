# Java Desktop (JavaFX and Swing) Component Inspector

> A Tool for help you to inspect the location and some properties (see features section) of the component under mouse, in a window hierarchy

[![License: LGPL v3](https://img.shields.io/badge/License-LGPLv3-blue.svg)](https://opensource.org/licenses/LGPL-3.0)
[![Build Status](https://travis-ci.com/TangoraBox/ComponentInspector.svg?branch=master)](https://travis-ci.com/TangoraBox/ComponentInspector)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.tangorabox%3Acomponent-inspector&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.tangorabox%3Acomponent-inspector)


## Features

- CSS class name in javafx node components [![css-class](doc/images/css-class.png)]()
- Field name of component declaration in parent (when possible) [![css-class](doc/images/field-name.png)]()
- The inspected component is highlighted _(since v1.1.0)_
---

## Usage

> The inspector window is only shown if you hold down the CONTROL key when you move the mouse

---

## Example Demo

![Example Demo](doc/images/demo.gif)

## ScreenShots

***JavaFX Component Inspector***

[![FXInspector](doc/images/FXInspector.png)]()

***Swing Component Inspector***

[![FXInspector](doc/images/SwingInspector.png)]()

***Swing inside JavaFX***

[![FXInspector](doc/images/JavaFXWithSwingNode.png)]()

***JavaFX inside Swing***

[![FXInspector](doc/images/SwingWithJavaFXPanel.png)]()

---

## How to use

Simple, one line of code to handle all:

***Java FX Example***

Add this line in the `public void start(Stage primaryStage)` of your main JavaFX class that extends `Application`:

```java
FXComponentInspectorHandler.handleAll();
```

***Swing Example***

Add this line in the `public static void main(String[] args)` of your application launch class:

```java
SwingComponentInspectorHandler.handleAll();
```

---

## Library import with Maven

The artifacts have been published to maven central:


### FXComponentInspector

***Java 11+***

```xml
<dependency>
    <groupId>com.tangorabox</groupId>
    <artifactId>component-inspector-fx</artifactId>
    <version>1.1.0</version>
</dependency>
```

***Java 8***

```xml
<dependency>
    <groupId>com.tangorabox</groupId>
    <artifactId>component-inspector-fx</artifactId>
    <version>1.1.0-java8</version>
</dependency>
```

---

### SwingComponentInspector

***Java 11+***

```xml
<dependency>
    <groupId>com.tangorabox</groupId>
    <artifactId>component-inspector-swing</artifactId>
    <version>1.1.0</version>
</dependency>
```

***Java 8***

```xml
<dependency>
    <groupId>com.tangorabox</groupId>
    <artifactId>component-inspector-swing</artifactId>
    <version>1.1.0-java8</version>
</dependency>
```

---


## Contributing

> If you want to contribute to upgrade this project with new features or fixing bugs, you're welcome, please make a pull request.

---

## Team


| <a href="https://github.com/garzy" target="_blank">**GaRzY**</a> | 
| :---: 
| [![GaRzY](https://avatars0.githubusercontent.com/u/10849239?s=200)](https://github.com/garzy)   
| <a href="https://github.com/garzy" target="_blank">`github.com/garzy`</a> | 


---

## Support

Reach out to me at one of the following places!

- Mail to [info@tangorabox.com](mailto:info@tangorabox.com)

---


## License

[![License: LGPL v3](https://img.shields.io/badge/License-LGPLv3-blue.svg)](https://opensource.org/licenses/LGPL-3.0)
