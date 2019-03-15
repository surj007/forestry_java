package com.forestry.bean;

import javax.validation.constraints.NotBlank;

public class WoodCert {
    private int id;

    @NotBlank
    private String amount;

    @NotBlank
    private String noticePic;

    @NotBlank
    private String ladingPic;

    @NotBlank
    private String declarationPic;

    private int cid;

    private String windows;

    private int status;

    private String refuse_reason;

    private String create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNoticePic() {
        return noticePic;
    }

    public void setNoticePic(String noticePic) {
        this.noticePic = noticePic;
    }

    public String getLadingPic() {
        return ladingPic;
    }

    public void setLadingPic(String ladingPic) {
        this.ladingPic = ladingPic;
    }

    public String getDeclarationPic() {
        return declarationPic;
    }

    public void setDeclarationPic(String declarationPic) {
        this.declarationPic = declarationPic;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getWindows() {
        return windows;
    }

    public void setWindows(String windows) {
        this.windows = windows;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRefuse_reason() {
        return refuse_reason;
    }

    public void setRefuse_reason(String refuse_reason) {
        this.refuse_reason = refuse_reason;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
