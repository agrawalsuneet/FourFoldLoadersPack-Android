# FourFold Loader
> Android FourFoldLoader

> A replacement of default android material progressbar with fourfold loader

![fourfoldloader](https://user-images.githubusercontent.com/12999622/26966423-9f680546-4d17-11e7-86db-f59b914a155f.gif)
![fourfoldloaderdialog](https://user-images.githubusercontent.com/12999622/26966424-9f7a9846-4d17-11e7-89fd-323293d36c08.gif)



## How To use
include below dependency in build.gradle of application and compile it
```
compile 'com.agrawalsuneet.androidlibs:fourfoldloader:0.1'
```

## Through XML
```
<com.agrawalsuneet.fourfoldloader.FourFoldLoader
        android:id="@+id/main_fourfoldloader"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:loader_animDuration="500"
        app:loader_disappear_animDuration="100"
        app:loader_firstSquareColor="@color/pink"
        app:loader_secondSquareColor="@color/blue"
        app:loader_thirdSquareColor="@color/purple"
        app:loader_forthSquareColor="@color/indigo"
        app:loader_squareLength="60dp"
        app:loader_startLoadingDefault="true"
        app:loader_overridePadding="false" />
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
   set if you want to start loading the loader by itself 
   when its visible without calling startLoading().

##  Through Code
```
        LinearLayout container = (LinearLayout) findViewById(R.id.container);

        /*FourFoldLoader loader = new FourFoldLoader(this, 200,
                getResources().getColor(R.color.green),
                getResources().getColor(R.color.red),
                getResources().getColor(R.color.blue),
                getResources().getColor(R.color.colorAccent), true);*/

        loader = new FourFoldLoader(this, true);
        loader.setSquareLenght(200);
        loader.setFirstSquareColor(ContextCompat.getColor(this, R.color.green));
        loader.setSecondSquareColor(ContextCompat.getColor(this, R.color.red));
        loader.setThirdSquareColor(ContextCompat.getColor(this, R.color.blue));
        loader.setForthSquareColor(ContextCompat.getColor(this, R.color.indigo));
        loader.setAnimationDuration(800);
        loader.setDisappearAnimationDurationr(200);

        container.addView(loader);
```

if want to start/stop loading of loader on some view click event
```
view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loader.isLoading()) {
                    loader.stopLoading();
                } else {
                    loader.startLoading();
                }
            }
        });

```

### Constructors 
1. `public FourFoldLoader(Context context)`
   default Constructor for any view

2. `public FourFoldLoader(Context context, boolean startLoadingDefault) `
   added a parameter setting the value of startLoadingDefault 
   which will start loading the loader as and when it will be
   visible

3. `public FourFoldLoader(Context context, int squareLenght, int firstSquareColor,
                          int secondSquareColor, int thirdSquareColor,
                          int forthSquareColor, boolean startLoadingDefault)`
   can set sqaureLength, all square colors with above constructors.
   
   remaining properties like animationDuration, fadeAnimationDuration,
   overridePadding can be set through setters.
   Interpolator can also be set using setter for animation
   
  no need to call `invalidate()` for loader explicitly after any setter. 
  loader will take care by itself


### Please note that minimum API level required to import this library is API 18 because of Android class ViewOverlay.

Feel free to drop a mail at agrawalsuneet@gmail.com if face any issue or require any additional functionality in it.

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
