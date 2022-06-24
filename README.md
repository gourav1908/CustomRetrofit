# CustomRetrofit
A pre-defined Retforit Instance
 > Step 1. Add the JitPack repository to your build file

```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  > Step 2. Add the dependency

```gradle
dependencies {
	        implementation 'com.github.gourav1908:CustomRetrofit:Tag'
	}
  ```
  > Step 3. Add the version 1.0.0 inplace of Tag
