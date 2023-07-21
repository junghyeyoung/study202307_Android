package com.example.step03customadapter;

/*
    ListView에 연결할 adapter 클래스 정의하기
    - BaseAdapter 추상 클래스를 상속받아서 만든다
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CountryAdapter extends BaseAdapter {
    //필드
    Context context;
    int layoutRes;
    List<CountryDto> list;

    //생성자(context, cell의 layout resource id, 모델)
    public CountryAdapter(Context context, int layoutRes, List<CountryDto> list){
        //생성자의 인자로 전달된 값을 필드에 저장한다
        this.context = context;
        this.layoutRes = layoutRes;
        this.list = list;
    }

    //모델의 개수를 리턴
    @Override
    public int getCount() {
        return list.size();
    }

    //i번째 인덱스에 해당하는 모델을 리턴
    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    //i번째 index에 해당하는 모델의 아이디(pk)가 있다면 리턴, 없다면 index를 리턴
    @Override
    public long getItemId(int i) {
        return i;
    }

    //i번째 인덱스에 해당하는 cell view를 리턴
    /*
        인자로 전달되는 i번째 cell view를 만들어서 리턴해야 한다
        cell view는 layout xml 문서를 전개해서 만들어야 한다
        전개해서 만든 view의 ImageView와 TextView에 적절한 데이터를 출력한 다음
        View 객체를 리턴해준다
        cell view는 모델의 개수만큼 다 만드는 것이 아니라 최소한의 개수만 만들어서
        기존에 만들었던 View 객체를 재사용해야 한다
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        //만일 null 이면
        if(view == null){
            Log.d("###", "###");
            //레이아웃 xml 문서를 전개해서 View 객체를 새로 만든다
            LayoutInflater inflater=LayoutInflater.from(context);
            view = inflater.inflate(layoutRes, viewGroup, false);
        }
        // i 에 해당하는 countryDto 객체
        CountryDto dto=list.get(i);
        //View 객체 안에 있는 ImageView, TextView 의 참조값을 얻어온다
        ImageView imageView=view.findViewById(R.id.imageView);
        TextView textView=view.findViewById(R.id.textView);
        //ImageView, TextView 에 정보를 출력한다
        imageView.setImageResource(dto.getResId());
        textView.setText(dto.getName());
        //i 번째 인덱스에 해당하는 View 를 리턴해 준다.
        return view;
    }
}