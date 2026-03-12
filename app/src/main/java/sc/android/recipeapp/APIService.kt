package sc.android.recipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


//setting up the base URL of the API link
private val retrofit = Retrofit.Builder()
    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()


//attaching the base URL with the endpoint
val recipeService = retrofit.create(APIService :: class.java)


interface APIService {

    //for getting data from the API endpoint
    @GET("categories.php")

    //putting the received data into CategoryList using the suspend function
    suspend fun getCategories() : CategoryList
}