package arprast.qiyosq.util;

import org.modelmapper.ModelMapper;

public class ModelMapperUtil {

	private static final ModelMapper modelMapper = new ModelMapper();

	/**
	 * {@code e.g UserModel userModel = ModelMapperUtil.map(user, UserModel.class); }
	 * 
	 * @since Not recommended use this related about performance. Recommended
	 *        use SELMA mapping.
	 * @param <B>
	 *            destination type
	 * @param source
	 *            object to map from
	 * @param destinationType
	 *            type to map to
	 * @return fully mapped instance of {@code destinationType}
	 * @author ARI-PRASETIYO
	 */
	public static <B> B map(Object source, Class<B> destinationType) {
		return modelMapper.map(source, destinationType);
	}
}
