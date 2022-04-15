package arprast.qiyosq.dto;

import arprast.qiyosq.ref.StatusPelaku;

import java.util.Date;
import java.util.List;

public class PersonalIdentity {

    private String idPelaku;
    private String name;
    private String fingerprint;
    private String address;
    private Date bornDate;
    private String noKtp;
    private String noSim;
    private String domisili;
    private String note;
    private List<String> noHp;
    private List<String> noHpRef;
    private StatusPelaku statusPelaku;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIdPelaku() {
        return idPelaku;
    }

    public void setIdPelaku(String idPelaku) {
        this.idPelaku = idPelaku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getNoSim() {
        return noSim;
    }

    public void setNoSim(String noSim) {
        this.noSim = noSim;
    }

    public String getDomisili() {
        return domisili;
    }

    public void setDomisili(String domisili) {
        this.domisili = domisili;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<String> getNoHp() {
        return noHp;
    }

    public void setNoHp(List<String> noHp) {
        this.noHp = noHp;
    }

    public List<String> getNoHpRef() {
        return noHpRef;
    }

    public void setNoHpRef(List<String> noHpRef) {
        this.noHpRef = noHpRef;
    }

    public StatusPelaku getStatusPelaku() {
        return statusPelaku;
    }

    public void setStatusPelaku(StatusPelaku statusPelaku) {
        this.statusPelaku = statusPelaku;
    }
}
