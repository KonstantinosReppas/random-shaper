# random-shaper
Android library that creates a random convex polygon bitmap

+ Shape color is configurable
+ Number of polygon points is configurable
+ Simple plug and play

# Setup

Add this line in your root build.gradle at the end of repositories:

```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' } // this line
  }
}
  ```
Add this line in your app build.gradle:
```gradle
dependencies {
  implementation 'com.github.KonstantinosReppas:random-shaper:LATEST_VERSION'
}
```

# Kotlin Usage
```
RandomShaper(Color.BLUE, 5).setupShape(mImageView) //arguments: shapeColor, numberOfPoints
OR
RandomShaper().setupShape(mImageView) //default arguments: Color.BLACK, 4
  ```


# Java Usage
```
new RandomShaper(Color.BLUE, 6).setupShape(mImageView);
  ```


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
