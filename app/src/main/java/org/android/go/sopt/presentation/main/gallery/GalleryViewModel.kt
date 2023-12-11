package org.android.go.sopt.presentation.main.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.go.sopt.domain.repository.ImageRepository
import org.android.go.sopt.util.ContentUriRequestBody
import org.android.go.sopt.util.state.RemoteUiState
import org.android.go.sopt.util.state.RemoteUiState.Failure
import org.android.go.sopt.util.state.RemoteUiState.Success
import org.android.go.sopt.util.state.RemoteUiState.Error
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
                    _postImageState.value = Success
                    Timber.d("POST IMAGE SUCCESS : $response")
                }
                .onFailure { t ->
                    if (t is HttpException) {
                        when (t.code()) {
                            400 -> _postImageState.value = Failure(CODE_IMAGE_OVERSIZED)
                            else -> _postImageState.value = Error
                        }
                    }
                    Timber.e("POST IMAGE FAILURE : $t")
                }
        }
    }

    companion object {
        private const val CODE_IMAGE_OVERSIZED = 400
    }
}
