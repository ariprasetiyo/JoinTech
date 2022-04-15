/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * @author ari-prasetiyo
 * @category These attribute fasterXML for set JSON message in class
 * authorization method authorizationList
 */
@JsonInclude(NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)
public class DaftarPelakuDto extends Dto {

    private static final long serialVersionUID = -5525131693784409145L;

    private PersonalIdentity personalIdentity;
    private String noLaporan;
    private String modus;
    private String pasal;
    private String tkp;
    private String pathFilename;
    private String pathFileBAP;
    private String note;
    private String lokasiLapas;
    private Date tanggalTkp;
    private List<PersonalIdentity> anggotaKomplotan;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public PersonalIdentity getPersonalIdentity() {
        return personalIdentity;
    }

    public void setPersonalIdentity(PersonalIdentity personalIdentity) {
        this.personalIdentity = personalIdentity;
    }

    public String getNoLaporan() {
        return noLaporan;
    }

    public void setNoLaporan(String noLaporan) {
        this.noLaporan = noLaporan;
    }

    public String getModus() {
        return modus;
    }

    public void setModus(String modus) {
        this.modus = modus;
    }

    public String getPasal() {
        return pasal;
    }

    public void setPasal(String pasal) {
        this.pasal = pasal;
    }

    public String getTkp() {
        return tkp;
    }

    public void setTkp(String tkp) {
        this.tkp = tkp;
    }

    public String getPathFilename() {
        return pathFilename;
    }

    public void setPathFilename(String pathFilename) {
        this.pathFilename = pathFilename;
    }

    public String getPathFileBAP() {
        return pathFileBAP;
    }

    public void setPathFileBAP(String pathFileBAP) {
        this.pathFileBAP = pathFileBAP;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLokasiLapas() {
        return lokasiLapas;
    }

    public void setLokasiLapas(String lokasiLapas) {
        this.lokasiLapas = lokasiLapas;
    }

    public Date getTanggalTkp() {
        return tanggalTkp;
    }

    public void setTanggalTkp(Date tanggalTkp) {
        this.tanggalTkp = tanggalTkp;
    }

    public List<PersonalIdentity> getAnggotaKomplotan() {
        return anggotaKomplotan;
    }

    public void setAnggotaKomplotan(List<PersonalIdentity> anggotaKomplotan) {
        this.anggotaKomplotan = anggotaKomplotan;
    }
}
