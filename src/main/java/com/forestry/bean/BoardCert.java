package com.forestry.bean;

import javax.validation.constraints.NotBlank;

public class BoardCert {
    @NotBlank
    private String amount;

    @NotBlank
    private String noticePic;

    @NotBlank
    private String contractPic;

    @NotBlank
    private String declarationPic;

    private String window;

    private int status;

    private String create_time;

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

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
