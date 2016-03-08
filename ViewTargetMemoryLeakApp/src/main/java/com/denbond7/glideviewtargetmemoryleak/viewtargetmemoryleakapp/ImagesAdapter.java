package com.denbond7.glideviewtargetmemoryleak.viewtargetmemoryleakapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Denis Bondarenko
 *         Date: 08.03.2016
 *         Time: 16:00
 *         E-mail: DenBond7@gmail.com
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

  private List<String> images;

  public ImagesAdapter(Context context, List<String> images) {
    this.images = images;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    return null;
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, int i) {

  }

  @Override
  public int getItemCount() {
    return images.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View itemView) {
      super(itemView);
    }
  }
}
