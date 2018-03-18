package kapil.circularlayoutmanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kapil.circularlayoutmanager.CircularLayoutManager;
import com.kapil.circularlayoutmanager.OnItemClickListener;
import com.kapil.circularlayoutmanager.ScrollWheel;

import java.util.ArrayList;
import java.util.List;

/**
 * An example activity implementing a recycler view with a circular layout manager and scroll wheel.
 */

public class MainActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private ScrollWheel scrollWheel;

    private List<Model> list;
    private CircularLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setViews();
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        scrollWheel = (ScrollWheel) findViewById(R.id.scroll_wheel);
    }

    private void setViews() {
        initializeList();
        recyclerView.setAdapter(new RecyclerViewAdapter(getApplicationContext(), list));
        recyclerView.addItemDecoration(new RecyclerItemDecoration());
        layoutManager = new CircularLayoutManager(getApplicationContext(), 350, 0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(getApplicationContext(),
                new OnRecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void OnItemClick(RecyclerView parent, int childIndex) {
                CircularLayoutManager linearLayoutManager = (CircularLayoutManager) recyclerView.getLayoutManager();
                int firstPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition() % list.size();
                int lastPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition() % list.size();
                int difference = Math.abs(linearLayoutManager.findFirstCompletelyVisibleItemPosition()-firstPosition* list.size());
                int itemsShown = linearLayoutManager.findLastCompletelyVisibleItemPosition() -  linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                int childIndexToScroll = itemsShown/2;
//                linearLayoutManager.scrollToPositionWithOffset(childIndex,difference);
//                linearLayoutManager.scrollToPosition((childIndex+childIndexToScroll));
                linearLayoutManager.scrollToPosition(childIndex);
                System.out.println("First:"+firstPosition+" last:"+lastPosition+" Scroll to"+(childIndex+childIndexToScroll));

            }
        }));
    }

    private void initializeList() {
        list = new ArrayList<>();
        String event = "Event ", timing = "12:00am - 12:00pm";

        for (int i = 0; i < 10; i++) {
            Model model = new Model();
            model.setEvent(event + (i + 1));
            model.setTimings(timing);

            list.add(model);
        }
    }
}
