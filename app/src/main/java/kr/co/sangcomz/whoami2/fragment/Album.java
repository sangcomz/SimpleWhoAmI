package kr.co.sangcomz.whoami2.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import kr.co.sangcomz.whoami2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Album extends Fragment {
//import android.support.v4.app.Fragment; 변경해줘야함

    ArrayList<String> imagePath;
    static String mCurrentPhotoPath;
    static String mLoaderPath;
    AlbumAdapter albumAdapter;

    GridView gridView;

    public Album() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_album, container, false);
        imagePath = new ArrayList<>();
        gridView = (GridView) rootView.findViewById(R.id.gv);
        albumAdapter = new AlbumAdapter(getActivity(), imagePath);
        gridView.setAdapter(albumAdapter);
        // Inflate the layout for this fragment
        return rootView;


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_camera) {
            takePicture();
            return true;
        } else if (id == R.id.action_gallery) {
            pickImage();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void pickImage() {
        Intent intent = new Intent(
                Intent.ACTION_PICK,      // 또는 ACTION_PICK
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");              // 모든 이미지
        //intent.putExtra("crop", "true");        // Crop기능 활성화
        intent.putExtra("outputFormat",         // 포맷방식
                Bitmap.CompressFormat.JPEG.toString());

        startActivityForResult(intent, 1);
    }

    public void takePicture() {
        /////////////////////camera/////////////////////////
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
//                Log.d("photoFile path ", String.valueOf(photoFile));
                startActivityForResult(takePictureIntent, 2);
            }
        }
    }

    private void galleryAddPic() {
        File f = new File(mCurrentPhotoPath);
        Log.d(mCurrentPhotoPath, f.getAbsolutePath());
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        Uri contentUri = Uri.fromFile(f);
        imagePath.add(mLoaderPath);
        System.out.println("mLoaderPath ::::: " + mLoaderPath);
        mLoaderPath = "";
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }

    private File createImageFile() throws IOException {   //파일 만들기
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        mLoaderPath = "file://" + image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 2:
                if (resultCode == getActivity().RESULT_OK) {
                    if (data != null) {
                        Uri choiceImgUri = data.getData();
                        System.out.println("path" + choiceImgUri.toString()); // logCat으로 경로확인.

                        imagePath.add(choiceImgUri.toString());
                        albumAdapter.notifyDataSetChanged();


                        //Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
                        // temp.jpg파일을 Bitmap으로 디코딩한다.

                        //ImageView _image = (ImageView) findViewById(R.id.imageView);
                        //_image.setImageBitmap(selectedImage);
                        // temp.jpg파일을 이미지뷰에 씌운다.
                    }
                }
                break;
            case 1:
                if (resultCode == getActivity().RESULT_OK) {
                    System.out.println("mLoaderPath ::::: " + data.getDataString());
                    imagePath.add(mLoaderPath);
                    albumAdapter.notifyDataSetChanged();
//                    galleryAddPic();
//                    scanFile(mCurrentPhotoPath);
                }
                break;
        }

    }

    //어댑터뷰 보면 좋을것같은 자료 http://www.slideshare.net/yjaeseok/20140808-android-study12adapterview
    public class AlbumAdapter extends BaseAdapter {
        ArrayList<String> imagePath;
        ViewHolder holder;
        private LayoutInflater inflater;


        AlbumAdapter(Context context, ArrayList<String> imagePath) {
            this.imagePath = imagePath;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return imagePath.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            if (view == null) {
                view = inflater.inflate(R.layout.hobby_list_item, viewGroup, false);
                holder = new ViewHolder();
                holder.imageView = (ImageView) view.findViewById(R.id.image_view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            Glide.with(getActivity())
                    .load(imagePath.get(position))
                    .centerCrop()
                    .crossFade()
                    .into(holder.imageView);

            return view;
        }

        //ViewHolder 설명 :::: http://theeye.pe.kr/archives/1253
        //http://bellgori.tistory.com/entry/Android-pattern-01-ViewHolder-pattern
        private class ViewHolder {
            private ImageView imageView;
        }
    }
}
