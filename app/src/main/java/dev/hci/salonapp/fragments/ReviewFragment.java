package dev.hci.salonapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import dev.hci.salonapp.R;
import dev.hci.salonapp.dtos.Review;
import dev.hci.salonapp.recycleviewadapter.RecViewReviewAdapter;

public class ReviewFragment extends Fragment {

    private RecyclerView recViewCommon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_salon_3_review, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recViewCommon = getView().findViewById(R.id.recViewReview);
        ArrayList<Review> reviewList = new ArrayList<>();
        reviewList.add(new Review("John", "This salon is the best, as always.",
                "14:10 10/06/2021", (float)3.5, 12, false, false));
        reviewList.add(new Review("James", "I have a good time.\nThe stylist that worked on my hair did a wonderful job.",
                "12:05 08/06/2021", (float)4, 23, true, false));
        reviewList.add(new Review("Jack", "Worst Salon ever. Seriously the WORST!",
                "16:32 07/06/2021", (float)2, 2, false, false));

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