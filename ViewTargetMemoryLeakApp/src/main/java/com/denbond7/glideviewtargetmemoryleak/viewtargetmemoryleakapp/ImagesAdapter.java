package com.denbond7.glideviewtargetmemoryleak.viewtargetmemoryleakapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @author Denis Bondarenko
 *         Date: 08.03.2016
 *         Time: 16:00
 *         E-mail: DenBond7@gmail.com
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

  private List<String> images;
  private Context context;

  public ImagesAdapter(Context context, List<String> images) {
    this.context = context;
    this.images = images;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.gallery_item, viewGroup, false));
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, int i) {
    Glide.with(context)
        .load(images.get(i))
        .error(android.R.drawable.ic_delete)
        .centerCrop()
        .into(viewHolder.imageViewCar);
  }

  @Override
  public int getItemCount() {
    return images.size();
  }

  public void updateImages(List<String> images) {
    this.images = images;
    notifyDataSetChanged();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageViewCar;

    public ViewHolder(View itemView) {
      super(itemView);
      imageViewCar = (ImageView) itemView.findViewById(R.id.imageViewCar);
    }
  }
}
