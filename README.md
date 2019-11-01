# MuTools
# 安卓工具类


## 参考项目
### RxTool
* **[RxTool](https://github.com/Tamsiree/RxTool)**
* **[RxTool](https://github.com/Tamsiree/RxTool)**
### Toasty
* **[Toasty](https://github.com/GrenderG/Toasty)**
* **[Toasty](https://github.com/GrenderG/Toasty)**

## 如何使用它

### 1.在build.gradle(Project:XXXX)的repositories添加
```text
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

### 2.在build.gradle(Module:app)的dependencies添加
```text
dependencies {
	implementation 'com.github.nullWolf007:MuTools:1.0.2'
}
```

### 3.在gradle.properties中添加

```properties
#表示当前项目启用 androidx
android.useAndroidX=true
```

### 4.在Application中初始化

```java
MuTool.init(this);
```

## 文档

[**查看文档**](https://github.com/nullWolf007/MuTools/wiki/Home)

[**查看文档**](https://github.com/nullWolf007/MuTools/wiki/Home)

[**查看文档**](https://github.com/nullWolf007/MuTools/wiki/Home)

## 更新日志

| 版本  |            描述             |
| :---: | :-------------------------: |
| 1.0.3 |       升级到androidx        |
| 1.0.2 |    添加Activity的工具类     |
| 1.0.1 | 添加SharedPreferences工具类 |
| 1.0.0 |       添加Toast工具类       |




