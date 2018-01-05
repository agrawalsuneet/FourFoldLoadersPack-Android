# FourFold Loader
> Android FourFoldLoader            [![BuddyBuild](https://dashboard.buddybuild.com/api/statusImage?appID=5961de4056635b00014ecda7&branch=master&build=latest)](https://dashboard.buddybuild.com/apps/5961de4056635b00014ecda7/build/latest?branch=master)

> A replacement of default android material progressbar with various Squares and Rectangles Loaders


### FourFoldLoader
![fourfoldloader](https://user-images.githubusercontent.com/12999622/26966423-9f680546-4d17-11e7-86db-f59b914a155f.gif)
![fourfoldloaderdialog](https://user-images.githubusercontent.com/12999622/26966424-9f7a9846-4d17-11e7-89fd-323293d36c08.gif)

please check a better quality demo [here](https://youtu.be/v0rr80_kAtw)

Other loaders: [SVGLoader](https://github.com/agrawalsuneet/SVGLoadersPack-Android), [ClockLoader](https://github.com/agrawalsuneet/LoadersPack), [RippleLoader](https://github.com/agrawalsuneet/LoadersPack), [RotatingCircularSticksLoader](https://github.com/agrawalsuneet/LoadersPack),  [LinearDotsLoader](https://github.com/agrawalsuneet/DotsLoader), [CircularDotsLoader](https://github.com/agrawalsuneet/DotsLoader), [LazyLoader](https://github.com/agrawalsuneet/DotsLoader), [TashieLoader](https://github.com/agrawalsuneet/DotsLoader), [SlidingLoader](https://github.com/agrawalsuneet/DotsLoader), [RotatingCircularDotsLoader](https://github.com/agrawalsuneet/DotsLoader), [ZipZapLoader](https://github.com/agrawalsuneet/SquareLoadersPack-Android), [WaveLoader](https://github.com/agrawalsuneet/SquareLoadersPack-Android)

## How To use
include below dependency in build.gradle of application and compile it
```
compile 'com.agrawalsuneet.androidlibs:fourfoldloader:0.3'
```

### FourFoldLoader
##### Through XML
```
<com.agrawalsuneet.fourfoldloader.loaders.FourFoldLoader
        android:id="@+id/main_fourfoldloader"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerVertical="true"
        app:fourfold_disappearAnimDuration="100"
        app:loader_animDuration="500"
        app:loader_firstSquareColor="@color/pink"
        app:loader_forthSquareColor="@color/indigo"
        app:loader_secondSquareColor="@color/blue"
        app:loader_squareLength="60dp"
        app:loader_startLoadingDefault="true"
        app:loader_thirdSquareColor="@color/purple" />
```
##### Through Code
* Kotlin
```
        var fourfoldLoader = FourFoldLoader(this, 200,
                resources.getColor(R.color.green),
                resources.getColor(R.color.red),
                resources.getColor(R.color.blue),
                resources.getColor(R.color.colorAccent), true)
                .apply {
                    animationDuration = 200
                    disappearAnimationDuration = 100
                }

        container.addView(fourfoldLoader)
```

* Java
```
        FourFoldLoader loader = new FourFoldLoader(this, 40,
                ContextCompat.getColor(this, R.color.red),
                ContextCompat.getColor(this, R.color.green),
                ContextCompat.getColor(this, R.color.blue),
                ContextCompat.getColor(this, R.color.colorAccent),
                true);

        loader.setDisappearAnimationDuration(100);
        loader.setAnimationDuration(500);

        container.addView(loader);
```

### Properties
1. `squareLenght (dimen) `
   length of individual squares inside the FourFoldLoader.
2. `first, second, third and forth SquareColor (color)`
   color of individual squares. Can pass hexadecimal color, 
   RGB or int color code from color.xml file.
3. `animationDuration (int)`
   duration to fold any flap in FourFoldLoader.
4. `fadeAnimationDuration (int)`
   duration to disappear the flap once folded.
5. `overridePadding (boolean)(defaultValue = false)`
   in order to show proper overlay while flapping, 
   a minimum padding of half of square length is added on all the sides of Loader, 
   if you want to override the padding, 
   please set this attribute as true and set the required padding.
6. `startLoadingDefault (boolean) (defaultValue = false)`
   set if you want to start loading the fourfoldLoader by itself
   when its visible without calling startLoading().

if want to start/stop loading of fourfoldLoader on some view click event
```
view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fourfoldLoader.isLoading()) {
                    fourfoldLoader.stopLoading();
                } else {
                    fourfoldLoader.startLoading();
                }
            }
        });

```

### Please note that minimum API level required to import this library is API 18 because of Android class ViewOverlay.
  
ZipZapLoader and WaveLoader were moved to [SquareLoadersPack-Android](https://github.com/agrawalsuneet/SquareLoadersPack-Android) because of minimum API level 18 dependency in this library. Moving forward, all the square and rectangle loaders will be added in SquareLoadersPack only.


Please take a 2 mins survey to make this library better [here](https://goo.gl/forms/ok6U8r2awTNkZC912).
It won't take more than 2 mins I promise :) or feel free to drop an email at agrawalsuneet@gmail.com if face any issue or require any additional functionality in it.
```
Copyright 2017 Suneet Agrawal

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
