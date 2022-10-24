package com.example.sqlitehinhanh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DoVatAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DoVat> doVatList;

    public DoVatAdapter(Context context, int layout, List<DoVat> doVatList) {
        this.context = context;
        this.layout = layout;
        this.doVatList = doVatList;
    }

    @Override
    public int getCount() {
        return doVatList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtTen, txtMoTa;
        ImageView imgHinhAnh;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout,null);
            viewHolder.txtTen = (TextView) convertView.findViewById(R.id.text_dong_TenDV);
            viewHolder.txtMoTa = (TextView) convertView.findViewById(R.id.text_dong_MoTa);
            viewHolder.imgHinhAnh =(ImageView) convertView.findViewById(R.id.image_dong_HinhAnh);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DoVat doVat = doVatList.get(position);
        viewHolder.txtTen.setText(doVat.getTen());
        viewHolder.txtMoTa.setText(doVat.getMota());
        // chuyen byte[] sang kieu bitmap
        byte[] hinhanh = doVat.getHinhanh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh,0,hinhanh.length);
        viewHolder.imgHinhAnh.setImageBitmap(bitmap);


        return convertView;
    }
}
