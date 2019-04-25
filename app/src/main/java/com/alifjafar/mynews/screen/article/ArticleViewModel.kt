package com.alifjafar.mynews.screen.article

import android.util.Log
import com.alifjafar.mynews.base.BaseResponse
import com.alifjafar.mynews.base.StatedViewModel
import com.alifjafar.mynews.models.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleViewModel : StatedViewModel<ArticleViewModel.State>() {

    override fun initState() = State()

    enum class Status {
        INIT, SUCCESS, ERROR
    }

    class State {
        var isLoading: Boolean = false
        var status: Status =
            Status.INIT
        var topHeadline: ArrayList<Article> = ArrayList()
    }

    var getArticle: (() -> Call<BaseResponse<ArrayList<Article>>>)? = null

    fun doGetArticle() {
        setState {
            isLoading = true
            status = Status.INIT
        }
        getArticle?.invoke()?.enqueue(object : Callback<BaseResponse<ArrayList<Article>>> {
            override fun onResponse(
                call: Call<BaseResponse<ArrayList<Article>>>,
                response: Response<BaseResponse<ArrayList<Article>>>
            ) {
                setState {
                    isLoading = false
                    if (response.isSuccessful) {
                        status = Status.SUCCESS
                        topHeadline = response.body()?.articles!!
                    } else {
                        status = Status.ERROR
                        Log.d("ERROR", response.body()?.status)
                    }
                }
            }
            override fun onFailure(call: Call<BaseResponse<ArrayList<Article>>>, t: Throwable) {
                setState {
                    isLoading = false
                    status = Status.ERROR
                }
            }
        })
    }
}