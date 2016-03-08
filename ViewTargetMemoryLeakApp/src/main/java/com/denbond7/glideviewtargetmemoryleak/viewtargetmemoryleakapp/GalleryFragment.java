package com.denbond7.glideviewtargetmemoryleak.viewtargetmemoryleakapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Bondarenko
 *         Date: 08.03.2016
 *         Time: 15:45
 *         E-mail: DenBond7@gmail.com
 */
public class GalleryFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<String>>{
  private ImagesAdapter imagesAdapter;
  private boolean isAllDataLoaded;



  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragmetn_gallery, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    imagesAdapter = new ImagesAdapter(getContext(), new ArrayList<String>());
    recyclerView.setAdapter(imagesAdapter);
  }

  @Override
  public Loader<List<String>> onCreateLoader(int id, Bundle args) {
    return null;
  }

  @Override
  public void onLoadFinished(Loader<List<String>> loader, List<String> data) {

  }

  @Override
  public void onLoaderReset(Loader<List<String>> loader) {

  }
}