package dev.hci.manager.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.hci.manager.R;
import dev.hci.manager.dtos.Review;
import dev.hci.manager.recycleviewadapter.RecViewReviewAdapter;

public class RatingFragment extends Fragment {

    private RecyclerView recViewCommon;
    private TextView txtCommon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_3_rating, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recViewCommon = getView().findViewById(R.id.recViewReview);
        ArrayList<Review> reviewList = new ArrayList<>();
        reviewList.add(new Review("Duy Tam", "14:10 10/06/2021",
                "28/06/2021", "18:00", "This salon is the best, as always.", 189, (float)3.5));
        reviewList.add(new Review("Minh Tam", "12:05 08/06/2021",
                "27/06/2021","09:00", "I have a good time. The stylist that worked on my hair did a wonderful job.", 183, (float)4));
        reviewList.add(new Review("Thanh Long", "16:32 07/06/2021",
                "26/06/2021","14:00", "Worst Salon ever. Seriously the WORST!", 164, (float)2));

        RecViewReviewAdapter reviewAdapter = new RecViewReviewAdapter(getContext());
        reviewAdapter.setReviewList(reviewList);
        recViewCommon.setAdapter(reviewAdapter);
        recViewCommon.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));


        Spinner spinnerSort = getView().findViewById(R.id.spinnerSortReview);
        ArrayList<String> dataSpinnerService = new ArrayList<>();
        dataSpinnerService.add("Most Recent");
        dataSpinnerService.add("Highest Rated");
        dataSpinnerService.add("Most Liked");
        dataSpinnerService.add("Your Reviews");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, dataSpinnerService);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSort.setAdapter(dataAdapter);
    }
}