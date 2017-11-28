import java.util.Set;
import java.util.Iterator;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * SquareSet class that is a set of Instantiated Squares
 * @author gmaroun3
 *
 * @version 1.0
 */
public class SquareSet implements Set<Square> {
    private Square[] arr = new Square[64];
    private int backingSize = 0; //pointer

    /**
     * Creates a SquareSet
     */
    public SquareSet() {
    }

    /**
     * Creates a square
     * @param c of possible squares
     */
    public SquareSet(Collection<Square> c) {
        arr = new Square[c.size()];
        addAll(c);
    }

    private class SquareIterator implements Iterator<Square> {
        private int ind = 0;

        /**
         * @Override the hasNext() method
         * @return the value of boolean
         */
        public boolean hasNext() {
            return ind < backingSize && arr[ind] != null;
        }

        /**
         * @Override the next() method
         * @return the next Square
         */
        public Square next() throws NoSuchElementException {
            if (hasNext()) {
                return arr[ind++];
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    /**
     * @Override the iterator() method
     * @return the Iterator
     */
    public Iterator<Square> iterator() {
        SquareIterator it = new SquareIterator();
        return it;
    }

    /**
     * @Override the add() method
     * @param square of possible square
     * @return the next Square
     */
    public boolean add(Square square) {
        if (square == null) {
            throw new NullPointerException();
        }
        if (square.getFile() < 'a' || square.getFile() > 'h'
            || square.getRank() < '1' || square.getRank() > '8'
            || square.getName().length() > 2) {
            throw new InvalidSquareException("" + square.getFile()
                + square.getRank());
        }
        if (this.contains(square)) {
            return false;
        } else if (arr.length == 0) {
            Square[] arr2 = new Square[1];
            arr2[0] = square;
            arr = arr2;
            backingSize++;
            return true;
        } else {
            if (backingSize < arr.length) {
                arr[backingSize] = square;
            } else {
                Square[] arr2 = new Square[arr.length * 2];
                for (int j = 0; j < arr.length; j++) {
                    arr2[j] = arr[j];
                }
                arr2[backingSize] = square;
                arr = arr2;
            }
            backingSize++;
            return true;
        }
    }

    /**
     * @Override the addAll() method
     * @param squares of possible squares
     * @return the next Square
     */
    public boolean addAll(Collection<? extends Square> squares) {
        boolean change = false;
        for (Object o : squares) {
            if (!isValid((Square) o)) {
                throw new InvalidSquareException("" + ((Square) o).getFile()
                + ((Square) o).getRank());
            }
        }
        for (Object o : squares) {
            if (add((Square) o)) {
                change = true;
            }
        }
        return change;
    }

    /**
     * @Override the isValid() method
     * @param square of possible square
     * @return the valid boolean
     */
    public boolean isValid(Square square) {
        if (square.getFile() < 'a' || square.getFile() > 'h'
            || square.getRank() < '1' || square.getRank() > '8'
            || square == null) {
            return false;
        }
        return true;
    }

    /**
     * @Override the contains() method
     * @param o of possible object
     * @return the contains boolean
     */
    public boolean contains(Object o) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                if (arr[i].equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @Override the containsAll() method
     * @param squares of possible squares
     * @return the boolean if containsAll
     */
    public boolean containsAll(Collection<?> squares) {
        for (Object o : squares) {
            if (!this.contains(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @Override the isValid() method
     * @param square of possible object
     * @return the remove boolean
     */
    public boolean remove(Object square) {
        int location = 0;
        boolean exists = this.contains(square);
        if (!exists) {
            return exists;
        }
        Square[] arr2 = new Square[arr.length];
        for (int i = 0; i < backingSize; i++) {
            if (arr[i].equals(square)) {
                location = i;
            }
        }
        for (int j = 0; j < location; j++) {
            arr2[j] = arr[j];
        }
        for (int x = location; x < arr.length; x++) {
            if (x == location) {
                arr2[x] = null;
            } else {
                arr2[x - 1] = arr[x];
            }
        }
        backingSize--;
        arr = arr2;
        return exists;
    }

    /**
     * @Override the removeAll() method
     * @param c of possible squares
     * @return the removeAll boolean
     */
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * @Override the retainAll() method
     * @param c of possible squares
     * @return the retainAll boolean
     */
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * @Override the clear() method
     */
    public void clear() {
        throw new UnsupportedOperationException();
    }

    /**
     * @Override the equals() method
     * @param o of possible object
     * @return the valid boolean
     */
    public boolean equals(Object o) {
        if (this == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (o instanceof Set) {
            return ((Set) o).size() == this.size()
                && this.containsAll((Set) o);
        }
        return false;
    }

    /**
     * @Override the hashCode() method
     * @return the hashcode int
     */
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < backingSize; i++) {
            hash = hash + arr[i].hashCode();
        }
        return hash;
    }

    /**
     * @Override the isEmpty() method
     * @return the isEmpty boolean
     */
    public boolean isEmpty() {
        if (backingSize == 0) {
            return true;
        }
        return false;
    }

    /**
     * @Override the size() method
     * @return the size integer
     */
    public int size() {
        return backingSize;
    }

    /**
     * @Override the isValid() method
     * @return the Object array
     */
    public Object[] toArray() {
        Object[] newArr = new Square[backingSize];
        if (backingSize == 0) {
            return newArr;
        }
        for (int i = 0; i < backingSize; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    /**
     * @Override the toArray() method
     * @param  <T> object
     * @param a of possible squares
     * @return the arbitrary array type
     */
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException();
        } else if (a.getClass().isAssignableFrom(arr.getClass()) == false) {
            throw new ArrayStoreException();
        }
        if (a.length < size()) {
            a = (T[]) new Object[size()];
        }
        for (int i = 0; i < size(); i++) {
            a[i] = (T) arr[i];
        }
        if (a.length > size()) {
            a[size()] = null;
        }
        return a;
    }
}