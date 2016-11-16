package com.qing.lighthttplibrary.ResultCover;

import com.qing.lighthttplibrary.LightHttp;

import org.simpleframework.xml.core.Persister;

import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by dell on 2016/11/2.
 */
public class BaseXmlResultCover implements LightHttp.LightHttpCover{
    private Persister persister;
    public BaseXmlResultCover(Persister persister){
        this.persister=persister;
    }
    @Override
    public Object just(Response mObject, Type type,Object expandValue) throws Exception {
        if(!(type instanceof Class)){
            throw new ArithmeticException("wrong class");
        }
        Class<?> myclass=(Class)type;
        Object read = persister.read(myclass,mObject.body().byteStream());
        return read;
    }
   public static BaseXmlResultCover create(Persister persister){
       return new BaseXmlResultCover(persister);
   }
}
