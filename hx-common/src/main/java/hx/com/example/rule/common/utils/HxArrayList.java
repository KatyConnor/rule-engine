package hx.com.example.rule.common.utils;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 *  更高效的list集合
 * @Author mingliang
 * @Date 2018-07-09 10:20
 */
public final class HxArrayList<T> implements List<T>,Cloneable, RandomAccess, Serializable {

    private static final long serialVersionUID = -4598088075242913858L;

    private final Class<?> clazz; //
    private T[] elementData;  // 数组，存储数据
    private int size; // 集合大小

    public HxArrayList(Class<?> clazz) {
        this.clazz = clazz;
        this.elementData = (T[])Array.newInstance(clazz, 32);
    }

    public HxArrayList(Class<?> clazz, int capacity) {
        this.clazz = clazz;
        this.elementData = (T[])Array.newInstance(clazz, capacity);
    }

    /**
     * 添加一个元素到集合
     * @param element
     * @return
     */
    @Override
    public boolean add(T element) {
        if (this.size < this.elementData.length) {
            this.elementData[this.size++] = element;
        } else {
            expand();
            this.elementData[this.size++] = element;
        }
        return true;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index;

            public boolean hasNext() {
                return this.index < HxArrayList.this.size;
            }

            public T next() {
                if (this.index < HxArrayList.this.size) {
                    return HxArrayList.this.elementData[this.index++];
                } else {
                    throw new NoSuchElementException("No more elements in FastList");
                }
            }
        };
    }

    @NotNull
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public <T1> T1[] toArray(@NotNull T1[] a) {
        throw new UnsupportedOperationException();
    }

    /**
     * 删除指定的某一个元素
     * @param o
     * @return
     */
    @Override
    public boolean remove(Object o) {
        for(int index = this.size - 1; index >= 0; --index) {
            if (o == this.elementData[index]) {
                int numMoved = this.size - index - 1;
                if (numMoved > 0) {
                    // 将数组中当前位置以后的数据全部往前移动一位，最后一位清空
                    System.arraycopy(this.elementData, index + 1, this.elementData, index, numMoved);
                }

                this.elementData[--this.size] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        for(int i = 0; i < this.size; ++i) {
            this.elementData[i] = null;
        }
        this.size = 0;
    }

    @Override
    public T get(int index) {
        return this.elementData[index];
    }

    @Override
    public T set(int index, T element) {
        // 验证下index是否越界,如果超了需要扩容
        if (index > this.elementData.length){
            expand();
        }
        T old = this.elementData[index];
        this.elementData[index] = element;
        return old;
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        if (this.size == 0) {
            return null;
        } else {
            T old = this.elementData[index];
            int numMoved = this.size - index - 1;
            if (numMoved > 0) {
                System.arraycopy(this.elementData, index + 1, this.elementData, index, numMoved);
            }
            this.elementData[--this.size] = null;
            return old;
        }
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    /**
     * 删除最后一个元素，返回被删除的元素
     * @return
     */
    public T removeLast() {
        T element = this.elementData[--this.size];
        this.elementData[this.size] = null;
        return element;
    }

    private void expand(){
        int oldCapacity = this.elementData.length;
        int newCapacity = oldCapacity << 1;
        T[] newElementData = (T[])Array.newInstance(this.clazz, newCapacity);
        System.arraycopy(this.elementData, 0, newElementData, 0, oldCapacity);
        this.elementData = newElementData;
    }

    public Object clone() {
        throw new UnsupportedOperationException();
    }

    public void forEach(Consumer<? super T> action) {
        throw new UnsupportedOperationException();
    }

    public Spliterator<T> spliterator() {
        throw new UnsupportedOperationException();
    }

    public boolean removeIf(Predicate<? super T> filter) {
        throw new UnsupportedOperationException();
    }

    public void replaceAll(UnaryOperator<T> operator) {
        throw new UnsupportedOperationException();
    }

    public void sort(Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }
}
