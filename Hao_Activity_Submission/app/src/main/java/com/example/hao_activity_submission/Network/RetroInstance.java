package com.example.hao_activity_submission.Network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {
    private static Retrofit retrofit = null;



    public static PunkApi getRetrofitInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient httpclient = new OkHttpClient.Builder().addInterceptor(interceptor)
//                    .addInterceptor(new Interceptor() {
//                @Override
//                public okhttp3.Response intercept(Chain chain) throws IOException {
//                    Request original = chain.request();
//                    Request.Builder requestBuilder = original.newBuilder()
//                            .header("Accept", "application/json")
//                            .method(original.method(), original.body());
//                    Request request = requestBuilder.build();
//                    return chain.proceed(request);
//                }
//            })
                    .build();

//            OkHttpClient httpClient = new OkHttpClient();
//            httpClient.interceptors().add(logging);

            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(PunkApi.BASE_URI)
                    .client(httpclient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(PunkApi.class);

    }
}
