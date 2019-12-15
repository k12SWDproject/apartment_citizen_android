package com.example.projectswd.repositories;



import android.content.Intent;
import android.widget.Toast;

import com.example.projectswd.activities.LoginActivity;
import com.example.projectswd.activities.MenuActivity;
import com.example.projectswd.model.FilterEmail;
import com.example.projectswd.model.FilterHouse;
import com.example.projectswd.model.LoginGoogle;
import com.example.projectswd.model.User;
import com.example.projectswd.model.UserUpdateDTO;
import com.example.projectswd.utils.CallBackData;
import com.example.projectswd.utils.ClientApi;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryImp implements UserRepository {
    @Override
    public void loginGoogle(LoginGoogle token, final CallBackData<String> callBackData) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody>  call = clientApi.APIService().loginGoogle(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    if(response.code() == 200){
                        callBackData.success(response.body().string());
                    }else if (response.code() == 204){
                        callBackData.success(null);
                    }else{
                        callBackData.fail("Đăng nhập thất bại");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void login(String username, String password, final CallBackData<String> callBackData) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.APIService().gettToken(username,password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    if(response.code()==200){
                        callBackData.success(response.body().string());
                    }else{
                        callBackData.fail("Đăng nhập thất bại");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBackData.fail("Vui lòng kiểm tra lại");
            }
        });
    }

    @Override
    public void getListMember(String token, FilterHouse filterHouse, final CallBackData<List<User>> callBackData) {
        Gson gson = new Gson();
        String json = gson.toJson(filterHouse);

        ClientApi clientApi = new ClientApi();
        Call<List<User>> call = clientApi.APIService().getMembers(token,json);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                try{
                    if(response.code()==200){
                        List<User> users = new ArrayList<>();
                        users= response.body();
                        if(users.size()>0) {
                            callBackData.success(users);
                        }else{
                            callBackData.fail("Vui lòng thêm thành viên");
                        }
                    }else{
                        callBackData.fail("Vui lòng kiểm tra lại");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

    }

    @Override
    public void getMemberByEmail(String token, FilterEmail filterEmail, final CallBackData<List<User>> callBackData) {
        Gson gson = new Gson();
        String json = gson.toJson(filterEmail);

        ClientApi clientApi = new ClientApi();
        Call<List<User>> call = clientApi.APIService().getMemberByEmail(token,json);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                try{
                    if(response.code()==200){
                        List<User> users = new ArrayList<>();
                        users= response.body();
                        if(users.size()>0) {
                            callBackData.success(users);
                        }else{
                            callBackData.fail("Không tìm thấy người dùng");
                        }
                    }else{
                        callBackData.fail("Vui lòng kiểm tra lại");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

    }

    @Override
    public void addMember(String token, String username, final CallBackData<ResponseBody> callBackData) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.APIService().addMember(token,username);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    if(response.code()==200){
                        callBackData.success(response.body());
                    }else{
                        callBackData.fail("Không thể thêm thành viên");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBackData.fail("Vui lòng kiểm tra lại");
            }
        });
    }

    @Override
    public void deleteMember(String token, String username, final CallBackData<ResponseBody> callBackData) {
         ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.APIService().deleteMember(token,username);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    if(response.code()==200){
                        callBackData.success(response.body());
                    }else{
                        callBackData.fail("Xóa thành viên thất bại");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBackData.fail("Vui lòng kiểm tra lại");
            }
        });
    }

    @Override
    public void updateMember(String token, UserUpdateDTO username, final CallBackData<User> callBackData) {
        ClientApi clientApi = new ClientApi();
        Call<User> call = clientApi.APIService().updateMember(token,username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                try{
                    if(response.code()==200){
                        User  user = response.body();
                        callBackData.success(user);
                    }else{
                        callBackData.fail("Cập nhật thất bại thất bại");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callBackData.fail("Vui lòng kiểm tra lại");
            }
        });
    }

    @Override
    public void getUserByUsername(String token, String username, final CallBackData<User> callBackData) {

       ClientApi clientApi = new ClientApi();
       Call<User> call = clientApi.APIService().getInforByUsername(token,username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){

                    try{

                        if(response.code()==200){
                            User user = new User();
                            user = response.body();
                            callBackData.success(user);
                        }else{
                            callBackData.fail("Lấy thông tin thất bại");
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }


                }

            }


            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callBackData.fail("Lấy thông tin thất bại");
            }
        });
    }
}
