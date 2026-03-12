package sc.android.recipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){

    //private variable as an object of the data class of recipe state
    private val _categoriesState = mutableStateOf(RecipeState())
    //public variable for outside access of the private variable
    val categoriesState : State<RecipeState> = _categoriesState


    //initializing function
    init {
        fetchCategories()
    }


    //fetch categories function for launching the coroutine and handling exception
    private fun fetchCategories(){

        //trigger for suspend function (runs in the background)
        viewModelScope.launch {

            //if there is no error in fetching the data
            try {
                //joins the base URL and endpoint and stores the data fetched
                val response = recipeService.getCategories()
                _categoriesState.value = _categoriesState.value.copy(
                    //list of categories
                    list = response.categories,
                    loading = false,
                    error = null
                )
            }
            //if there is error in fetching the data
            catch(e : Exception){
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    error = "\n${e.message}"
                )
            }
        }
    }


    //data class for showing the state of the recipe app
    data class RecipeState(
        val loading : Boolean = true,
        val list : List<CategoryItem> = emptyList(),
        val error : String? = null
    )

}