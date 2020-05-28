package com.forestry.bean;

// javax.validation只提供接口，具体的实现类是hibernate-validator提供的，hibernate-validator在springboot中默认引入
import javax.validation.constraints.NotBlank;

public class BoardCert {
    private int id;

    // 所有类似@NotBlank的验证注解都可以@NotBlank(message = "数量不能为空")这样自定义返回message
    // @NotBlank用于String上，代表String不能是null且去除两端空白字符后的长度大于0
    @NotBlank
    private String amount;

    @NotBlank
    private String noticePic;

    @NotBlank
    private String contractPic;

    @NotBlank
    private String declarationPic;

    private int cid;

    private String windows;

    private int status;

    private String refuse_reason;

    private String check_person;

    private String approve_remark;

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

    public String getContractPic() {
        return contractPic;
    }

    public void setContractPic(String contractPic) {
        this.contractPic = contractPic;
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

    public String getCheck_person() {
        return check_person;
    }

    public void setCheck_person(String check_person) {
        this.check_person = check_person;
    }

    public String getApprove_remark() {
        return approve_remark;
    }

    public void setApprove_remark(String approve_remark) {
        this.approve_remark = approve_remark;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
