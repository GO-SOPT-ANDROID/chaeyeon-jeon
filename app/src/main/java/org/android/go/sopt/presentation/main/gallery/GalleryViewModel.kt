package org.android.go.sopt.presentation.main.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import org.android.go.sopt.domain.repository.ImageRepository
import org.android.go.sopt.util.ContentUriRequestBody
import org.android.go.sopt.util.state.RemoteUiState
import org.android.go.sopt.util.state.RemoteUiState.Failure
import org.android.go.sopt.util.state.RemoteUiState.Success
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
) : ViewModel() {
    private val _postImageState = MutableLiveData<RemoteUiState>()
    val postImageState: LiveData<RemoteUiState>
        get() = _postImageState

    private val _imageFile = MutableLiveData<ContentUriRequestBody>()
    val imageFile: LiveData<ContentUriRequestBody>
        get() = _imageFile

    fun setImageFile(requestBody: ContentUriRequestBody) {
        _imageFile.value = requestBody
    }

    fun postImage() {
        viewModelScope.launch {
            // TODO: null 처리
            imageRepository.uploadImage(imageFile.value!!.toFormData())
                .onSuccess { response ->
                    Timber.d("POST IMAGE SUCCESS : $response")
                    _postImageState.value = Success
                }
                .onFailure { throwable ->
                    if (throwable is HttpException) {
                        Timber.e("POST IMAGE FAILURE(${throwable.code()}) : ${throwable.message()}")
                        _postImageState.value = Failure(null)
                    }
                }
        }
    }
}
