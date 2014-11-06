package edu.umw.cpsc.twitterAlt.controller;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MongoUtil {

	public static BasicDBObject convertToDBObject(Object o) {

		Map<String, Object> objectAsMap = new HashMap<String, Object>();

		try {
			BeanInfo info = Introspector.getBeanInfo(o.getClass());
			for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
				Method reader = pd.getReadMethod();
				if (reader != null)
					objectAsMap.put(pd.getName(), reader.invoke(o));
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return new BasicDBObject(objectAsMap);
	}
	
	public static Object convertFromDBObject(DBObject dbo){
		
		return null;
	}
}
