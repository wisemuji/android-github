package camp.nextstep.edu.github

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import camp.nextstep.edu.github.domain.Repository
import camp.nextstep.edu.github.domain.RepositoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repositoryUseCase: RepositoryUseCase,
) : ViewModel() {

    private val _repositories = MutableLiveData<List<Repository>>()
    val repositories: LiveData<List<Repository>>
        get() = _repositories

    private val _loadingFailed = SingleLiveEvent<Boolean>()
    val loadingFailed: LiveData<Boolean>
        get() = _loadingFailed

    fun fetchRepositories() = viewModelScope.launch {
        repositoryUseCase()
            .onSuccess { _repositories.value = it }
            .onFailure { _loadingFailed.value = true }
    }
}