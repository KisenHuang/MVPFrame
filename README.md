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
onModelComplete(ModelResult) 是Model处理数据的结果回调方法

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

#增加对列表的mvp实现
-------
其实list(RecyclerView、ListView)、Adapter、Model、Data。同样是mvp的体现，也可以使用mvp框架去实现。
这里以RecyclerView为例(RecyclerView灵活，依赖少，可拓展性强，效果也很棒，建议大家使用)

##Adapter
一般我们在写列表界面时都会写很多Adapter，代码重复，无非就是onBindViewHolder(),getItemViewType(),getItemCount()
和onCreateViewHolder()方法，我们可以将这几个方法的实现转移到Data数据类中，这样我们定义IAdapter接口：

    public interface IAdapter {

        /**
         * 需要实现，返回对应Item的布局文件Id 如果返回0，则使用适配器默认布局
         *
         * @return 返回当前数据类对应布局
         */
        int getItemResId();

        /**
         * 必须实现，在数据类中直接将数据适配到通过BaseViewHolder获取到的视图中
         *
         * @param helper          用来获取Item的控件
         * @param adapterPosition 该Item在Adapter中的位置
         *                        {@link android.widget.BaseAdapter#getView(int, View, ViewGroup)}
         */
        void onBindViewHolder(BaseViewHolder helper, int adapterPosition);

        /**
         * 需要实现，默认返回0，同一列表中出现多种不同的布局时，必须返回不同的类型，
         * 如果返回相同的值，会因BaseViewHolder复用出现布局错乱，处理数据时异常
         * 在{@link IAdapter#getItemResId()}中已经把对应的布局返回给适配器
         *
         * @return 返回当前自定义Item类型
         * {@link android.widget.BaseAdapter#getItemViewType(int)}
         */
        int getItemType();

        /**
         * 在Adapter中获取到的Item的位置数据
         *
         * @return item在adapter中的位置
         */
        int getItemPosition();
    }

自定义Adapter，利用IAdapter将逻辑转移到Data中：

    public class BaseAdapter<I extends IAdapter> extends RecyclerView.Adapter<BaseViewHolder> {

        private List<I> mItems;
        private int itemPos;

        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(mItems.get(itemPos).getItemResId(), parent, false);
            return new BaseViewHolder(v);
        }

        @Override
        public void onBindViewHolder(BaseViewHolder holder, int position) {
            itemPos = holder.getAdapterPosition();
            mItems.get(position).onBindViewHolder(holder, position);
        }

        @Override
        public int getItemViewType(int position) {
            return mItems.get(position).getItemType();
        }

        @Override
        public int getItemCount() {
            return mItems == null ? 0 : mItems.size();
        }

        ...
    }

这样我们就可以避免XXXAdapter，只需要一个BaseAdapter就够了，如果有特殊要求也可以重新实现。
在Data中我们就可以把逻辑处理好。

    public class UserData implements IAdapter {

        private int pos;
        private String name;
        private String pwd;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        @Override
        public int getItemResId() {
            return 0;
        }

        @Override
        public void onBindViewHolder(BaseViewHolder helper, int adapterPosition) {
            pos = adapterPosition;
        }

        @Override
        public int getItemType() {
            return 0;
        }

        @Override
        public int getItemPosition() {
            return pos;
        }
    }

## Item实现
但是这样又会带来一个问题：数据类一般是由网络请求得到的，不会添加过多的类引用(例如：Context上下文,逻辑处理对象等)
这样就会导致局限性，当我们有更多需求时，在这种方式下，处理数据类逻辑会更复杂，而数据类本身代码量也会随着处理逻辑的
增加而增加。所以我们不适用数据类实现IAdapter，我们单独定义一个Item类实现IAdapter，并且增加相应的API：

    public abstract class Item<D extends Data> implements IAdapter, Interact<D>, View.OnClickListener {

        protected D data;
        protected ItemLogic logic;
        protected BaseAdapter adapter;
        protected int position;
        protected Context mContext;

        @Override
        public void onBindViewHolder(BaseViewHolder helper, int adapterPosition) {
            position = adapterPosition;
            setViewData(helper);
            helper.itemView.setEnabled(itemEnable());
            helper.itemView.setOnClickListener(this);
            onRefreshViewStyle();
        }

        @Override
        public int getItemType() {
            //默认返回 0，可重写
            return 0;
        }

        @Override
        public int getItemPosition() {
            return position;
        }

        @Override
        public void onClick(View v) {
            if (logic != null && logic.isReady()) {
                logic.onItemClick(adapter, this);
            }
            onItemClick(v);
        }

        /**
         * 返回Item持有数据
         */
        public D getData() {
            return data;
        }

        /**
         * 设置处理逻辑
         */
        public void setLogic(ItemLogic logic) {
            this.logic = logic;
        }

        /**
         * 给Item设置数据
         */
        public void setData(D data) {
            this.data = data;
        }

        /**
         * 设置Adapter
         */
        public void setAdapter(BaseAdapter adapter) {
            this.adapter = adapter;
        }

        /**
         * 设置上下文
         */
        public void setContext(Context context) {
            mContext = context;
        }
    }

Item类还是实现了Interact接口，Interact接口主要是一些Item的交互与逻辑

    public interface Interact<D extends Data> {
        /**
         * 用于更新UI样式
         */
        void onRefreshViewStyle();

        /**
         * 得到Data数据，显示在Item上
         *
         * @param helper item UI持有对象
         * @see IAdapter setViewData(Context context,BaseViewHolder helper, int adapterPosition)
         */
        void setViewData(BaseViewHolder helper);

        /**
         * 设置Item是否可以点击
         */
        boolean itemEnable();

        /**
         * 条目点击事件。
         * {@link Interact#itemEnable()}必须返回true，这个方法才会被调用
         *
         * @param v item对应View
         */
        void onItemClick(View v);

        /**
         * 工厂方法创建Item时调用的方法
         *
         * @return 返回一个新的Item实例
         */
        Item<D> newSelf();

        /**
         * Item被创建时，设置完数据后调用，
         * 在{@link Item#onBindViewHolder(BaseViewHolder, int)}之前被调用，
         * 只被调用一次
         */
        void readyTodo();
    }

到目前为止列表的MVP框架已经建好了。View(Adapter)、Presenter(Item)。
大家可能又在想Item与Data对IAdapter的实现，比较这两种方式，感觉Item没有很大优势？
现在我有这样一个需求：在一个列表中，实现Item多选，并返回选择的结果。
那我们继续：

    /**
     * 列表Presenter
     * <p>
     * 绑定{@link ItemLogic}列表逻辑处理
     * 生成默认BaseAdapter
     * 使用ItemFactory生成Item列表
     * </p>
     * Created by huang on 2017/2/7.
     */
    public abstract class BaseListPresenter<D extends Data> extends BasePresenter {

        private Item<D> mItemTemplate;
        private ItemFactory<D> factory;
        protected ItemLogic itemLogic;
        private BaseAdapter<Item<D>> adapter;

        @Override
        public void attachView(View view) {
            super.attachView(view);
            mItemTemplate = setupItemTemplate();
            adapter = new BaseAdapter<>();
            factory = new ItemFactory<>(view, mItemTemplate, adapter);
        }

        /**
         * 在父类中注册ItemLogic，
         * 主要是在创建Item时传给所有Item，保持所有Item都持有一个ItemLogic对象
         * {@link ItemFactory#makeItems(List, ItemLogic)}
         *
         * @param logic 在父类中注册的ItemLogic
         */
        protected void setItemLogic(ItemLogic logic) {
            itemLogic = logic;
        }

        @Override
        public void detachView() {
            super.detachView();
            if (itemLogic != null) {
                itemLogic.clear();
                itemLogic = null;
            }
            mItemTemplate = null;
            factory = null;
            adapter.clear();
        }

        /**
         * 通过list生产出Item列表
         * {@link ItemFactory#makeItems(List, ItemLogic)}
         *
         * @param list 生产Item所需数据源
         */
        public void notifyAfterLoad(List<D> list) {
            List<Item<D>> items = factory.makeItems(list, itemLogic);
            adapter.addData(items);
        }

        public BaseAdapter<Item<D>> getAdapter() {
            return adapter;
        }

        /**
         * 设置Item模板用于生产列表
         * {@link Item#newSelf()}
         *
         * @return 一个Item模板
         */
        protected abstract Item<D> setupItemTemplate();
    }

现在又增加了一个ItemLogic类，在BaseListPresenter中有一个ItemFactory工厂类，每一个Item都会持有ItemLogic对象，
我们通过ItemLogic实现Item之间的逻辑交互。说到这里，我们这个开发框架介绍完了。相对于Google提供的有关mvp的Demo，
类的数量就少了很多，这里我们提取了View、Presenter、BaseAdapter、Item，这样我们在使用时，普通页面只需要创建四个类
：Activity、Model、Presenter、Data，而列表界面就比较多了，需要创建六各类：Activity、ListPresenter、
Item、Model、Logic(如果不需要，可以不创建，而且可以重复使用，与Item是解耦和的)、Data;