package com.example.projectswd.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectswd.R;
import com.example.projectswd.activities.MenuActivity;
import com.example.projectswd.activities.OrderDetailActivity;
import com.example.projectswd.adapter.OrderAdapter;
import com.example.projectswd.contract.ProductFragmentContract;
import com.example.projectswd.model.OrderDTO;
import com.example.projectswd.model.OrderDetail;
import com.example.projectswd.presenters.ProductFragmentPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment implements ProductFragmentContract.view {


    public OrderFragment() {
        // Required empty public constructor
    }
    private ProductFragmentPresenter presenter;
    String token;
    TextView txtNotReceive, txtReceived;
    ListView lvNotReceive, lvReceived;
    List<OrderDTO> orderNotReceive,orderReceived;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        token = getArguments().getString("TOKEN");
        lvNotReceive= view.findViewById(R.id.lvOrderNotReceive);
        lvReceived = view.findViewById(R.id.lvOrderReceived);
        txtReceived = view.findViewById(R.id.txtOrderReceivedNull);
        txtNotReceive = view.findViewById(R.id.txtOrderNotReceivedNull);
        initPresenter();
        presenter.getOrders(token);


        return view;
    }

    private void initPresenter(){
        presenter = new ProductFragmentPresenter(this);

    }

    @Override
    public void getOrderSuccess(List<OrderDTO> orderDTOS) {
        final OrderAdapter adapterNotReceive = new OrderAdapter();
        OrderAdapter adapterReceived = new OrderAdapter();
        orderNotReceive= new ArrayList<>();
         orderReceived= new ArrayList<>();
        for(int i =0; i<orderDTOS.size();i++){
            //status == 0 chua nhan //// ==1 da nhan
            if (orderDTOS.get(i).getOrder().getStatus()==0){
                orderNotReceive.add(orderDTOS.get(i));


            }else{
                orderReceived.add(orderDTOS.get(i));


            }
        }
        if(orderNotReceive.size()>0){
            adapterNotReceive.setOrderDTOS(orderNotReceive);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) lvNotReceive.getLayoutParams();
            lp.height = 230 * orderNotReceive.size();
            lvNotReceive.setLayoutParams(lp);
            lvNotReceive.setAdapter(adapterNotReceive);
            detail(lvNotReceive,adapterNotReceive);
        }else{
            lvNotReceive.setVisibility(View.GONE);
            txtNotReceive.setVisibility(View.VISIBLE);
            txtNotReceive.setText("Bạn không có đơn hàng chưa nhận");
        }


        if(orderReceived.size()>0){
            adapterReceived.setOrderDTOS(orderReceived);
            LinearLayout.LayoutParams lp2 = (LinearLayout.LayoutParams) lvReceived.getLayoutParams();
            lp2.height = 230 * orderReceived.size();
            lvReceived.setLayoutParams(lp2);
            lvReceived.setAdapter(adapterReceived);
            detail(lvReceived,adapterReceived);
        }else{
            txtReceived.setVisibility(View.VISIBLE);
            lvReceived.setVisibility(View.GONE);
            txtReceived.setText("Bạn không có đơn hàng đã nhận");
        }



    }

    @Override
    public void getOrdersFail(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
    private void detail(ListView listView, final OrderAdapter adapter){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                List<OrderDetail> orderDetails = adapter.getOrderDTOS().get(position).getOrderDetail();
                intent.putExtra("DETAIL", (Serializable) orderDetails);
                startActivity(intent);
            }
        });
    }
}
