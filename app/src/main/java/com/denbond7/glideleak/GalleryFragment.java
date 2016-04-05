package com.denbond7.glideleak;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.denbond7.glideleak.viewtargetmemoryleakapp.R;

/**
 * @author Denis Bondarenko
 *         Date: 08.03.2016
 *         Time: 15:45
 *         E-mail: DenBond7@gmail.com
 */
public class GalleryFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<String>> {
  private ImagesAdapter imagesAdapter;
  private View layoutContainer;
  private View layoutProgress;
  private View layoutStatus;

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

    layoutContainer = view.findViewById(R.id.layoutContainer);
    layoutProgress = view.findViewById(R.id.layoutProgress);
    layoutStatus = view.findViewById(R.id.layoutStatus);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    getLoaderManager().initLoader(0, null, this);
  }

  @Override
  public Loader<List<String>> onCreateLoader(int id, Bundle args) {
    return new LoadImagesAsyncTaskLoader(getContext(), CustomRetrofitHelper.getInstance());
  }

  @Override
  public void onLoadFinished(Loader<List<String>> loader, List<String> data) {
    layoutProgress.setVisibility(View.GONE);

    if (data != null) {
      layoutContainer.setVisibility(View.VISIBLE);
      layoutStatus.setVisibility(View.GONE);
      imagesAdapter.updateImages(data);
    } else {
      layoutContainer.setVisibility(View.GONE);
      layoutStatus.setVisibility(View.VISIBLE);
    }
  }

  @Override
  public void onLoaderReset(Loader<List<String>> loader) {

  }
}
