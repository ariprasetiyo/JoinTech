package arprast.qiyosq.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ari-prasetiyo
 */
@MappedSuperclass
public class ModelNoIdEntity implements Serializable {

    private static final long serialVersionUID = 2432434267482377275L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "created_time", insertable = true, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    //@DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private Date createdTime;//= new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "modified_time", insertable = false, updatable = true,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
    )
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private Date modifiedTime; //= new Date();

    @Column(length = 5)
    private String version = "1.0";

    @Column(name ="is_enable")
    private boolean enable = false;

    @Column(name = "created_by", length = 50, nullable = true)
    private String createdBy = null;

    @Column(name = "modified_by", length = 50, nullable = true)
    private String modifiedBy = null;


    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    @PrePersist
    public void setCreatedTime() {
        this.createdTime = new Date();
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    @PreUpdate
    public void setModifiedTime() {
        this.modifiedTime = new Date();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
