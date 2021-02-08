package com.example.client_zhihu_hzy.RecyclerViewAdapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client_zhihu_hzy.Activity.MyAnswerActivity;
import com.example.client_zhihu_hzy.Activity.MyQuestionActivity;
import com.example.client_zhihu_hzy.Activity.OthersAnswerActivity;
import com.example.client_zhihu_hzy.Activity.OthersQuestionActivity;
import com.example.client_zhihu_hzy.R;

import java.util.List;




public class AnswerAdapterNew extends RecyclerView.Adapter<AnswerAdapterNew.ViewHolder> {

    private List< AnswerItemNew> mAnswerItemList;
    //内部类，基本数据结构
    static class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        TextView itemName;
        ImageView itemHeadImage;
        TextView itemAnswer;
        TextView itemAgreeCount;
        TextView itemCommentCount;


        //内部类构造函数，传入布局
        public ViewHolder(View view) {
            super(view);
            itemView = view;
            itemName =(TextView)view.findViewById(R.id.tv_name);
            itemHeadImage =(ImageView) view.findViewById(R.id.im_head);
            itemAnswer =(TextView)view.findViewById(R.id.tvAnswer);
            itemAgreeCount =(TextView)view.findViewById(R.id.tvAgree);
            itemCommentCount =(TextView)view.findViewById(R.id.tvCommentCount);
        }
    }


    //适配器构造函数
    public AnswerAdapterNew(List<AnswerItemNew> answerItemList) {
        mAnswerItemList = answerItemList;
    }


    //创建ViewHolder实例
    @Override
    public AnswerAdapterNew.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_item,parent,false);
        final AnswerAdapterNew.ViewHolder holder = new AnswerAdapterNew.ViewHolder(view);
        //对一个item设置监听事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = holder.getAdapterPosition();
                AnswerItemNew answerItem = mAnswerItemList.get(position);

                SharedPreferences sp = v.getContext().getSharedPreferences("loginToken",0);
                int uId = sp.getInt("uid",7);

                Log.d("AnswerAdapterNew","Answerer id is "+answerItem.getAnswererId());


                if(uId== answerItem.getAnswererId()){
                    //表示点进的是自己发布的回答
                    Log.d("ContentAdapteraaaaaaaa","AnswererId is "+ answerItem.getAnswererId());
                    int answerId = answerItem.getAnswerId();
                    Intent intent =new Intent(v.getContext(), MyAnswerActivity.class);
                    intent.putExtra("extra_AnswerId",answerId);
                    v.getContext().startActivity(intent);
                    Toast.makeText(v.getContext(),"you clicked your answer ",Toast.LENGTH_SHORT).show();//测试用，提示

                }else{//点进了别人发布的问题
                    Log.d("ContentAdapteraaaaaaaa","AnswererId is "+ answerItem.getAnswererId());
                    int answerId = answerItem.getAnswerId();
                    Intent intent_others =new Intent(v.getContext(), OthersAnswerActivity.class);
                    intent_others.putExtra("extra_AnswerId",answerId);
                    v.getContext().startActivity(intent_others);
                    Toast.makeText(v.getContext(),"you clicked others' answer ",Toast.LENGTH_SHORT).show();//测试用，
                }


//                这里暂且只是实现某一个问题的详细展示，具体数据有待确定

            }

        });
        return holder;
    }


    //传展示的信息 到 界面
    @Override
    public void onBindViewHolder(@NonNull AnswerAdapterNew.ViewHolder holder, int position) {
        Log.d("AnswerAdapterNew","position"+position);
        AnswerItemNew answerItem = mAnswerItemList.get(position);
        holder.itemName.setText(answerItem.getName());
        holder.itemHeadImage.setImageResource(answerItem.getHeadImageId());
        holder.itemAnswer.setText(answerItem.getAnswer());
        holder.itemAgreeCount.setText(answerItem.getAgreeCount()+"赞同");
        holder.itemCommentCount.setText(answerItem.getCommentCount()+"评论");

    }


    //对item计数
    @Override
    public int getItemCount() {
        return mAnswerItemList.size();
    }

    public long getItemId(int position) {
        return position;
    }


}
