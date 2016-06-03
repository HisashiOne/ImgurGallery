# ImgurGallery For Android

ImgurGallery is a android app for preview image content in a:

  - Gridview
  - ListView
  - StagedGridView
  
You can sort by:
  
  - Hot
  - Top
  - User

 ### Set Your Client ID Number
  
```
       
    private static final String BASE_URL = "https://api.imgur.com/3/gallery/";
    static String _extURL;
    private static AsyncHttpClient client = new AsyncHttpClient();

    static {
        client.addHeader("Authorization", "Client-ID " + "***********"); // Set Your User Client ID to pass autentification
    }
```
  
  ![alt tag](http://res.cloudinary.com/dstpgxcdm/image/upload/c_scale,w_259/v1464112509/Screenshot_2016-05-17-16-10-37_jjsztl.png)
  ![alt tag](http://res.cloudinary.com/dstpgxcdm/image/upload/c_scale,w_259/v1464112697/Screenshot_2016-05-17-16-10-48_q35au1.png)
  ![alt tag](http://res.cloudinary.com/dstpgxcdm/image/upload/c_scale,w_259/v1464112806/Screenshot_2016-05-17-16-11-01_jokac6.png)
  ![alt tag](http://res.cloudinary.com/dstpgxcdm/image/upload/c_scale,w_259/v1464112889/Screenshot_2016-05-17-16-11-18_inbk10.png)
  
