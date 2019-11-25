# 核心网络请求库

本库集成了retrofit2的再次封装，可以上传(带进度条)，下载（带进度条），get,post,raw数据，并且自定义请求头，token等。采用隔离层隔离网络数据，实现一行代码切换网络请求库。

话不多说，来看如何进行快速网络请求
GET:
        HttpHelper.getInstance().get("/test", new HashMap<>(), new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(Object result) {
                
            }

            @Override
            public void onFault(int code, String errorMsg) {

            }
        });
Post:
       HttpHelper.getInstance().rxPost("/test", new HashMap<>(), new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(Object result) {
                
            }

            @Override
            public void onFault(int code, String errorMsg) {

            }
        });
        
DownLoad:(实现下载，记得申请权限哦)
      HttpHelper.getInstance().rxDownload("/test", new DownHttpCallback() {
            @Override
            protected void onProgress(Integer result) {
                
            }

            @Override
            protected void downComplete() {

            }
        },"fileName");
        
upLoad:上传文件
 HttpHelper.getInstance().rxUpload("", new UploadProgressRequestBody(new File("11"), new HashMap<>(), new ProgressListener() {
            @Override
            public void onProgress(long writtenLength, long totalLength, boolean isFinish) {

            }
        }), new ListHttpCallback() {
            @Override
            protected void onError(int i, String msg) {

            }

            @Override
            public void onFromListSuccess(List result) {

            }
        });

如何使用：
第一步 引入
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
第二步 引入依赖
dependencies {
	        implementation 'com.github.nigelly:coreLib:v1.0.0'
	}
  
第三步请在你的Application里面进行初始化:
ProjectInit.init(this)
                .withApiHostSing("你的Api地址")
                .withAppIsDebug(true)//正式发布请改为false
                .withApplicationContext(this)
                .build();
        HttpHelper.init(new RetrofitProcess());
