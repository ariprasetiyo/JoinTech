package arprast.qiyosq.model;

import java.util.Date;

public class DaftarPelakuModel {

    Long id;
    String nama;
    String refIdKomplotan;
    String address;
    Date birthDay;
    String noKtp;
    String noLaporan;
    String note;
    String modus;
    String pasal;
    String status;
    String lokasiLapas;
    String lokasiTkp;
    Date tanggalTkp;

    String photoProfilePath;
    String pdfFilePath;

    public DaftarPelakuModel(Long id, String nama, String refIdKomplotan, String address, Date birthDay, String noKtp, String noLaporan, String note,
                             String modus, String pasal, String status, String lokasiLapas, String lokasiTkp, Date tanggalTkp, String photoProfilePath, String pdfFilePath) {
        this.id = id;
        this.nama = nama;
        this.refIdKomplotan = refIdKomplotan;
        this.address = address;
        this.birthDay = birthDay;
        this.noKtp = noKtp;
        this.noLaporan = noLaporan;
        this.note = note;
        this.modus = modus;
        this.pasal = pasal;
        this.status = status;
        this.lokasiLapas = lokasiLapas;
        this.lokasiTkp = lokasiTkp;
        this.tanggalTkp = tanggalTkp;
        this.photoProfilePath = photoProfilePath;
        this.pdfFilePath = pdfFilePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getNoLaporan() {
        return noLaporan;
    }

    public void setNoLaporan(String noLaporan) {
        this.noLaporan = noLaporan;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLokasiLapas() {
        return lokasiLapas;
    }

    public void setLokasiLapas(String lokasiLapas) {
        this.lokasiLapas = lokasiLapas;
    }

    public String getLokasiTkp() {
        return lokasiTkp;
    }

    public void setLokasiTkp(String lokasiTkp) {
        this.lokasiTkp = lokasiTkp;
    }

    public Date getTanggalTkp() {
        return tanggalTkp;
    }

    public void setTanggalTkp(Date tanggalTkp) {
        this.tanggalTkp = tanggalTkp;
    }

    public String getRefIdKomplotan() {
        return refIdKomplotan;
    }

    public void setRefIdKomplotan(String refIdKomplotan) {
        this.refIdKomplotan = refIdKomplotan;
    }

    public String getPhotoProfilePath() {
        return photoProfilePath;
    }

    public void setPhotoProfilePath(String photoProfilePath) {
        this.photoProfilePath = photoProfilePath;
    }

    public String getPdfFilePath() {
        return pdfFilePath;
    }

    public void setPdfFilePath(String pdfFilePath) {
        this.pdfFilePath = pdfFilePath;
    }
}
