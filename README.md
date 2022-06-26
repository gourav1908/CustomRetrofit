# CustomRetrofit v-1.1.0
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
	        implementation 'com.github.gourav1908:CustomRetrofit:Version'
	}
  ```
  > Step 3. Add the version 1.0.2 inplace of Version


> Step 4. Usage, Create a Singleton class for getting instance

```singleton
object RetroInstance {
    const val BASE_URL = "https://newsapi.org/v2/"
    fun getRetroInstance(): Retrofit {
        return BuildRetrofit.getRetrofitInstance(BASE_URL)
    }
}
```

> Step 5. In your Main Activity or Viewmodel class

```
	/* start a coroutine scope */
            runBlocking(Dispatchers.IO) {
                /* getting the retrofit instance from singleton and calling API */
                val callAPI = RetroInstance.getRetroInstance()
                    .create(APIService::class.java)
                    .getHeadlines("us", "business", "ba405e756b0a4141948317e8b122f77")

                /* getting **response** in a variable */
                val response: ResponseModel = BuildRetrofit.getResponse(callAPI)  /* ResponseModel is a Generic Response class */

                /* check response status if true or false */
                if (response.status) {
                    /* if true - use **convertResponse** method to get response in your own data class object (here is NewsModel) */
                    val newsModel: NewsModel = BuildRetrofit.convertResponse(
                        response.Data.toString(),
                        NewsModel::class.java
                    )
                    runOnUiThread {
                        /* do UI related work */
                    }
                } else {
                    /* if false - get error message basis on the error code */
                    response.code
                    response.message
                }
            }
	    ```








