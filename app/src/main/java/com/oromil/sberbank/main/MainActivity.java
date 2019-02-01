package com.oromil.sberbank.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.oromil.sberbank.R;
import com.oromil.sberbank.models.Post;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainViewContract {

    private MainPresenter mPresenter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PostsAdapter mAdapter;
    private RecyclerView recyclerView;
    private Button btnTop;
    private View emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mPresenter = new MainPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    private void initViews() {
        progressBar = findViewById(R.id.progressBar);
        btnTop = findViewById(R.id.btnTop);
        swipeRefreshLayout = findViewById(R.id.swipeLayout);
        emptyView = findViewById(R.id.emptyView);
        initRecyclerView();
        initRecyclerScrollListener();

        btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(0);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                emptyView.setVisibility(View.GONE);
                mPresenter.loadData();
            }
        });
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        mAdapter = new PostsAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    private void initRecyclerScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy < 0 &&
                        ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition() != 0) {
                    btnTop.setVisibility(View.VISIBLE);
                } else btnTop.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void updateData(List<Post> data) {
        mAdapter.updateData(data);
    }

    @Override
    public  void showNetworkError(){
        if (recyclerView.getAdapter().getItemCount() == 0)
            emptyView.setVisibility(View.VISIBLE);
        Toast.makeText(this, getText(R.string.network_error), Toast.LENGTH_LONG).show();
    }
}
