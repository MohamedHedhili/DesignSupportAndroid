package com.mohamedhedhili.designsupportandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link RecyclerViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class RecyclerViewFragment extends Fragment implements RecyclerViewAdapter.RecyclerViewAdapterCallBack {

    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.inject(this, view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new RecyclerViewAdapter(this);
        adapter.addObjects(createObjects());

        recyclerView.setAdapter(adapter);
    }

    private List<MyObject> createObjects() {
        List<MyObject> objects = new ArrayList<>();

        objects.add(new MyObject("Hammamet", "http://www.jektis.com/photos/280_718.jpg"));
        objects.add(new MyObject("Sousse", "http://www.planetware.com/photos-large/TUN/tunisia-sousse-great-mosque.jpg"));
        objects.add(new MyObject("Djerba", "https://image.resabooking.com/images/image_panoramique/joya_paradise_3.jpg"));
        objects.add(new MyObject("Touzeur", "http://hellotunisia.com/wp-content/uploads/2011/12/images/grand/tozeur/tunisia_photo_tozeur%20(25).jpg"));
        objects.add(new MyObject("Mahdia", "http://www.evasion-travel.com/img/pictures_regions/mahdia.jpg"));
        objects.add(new MyObject("Chebba", "http://s4.e-monsite.com/2011/09/24/05/resize_550_550//SDC14830.jpg"));


        return objects;
    }

    @Override
    public void onRecyclerViewElementClicked(View view, int position, MyObject element) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.MY_OBJECT, element);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(),
                Pair.create(view.findViewById(R.id.image), getResources().getString(R.string.image))
        );

        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
    }
}