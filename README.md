# **📱 Android App Size Helpers**


---
Android App Size Helpers is a lightweight Android library that helps developers build responsive UI layouts using screen percentage–based sizing and scaling utilities, similar to Flutter and React Native

---

## ✨ **Features**

- ✔ Screen percentage width & height
- ✔ Scaled dp / sp (.w, .h, .sp)
- ✔ Padding & margin helpers
- ✔ Aspect ratio support (16:9, 1:1, etc.)
- ✔ Auto text scaling
- ✔ Orientation based sizing
- ✔ No custom views
- ✔ No XML attributes required



  ---

# **Preview**
---
<p align="center">
  <img src="https://github.com/S13reya/Android_SwipeTransition/blob/stages/app/src/main/assets/demovideo.gif" height="320"/>




</p>


## ⚡ **Installation**

**Step 1:** Add JitPack repository to your root build.gradle:

```gradle
maven { url = uri("https://jitpack.io") }
```

**Step 2:** Add the dependency in your app `build.gradle` (example if hosted on JitPack):  

```gradle
dependencies {
	        implementation 'com.github.Excelsior-Technologies-Community:Android_SwipeTransition:1.0.0'

}
```


## **🚀 Usage Examples**

1️⃣ Width & Height in Percentage

```
textView.setSizePercent(
    widthPercent = 0.6f,
    heightPercent = 0.08f
)
```

2️⃣ Padding in Percentage

```
view.setPaddingPercent(
    leftPercent = 0.04f,
    topPercent = 0.02f,
    rightPercent = 0.04f,
    bottomPercent = 0.02f
)

```
3️⃣ Aspect Ratio Sizing

```
imageView.setSizeWithAspectRatio(
    widthPercent = 0.8f,
    aspectRatio = AppSizeHelper.AspectRatio.RATIO_16_9
)
```

4️⃣ Responsive Text Size

```
textView.setTextSizePercent(0.03f)


```
5️⃣ Flutter-style Scaling Extensions

```
val width = 200.w(context)
val height = 60.h(context)
val font = 16.sp(context)

```
6️⃣ Scaled View Size

```
view.setScaledSize(
    widthDp = 220,
    heightDp = 70
)


```
7️⃣ Orientation Based Size

```
view.setSizePercentOrientation(
    portraitWidth = 0.9f,
    portraitHeight = 0.1f,
    landscapeWidth = 0.6f,
    landscapeHeight = 0.2f
)
```



## **📄 License**

**MIT License**  
```
Copyright (c) 2025 Excelsior Technologies

Permission is hereby granted, free of charge, to any person obtaining a copy  
of this software and associated documentation files (the "Software"), to deal  
in the Software without restriction, including without limitation the rights  
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell  
copies of the Software, and to permit persons to whom the Software is  
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all  
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED **"AS IS"**, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,  
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
```



  
