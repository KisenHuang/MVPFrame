# MVPFrame
一个基于MVP开发框架代码库，MVPFrame由model、view、presenter、data 4部分组成，并且将view、presenter向上提取，
封装成基类，子类只需要继承基类使用即可，不用定义接口使用。

# MVP组成
-------
## Presenter
负责View与Model之间的交互和数据通信，这是Presenter定义与实现

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
    }

## View
主要负责UI交互，通过initView、initData、initListener、newPresenter初始化，注newPresenter只能被调用一次，
需要保证View中presenter对象不变。openLoadingAnim和closeLoadingAnim是进行有延时操作时对Loading效果的控制。
onModelComplete(ModelResult) 是Model处理数据的结果返回

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

Activity的基类实现，将View嵌入到Activity中。获取Presenter对象不是通过newPresenter()方法，而是getPresenter()
方法。通过ResultAnalysis类将Model返回结果ModelResult进行解析，并调用handleError()方法处理错误结果。

    public abstract class MvpActivity<P extends BasePresenter> extends AppCompatActivity implements View<P> {

       ...

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

        /**
         * Model错误处理
         *
         * @param e 异常
         */
        protected abstract void handleError(ModelException e);

    }

# Model 和 Data
## Model
数据处理，例如获取网络数据、IO数据。并将获取数据通过Presenter传递给View
例如：
    public class ListModel {

        public void onRefresh(final int resultCode, RequestParam param, final ResultCallback callback) {
            final ArrayList<ListData> list = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                list.add(new ListData(i, "标题 " + i));
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ModelResult result = new ModelResult(resultCode);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("result", list);
                    result.setBundle(bundle);
                    callback.onComplete(result);
                }
            }, 500);
        }

        public void commit(int resultCode, MultiLogic logic, ResultCallback callback) {
            StringBuilder builder = new StringBuilder();
            List<IAdapter> select = logic.getSelectItems();
            for (IAdapter item : select) {
                ListItem listItem = (ListItem) item;
                builder.append(listItem.getItemPosition());
            }
            ModelResult result = new ModelResult(resultCode);
            Bundle bundle = new Bundle();
            bundle.putString("ids", builder.toString());
            result.setBundle(bundle);
            callback.onComplete(result);
        }
    }
## Data
数据模型，默认实现Parcelable接口。
定义Data的意义在于，再打包时，数据模型类不能被混淆，这时就可以
-keep class * implements com.kisen.mvplib.bean.Data{*;}
