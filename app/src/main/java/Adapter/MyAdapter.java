package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.e.viewpager.MainActivity;
import com.e.viewpager.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;

import Model.Movie;

public class MyAdapter extends PagerAdapter {
    Context context;
    List<Movie> movieList;
    LayoutInflater inflater;

    public MyAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view=inflater.inflate(R.layout.view_pager_item,container,false);

        TextView movie_title=(TextView) view.findViewById(R.id.movie_title);
        TextView no_image=(TextView) view.findViewById(R.id.tv_no_image);
        TextView movie_description=(TextView) view.findViewById(R.id.movie_description);
        ImageView movie_image=(ImageView)view.findViewById(R.id.movie_image);
        FloatingActionButton btn_fav=(FloatingActionButton)view.findViewById(R.id.btn_fav);

        try {
            Picasso.get().load(movieList.get(position).getImage()).into(movie_image);
        }
        catch (Exception e){
            no_image.setVisibility(View.VISIBLE);
        }

        movie_title.setText(movieList.get(position).getName());
        movie_description.setText(movieList.get(position).getDescription());

        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "button clicked", Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Film Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        container.addView(view);

        return view;

    }
}
