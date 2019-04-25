package com.alifjafar.mynews.screen

import com.alifjafar.mynews.base.BaseResponse
import com.alifjafar.mynews.base.StatedViewModel
import com.alifjafar.mynews.models.Article
import com.alifjafar.mynews.network.NewsApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : StatedViewModel<MainViewModel.State>() {

    private var mApiService: NewsApiService = NewsApiService.create()

    override fun initState() = State()

    enum class Status {
        INIT, SUCCESS, ERROR
    }

    class State {
        var isLoading: Boolean = false
        var status: Status = Status.INIT
        var topHeadline : ArrayList<Article> = ArrayList()
    }

    fun doGetTopHeadlines(country: String) {
        state.isLoading = true
        state.status = Status.INIT
        updateState()
        mApiService.getTopHeadlines(country).enqueue(object : Callback<BaseResponse<ArrayList<Article>>> {
            override fun onResponse(
                call: Call<BaseResponse<ArrayList<Article>>>,
                response: Response<BaseResponse<ArrayList<Article>>>
            ) {
                state.isLoading = false
                if (response.isSuccessful) {
                    state.status = Status.SUCCESS
                    state.topHeadline = response.body()?.articles!!
                } else {
                    state.status = Status.ERROR
                }
                updateState()
            }

            override fun onFailure(call: Call<BaseResponse<ArrayList<Article>>>, t: Throwable) {
                state.isLoading = false
                state.status = Status.ERROR
            }


        })
    }
}