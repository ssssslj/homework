package util;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Date;

@SuppressWarnings("rawtypes")
public class ComparatorUtil implements Comparator{
    //Ҫ�����������
    private String name;
    //�Ƿ�����
    private boolean falg = true;
    /**
     * @param name Ҫ�����������,����ǻ������͵���������nullֵ
     * @param falg false/true : ����/����
     */
    public ComparatorUtil(String name, boolean falg) {
        super();
        this.name = name;
        this.falg = falg;
    }

    @Override
    public int compare(Object o1, Object o2) {
        int result = 0;
        //���Ϊ��|�������Ͳ���ȣ��������д���
        if(o1 == null || o2 == null || !o1.getClass().getName().equals(o2.getClass().getName())){
            return result;
        }
        //7��������͵Ĵ���(boolean����.�Ұ�Collections.reverse()/Collections.sort()����Ҳ����) 
        if(isBaseType(o1)){
            //�Ƚ�
            return baseTypeOpt(o1,o2);
        }
        try {
            Field f1 = o1.getClass().getDeclaredField(name);
            Field f2 = o2.getClass().getDeclaredField(name);
            //����private�ɶ�
            f1.setAccessible(true);
            f2.setAccessible(true);
            result = baseTypeOpt(f1.get(o1),f2.get(o2));
        }  catch (Exception e) { //�쳣���ô�����,���û�ж�Ӧ������,�ǾͲ�������.(�ֶ�����)
            e.printStackTrace();
        }
        return result;
    }

    private int baseTypeOpt(Object o1, Object o2) {
        int result = 0;
        if(o1 instanceof String){
            result = o1.toString().compareTo(o2.toString());
        }else if(o1 instanceof Integer){
            result = ((Integer)o1) - ((Integer)o2);
        }else if(o1 instanceof Double){
            if(((Double)o1 - (Double)o2) > 0){
                result = 1;
            }else if(((Double)o1 - (Double)o2) < 0){
                result = -1;
            }
        }else if(o1 instanceof Float){
            if(((Float)o1 - (Float)o2) > 0){
                result = 1;
            }else if(((Float)o1 - (Float)o2) < 0){
                result = -1;
            }
        }else if(o1 instanceof Character){
            result = ((Character)o1).compareTo(((Character)o2));
        }else if(o1 instanceof Short){
            result = ((Short)o1) - ((Short)o2);
        }else if(o1 instanceof Long){
            if(((Long)o1 - (Long)o2) > 0){
                result = 1;
            }else if(((Long)o1 - (Long)o2) < 0){
                result = -1;
            }
        }else if (o1 instanceof Date) {
			result = ((Date)o1).compareTo((Date)o2);
		}
        //����
        if(isFalg()){
            result = -result;
        }
        return result;
    }

    private boolean isBaseType(Object o) {
        if((o instanceof String) || (o instanceof Integer)
                || (o instanceof Double) || (o instanceof Float) 
                || (o instanceof Character) || (o instanceof Short)
                || (o instanceof Long)){
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFalg() {
        return falg;
    }

    public void setFalg(boolean falg) {
        this.falg = falg;
    }
    
}