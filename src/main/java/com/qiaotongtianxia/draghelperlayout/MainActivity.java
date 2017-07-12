package com.qiaotongtianxia.draghelperlayout;

import android.graphics.Rect;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DragHelperLayout.DragListener{

    private static final String TAG = "========";
    private RecyclerView recyclerView;
    private DragHelperLayout dragLayout;
    private NestedScrollView scrollView;
    private List<String> list;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rView);
        dragLayout = (DragHelperLayout) findViewById(R.id.dragLayout);
        dragLayout.setListener(this);
//        scrollView = (NestedScrollView) findViewById(R.id.scrollView);
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(i+"");
        }
        adapter = new Adapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.top = 1;
            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean isCanPullDown() {
        return !recyclerView.canScrollVertically(-1);//表示是否能向下滚动，false表示已经滚动到顶部
//        return scrollView.getScrollY() ==0 ; scrollView判断到达顶部
    }

    @Override
    public boolean isCanPullUp() {
        return !recyclerView.canScrollVertically(1);//表示是否能向上滚动，false表示已经滚动到底部
//        scrollView判断到达底部
//        return scrollView.getMeasuredHeight()+scrollView.getScrollY() == scrollView.getChildAt(0).getMeasuredHeight();
    }

}