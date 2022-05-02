package common;

import java.util.List;

public interface Serializer<T, Y>{
    public Y serializeOne(T input);

    public List<Y> serializeMore(List<T> input);
}
