package com.jack.android.template.core.http;


import com.jack.android.template.core.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hou on 2017/3/14.09:59
 * introduce :
 */
@Module
public class HttpManager {

    private DataApi dataApi;
    private Retrofit mRetrofit;
    private final String baseUrl = Config.getBaseUrl();

    private HttpManager() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)//headers 插入器
                .addInterceptor(new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))//logs 插入器
                .addInterceptor(response401Interceptor)
//                .addInterceptor(new StethoInterceptor())//添加了stetho的拦截器
//                .cache(new Cache(new File(FollowMeApplication.getApplication().getCacheDir(), "responses"), 10 * 1024 * 1024)) //http缓存文件夹

                .retryOnConnectionFailure(true)
                .build();

        dataApi = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build().create(DataApi.class);

    }

    public static HttpManager getInstance() {
        return HttpManagerSingleton.getInstance();
    }

    private static class HttpManagerSingleton {
        static HttpManager httpManger;

        static HttpManager getInstance() {
            if (httpManger == null) {
                synchronized (HttpManagerSingleton.class) {
                    if (httpManger == null) {
                        httpManger = new HttpManager();
                    }
                }
            }
            return httpManger;
        }
    }

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
//            String auth = FollowMeApplication.getAuthorization();
//            String machineCode = MachineInfo.getMachineCode(FollowMeApplication.getApplication());
//            Request request = chain.request().newBuilder()
//                    .addHeader("DeviceID", machineCode)
//                    .addHeader("Authorization", auth)
//                    .build();
//            return chain.proceed(request);
            return null;
        }
    };

    private static final Interceptor response401Interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());
            if (response.code() == HttpStatus.SC_UNAUTHORIZED){
                //未授权
            }
            return response;
        }
    };

    @Provides
    public DataApi getDataApi() {
        return dataApi;
    }
}
