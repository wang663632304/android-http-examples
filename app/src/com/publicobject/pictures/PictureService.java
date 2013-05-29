package com.publicobject.pictures;

import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface PictureService {
  @GET("/{directory}")
  void listPictures(@Path("directory") String directory, Callback<List<String>> picturesCallback);
}