### 简介

GProgress是一个进度条(适用于类似声音大小调整的地方)

效果图:

![](https://www.gloomyer.com/img/img/gprogress.gif)

---

### 使用概述

复制GProgress和values下attrs到你的项目中去

需要你为这个View制定固定的高度和宽度(即不能使用wrap_content或match_parent)

推荐宽:高为1:1

拥有如下属性:

```xml
<attr name="selectedColor" /> //被选中的颜色
<attr name="unSelectedColor" /> //为被选中的颜色
<attr name="thickness" /> //进度宽度
<attr name="imgSize" /> //中间的图片高宽1:1
<attr name="itemCount" /> //总进度，可以通过setTotal(int total)修改!
<attr name="bgImg" /> //中间的图片
```

public method:

```java
 public void setTotal(int total) {} //设置总进度
 public int getTotal() {} //获取总进度
 public void setProgress(int progress) {} //设置当前进度
 public int getProgress() {} //获取当前进度
```

如果发现bug等,

欢迎提交issues

个人博客:http://ww.gloomyer.com

email:gloomyneter@gmail.com