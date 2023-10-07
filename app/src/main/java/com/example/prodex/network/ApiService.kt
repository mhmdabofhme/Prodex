package com.example.prodex.network

import com.example.prodex.models.categories.CategoriesModel
import com.example.prodex.models.categories.Category
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
//
    @GET("api/categories")
    suspend fun getCategories(): Response<CategoriesModel>

//
//    @POST("register")
//    suspend fun register(@Body data: Register): Response<AuthResponse<User>>
//
//    @GET("profile")
//    suspend fun getProfile(): Response<BaseResponseObject<Profile>>
//
//    @GET("profile/{id}")
//    suspend fun getProfile(@Path("id") id: Int): Response<BaseResponseObject<Profile>>
//
//    @Multipart
//    @POST("profile")
//    suspend fun updateProfile(
//        @Part("name_ar") name_ar: RequestBody? = null,
//        @Part("name_en") name_en: RequestBody? = null,
//        @Part("email") email: RequestBody? = null,
//        @Part("password") password: RequestBody? = null,
//        @Part("country_id") country_id: RequestBody? = null,
//        @Part("bio_ar") bio_ar: RequestBody? = null,
//        @Part("bio_en") bio_en: RequestBody? = null,
//        @Part photo: MultipartBody.Part? = null,
//    ): Response<BaseResponseObject<Profile>>
//
//    @POST("projects")
//    suspend fun addProject(@Body data: AddProject): Response<BaseResponseObject<Project>>
//
//    @GET("projects")
//    suspend fun getProjects(): Response<BaseResponseArray<Project>>
//
//    @Multipart
//    @POST("create_post")
//    suspend fun addPost(
//        @Part("title_ar") title_ar: RequestBody? = null,
//        @Part("title_en") title_en: RequestBody? = null,
//        @Part("description_ar") description_ar: RequestBody? = null,
//        @Part("description_en") description_en: RequestBody? = null,
//        @Part("lat") lat: RequestBody? = null,
//        @Part("lang") lang: RequestBody? = null,
//        @Part("project_id") project_id: RequestBody? = null,
//        @Part images: MultipartBody.Part? = null,
//    ): Response<BaseResponseObject<String>>
//
//    @GET("posts")
//    suspend fun getPosts(
//        @Query("page") page: Int? = null
//    ): Response<BaseResponseObject<PaginationResponse<Post>>>
//
//
//    @GET("posts/{id}")
//    suspend fun getPost(@Path("id") id: Int): Response<BaseResponseArray<Post>>
//
//    @POST("delete_post/{id}")
//    suspend fun deletePost(@Path("id") id: Int): Response<BaseResponseObject<String>>
//
//    @Multipart
//    @POST("update_post")
//    suspend fun updatePost(
//        @Part("title_ar") title_ar: RequestBody? = null,
//        @Part("title_en") title_en: RequestBody? = null,
//        @Part("description_ar") description_ar: RequestBody? = null,
//        @Part("description_en") description_en: RequestBody? = null,
//        @Part("lat") lat: RequestBody? = null,
//        @Part("lang") lang: RequestBody? = null,
//        @Part("project_id") project_id: RequestBody? = null,
//        @Part("post_id") post_id: RequestBody? = null,
//        @Part images: MultipartBody.Part? = null,
//    ): Response<BaseResponseObject<String>>
//
//    @POST("create_contact")
//    suspend fun createContact(@Body data: Contacts): Response<BaseResponseObject<String>>
//
//
//    @POST("create_following")
//    suspend fun createFollowing(@Body data: Follow): Response<BaseResponseObject<String>>
//
//
//    @GET("get_countries")
//    suspend fun getCountries(): Response<BaseResponseArray<Country>>
//
//
//    @GET("get_users")
//    suspend fun getUsers(): Response<BaseResponseArray<User>>
//
//
//    @GET("get_followers")
//    suspend fun getFollowers(): Response<BaseResponseArray<Followers>>
//
//    @GET("get_following")
//    suspend fun getFollowing(): Response<BaseResponseArray<Following>>
//
//
//    @GET("getfeatures")
//    suspend fun getFeatures(): Response<BaseResponseArray<User>>
//
//    @POST("unfollow")
//    suspend fun unfollow(@Body data: Follow): Response<BaseResponseObject<String>>
//
//
//    @POST("logout")
//    suspend fun logout(): Response<BaseResponseObject<String>>
//
//
//    @POST("removeimage")
//    suspend fun removeImage(@Body data: RemoveImage): Response<BaseResponseObject<String>>
//
//
//    @POST("search_users")
//    suspend fun searchUsers(
//        @Body data: Search,
//        @Query("page") page: Int? = null
//    ): Response<BaseResponseObject<PaginationResponse<User>>>
//
//
//    @GET("get_notifications")
//    suspend fun getNotifications(@Query("page") page: Int? = null): Response<BaseResponseObject<PaginationResponse<Notification>>>


//    // -------------------------*********************-----------------------------------------------
//
//    //TODO :Response
//    @GET("api/user/add-product-to-favorite/{id}")
//    suspend fun addToFavorites(@Path("id") id: Int): Response<SimpleResponse>

//    @GET("/api/user/remove-product-from-favorite/{id}")
//    suspend fun removeFromFavorites(@Path("id") id: Int): Response<SimpleResponse>
//
//
//    // -------------------------*********************-----------------------------------------------
//
//    @GET("/api/user/orders/{id}")
//    suspend fun getOrderDetails(@Path("id") id: Int): Response<OrderDetailsResponse>


}