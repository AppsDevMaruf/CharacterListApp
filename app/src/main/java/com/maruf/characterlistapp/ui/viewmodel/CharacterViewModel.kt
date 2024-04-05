package com.maruf.characterlistapp.ui.viewmodel


import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruf.characterlistapp.utils.NetworkResult
import com.maruf.characterlistapp.utils.NetworkUtils
import com.maruf.characterlistapp.data.Repository
import com.maruf.characterlistapp.model.CharactersModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(@ApplicationContext private val appContext: Context, private val repository: Repository) : ViewModel() {

    
    /** RETROFIT */
    val charactersResponse: MutableLiveData<NetworkResult<CharactersModel>?> = MutableLiveData()


    fun getCharacter() = viewModelScope.launch {
        getRecipesSafeCall()
    }

    private suspend fun getRecipesSafeCall() {
        charactersResponse.value = NetworkResult.Loading()
        if (NetworkUtils.hasInternetConnection(appContext)) {
            try {
                val response = repository.remote.getCharacters()
                charactersResponse.value = handleCharacterResponse(response)


            } catch (_: Exception) {
                charactersResponse.value = NetworkResult.Error("Character not found.")
            }

        } else {
            charactersResponse.value = NetworkResult.Error("No Internet Connection.")
        }

    }
    

    private fun handleCharacterResponse(response: Response<CharactersModel>): NetworkResult<CharactersModel> {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }

            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited.")
            }

            response.body()?.results.isNullOrEmpty() -> {
                return NetworkResult.Error("Character not found.")
            }

            response.isSuccessful -> {
                return NetworkResult.Success(response.body()!!)
            }

            else -> {
                return NetworkResult.Error(response.message())
            }
        }

    }


}