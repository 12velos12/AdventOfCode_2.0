package Y2021.D5;

import java.util.Objects;

public class Line {
    private Coordinate lineStart;
    private Coordinate lineEnd;

    public Coordinate getLineStart() {
        return lineStart;
    }
    public void setLineStart(Coordinate lineStart) {
        this.lineStart = lineStart;
    }

    public Coordinate getLineEnd() {
        return lineEnd;
    }
    public void setLineEnd(Coordinate lineEnd) {
        this.lineEnd = lineEnd;
    }

    public Line(Coordinate lineStart, Coordinate lineEnd){
        this.lineStart = lineStart;
        this.lineEnd = lineEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(lineStart, line.lineStart) && Objects.equals(lineEnd, line.lineEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineStart, lineEnd);
    }
}
