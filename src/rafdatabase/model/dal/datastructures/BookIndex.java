package rafdatabase.model.dal.datastructures;

import java.io.Serializable;

/**
 * Created by j2arr on 8/24/2016.
 */
public class BookIndex implements Comparable<BookIndex>, Serializable {

    private int id;
    private long position;

    public BookIndex(int id, long position) {
        this(id);
        this.position = position;
    }

    public BookIndex(int id) {
        this.id = id;
        this.position = -1;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public int compareTo(BookIndex bi) {
        if (bi.id == this.id)
            return 0;
        return this.id - bi.id;
    }

    @Override
    public String toString() {
        return "{" + this.id + " : " + position + "}";
    }
}
