package app.thegoodparts

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import app.thegoodparts.data.repository.JsonGeneratorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompanyDetailViewModel
@ViewModelInject constructor(
   private val repository: JsonGeneratorRepository
) : ViewModel() {

   fun getCompanyDetails(id: String) = repository.getCompanyDetails(id).asLiveData()

   fun toggleFollowingState(id: String) {
      viewModelScope.launch(Dispatchers.IO) {
         repository.toggleFollowingState(id)
      }
   }

   fun toggleFavouriteState(id: String) {
      viewModelScope.launch(Dispatchers.IO) {
         repository.toggleFavouriteState(id)
      }
   }

   fun toggleMemberFavouriteState(id: String) {
      viewModelScope.launch(Dispatchers.IO) {
         repository.toggleMemberFavouriteState(id)
      }
   }
}
