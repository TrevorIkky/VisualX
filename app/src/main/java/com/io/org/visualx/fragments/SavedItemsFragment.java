package com.io.org.visualx.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.io.org.visualx.R;
import com.io.org.visualx.adapter.DisplayAdapter;
import com.io.org.visualx.core.finding.FindingConfig;
import com.io.org.visualx.core.finding.FindingService;
import com.io.org.visualx.ebay.marketplace.search.v1.services.AckValue;
import com.io.org.visualx.ebay.marketplace.search.v1.services.FindItemsByKeywordsRequest;
import com.io.org.visualx.ebay.marketplace.search.v1.services.FindItemsByKeywordsResponse;
import com.io.org.visualx.ebay.marketplace.search.v1.services.PaginationInput;
import com.io.org.visualx.ebay.marketplace.search.v1.services.SearchItem;
import com.io.org.visualx.libs.RequestProcessor;
import com.io.org.visualx.libs.callback.HttpCallback;
import com.io.org.visualx.libs.domain.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.view.View.GONE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedItemsFragment extends Fragment {

    private FindingConfig config = new FindingConfig();
    private RecyclerView recyclerView;
    private ShimmerFrameLayout shimmerFrameLayout;
    private DisplayAdapter displayAdapter;
    private List<SearchItem> searchItemList = new ArrayList<>();

    public SavedItemsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStop() {
        super.onStop();
        shimmerFrameLayout.setVisibility(GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        shimmerFrameLayout.setVisibility(View.VISIBLE);
    }


    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_items, container, false);

        config.setAppName("TrevorIk-visualx-PRD-372183157-1c6a4b4c");
        recyclerView = view.findViewById(R.id.items_recycler_view);
        shimmerFrameLayout = view.findViewById(R.id.shimmer_layout);


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("PREF_ITEM", Context.MODE_PRIVATE);
        String query = sharedPreferences.getString("query", "null");

        EditText q = view.findViewById(R.id.query);
        q.setText(query);

        if (query.equals("null")) {
            query = "lenovo";
        } else {
            // String keywords = ((EditText) findViewById(R.id.edit_input)).getText().toString();
            FindItemsByKeywordsRequest request = new FindItemsByKeywordsRequest();
            request.keywords = query;
            PaginationInput pi = new PaginationInput();
            pi.pageNumber = 1;
            pi.entriesPerPage = 10;
            request.paginationInput = pi;

            RequestProcessor requestProcessor = FindingService.getFindItemsByKeywordsHttpRequest(request, new FindItemsByKeywordsCallback(), config);
            requestProcessor.invokeAsync(getActivity());
        }


        return view;


    }


    private final class FindItemsByKeywordsCallback implements HttpCallback<FindItemsByKeywordsResponse> {

        @Override
        public void onSuccess(FindItemsByKeywordsResponse responseData) {

            if (responseData.ack == AckValue.SUCCESS) {
                Toast.makeText(getActivity(),
                        responseData.searchResult.item.get(0).title,
                        Toast.LENGTH_LONG).show();


                shimmerFrameLayout.setVisibility(GONE);


                searchItemList.clear();
                searchItemList = responseData.searchResult.item;

                displayAdapter = new DisplayAdapter(getActivity(), searchItemList);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(displayAdapter);


                displayAdapter.notifyDataSetChanged();


            } else {
                Toast.makeText(getActivity(),
                        responseData.errorMessage.error.get(0).message,
                        Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onHttpError(ResponseStatus responseCode) {
            Toast.makeText(getActivity(),
                    responseCode.getStatusCode() + " " + responseCode.getStatusMessage(),
                    Toast.LENGTH_LONG).show();

        }

    }


}
