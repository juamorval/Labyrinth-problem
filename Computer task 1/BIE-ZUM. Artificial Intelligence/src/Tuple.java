import java.util.Objects;
import java.util.PriorityQueue;

public class Tuple implements Comparable<Tuple>{

    Integer n1;
    Integer n2;

    public Tuple(Integer n1, Integer n2) {
        this.n1=n1;
        this.n2=n2;
    }

    public Integer getN1() {
        return n1;
    }

    public void setN1(Integer n1) {
        this.n1 = n1;
    }

    public Integer getN2() {
        return n2;
    }

    public void setN2(Integer n2) {
        this.n2 = n2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple tuple = (Tuple) o;
        return Objects.equals(n1, tuple.n1) && Objects.equals(n2, tuple.n2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n1, n2);
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "n1=" + n1 +
                ", n2=" + n2 +
                '}';
    }

    @Override
    public int compareTo(Tuple tp) {
        int res = tp.getN1();
        if(res<this.n1) res = 1;
        else if(res>this.n1) res=-1;
        else res =0;

        return res;
    }
}
