package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.recycler.LinearItemDecoration;
import com.example.myapplication.recycler.MyAdapter;
import com.example.myapplication.recycler.TestData;
import com.example.myapplication.recycler.TestDataSet;
import android.os.Bundle;

public class RankActivity extends AppCompatActivity implements MyAdapter.IOnItemClickListener{


    private static final String TAG = "TAG";

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private GridLayoutManager gridLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        initView();
    }
    private void initView() {
        //获取实例
        recyclerView = findViewById(R.id.recycler);
        //更改数据时不会变更宽高
        recyclerView.setHasFixedSize(true);
        //创建线性布局管理器
        layoutManager = new LinearLayoutManager(this);
        //创建格网布局管理器
        gridLayoutManager = new GridLayoutManager(this, 2);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //创建Adapter
        mAdapter = new MyAdapter(TestDataSet.getData());
        //设置Adapter每个item的点击事件
        mAdapter.setOnItemClickListener(this);
        //设置Adapter
        recyclerView.setAdapter(mAdapter);
        //分割线
        LinearItemDecoration itemDecoration = new LinearItemDecoration(Color.BLUE);
//        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //动画
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(3000);
        recyclerView.setItemAnimator(animator);
    }
    @Override
    public void onItemCLick(int position, TestData data) {
        Toast.makeText(RankActivity.this, "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
        mAdapter.addData(position + 1, new TestData("新增头条", "0w"));
    }

    @Override
    public void onItemLongCLick(int position, TestData data) {
        Toast.makeText(RankActivity.this, "长按了第" + position + "条", Toast.LENGTH_SHORT).show();
        mAdapter.removeData(position);
    }
}