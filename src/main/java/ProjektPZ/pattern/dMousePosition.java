package ProjektPZ.pattern;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dmouseposition", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class dMousePosition implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "weapon_id")
    private Pattern pattern;
    @Column
    private int dx;
    @Column
    private int dy;
    @Column
    private long dt;

    public dMousePosition() {

    }

    public dMousePosition(int dx, int dy, long dt, Pattern pattern) {
        this.dx = dx;
        this.dy = dy;
        this.dt = dt;
        this.pattern = pattern;
    }

    public dMousePosition(int dx, int dy, long dt) {
        this.dx = dx;
        this.dy = dy;
        this.dt = dt;
    }


    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public long getDt() {
        return dt;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return "dMousePosition{" +
                "dx=" + dx +
                ", dy=" + dy +
                ", dt=" + dt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dMousePosition that = (dMousePosition) o;
        return dx == that.dx &&
                dy == that.dy;
    }

    public dMousePosition add(dMousePosition point) {
        return new dMousePosition(this.dx + point.getDx(), this.dy + point.getDy(), this.dt + point.getDt());
    }

}
