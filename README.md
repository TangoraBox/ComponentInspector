# Java Desktop (JavaFX and Swing) Component Inspector

> A Tool for help you to inspect the location and properties of certain components in a window hierarchy

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

***JavaFX Component Inspector***

[![FXInspector](doc/images/FXInspector.png)]()

***Swing Component Inspector***

[![FXInspector](doc/images/SwingInspector.png)]()

***Swing inside JavaFX***

[![FXInspector](doc/images/JavaFXWithSwingNode.png)]()

***JavaFX inside Swing***

[![FXInspector](doc/images/SwingWithJavaFXPanel.png)]()

***Example Demo***

![Example Demo](doc/images/demo.gif)

---

## Java FX Example

```java
FXComponentInspectorHandler.handleAll();
```

## Swing Example

```java
SwingComponentInspectorHandler.handleAll();
```

---

## Installation (Currently not available, coming soon)

> Currently, I've created the artifact for openjdk 11+ and openjfx 11+ but if you need target java 8 version, please open an issue.

### Maven

***FXComponentInspector***

```xml
<dependency>
    <groupId>com.tangorabox</groupId>
    <artifactId>component-inspector-fx</artifactId>
    <version>[LATEST_VERSION]</version>
</dependency>
```

***SwingComponentInspector***

```xml
<dependency>
    <groupId>com.tangorabox</groupId>
    <artifactId>component-inspector-swing</artifactId>
    <version>[LATEST_VERSION]</version>
</dependency>
```


## Features

- css class name in javafx node components [![css-class](doc/images/css-class.png)]()

- field name of component declaration in parent (when possible) [![css-class](doc/images/field-name.png)]()

## Usage 

> The inspector window is only shown if you hold down the CONTROL key when you move the mouse

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
- Twitter at <a href="http://twitter.com/garzydj" target="_blank">`@garzydj`</a>

---


## License

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

- **[GPL v3](https://opensource.org/licenses/gpl-3.0.html)**