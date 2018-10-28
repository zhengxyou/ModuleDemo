
[TOC]

# ModuleDemo

#### 项目介绍
Android模块化(组件化)探索实践

>具体实现可看代码

#### 结构
主要分为4层

##### app空壳
新建的时候建议选择No Add Activity
>不包含任何业务，只负责管理、组装组件和模块。还有如应用签名，多渠道打包、混淆等
>>必须依赖`api project(':basemodule')`基础公共模块(baseModule)和`主模块`,baseModule已经依赖了公共组件`commonlibrary`,所以不需要在依赖公共组件

>>加载模块，必须判断模块是否是集成模式，如
>>>```
>>>if (!rootProject.ext.isRunAlone_main) {
>>>    implementation project(':mainmodule')
>>>}
>>>```

##### 业务模块层
业务模块分成3类
1. 主模块`MainModule`，只有一个:放引导页、启动界面、主界面等
>因为主模块存放启动界面的缘故，所以`不需要根据是否是单独开发的模式而新建开发用的清单文件`
2. 其他模块`otherModule`，只有一个:存放其他不足以分离成单独业务模块的功能，如登陆、注册、升级、分享等，后期可以根据迭代视情况分离
>`otherModule`和第3类其他具体模块不仅需要根据是否是开发模块调整`plugin`类型，还需要新建单独开发用的清单文件和代码
3. 其他具体模块，多个：具体功能模块

##### 基础/通用模块
新建的时候建议选择No Add Activity

此模块负责
>所有的上层模块必须引用这层

>第三方依赖及初始化

>此层提供全局Application,并在清单文件设置，这样上层模块就可以不用在设置就可以直接引用

>应用所需的权限全部放在这层申请

>这一层因为放的是一些公用的资源，所以也`不太需要根据是否是单独开发的模式而新建开发用的清单文件`

这层放的是其他模块需要用到的公共数据，如
1. 通用UI控件:如状态视图
2. 第三方依赖及初始化
3. 通用的资源文件:布局,主题，文本，大小,颜色等等
4. 一些基类：如Activity,Fragment
5. 三方组件的操作工具类，如网络请求
6. ...

##### 公共组件`commonLibrary`
放些自研工具类、通用UI，日志类，基类等

#### 需要解决的问题

##### 单独开发模式与集成模式的切换问题
该问题可以分解成3个问题：
1. `plugin`类型的切换
2. `ApplicationId`的冲突：一个APP只有`一个ApplicationId`的，在集成模式的时候需要去掉
3. `AndroidManifest`合并

解决方案：

这3个主要通过定义一个常量来控制，每个业务模块都需要定义一个对应的控制常量

如定义一个`isRunAlone_main=true`来控制的切换

问题一、二:
```
if (isRunAlone_main.toBoolean()) {
    apply plugin: 'com.android.library'
} else {
    apply plugin: 'com.android.application'
}

 if (isRunAlone_main.toBoolean()) {
     // 单独调试时添加 applicationId ，集成调试时移除
     applicationId "com.loong.login"
 }
```
问题三:
根据常量配置不同的清单文件

首先我们要新建一个`AndroidManifest.xml`文件，加上原有的`AndroidManifest`文件，在两个文件中就可以分别配置单独调试和集成调试时的不同的配置，可以新建一个与Java包同级的名为manifest文件夹将新建的清单文件放在这里面
```
// main/manifest/AndroidManifest.xml 单独调试
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.loong.share">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".ShareActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>

// main/AndroidManifest.xml 集成调试
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.loong.share">
    <application android:theme="@style/AppTheme">
        <activity android:name=".ShareActivity"/>
    </application>
</manifest>
```
##### 界面跳转与通信

可以通过使用`ARouter`与`EventBus`解决这个问题

>EventBus通信需要的类，可定义在`baseModule`中
##### 全局ApplicationContext的获取及组件数据初始化

目前的解决方案是在`baseModule`层，定义一个Application的BaseApplication,在`baseModule`的清单文件中设置这个BaseApplication，然后每个业务Module依赖这个baseModule即可，这样可以达到每个Module都只有一个共同的BaseApplication

第三方或者自己组件也在这个`BaseApplication`中进行初始化

##### 资源名冲突
为了避免资源冲突的问题，我们可在Module中的build.gradle配置资源名的前缀，一方面避免资源冲突，另一方面，也便于标识资源所在的模块：
```
android {
//设置了resourcePrefix值后，所有的资源名必须以指定的字符串做前缀，否则会报错。
 //但是resourcePrefix这个值只能限定xml里面的资源，并不能限定图片资源，所有图片资源仍然需要手动去修改资源名
    resourcePrefix "moduleName_"
}
```
而对于 Module 中有些资源不想被外部访问的，我们可以创建 `res/values/public.xml`，添加到 `public.xml` 中的 resource 则可被外部访问，未添加的则视为私有：
```
<resources>
    <public name="module1_str" type="string"/>
</resources>
```
##### 混淆
组件化项目的Java代码混淆方案采用在集成模式下集中在app壳工程中混淆，各个业务组件不配置混淆文件。

集成开发模式下在app壳工程中build.gradle文件的release构建类型中开启混淆属性，其他buildTypes配置方案跟普通项目保持一致，Java混淆配置文件也放置在app壳工程中，各个业务组件的混淆配置规则都应该在app壳工程中的混淆配置文件中添加和修改。

之所以不采用在每个业务组件中开启混淆的方案，是因为 组件在集成模式下都被 Gradle 构建成了 release 类型的arr包，一旦业务组件的代码被混淆，而这时候代码中又出现了bug，将很难根据日志找出导致bug的原因；另外每个业务组件中都保留一份混淆配置文件非常不便于修改和管理，这也是不推荐在业务组件的 build.gradle 文件中配置 buildTypes （构建类型）的原因。
##### 版本统一管理与控制常量
1. 工程的根目录下创建`config.gradle`
2. 根目录下的build.gradle中加入`apply from: "config.gradle"`

配置模板示例
```
ext {
        isModule_other = false;//false:作为集成模式存在，true:作为组件模式存在
        isModule_main = false;
        // 各个组件版本号的统一管理
       android = [
            compileSdkVersion: 24,
            buildToolsVersion: "25.0.2",
            minSdkVersion    : 16,
            targetSdkVersion : 22,
           versionCode      : 1,
            versionName      : '1.0.0'
        ]
     libsVersion = [
           // 第三方库版本号的管理
            supportLibraryVersion = "25.3.0",
            retrofitVersion = "2.1.0",
            glideVersion = "3.7.0",
             eventbusVersion = "3.0.0",
           gsonVersion = "2.8.0"
      ]
    // 依赖库管理
    dependencies = [
            appcompatV7               : "com.android.support:appcompat-v7:$rootProject.supportLibraryVersion",
           design                    : "com.android.support:design:$rootProject.supportLibraryVersion"
    ] }
```
#### 参考链接

1. [Android 组件化最佳实践](https://mp.weixin.qq.com/s/ubihF5bDbofZfKTT-Ou2gw)
2. [Android 模块化探索与实践](https://zhuanlan.zhihu.com/p/26744821)
3. [Android模块化实践](http://tinycoder.cc/2018/07/10/Android%E6%A8%A1%E5%9D%97%E5%8C%96%E5%AE%9E%E8%B7%B5/)
4. [Android组件化框架项目详解](https://cloud.tencent.com/developer/article/1039736)
5. [Android 模块化探索和实践（1）：基本思路](https://www.jianshu.com/p/a2382dfb76ed)
6. [Android组件化方案](https://mp.weixin.qq.com/s/gCZYtXxBEPmcn53sWsLJ4g)
7. [组件化在项目中的使用姿势](https://mp.weixin.qq.com/s/LXqPpoePcFOkB9bCdQuoKw)
8. [Android组件化方案](https://blog.csdn.net/guiying712/article/details/55213884#3%E5%85%A8%E5%B1%80context%E7%9A%84%E8%8E%B7%E5%8F%96%E5%8F%8A%E7%BB%84%E4%BB%B6%E6%95%B0%E6%8D%AE%E5%88%9D%E5%A7%8B%E5%8C%96)
