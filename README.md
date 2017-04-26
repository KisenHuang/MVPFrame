# MVPFrame
一个基于MVP开发框架代码库，MVPFrame由model、view、presenter、data 4部分组成，并且将view、presenter向上提取，
封装成基类，子类只需要继承基类使用即可，不用定义接口使用。

# MVP组成
-------
## Presenter
负责View与Model之间的交互和数据通信，这是Presenter定义

    public interface IPresenter {

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

基类实现

    /**
     * 普通页面使用的Presenter
     * Created by huang on 2017/2/7.
     */
    public class BasePresenter implements IPresenter {

        private View view;

        protected View getView() {
            return view;
        }

        @Override
        public void attachView(View view) {
            this.view = view;
        }

        @Override
        public void detachView() {
            view = null;
        }

        /**
         * 默认实现结果回调类
         */
        public class DefResultCallback implements ResultCallback {

            @Override
            public void onComplete(ModelResult result) {
                //关闭加载动画
                getView().closeLoadingAnim();
                //通知View，数据加载完毕
                getView().onModelComplete(result);
            }
        }
    }

# View
主要处理UI交互

    public interface View<P extends BasePresenter> {

        /**
         * 初始化视图
         */
        void initView();

        /**
         * 初始化数据
         */
        void initData();

        /**
         * 初始化监听
         */
        void initListener();

        /**
         * 创建Presenter
         *
         * @return IPresenter实现类
         */
        P newPresenter();

        /**
         * 打开加载动画
         */
        void openLoadingAnim();

        /**
         * 关闭加载动画
         */
        void closeLoadingAnim();

        /**
         * Model加载数据完成回调方法
         *
         * @param result 返回结果
         */
        void onModelComplete(ModelResult result);
    }



    /**
     * Mvp模式View模板Activity基类
     * Created by huang on 2017/2/7.
     */
    public abstract class MvpActivity<P extends BasePresenter> extends AppCompatActivity implements View<P> {

        private P presenter;
        protected Context mContext;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mContext = this;
        }

        @Override
        protected void onPostCreate(@Nullable Bundle savedInstanceState) {
            super.onPostCreate(savedInstanceState);
            initView();
            initData();
            initListener();
        }

        /**
         * 初始化Presenter并关联Activity
         *
         * @return presenter
         */
        protected P getPresenter() {
            if (presenter == null) {
                presenter = newPresenter();
                if (presenter != null) {
                    presenter.attachView(this);
                }
            }
            return presenter;
        }

        @Override
        public void onModelComplete(ModelResult result) {
            result.analysis(new ResultAnalysis.DefResultAnalysis() {

                @Override
                public void fail(int reqCode, ModelException e) {
                    handleError(e);
                }
            });
        }

        @Override
        public void openLoadingAnim() {

        }

        @Override
        public void closeLoadingAnim() {

        }

        /**
         * Model错误处理
         *
         * @param e 异常
         */
        protected abstract void handleError(ModelException e);

        @Override
        protected void onDestroy() {
            super.onDestroy();
            mContext = null;
            if (presenter != null) {
                presenter.detachView();
                presenter = null;
            }
        }
    }

# Model
数据处理，例如获取网络数据、IO数据。并将获取数据通过Presenter传递给View

# Data
数据模型


项目地址
-------
# git
https://github.com/Huang102/MVPFrame
