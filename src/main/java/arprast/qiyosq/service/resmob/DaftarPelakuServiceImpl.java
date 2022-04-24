package arprast.qiyosq.service.resmob;

import arprast.qiyosq.dao.AuthorizationDao;
import arprast.qiyosq.dao.AuthorizationDaoImpl;
import arprast.qiyosq.dao.MenusDao;
import arprast.qiyosq.dao.DaoImpl;
import arprast.qiyosq.dto.AuthorizationDto;
import arprast.qiyosq.dto.DaftarPelakuDto;
import arprast.qiyosq.dto.PersonalIdentity;
import arprast.qiyosq.dto.RequestData;
import arprast.qiyosq.model.AuthorizationModel;
import arprast.qiyosq.model.DaftarPelakuModel;
import arprast.qiyosq.model.MenusModel;
import arprast.qiyosq.ref.ActionType;
import arprast.qiyosq.ref.StatusCode;
import arprast.qiyosq.util.LogUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class DaftarPelakuServiceImpl implements DaftarPelakuService {

    private static final Logger logger = LoggerFactory.getLogger(DaftarPelakuServiceImpl.class);
    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static final TypeReference<List<AuthorizationDto>> typeRef = new TypeReference<List<AuthorizationDto>>() {
    };
    private static final ObjectWriter authorizationWriter = jsonMapper.writerFor(typeRef);

    @Autowired
    AuthorizationDao authorizationDao;

    @Autowired
    AuthorizationDaoImpl authorizationDaoImpl;

    @Autowired
    MenusDao dsSysMenuDao;

    @Autowired
    DaoImpl daoImpl;

    /**
     * This method is responsible to map list of objects to a given class.
     *
     * @param type    Mapping class
     * @param records list of objects that are mapping to instance of the given class
     * @return a List of mapped objects
     */
    public static List mapObject(Class type, List<Object[]> records) {
        List result = new LinkedList();
        for (Object[] record : records) {
            List<Class> tupleTypes = new ArrayList();
            for (Object field : record) {
                // if a field contains null value assign empty string, because in my DTO class
                // types of all constructor parameters are String.
                if (field == null) {
                    field = "";
                }
                tupleTypes.add(field.getClass());
            }
            Constructor ctor;
            try {
                Class[] classes = new Class[record.length];
                ctor = type.getConstructor(
                        tupleTypes.toArray(classes)
                );
                result.add(ctor.newInstance(record));
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static <T> T map1(Class<T> type, Object[] tuple) {
        List<Class<?>> tupleTypes = new ArrayList<>();
        for (Object field : tuple) {
            tupleTypes.add(field.getClass());
        }
        try {
            Constructor<T> ctor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]));
            return ctor.newInstance(tuple);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> ma2(Class<T> type, List<Object[]> records) {
        List<T> result = new LinkedList<>();
        for (Object[] record : records) {
            result.add(map1(type, record));
        }
        return result;
    }

    public static <T> T map(Class<T> type, Object[] tuple) {
        List<Class<?>> tupleTypes = new ArrayList<>();
        for (Object field : tuple) {

            if (field == null) {
                tupleTypes.add(null);
                continue;
            }

            tupleTypes.add(field.getClass());
        }
        try {
            Constructor<T> ctor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]));
            return ctor.newInstance(tuple);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> map(Class<T> type, List<Object[]> records) {
        List<T> result = new LinkedList<>();
        for (Object[] record : records) {
            result.add(map(type, record));
        }
        return result;
    }

    public int updateAuthorization(Long id, AuthorizationDto authorizationDto) {
        LogUtil.logDebugType(logger, true, ActionType.VIEW, "{}", authorizationDto.toString());
        return authorizationDao.updateAuthorization(id, authorizationDto.isInsert(), authorizationDto.isUpdate(),
                authorizationDto.isDelete(), authorizationDto.isDisabled());
    }

    public void deleteAuthorization(Long id) {
        LogUtil.logDebugType(logger, true, ActionType.DELETE, "Delete menu {}", id);
        authorizationDao.delete(id);
    }

    public void viewSysRoles(Long idRoles) {
        // model.addAttribute("selectRoleValue", idRoles);
        // List<RolesModel> listAllSysRole = (List<RolesModel>)
        // dsSysRoles.findAll();
        // model.addAttribute("listRoles", listAllSysRole);
    }

    public List<MenusModel> listMenu() {
        return (List<MenusModel>) dsSysMenuDao.findAll();
    }

    public List<AuthorizationModel> listMenuAuthorization(Long idRole) {
        return authorizationDao.getForScreenMenu(idRole);
    }

    public AuthorizationDto getAuthorizationList(int offset, int limit, String keySearch) {
        authorizationDao.findAll();
        return null;
    }

    /**
     * @deprecated
     */
    public String getAuthorizationJson(Long idRole) {
        RequestData requestData = new RequestData();
        requestData.setId(idRole);
        try {
            return authorizationWriter.writeValueAsString(getDaftarPelakuList(requestData));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DaftarPelakuDto> getDaftarPelakuList(RequestData requestData) {

        logger.debug("{}", requestData.toString());
        if (requestData == null || requestData.getId() == null) {
            return null;
        }

        List<DaftarPelakuModel> getDaftarPelakuList = daoImpl.getDaftarPelaku();
        List<DaftarPelakuDto> daftarPelakuList = new ArrayList<>();
        for (DaftarPelakuModel daftarPelaku : getDaftarPelakuList) {
            daftarPelakuList.add(mapToDaftarPelakuDto(daftarPelaku));
        }
        return daftarPelakuList;
    }

    private DaftarPelakuDto mapToDaftarPelakuDto(final DaftarPelakuModel daftarPelakuModel) {
        DaftarPelakuDto daftarPelakuDto = new DaftarPelakuDto();

        PersonalIdentity personalIdentity = new PersonalIdentity();
        personalIdentity.setAddress(daftarPelakuModel.getAddress());
        personalIdentity.setBornDate(daftarPelakuModel.getBirthDay());
        personalIdentity.setFingerprint(null);
        personalIdentity.setIdPelaku(daftarPelakuModel.getId().toString());
        personalIdentity.setName(daftarPelakuModel.getNama());
        personalIdentity.setNoKtp(daftarPelakuModel.getNoKtp());

        daftarPelakuDto.setPersonalIdentity(personalIdentity);
        daftarPelakuDto.setModus(daftarPelakuModel.getModus());
        daftarPelakuDto.setNoLaporan(daftarPelakuModel.getNoLaporan());
        daftarPelakuDto.setNote(daftarPelakuModel.getNote());
        daftarPelakuDto.setPasal(daftarPelakuModel.getPasal());
        daftarPelakuDto.setTkp(daftarPelakuModel.getLokasiTkp());
        daftarPelakuDto.setPathFileBAP(daftarPelakuModel.getPdfFilePath());
        daftarPelakuDto.setPathFilename(daftarPelakuModel.getPhotoProfilePath());
        return daftarPelakuDto;
    }


    public DaftarPelakuDto saveDaftarPelaku(DaftarPelakuDto daftarPelakuDto) {

        LogUtil.logDebugType(logger, true, ActionType.SAVE, "{}", daftarPelakuDto.toString());
        if (daftarPelakuDto.getId() == null) {
            daftarPelakuDto.setStatusCode(StatusCode.SAVE_ERROR);
            daftarPelakuDto.setMessage(StatusCode.NULL_VALUE.desc);
            return daftarPelakuDto;
        }

//		AuthorizationModel authorizationModel = authorizationMapper.asAuthorizationModel(daftarPelakuDto);
//		authorizationModel = authorizationDao.save(authorizationModel);
//
//		if (authorizationModel == null || authorizationModel.getId() == null) {
//            daftarPelakuDto.setStatusCode(StatusCode.SAVE_ERROR);
//            daftarPelakuDto.setMessage(StatusCode.SAVE_AUTHORIZATION_ERROR.stringValue);
//			return daftarPelakuDto;
//		}

//        daftarPelakuDto.setId(authorizationModel.getId());
        daftarPelakuDto.setStatusCode(StatusCode.SAVE_SUCCEED);
        daftarPelakuDto.setMessage(StatusCode.SAVE_SUCCEED.desc);

        return daftarPelakuDto;
    }

    @Override
    public long countAuthorization(long roleId) {
        return authorizationDao.countByRole(roleId);
    }

    @Override
    public List<String> getButtonActionAcl(final String username, final String menuName, final String roleName) {
        return authorizationDaoImpl.buttonAcionACL(username, menuName, roleName);
    }
}
