package lotto.util.convertor;

import java.util.List;

public interface InputConvertor<T> {

	List<T> covertToList(final String str);
	T covert(final String str);

}
