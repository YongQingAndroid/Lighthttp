package chart.qing.com.lightui.recyclerview;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
/**
 * This adapter is based on native Android RecyclerView refactoring
 *Support multi-level list of the parent list and child list independent display, support list, form
 *And waterfall layout, to support the dynamic calculation of sub list item size
 * My open source community account is fengling136
 * Welcome attention
 * Thanks for your use
 * the power by ZYQ
 */
public abstract class ExpandRecyclerviewAdapter<H extends RecyclerView.ViewHolder,V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final int TITLE=0,COUNT=1;
    private List<Boolean> isTitle=new ArrayList<>();
    private List<String> isTrueCount=new ArrayList<>();
    private List<Boolean> weight=new ArrayList<>();
    public ExpandRecyclerviewAdapter(){
        registerAdapterDataObserver(new SectionDataObserver());
    }
    protected void inItAdapter(){
        getTrueCount();
    }
    protected abstract int getGroupCont();
    protected abstract int getChildeLimit();
    protected abstract int getChildeCout(int posinon);
    public boolean isTitleCount(int posion){
        return isTitle.get(posion);
    }
    public boolean isCountWeight(int posion){
        return weight.get(posion);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TITLE){
             return onCreateGroupHolder(parent, viewType);
        }else{
            return onCreateChildHolder(parent, viewType);
        }
    }
    protected abstract H onCreateGroupHolder(ViewGroup parent, int viewType);
    protected abstract V onCreateChildHolder(ViewGroup parent, int viewType);
    private void getTrueCount(){
        isTitle.clear();
        isTrueCount.clear();
        weight.clear();
        int i=0;
        int Count=getGroupCont();
        while(i<Count){
            isTitle.add(true);
            isTrueCount.add(i+",");
            weight.add(true);
            int childSzie=getChildeCout(i);
            boolean less=childSzie<getChildeLimit();
            for(int m=0;m<childSzie;m++){
                isTitle.add(false);
                weight.add(less);
                isTrueCount.add(i+","+m);
           }
            i++;
        }
    }
    @Override
    public int getItemViewType(int position) {
      if(isTitle.get(position)){
          return TITLE;
      }else{
          return COUNT;
      }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String split=isTrueCount.get(position);
        String[] items=split.split(",");
        if(getItemViewType(position)==TITLE){
            int posion=Integer.valueOf(items[0]);
            onBindGroupHolder((H)holder,posion);
        }else{
            onBindChildHolder((V)holder,Integer.valueOf(items[0]),Integer.valueOf(items[1]));
        }
    }
    protected  void NotifiedGroupHolder(H holder,int position,Object object){};
    protected  abstract void onBindGroupHolder(H holder,int position);
    protected  abstract void onBindChildHolder(V holder,int Gosition,int Cpoint);
    @Override
    public int getItemCount() {
        return isTitle.size();
    }
    class SectionDataObserver extends RecyclerView.AdapterDataObserver{
        @Override
        public void onChanged() {
            getTrueCount();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            getTrueCount();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            getTrueCount();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            getTrueCount();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            getTrueCount();
        }
    }
}
