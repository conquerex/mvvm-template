package what.the.mvvm.model

import io.reactivex.Single
import what.the.mvvm.KAKAO_APP_KEY
import what.the.mvvm.model.enum.KakaoSearchSortEnum
import what.the.mvvm.model.response.ImageSearchResponse
import what.the.mvvm.model.service.KakaoSearchService

class DataModelImpl(private val service: KakaoSearchService) : DataModel {

    override fun getData(query: String, sort: KakaoSearchSortEnum, page: Int, size: Int): Single<ImageSearchResponse> {
        return service.searchImage(auth = "KakaoAK $KAKAO_APP_KEY", query = query, sort = sort.sort, page = page, size = size)
    }
}