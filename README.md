# MVPFrame
a demo of the frame MVP

MVP组成
-------
># Presenter
>负责View与Model之间的交互和数据通信
>public interface IPresenter {

     /**
      * Presenter被初始化时调用
      *
      * @param view Activity对象
      */
     void attachView(View view);

     /**
      * Activity被销毁时调用
      * {@link Activity#onDestroy()}
      */
     void detachView();
 }

># View
>主要处理UI交互

># Model
>数据处理，例如获取网络数据、IO数据。并将获取数据通过Presenter传递给View

># Data
>数据模型


项目地址
-------
># git
>https://github.com/Huang102/MVPFrame
