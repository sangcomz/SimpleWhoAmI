package kr.co.sangcomz.whoami2.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

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

    static ArrayList<String> imagePath;
    static String mCurrentPhotoPath;
    static String mLoaderPath;
    AlbumAdapter albumAdapter;
    GridView gridView;

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
                Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI); //sd card Image
//      EXTERNAL_CONTENT_URI	The content:// style URI for the "primary" external storage volume.
//      INTERNAL_CONTENT_URI	The content:// style URI for the internal storage.
        startActivityForResult(intent, 1);
    }

    public void takePicture() {
        /////////////////////camera/////////////////////////
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//암시적 인텐트.
        //////////////암시적 인텐트///////////////////
        //ACTION_VIEW content://contacts/people/1 - 1번 역락처 정보를 표시한다.
        //ACTION_DIAL content://contacts/people/1 - 1번 연락처로 전화걸기 화면을 표시한다.
        //ACTION_VIEW tel:01012345678 - 010-1234-5678 번호로 전화걸기 화면표시.
        //ACTION_DIAL tel:01012345678 - 010-1234-5678 번호로 전화걸기 화면표시.
        //ACTION_CALL tel:01012345678 - 010-1234-5678 번호로 바로 전화 걸기.
        //ACTION_EDIT content://contacts/people/1 - 1번 역락처 정보를 편집한다.
        //ACTION_VIEW content://contacts/people/ - 연락처 리스트를 표시한다.

        //resolveActivity :::: Determine the best action to perform for a given Intent.
        System.out.println("takePictureIntent.resolveActivity(getActivity().getPackageManager()) :::: " +
                takePictureIntent.resolveActivity(getActivity().getPackageManager()));
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile(); //이미 파일을 만들어버림. 실패할경우 파일 삭제.
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
        mLoaderPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 2:
                if (resultCode == getActivity().RESULT_OK) {
//                    System.out.println(mCurrentPhotoPath); // logCat으로 경로확인.

//                    imagePath.add(mCurrentPhotoPath);
                    imagePath.add(mLoaderPath);
                    albumAdapter.notifyDataSetChanged();

//                    for (int i = 0; i < imagePath.size(); i++) {
//                        System.out.println("imagePath :::: " + imagePath.get(i));
//                    }
                } else {
                    File file = new File(mLoaderPath);
                    file.delete();
                }

                break;
            case 1:
                if (resultCode == getActivity().RESULT_OK) {
                    System.out.println("mLoaderPath ::::: " + data.getDataString());
                    imagePath.add(data.getDataString());
                    albumAdapter.notifyDataSetChanged();
//                    for (int i = 0; i < imagePath.size(); i++) {
//                        System.out.println("imagePath :::: " + imagePath.get(i));
//                    }
                }
                break;
        }

    }


    //어댑터뷰 보면 좋을것같은 자료 http://www.slideshare.net/yjaeseok/20140808-android-study12adapterview
    public class AlbumAdapter extends BaseAdapter {
        ArrayList<String> imagePath;
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
            return imagePath.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ViewHolder holder; //final?
            if (view == null) {
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.album_list_item, viewGroup, false);
                holder.imageView = (ImageView) view.findViewById(R.id.image_view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }


            System.out.println("image Path :::: " + imagePath.get(position));
            Glide.with(getActivity())
                    .load(imagePath.get(position))
                    .centerCrop()
                    .into(holder.imageView);

            return view;
        }

        //ViewHolder 설명 :::: http://theeye.pe.kr/archives/1253
        //http://bellgori.tistory.com/entry/Android-pattern-01-ViewHolder-pattern
        private class ViewHolder {
            ImageView imageView;
        }
    }
}
