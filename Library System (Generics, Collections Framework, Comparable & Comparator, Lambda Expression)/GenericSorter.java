import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
public class GenericSorter {
    public static <T> void sort(List<T> list, Comparator<T> comparator) {
        list.sort(comparator);
    }
    public static <T, U extends Comparable<? super U>> Comparator<T> getComparator(Function<T, U> keyExtractor) {
        return Comparator.comparing(keyExtractor);
    }
}
