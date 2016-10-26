package com.qing.lighthttplibrary;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Type;
import okhttp3.Response;

/**
 * This network framework is based on the production of okhttp
 * Network framework free open source, and the final right to interpret the author.
 * The author will go regularly to update the business code, but no obligation to notify the user.
 * My open source community account is fengling136
 * Welcome attention
 * Thanks for your use
 * the power by ZYQ
 */
public  class LightHttp {
    private Builder builder;
    public static String TAG="lighthttp";
    private LightHttp( Builder builder){
        this.builder=builder;
    }
    /**
     *Dynamic proxy non object class is achieved by using the default implementation method of dynamic proxy class.
     */
    public <T>T  creat (Class<T> myclass){
        InvocationHandler handler=new PraseJsonHandler(builder);
        Object ob=java.lang.reflect.Proxy.newProxyInstance(myclass.getClassLoader(), new Class<?>[] { myclass}, handler);
    return (T) ob;
}
    public static final class Builder{
        private String msrc;
        private LightHttpCover cover;
        private LightAdapterCover adapterCover;
        public Builder src(String src){
            this.msrc=src;
            return this;
        }

        public LightAdapterCover getAdapterCover() {
            return adapterCover;
        }

        public Builder addAdapterCover(LightAdapterCover adapterCover) {
            this.adapterCover = adapterCover;
            return  this;
        }
        public Builder addResultCover(LightHttpCover cover){
            this.cover=cover;
            return this;
        }
        public LightHttp build(){
            return new LightHttp(this);
        }
        protected LightHttpCover getCover() {
            return cover;
        }

        protected String getMsrc() {
            return msrc;
        }

    }
   public interface LightHttpCover <T>{
      T just(Response mObject, Type type) throws Exception;
   }
   public interface LightAdapterCover<M>{
       M adapter(Type type, LightHttpRequest mRequest);
   }
}
