package zhang.algorithm.modelUtil.Array;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/7/9
 * Time: 下午3:24
 * To change this template use File | Settings | File Templates.
 */
public class CollectionsTool<T> {
    /**
     *
     * @param array
     * @return
     */
    public Set<T> setFactory(T[] array) {
        Set<T> set = new HashSet<T>();
        for (T t : array) {
            set.add(t);
        }
        return set;
    }

    public void reverse(T[] array){
        reverse(array, 0, array.length);
    }

    public void reverse(T[] array, int start, int end){
        while(start < end){
            swap(array, start++, end--);
        }
    }

    public void swap(T[] array, int left, int right){
        T temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void main(String[] args) {
        //set 使用范例
        CollectionsTool<String> collectionsTool = new CollectionsTool<String>();
        String[] strs = {"hot", "dog", "dot"};
        Set<String> wordList = collectionsTool.setFactory(strs);
    }
}
