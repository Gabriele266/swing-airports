package common;

import java.util.List;

public interface Parser<T, InputPart> {
    public T parseOne(InputPart part);

    public List<T> parseMore(List<InputPart> parts);
}
