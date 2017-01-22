package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.velkonost.robofest.R;

public class AboutOrganizersAdapter extends RecyclerView.Adapter<AboutOrganizersAdapter.AboutOrganizersViewHolder>{


    public AboutOrganizersAdapter(){}

    @Override
    public AboutOrganizersViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_about_organizers, parent, false);

        return new AboutOrganizersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AboutOrganizersViewHolder holder, int position){
    }

    @Override
    public int getItemCount(){
        return 1;
    }

    public static class AboutOrganizersViewHolder extends RecyclerView.ViewHolder {

        public AboutOrganizersViewHolder(View itemView) {
            super(itemView);

        }
    }
}
