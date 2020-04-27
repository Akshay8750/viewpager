package Listener;

import java.util.List;
import Model.Movie;

public interface IFirebaseLoadDOne {
    void  onFirebaseLoadSuccess(List<Movie>movieList);
    void  onFirebaseLoadFailed(String Message);

}
