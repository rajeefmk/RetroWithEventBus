package com.example.chiragshenoy.myapplication.REST;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chiragshenoy on 03/09/16.
 */

public class ApiClient {


    // For the example get requests
//    public static final String API_ROOT = "http://ec2-54-169-45-113.ap-southeast-1.compute.amazonaws.com/";

    // For the example post requests
    public static final String API_ROOT = "http://staging-now.hashlearn.com/";


    private static volatile ApiClient instance;

    private StudentNetworkService mStudentNetworkService;

    private ApiClient() {
        init();
    }

    public static ApiClient getInstance() {
        ApiClient localInstance = instance;
        if (localInstance == null) {
            synchronized (ApiClient.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ApiClient();
                }
            }
        }
        return localInstance;
    }

    public void init() {
        //Set up this Only during debug/staging mode.
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_ROOT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        mStudentNetworkService = retrofit.create(StudentNetworkService.class);
    }

    public StudentNetworkService getStudentNetworkService() {

        return mStudentNetworkService;

    }

}
