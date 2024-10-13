package top.arturkus.insert.helpers;

import java.util.Date;
import java.util.Objects;

public record DefaultResponse(Boolean success, String message, Date timestamp) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultResponse that = (DefaultResponse) o;
        return Objects.equals(message, that.message) && Objects.equals(success, that.success);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, message);
    }
}