package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.velkonost.robofest.R;

public class AboutContactAdapter extends RecyclerView.Adapter<AboutContactAdapter.AboutContactViewHolder>{


    public AboutContactAdapter(){}

    @Override
    public AboutContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_about_contact, parent, false);

            return new AboutContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AboutContactViewHolder holder,int position){
            holder.desc.setText("8-800-2222-108");
            holder.desc2.setText("robofestomsk@mail.ru");
    }

    @Override
    public int getItemCount(){
            return 1;
    }

    public static class AboutContactViewHolder extends RecyclerView.ViewHolder {

        TextView desc;
        TextView desc2;


        public AboutContactViewHolder(View itemView) {
            super(itemView);

            desc = (TextView) itemView.findViewById(R.id.desc);
            desc2 = (TextView) itemView.findViewById(R.id.desc2);


        }
    }
}
